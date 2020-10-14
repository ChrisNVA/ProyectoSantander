package com.sellcom.santander.edcnomina.reportes;

import org.springframework.stereotype.Service;

/**
 * Clase ReglasNegocio donde se encuentran los metodos que permiten la logica de negocio.
 * @author Cesar Vazquez.
 * @version 1.0.
 * @since 28/10/2019.
 *
 */

@Service
public class ReglasNegocioReporteador {

	/**
	 * Metodo obtenerSeccion para conocer la linea en la que nos encontramos.
	 * @param registro linea de archivo a leer.
	 * @return La seccion encontrada.
	 */	
	public String obtenerSeccion(String registro) {
		if (registro.split("\\|").length > 1) {
			return registro.split("\\|")[0];
		} 
		return null;	
	}
	
	/**
	 * Metodo obtenerCodigoPostal funciona para traer el campo del cp.
	 * @param registro linea de archivo a leer.
	 * @return El tipo de producto encontrado.
	 */
	public String obtenerCodigoPostal(String registro) {
		if (registro.split("|").length > 12) {
			return registro.split("\\|")[7];
		}
		return null;
	}

	/**
	 * Metodo obtenerFolio funciona para traer el campo del cp.
	 * @param registro linea de archivo a leer.
	 * @return El tipo de producto encontrado.
	 */
	public String obtenerFolio(String registro) {
		if (registro.split("|").length > 7) {
			return registro.split("\\|")[2];
		}
		return null;
	}
	
	/**
	 * Metodo generarLlave para crear ids.
	 * @param mensajeria es el objeto que contiene los datos de generacion.
	 * @return llave generada con transportista, rango, mensajeria.
	 */
	public String generarLlave(MensajeriaReporteador mensajeria) {
		return mensajeria.getIdTransportista()+"-"+mensajeria.getRango()+"-"+mensajeria.getIdMensajeria();
	}
	
	/**
	 * Metodo generarLlave para crear ids.
	 * @param mensajeria es el objeto que contiene los datos de generacion.
	 * @param indice para no perder registros.
	 * @return llave generada con transportista, rango, mensajeria.
	 */
	public String generarLlave(MensajeriaReporteador mensajeria, int indice) {
		return mensajeria.getIdTransportista()+"-"+mensajeria.getIdMensajeria()+"-"+indice;
	}
	
}
