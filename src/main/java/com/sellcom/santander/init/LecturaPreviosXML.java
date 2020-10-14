package com.sellcom.santander.init;
import com.sellcom.santander.edcnomina.ReglasNegocio;
import com.sellcom.santander.xsd.ProductSettings.Previo.ItemSettings.Field;
import com.sellcom.santander.edcnomina.Constantes;
import com.sellcom.santander.edcnomina.reportes.ConstantesReporteador;
import com.sellcom.santander.exceptions.ConfigException;
import com.sellcom.santander.tools.Utils;
import com.sellcom.santander.tools.XMLParser;
import com.sellcom.santander.xsd.ProductSettings;
import com.sellcom.santander.xsd.ProductSettings.Segment.ItemSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBElement;
import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Clase TestLecturaXML para probar el parseo xml.
 * @author C�sar V�zquez.
 * @version 1.0.
 * @since 11/11/2019.
 *
 *
 * Control de cambios: 18/12/2019 Se elimina ruta de carpeta para archivo de segmentos.
 */
@Slf4j
@Service
public class LecturaPreviosXML {
	//Se crea el objeto environment
	@Autowired
	private Environment environment;
	
	//variable escencial para almacenar nombre de archivo cuando existe instruccion_correspondencia
	private String valorTipoSegmento;
	//Se crea el objeto utils
	@Autowired
	private  Utils utils;
	//Se crea el objeto reglasNegocio
	@Autowired
	private ReglasNegocio reglasNegocio;
	
	/**
	 * Metodo buscarValorCampo para encontrar valores.
	 * @param xy seccion y campo.
	 * @param texto donde se buscaran las secciones.
	 * @return el valor del campo.
	 */
	private String buscarValorCampo(String[] xy, String texto) {
		String[] secciones = null;
		// se dividen las secciones.
		secciones = texto.split("\n");
			
		//Recorremos cada seccion para buscar el valor del campo
		for (String seccion:secciones) {
			if (xy[0].equals(seccion.split("\\|")[0])) {
				int posicion = Integer.parseInt(xy[1]);
				return seccion.split("\\|")[posicion];
			}
		}
		return null;
	}

	
	/**
	 * Metodo obtenerPosicionCampo para obtener seccion y posicion del campo.
	 * @param dataField nombre
	 * @return Arreglo de texto (0-seccion, 1-posicion)
	 */
	private String[] obtenerPosicionCampo(String dataField) {
		String propiedad = environment.getProperty(dataField);
		if (propiedad != null) {
			return propiedad.split("_");
		} else {
			return new String[2];
		}
	}

	/**
	 * Metodo obtenerTipoEnSegmento que busca en el xml el dato que corresponde.
	 * @param texto es el valor a buscar.
	 * @param llave 
	 * @return cadena que indica el segmento alque corresponde.
	 */
	@SuppressWarnings("unchecked")
	public String obtenerTipoEnSegmento(String texto, String llave) {

		String[] tipoEnSegmento = new String[3] ;
		tipoEnSegmento[1] = Constantes.NO_ENCONTRADO;

		File xml = new File(environment.getProperty(Constantes.ARCHIVO_SEGMENTOS));
		if (xml.getPath().equals("") ) {
			return null;
		} 
		
		try {
			ProductSettings product = XMLParser.unmarshalFileToObject(xml);
			Optional<ProductSettings> isProduct = Optional.ofNullable(product);
			
			if (isProduct.isPresent()) {
				//Se itera el contenido del ItemSettings
				for(ProductSettings.Segment.ItemSettings item:product.getSegment().getItemSettings()) {

					StringBuilder setItemSettings = new StringBuilder();
					setItemSettings.append("Id: " + item.getId() + "   \t");
					setItemSettings.append("Type: " + item.getType() + ConstantesReporteador.TABULADOR);
					setItemSettings.append("Priority: " + item.getPriority() + ConstantesReporteador.TABULADOR);
					setItemSettings.append("Logic: " + item.getLogic() + ConstantesReporteador.TABULADOR);
					// Se itera el contenido del itemSetting
					tipoEnSegmento = iterarContenido(item, texto, tipoEnSegmento,setItemSettings, llave );
				}

			}
		} catch (ConfigException e) {
			log.error(e.getMessage());
		}
		
		//En caso de no localizar el campo y valos no regresa nada
		if (tipoEnSegmento[1].equals(Constantes.ENCONTRADO)) {
			return tipoEnSegmento[2].concat("|").concat(tipoEnSegmento[0]).concat("|M");
		} else {
			return "";
		}
	}

	/**
	 *  Metodo iterarContenido que genera un proceso para determinar el tipo de segmento al que pertenece el cliente.
	 * @param item elmento a desglosar.
	 * @param texto del cliente.
	 * @param tipoEnSegmento para llevar valores previos.
	 * @param setItemSettings para establecer elementos y valores.
	 * @param llave
	 * @return arreglo de texto con el segmento.
	 */
	private String[] iterarContenido(ItemSettings item, String texto, String[] tipoEnSegmento,
			StringBuilder setItemSettings, String llave) {
		for(Serializable valueItemAndFields:item.getContent()) {
		tipoEnSegmento = procesarContenido(texto, tipoEnSegmento, valueItemAndFields, setItemSettings, llave);
	}
		return tipoEnSegmento;
	}

	/**
	 * Metodo procesarContenido procesa el contenido 
	 * @param texto son datos de cliente.
	 * @param tipoEnSegmento arreglo con par de valores para texto encontrado y texto que indica resultado "ENCONTRADO/NO_ENCONTRADO"
	 * @param valueItemAndFields
	 * @param setItemSettings para establecer elementos y valores.
	 * @param llave
	 * @return arreglo de texto con el segmento.
	 */
	private String[] procesarContenido(String texto, String[] tipoEnSegmento, Serializable valueItemAndFields, StringBuilder setItemSettings, String llave) {
		boolean encontroMensajeria = false;
		//obtenemos el id de mensajeria
		String idMensajeria = llave.split("-")[0];
		if (valueItemAndFields instanceof String) {
				tipoEnSegmento=asignaSegmento(tipoEnSegmento, setItemSettings, valueItemAndFields);
		} else if (valueItemAndFields instanceof JAXBElement) {
			Field field = ((JAXBElement<Field>) valueItemAndFields).getValue();
			
			StringBuilder setField = new StringBuilder();
			setField.append(Constantes.T_DATA_FIELD + field.getDataField() + ConstantesReporteador.TABULADOR);
			setField.append(Constantes.T_DATA_TYPE + field.getDataType() + ConstantesReporteador.TABULADOR);
			setField.append("Include: " + field.getInclude());
			
			if(validacion(field)) {
				codigoCliente(texto, tipoEnSegmento, field);
			} 

			if(field.getDataField().contentEquals("ID_MENSAJERIA")) {
				for (String data:field.getData()) {
					if(data.contentEquals(idMensajeria)) {
						log.info("CORRESPONDE EL SEGMENTO "
								+valorTipoSegmento+", "+tipoEnSegmento[1]);
						tipoEnSegmento[0] = valorTipoSegmento;
						encontroMensajeria = true;
					}
				}
			}
			instruccionCorrespondecia(texto, tipoEnSegmento, field, encontroMensajeria);
		}
	
		return tipoEnSegmento;
	}
	
	public void codigoCliente(String texto,String[] tipoEnSegmento,Field field) {
		String[] xy = obtenerPosicionCampo(Constantes.CAMPO_CODIGO_DE_CLIENTE);
		String[] codigosClientes = utils.obtenerCodigosClientes(field.getData().get(0));
		if(codigosClientes!=null) {
			int cont = codigosClientes.length;
			while(cont > 0) {
				cont--;
				obtenerValorSeccionPosicion(texto, codigosClientes[cont], xy, tipoEnSegmento);
				if (tipoEnSegmento[1].contentEquals(Constantes.ENCONTRADO)) {
					break;
				}
			}
		}
	}
	public boolean validacion(Field field) {
		return field.getDataType().contentEquals("substr") && field.getDataField().contentEquals("FILENAME");
	}
	
	public void instruccionCorrespondecia(String texto,String[] tipoEnSegmento,Field field,boolean encontroMensajeria) {
		// Ahora buscamos la instruccion correpondencia de la lista 
		if(field.getDataField().contentEquals(Constantes.INSTRUCCION_CORRESPONDENCIA) && encontroMensajeria) {
			String[] xy = obtenerPosicionCampo(Constantes.INSTRUCCION_CORRESPONDENCIA);
			obtenerValorSeccionPosicionInstruccionCorrespondenciaMensajeria(texto, field.getData(), xy, tipoEnSegmento, valorTipoSegmento);
			
		}
		else {
			for (String data:field.getData()) {

				//Obtenemos posicion desde mapeo en parametros.properties.
				String[] xy = obtenerPosicionCampo(field.getDataField());
				obtenerValorSeccionPosicion(texto, data,  xy, tipoEnSegmento);
			}
		}
		
		
	}
	
	public String[] asignaSegmento(String[] tipoEnSegmento,StringBuilder setItemSettings,Serializable valueItemAndFields) {
		valueItemAndFields = ((String) valueItemAndFields).replaceAll("\\n", "").replaceAll("\\t", "").trim();
		if (null != valueItemAndFields && !"".equals(valueItemAndFields)) {
		//Se asigna el tipo de segmento
		if (tipoEnSegmento[1].equals(Constantes.NO_ENCONTRADO)) {
			tipoEnSegmento[0] = valueItemAndFields.toString();
		}
		
		setItemSettings.append(Constantes.ITEM_SETTINGS_VALUE + valueItemAndFields);
		//instruccion correspondencia
		int indexTipo = setItemSettings.indexOf(Constantes.ITEM_SETTINGS_VALUE);
		if (indexTipo > -1) {
			valorTipoSegmento = setItemSettings.substring(indexTipo);
		}

		int indexPriority = setItemSettings.indexOf("Priority:");
		String prioridad = setItemSettings.substring(indexPriority, indexPriority+12).split(":")[1];
		valorTipoSegmento = valueItemAndFields.toString();
		if(prioridad.trim() !=null ) {
			tipoEnSegmento[2] = prioridad.trim();
		}
		}
		return tipoEnSegmento;
	}
	/**
	 * Metodo obtenerValorSeccionPosicion para conocer si se localiza el texto del campo.
	 * @param texto son datos de cliente.
	 * @param data es el dato encontrado en el xml de segmentos.
	 * @param xy son las coordenadas del campo en interfaz de cliente.
	 * @param tipoEnSegmento arreglo con par de valores para texto encontrado y texto que indica resultado "ENCONTRADO/NO_ENCONTRADO"
	 * @return Arreglo de cadenas.
	 */
	private String[] obtenerValorSeccionPosicion(String texto, String data, String[] xy, String[] tipoEnSegmento) {
		if (xy[0] != null) {
			//Se busca el valor en la seccion/posicion del ultimo elemento del map (bloque)
			String valorCampo = buscarValorCampo(xy, texto);
			if (valorCampo != null && !valorCampo.equals("") && valorCampo.equals(data)) {
				tipoEnSegmento[1] = Constantes.ENCONTRADO;
			}
		}
		return tipoEnSegmento;
	}

	
	/**
	 * Metodo obtenerValorSeccionPosicionInstruccionCorrespondenciaMensajeria para conocer si se localiza el texto del campo.
	 * @param texto son datos de cliente.
	 * @param listData lista de datos.
	 * @param xy son las coordenadas del campo en interfaz de cliente.
	 * @param tipoEnSegmento arreglo con par de valores para texto encontrado y texto que indica resultado "ENCONTRADO/NO_ENCONTRADO"
	 * @param tipoEnSeccion
	 * @return Arreglo de cadenas.
	 */
	private String[] obtenerValorSeccionPosicionInstruccionCorrespondenciaMensajeria(String texto, List<String> listData, String[] xy, String[] tipoEnSegmento, String tipoEnSeccion) {
		if (xy[0] != null) {
			//Se busca el valor en la seccion/posicion del ultimo elemento del map (bloque)
			String valorCampo = buscarValorCampo(xy, texto);
			
			for(String data:listData) {
				if (valorCampo != null && !valorCampo.equals("") && valorCampo.equals(data)) {
					tipoEnSegmento[0] = tipoEnSeccion;
					tipoEnSegmento[1] = Constantes.ENCONTRADO;
				}
			}	
		}
		return tipoEnSegmento;
	}	

	/**
	 *  Metodo iterarContenidoPrevio que genera un proceso para determinar el tipo de previo al que pertenece el cliente.
	 * @param item elmento a desglosar.
	 * @param texto del cliente.
	 * @param tipoEnPrevio para llevar valores previos.
	 * @param setItemSettings para establecer elementos y valores.
	 * @return arreglo de texto con el tipo de previo.
	 */
	private String[] iterarContenidoPrevio(com.sellcom.santander.xsd.ProductSettings.Previo.ItemSettings item, String texto, String[] tipoEnPrevio,
			StringBuilder setItemSettings) {
		for(Serializable valueItemAndFields:item.getContent()) {
			tipoEnPrevio = procesarContenidoPrevio(texto, tipoEnPrevio, valueItemAndFields, setItemSettings);
	}
		return tipoEnPrevio;
	}
	
	/**
	 *  Metodo procesarContenidoPrevio procesa el conteido previo
	 * @param texto son datos de cliente.
	 * @param tipoEnPrevio para llevar valores previos.
	 * @param valueItemAndFields 
	 * @param setItemSettings para establecer elementos y valores.
	 * @return arreglo de texto con el tipo de previo.
	 */
	private String[] procesarContenidoPrevio(String texto, String[] tipoEnPrevio, Serializable valueItemAndFields, StringBuilder setItemSettings) {
		if (valueItemAndFields instanceof String) {
				tipoEnPrevio=asignaTipoPrevio(valueItemAndFields, tipoEnPrevio, setItemSettings);
		} else if (valueItemAndFields instanceof JAXBElement) {
			Field field = ((JAXBElement<com.sellcom.santander.xsd.ProductSettings.Previo.ItemSettings.Field>) valueItemAndFields).getValue();
			StringBuilder setField = new StringBuilder();
			setField.append(Constantes.T_DATA_FIELD + field.getDataField() + ConstantesReporteador.TABULADOR);
			setField.append(Constantes.T_DATA_TYPE + field.getDataType() + ConstantesReporteador.TABULADOR);
			setField.append("Data: " + field.getData() + ConstantesReporteador.TABULADOR);
			String idItems=null;
			
			for (String data: field.getData()) {
				
				if(field.getDataType().contentEquals("file")
						&& field.getDataField().contentEquals("CODECLIENT")) {
					//Obtenemos posicion desde mapeo en parametros.properties.
					
					String[] xy = obtenerPosicionCampo(Constantes.CAMPO_CODIGO_DE_CLIENTE);
					String[] codigosClientes = utils.obtenerCodigosClientes(data);
					if(codigosClientes!=null) {
						int cont = codigosClientes.length;
						while(cont > 0) {
							cont--;
							obtenerValorSeccionPosicion(texto, codigosClientes[cont],  xy, tipoEnPrevio);
							if (tipoEnPrevio[1].contentEquals(Constantes.ENCONTRADO)) {
								break;
							}
						}
					}
				}else if(field.getDataType().contentEquals("file")
						&& field.getDataField().contentEquals("NUMERO_DE_CUENTA")) {

					String[] xy = obtenerPosicionCampo(Constantes.NUMERO_DE_CUENTA);
					String[] codigosClientes = utils.obtenerCodigosClientes(data);
					if(codigosClientes!=null) {
						int cont = codigosClientes.length;
						while(cont > 0) {
							if (tipoEnPrevio[1].contentEquals(Constantes.ENCONTRADO)) {
								break;
							}
							cont--;
							obtenerValorSeccionPosicion(texto, codigosClientes[cont],  xy, tipoEnPrevio);

						}
						xy = obtenerPosicionCampo(Constantes.BUC);
						cont = codigosClientes.length;
						while(cont > 0) {
							if (tipoEnPrevio[1].contentEquals(Constantes.ENCONTRADO)) {
								break;
							}
							cont--;
							obtenerValorSeccionPosicion(texto, codigosClientes[cont],  xy, tipoEnPrevio);
						}
					}

				}
				else {
					//Obtenemos posicion desde mapeo en parametros.properties.
					String[] xy = obtenerPosicionCampo(field.getDataField());
					obtenerValorSeccionPosicion(texto, data,  xy, tipoEnPrevio);
				}
				
			}

			tipoEnPrevio[3] =idItems;
		}
	
		return tipoEnPrevio;
	}
	
	
	public String[] asignaTipoPrevio(Serializable valueItemAndFields,String[] tipoEnPrevio,StringBuilder setItemSettings) {
		valueItemAndFields = ((String) valueItemAndFields).replaceAll("\\n", "").replaceAll("\\t", "").trim();
		if (null != valueItemAndFields && !"".equals(valueItemAndFields)) {
			//Se asigna el tipo de previo
			if (tipoEnPrevio[1].equals(Constantes.NO_ENCONTRADO)) {
				tipoEnPrevio[0] = valueItemAndFields.toString().trim();
			}
			
			setItemSettings.append(Constantes.ITEM_SETTINGS_VALUE + valueItemAndFields);
			tipoEnPrevio[2] = "";
		}
		return tipoEnPrevio;
		
	}
	
	/**
	 *  Metodo obtenerTipoEnPrevio obtiene el tipo previo
	 * @param texto son datos de cliente.
	 * @param archivoOrigen archivo origen
	 * @return instruccion correspondiente al valor
	 */
	@SuppressWarnings("unchecked")
	public String[] obtenerTipoEnPrevio(String texto, String archivoOrigen,String omitirID) {
		String[] tipoEnPrevio = new String[4] ;
		tipoEnPrevio[1] = Constantes.NO_ENCONTRADO;

		File xml = new File(environment.getProperty(Constantes.ARCHIVO_PREVIOS));

		try {
			ProductSettings product = XMLParser.unmarshalFileToObject(xml);
			Optional<ProductSettings> isProduct = Optional.ofNullable(product);
			
			if (isProduct.isPresent()) {
				//Se itera el contenido del ItemSettings
				for(ProductSettings.Previo.ItemSettings item:product.getPrevio().getItemSettings()) {
					if(!omitirID.contains(String.valueOf(item.getId()))) {
					StringBuilder setItemSettings = new StringBuilder();
					setItemSettings.append("Id Previo: " + item.getId() + "   \t");

					// Se itera el contenido del itemSetting
					tipoEnPrevio = iterarContenidoPrevio(item, texto, tipoEnPrevio,setItemSettings);
					if(tipoEnPrevio[1]==Constantes.ENCONTRADO) {
						tipoEnPrevio[3]=String.valueOf(item.getId());
						break;
					}
					}
				}
			}
		} catch (ConfigException e) {
			
			log.error(e.getMessage());
		}
		if(tipoEnPrevio[1]==Constantes.NO_ENCONTRADO) {
			tipoEnPrevio[3]="END";
		}
		return salidaPrevio(tipoEnPrevio,archivoOrigen);
	}

	public String[] salidaPrevio(String[] tipoEnPrevio,String archivoOrigen) {
		String[] salida=new String[2];
		String[] salidaNoEncontrada=null;
		//En caso de no localizar el campo y valos no regresa nada
				if (tipoEnPrevio[1].equals(Constantes.ENCONTRADO)) {
					String instruccionCorrespondenciaValor =  reglasNegocio.getCorrespondenciaMensajeria();
					if( instruccionCorrespondenciaValor.equals("R")  ){
						instruccionCorrespondenciaValor = reglasNegocio.getCorrespondenciaSucursal();
					}
					salida[0]=instruccionCorrespondenciaValor.concat(tipoEnPrevio[2]).concat("|").concat(archivoOrigen).concat("|").concat(tipoEnPrevio[0].concat("|A"));
					salida[1]=tipoEnPrevio[3];
					return salida;
				} else {
					return salidaNoEncontrada;
				}
	}
	

}
