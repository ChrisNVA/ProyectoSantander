package com.sellcom.santander.edcnomina.reportes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Clase Mensajeria para obtener registros divididos en campos del archivo de mensajeria
 * @author Cesar Vazquez.
 * @version 1.0
 * @since 14/11/2019
 *
 * Control de cambios: 5/12/2019 Se agrega ruta de carpeta para archivo de parametros.
 * Control de cambios: 18/12/2019 Se elimina ruta de carpeta para archivo de mensajeria.
 */
@Service
public class MensajeriaReporteador {
	//Se crea el objeto environment
	@Autowired
	private Environment environment;

	//Variable privada de tipo String llamada idMensajeria
	private String idMensajeria;
	//Variable privada de tipo String llamada idGrupo
	private String idGrupo;
	//Variable privada de tipo String llamada idTransportista
	private String idTransportista;
	//Variable privada de tipo String llamada rango
	private String rango;

	/**
	 * Metodo obtenerRegistro busca y devuelve la linea de archivo de mensajeria que corresponde al codigo postal.
	 * @param codigoPostal es el filtro por el cual se busca.
	 * @param mensajeria2 objeto donde se setea el rango.
	 * @return linea de archivo donde se encuentra la coincidencia.
	 * @throws IOException en caso de problemas al leer.
	 */
	@SuppressWarnings("resource")
	public String[] obtenerRegistro(String codigoPostal, MensajeriaReporteador mensajeria2) throws  IOException    {

		//Se inicializan variables
		String linea;
		String[] lineaEncontrada = null;
    	try (
    			//En caso de mensajeria
    		BufferedReader br = new BufferedReader(new FileReader(environment.getProperty(ConstantesReporteador.ARCHIVO_MENSAJERIA)))
    	) {
		     while((linea = br.readLine()) != null && lineaEncontrada == null) {
		    	 // Se llena la lista con los codigos
		    	 List<String> rangosCodigosPostales = obtenerRangosCodigosPostales(linea);
		    	 //por cada rango buscar que se encuentre dentro el codigo postal.
	    		 lineaEncontrada =  UtilsReporteador.definirRangoCodigosPostalesReporteador(linea, codigoPostal, rangosCodigosPostales); 
		     }
		     
    	} catch(IOException e) {
    		// ocurrio error con el archivo de mensajeria
    		throw new IOException(e);
    	} 
    	return lineaEncontrada;	
	}

	/**
	 * Metodo obtenerRangosCodigosPostales.
	 * @param linea de archivo donde se busca el codigo postal.
	 * @return lista de codigos postales encontrados.
	 */
	private List<String> obtenerRangosCodigosPostales(String linea) {
		List<String> rangos = new ArrayList<>();
		// A partir de la columna 60
		String codigosPostales = linea.substring(60) ;
		int contador = 0;
		int inicio = 0;
		while (contador <= codigosPostales.length()) {
			//Se almacenan desde sub cadena	
			String tmp = codigosPostales.substring(inicio, inicio +11);				
			inicio+=11;
			rangos.add(tmp);
			//Con la siguiente linea evitamos una excepcion
			contador = inicio +11;
		}
		return rangos;
	}

	/**
	 * @return the idMensajeria
	 */
	public String getIdMensajeria() {
		return idMensajeria;
	}
	/**
	 * @param idMensajeria the idMensajeria to set
	 */
	public void setIdMensajeria(String idMensajeria) {
		this.idMensajeria = idMensajeria;
	}
	/**
	 * @return the idGrupo
	 */
	public String getIdGrupo() {
		return idGrupo;
	}
	/**
	 * @param idGrupo the idGrupo to set
	 */
	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
	}
	/**
	 * @return the idTransportista
	 */
	public String getIdTransportista() {
		return idTransportista;
	}
	/**
	 * @param idTransportista the idTransportista to set
	 */
	public void setIdTransportista(String idTransportista) {
		this.idTransportista = idTransportista;
	}
	/**
	 * @return the rango
	 */
	public String getRango() {
		return rango;
	}
	/**
	 * @param rango the rango to set
	 */
	public void setRango(String rango) {
		this.rango = rango;
	}

}
