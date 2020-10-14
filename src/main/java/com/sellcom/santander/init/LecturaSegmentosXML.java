package com.sellcom.santander.init;

import com.sellcom.santander.edcnomina.ReglasNegocio;
import com.sellcom.santander.edcnomina.reportes.ConstantesReporteador;
import com.sellcom.santander.tools.Utils;
import com.sellcom.santander.tools.XMLParser;
import com.sellcom.santander.xsd.ProductSettings;
import com.sellcom.santander.xsd.ProductSettings.Segment.ItemSettings;
import com.sellcom.santander.xsd.ProductSettings.Segment.ItemSettings.Field;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBElement;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static com.sellcom.santander.edcnomina.Constantes.ARCHIVO_SEGMENTOS;
import static com.sellcom.santander.edcnomina.Constantes.BUC;
import static com.sellcom.santander.edcnomina.Constantes.ERROR_PARSEANDO;
import static com.sellcom.santander.edcnomina.Constantes.NUMERO_DE_CUENTA;
import static com.sellcom.santander.edcnomina.Constantes.TIPO_RETENCION;
import static com.sellcom.santander.edcnomina.Constantes.T_DATA_FIELD;
import static com.sellcom.santander.edcnomina.Constantes.T_DATA_TYPE;

@Slf4j
@Service
public class LecturaSegmentosXML {
	//Se crea el objeto environment
	@Autowired
	private Environment environment;
	//Se crea el objeto utils
	@Autowired
	private  Utils utils;
	//Se crea el objeto reglasNegocio
	@Autowired
	private ReglasNegocio reglasNegocio;



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
	 * Metodo existeValorSeccionPosicion para conocer si se localiza el texto del campo.
	 * @param texto son datos de cliente.
	 * @param data es el dato encontrado en el xml de segmentos.
	 * @param xy son las coordenadas del campo en interfaz de cliente.
	 * @return Arreglo de cadenas.
	 */
	private boolean existeValorSeccionPosicion(String texto, String data, String[] xy) {
		if (xy[0] != null) {
			//Se busca el valor en la seccion/posicion del ultimo elemento del map (bloque)
			String valorCampo = buscarValorCampo(xy, texto);
			
			if (valorCampo != null && !valorCampo.equals("") && valorCampo.equals(data)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo buscarValorCampo para encontrar valores.
	 * @param xy seccion y campo.
	 * @param texto son datos del cliente.
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
				if (seccion.split("\\|").length -1>= posicion)
					return seccion.split("\\|")[posicion];
			}
		}
		return null;
	}

	/**
	 * Metodo obtenerTipoEnSegmento para conocer a que segmento pertenece el cliente.
	 * @param texto son datos de cliente.
	 * @param llave
	 * @param sucursal
	 * @param archivoOrigen es el archivo del cual proviene ese cliente.
	 * @param fechaParametro es el argumento enviado desde linea de comandos.
	 * @return tipo de segmento
	 */
	@SuppressWarnings("unchecked")
	public String[] obtenerTipoEnSegmento(String texto, String llave, String sucursal, String archivoOrigen, String fechaParametro,boolean omitirTipo,String omitirID) {
		String[] salida=new String[3];
		String[] salidaNoEncontrada=null;
		
		File xml = new File(environment.getProperty(ARCHIVO_SEGMENTOS));
		try {
			ProductSettings product = XMLParser.unmarshalFileToObject(xml);
			Optional<ProductSettings> isProduct = Optional.ofNullable(product);
			
			if (isProduct.isPresent()) {
				
				String tipoEnSegmento = "";
				int totalCoincidencias = 0;
				int totalSecciones = 0;
				int count=0;
				int id=0;
				int tipo=0;
				for(ItemSettings item:product.getSegment().getItemSettings()) {
					count++;
					if(omitirSegmentos(item, omitirID, omitirTipo)) {
					StringBuilder setItemSettings = new StringBuilder();
					setItemSettings.append("Id: " + item.getId() + "   \t");
					setItemSettings.append("Type: " + item.getType() + ConstantesReporteador.TABULADOR);
					setItemSettings.append("Priority: " + item.getPriority() + ConstantesReporteador.TABULADOR);
					setItemSettings.append("start_date: " + item.getStartDate() + ConstantesReporteador.TABULADOR);
					setItemSettings.append("end_date: " + item.getEndDate() + ConstantesReporteador.TABULADOR);
										
					if(!tipoEnSegmento.contentEquals("") && totalCoincidencias == totalSecciones) {
						return salida(tipoEnSegmento, id, tipo);
					}

					StringBuilder valorProbable = new StringBuilder();
					totalCoincidencias = 0;
					totalSecciones = 0;


					String instruccionCorrespondenciaValor =  instruccionCorrespondenciaValor();

					//Estas variables cambian durante el for
					boolean bSeccionMapeadaEncontrada = false;
					boolean pasoPorArchivoCliente = false;
					boolean pasoPorSeccionMapeada = false;			
					boolean bClienteEncontrado = false;
					boolean bSeccionPlazaEncontrada = false;
					
					for(Object valueItemAndFields : item.getContent() ) {
						
						//En caso de que no sea valido se sale de la búsqueda del segmento
						if(!esFechaValida(fechaParametro, item.getStartDate(), item.getEndDate())) {
							break;
						}

						if (valueItemAndFields instanceof String) {
							almacenarValorProbable(valueItemAndFields, setItemSettings, valorProbable);

						} else if (valueItemAndFields instanceof JAXBElement) {
							
							Field field = ((JAXBElement<Field>) valueItemAndFields).getValue();
							StringBuilder setField = new StringBuilder();
							setField.append(T_DATA_FIELD + field.getDataField() + ConstantesReporteador.TABULADOR);
							setField.append(T_DATA_TYPE + field.getDataType() + ConstantesReporteador.TABULADOR);
							totalSecciones +=1; //incrementa el numero de secciones.

							if(field.getDataType().contentEquals("file")
									&& field.getDataField().contentEquals("NUMERO_DE_CUENTA")) {

									pasoPorArchivoCliente = true;
									boolean isNumeroCuenta = false;
									String[] xy = obtenerPosicionCampo(NUMERO_DE_CUENTA);
									String[] codigosClientes = utils.obtenerCodigosClientes(field.getData().get(0));
									if (codigosClientes != null) {
										int cont = codigosClientes.length;
										while (cont > 0) {
											cont--;
											if (existeValorSeccionPosicion(texto, codigosClientes[cont], xy)) {
												totalCoincidencias += 1;
												bClienteEncontrado = true;
												id = item.getId();
												tipo = item.getType();
												tipoEnSegmento = salidaSegmento(instruccionCorrespondenciaValor, archivoOrigen,
														valorProbable, tipo);
												isNumeroCuenta = true;
												break;
											}

										}
										xy = obtenerPosicionCampo(BUC);
										cont = codigosClientes.length;
										while (cont > 0 && !isNumeroCuenta) {
											cont--;
											if (existeValorSeccionPosicion(texto, codigosClientes[cont], xy)) {
												totalCoincidencias += 1;
												bClienteEncontrado = true;
												id = item.getId();
												tipo = item.getType();
												tipoEnSegmento = salidaSegmento(instruccionCorrespondenciaValor, archivoOrigen,
														valorProbable, tipo);
												break;
											}
										}
									}


									// Cuando existe un archivo con listado de valores de un campo 'data_type:file'
							} else if(field.getDataType().contentEquals("file")) {
								pasoPorArchivoCliente = true;
								//Obtenemos posicion desde mapeo en parametros.properties.
								String[] xy = obtenerPosicionCampo(field.getDataField());
								String[] codigos = utils.obtenerCodigosClientes(field.getData().get(0));
								if(codigos!=null) {
									int cont = codigos.length;
									while(cont > 0) {
										cont--;
										if (existeValorSeccionPosicion(texto, codigos[cont], xy)) {
											totalCoincidencias += 1;
											bClienteEncontrado = true;
											id=item.getId();
											tipo=item.getType();
											tipoEnSegmento = salidaSegmento(instruccionCorrespondenciaValor, archivoOrigen, valorProbable, tipo);
											break;
										}
									}
								}
							} // Cuando el data_type es 'range' se busca la coincidencia entre los rangos.
							else if(field.getDataType().contentEquals("range")) {
								pasoPorArchivoCliente = true;
								//Obtenemos posicion desde mapeo en parametros.properties.
								String[] xy = obtenerPosicionCampo(field.getDataField());
								if (perteneceAlRango(texto, field.getData().get(0), xy)) {
									totalCoincidencias += 1;
									id=item.getId();
									tipo=item.getType();
									tipoEnSegmento = salidaSegmento(instruccionCorrespondenciaValor, archivoOrigen, valorProbable, tipo);
									break;
								}

							} 							
							else if(field.getDataType().contentEquals("substr") 
									&& field.getDataField().contentEquals("FILENAME")) {
								
								pasoPorArchivoCliente = true;
								//Obtenemos posicion desde mapeo en parametros.properties.
								String[] xy = obtenerPosicionCampo("CODIGO_DE_CLIENTE");
								String[] codigosClientes = utils.obtenerCodigosClientes(field.getData().get(0));
								if(codigosClientes!=null) {
									int cont = codigosClientes.length;
									while(cont > 0) {
										cont--;
										if (existeValorSeccionPosicion(texto, codigosClientes[cont], xy)) {
											totalCoincidencias += 1;
											bClienteEncontrado = true;
											id=item.getId();
											tipo=item.getType();
											tipoEnSegmento = salidaSegmento(instruccionCorrespondenciaValor, archivoOrigen, valorProbable, tipo);
											break;
										}
									}
								}
							} else if(field.getDataField().contentEquals("ID_MENSAJERIA")) {
								String idMensajeria = llave.split("-")[0];
								pasoPorSeccionMapeada = true;
								
								for (String data:field.getData()) {
									//sucursales
									if(sucursal!=null && data.contentEquals(sucursal)) {
										totalCoincidencias += 1;
										bSeccionPlazaEncontrada =  true;
										//solo si es R en el siguiente campo se cumple la regla
										break;
									}									
									
									//mensajeria
									if(data.contentEquals(idMensajeria)) {
										totalCoincidencias += 1;
										bSeccionMapeadaEncontrada = true;
										id=item.getId();
										tipo=item.getType();
										tipoEnSegmento = salidaSegmento(instruccionCorrespondenciaValor, archivoOrigen, valorProbable, tipo);
										break;
									}
								}
								
							} else {
								
								String[] xy = obtenerPosicionCampo(field.getDataField());
								for (String data:field.getData()) {
									if (existeValorSeccionPosicion(texto, data, xy)) {
										totalCoincidencias += 1;
										// si existre en la seccion se agrega el tipo de segmento
										id=item.getId();
										tipo=item.getType();
										tipoEnSegmento = salidaSegmento(instruccionCorrespondenciaValor, archivoOrigen, valorProbable, tipo);

										//validamos si paso por base de clientes y si hizo match
										if(pasoPorArchivoCliente) {
											if(bClienteEncontrado) {
												tipoEnSegmento = salidaSegmento(instruccionCorrespondenciaValor, archivoOrigen, valorProbable, tipo);
												return salida(tipoEnSegmento, id, tipo);
											} else {
												tipoEnSegmento ="";
												totalCoincidencias = 0;
												break;
											}
										}
										//Validamos si paso por plaza sucursal, si hizo match
										if(bSeccionPlazaEncontrada && data.equals(TIPO_RETENCION)) {
											tipoEnSegmento = salidaSegmento(instruccionCorrespondenciaValor, archivoOrigen, valorProbable, tipo);
											return  salida(tipoEnSegmento, id, tipo);											
										}
										
										//validamos si paso por seccion mapeada y si hizo match
										if(pasoPorSeccionMapeada) {
											if(bSeccionMapeadaEncontrada) {
												tipoEnSegmento = salidaSegmento(instruccionCorrespondenciaValor, archivoOrigen, valorProbable, tipo);
												return salida(tipoEnSegmento, id, tipo);												
											} else {
												tipoEnSegmento ="";
												totalCoincidencias = 0;
												break;												
											}
										}
										
										//En caso de que exista otra seccion a validar se indica que aqu� ya hizo match
										bSeccionMapeadaEncontrada = true;
										break;
									}
								}
								

							}

						}
					}// Es este for el content.

				}
					salida[1]=count==product.getSegment().getItemSettings().size()?"END":String.valueOf(id);
					salida[2]=String.valueOf(item.getType());
				}// Es este el for de items settings.
					salida[0]=tipoEnSegmento;

				return salida;

			}
		} catch (Exception e) {
			//Se envia a log el error
            log.error(e.toString());
            log.error( e.getLocalizedMessage());
            log.error( e.getMessage());
			log.error(ERROR_PARSEANDO+ xml.getAbsolutePath());
		}
		
		return salidaNoEncontrada;
	}
	
	
	public boolean omitirSegmentos(ItemSettings item,String omitirID,boolean omitirTipo) {
		if((item.getType()!=2 && omitirTipo)) {
			return false;
		}
			return !omitirID.contains(String.valueOf(item.getId()));
		}
	
	public String [] salida(String tipoEnSegmento,int id,int tipo) {
		String[] salida=new String[3];
		salida[0]=tipoEnSegmento;
		salida[1]=String.valueOf(id==0?"":id);
		salida[2]=String.valueOf(tipo==0?"":tipo);
		return salida;
	}
	public String salidaSegmento(String instruccionCorrespondenciaValor,String archivoOrigen,StringBuilder valorProbable,int tipo) {
		String tipoEnSegmento="";
		if(tipo!=2) {
			tipoEnSegmento=instruccionCorrespondenciaValor.concat("|").concat(archivoOrigen).concat("|").concat(valorProbable.toString()).concat("|M");
		}else {
			tipoEnSegmento=instruccionCorrespondenciaValor.concat("|").concat(archivoOrigen).concat("|").concat(valorProbable.toString()).concat("|A");
		}
		return tipoEnSegmento;
	}

	public String instruccionCorrespondenciaValor() {
		String instruccionCorrespondenciaValor =  reglasNegocio.getCorrespondenciaMensajeria();
		if( instruccionCorrespondenciaValor.equals("R")  ){
			instruccionCorrespondenciaValor = reglasNegocio.getCorrespondenciaSucursal();
		}
		return instruccionCorrespondenciaValor;
	}
	
	public void almacenarValorProbable(Object valueItemAndFields,StringBuilder setItemSettings,StringBuilder valorProbable) {
		valueItemAndFields = ((String) valueItemAndFields).replaceAll("\\n", "").replaceAll("\\t", "").trim();
		if (null != valueItemAndFields && !"".equals(valueItemAndFields)) {
			setItemSettings.append(ConstantesReporteador.ITEM_SETTINGS_VALUE + valueItemAndFields);
			//Almacenamos el probable valor
			valorProbable.append((String) valueItemAndFields);
			
		}
	}
	

	/**
	 * Metodo esFechaValida para saber si la fecha de parametro entra en el rango de las fechas del segmento.
	 * @param fechaParametro llega en formato 'yyyyMMdd'.
	 * @param startDate tiene formato 'yy-MM-dd'.
	 * @param endDate tiene formato 'yy-MM-dd'.
	 * @return valor booleano que indica si entra en el rango o no.
	 */
	private boolean esFechaValida(String fechaParametro, String startDate, String endDate) {
		
		if (startDate==null || endDate==null) {
			return false;
		}
		
		 fechaParametro= cambiarFormatoFechaParametro(fechaParametro);
		 startDate = cambiarFormatoFechaSegmento(startDate);
		 endDate= cambiarFormatoFechaSegmento(endDate);
			
		try {		
			DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
			Date parametro = fecha.parse(fechaParametro);
			Date inicio = fecha.parse(startDate);
			Date fin = fecha.parse(endDate);

			// comparamos fechas
			if ( parametro.before(inicio) || parametro.after(fin) ) {
				return false;
			} 
			
		} catch(java.text.ParseException e ) {
			//En caso de excepcion se return false por mal formato de fecha y continua con el flujo
			return false;
		}
		
		return true;
	}

	/**
	 * Metodo cambiarFormatoFechaParametro en el que entra 'yyyyMMdd' y sale 'dd/MM/yyyy'
	 * @param fechaArgumento yyyyMMdd.
	 * @return dd/MM/yyyy.
	 */
	private String cambiarFormatoFechaParametro(String fechaArgumento) {
		return fechaArgumento.substring(6,8)+"/"+fechaArgumento.substring(4,6)+"/"+fechaArgumento.substring(0,4);
	}

	/**
	 * Metodo cambiarFormatoFechaSegmento en el que entra 'yy-MM-dd' y sale 'dd/MM/yyyy'. 
	 * @param fechaSegmento yy-MM-dd.
	 * @return 'dd/MM/yyyy'.
	 */
	private String cambiarFormatoFechaSegmento(String fechaSegmento) {
		String[] fechaTmp = fechaSegmento.split("-");
		return fechaTmp[2]+"/"+fechaTmp[1]+"/20"+fechaTmp[0];
	}

	/**
	 * Metodo perteneceAlRango  
	 * @param texto son datos de cliente.
	 * @param data es el dato encontrado en el xml de segmentos.
	 * @param xy seccion y campo.
	 * @return tipo de valor boolean.
	 */
	private boolean perteneceAlRango(String texto, String data, String[] xy) {
		if (xy[0] != null) {
			//Se busca el valor en la seccion/posicion del ultimo elemento del map (bloque)
			String valorCampo = buscarValorCampo(xy, texto);
			String[] rango = data.split(",");
			try {
				if ( (Integer.parseInt(rango[0].trim()) <= Integer.parseInt(valorCampo)) 
						&& (Integer.parseInt(rango[1].trim()) >= Integer.parseInt(valorCampo)))  {
					return true;
				}
			} catch (NumberFormatException e) {
				//En caso de excepcion se return false por mal formato de valores y continua con el flujo
				return false;
			} 
		}
		return false;
	}
}
