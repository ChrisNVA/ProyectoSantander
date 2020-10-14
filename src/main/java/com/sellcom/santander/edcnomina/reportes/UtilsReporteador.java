package com.sellcom.santander.edcnomina.reportes;

import java.util.List;


/**
 * Clase Utils encargada de metodos de conversion y reemplazo en cadenas.
 * @author Cesar Vazquez.
 * @since 26/04/2020
 *
 */
public class UtilsReporteador {
	
	// variable de tipo String llamada rango
	private String rangoReporteador;
	//variable final de tipo string llamada REGEX_ONLY_NUMBER para aceptar solo numeros
	private static final String REGEX_ONLY_NUMBER_REPORTEADOR = "\\d+";

	/**
	 * Metodo definirRangoCodigosPostales 
	 * @param linea es la extraida del archivo origin.
	 * @param codigoPostal es el argumento a evaluar.
	 * @param rangosCodigosPostales listado de codigos postales,
	 * @return el rango en caso de pertenecer.
	 */
	public static String[] definirRangoCodigosPostalesReporteador(String linea, String codigoPostal, List<String> rangosCodigosPostales) {
	 String[] lineaEncontrada = new String[2];
	 String[] lineaNoEncontrada = null;
   	 //por cada rango buscar que se encuentre dentro el codigo postal.
   	 for(String rangosCodigoPostal:rangosCodigosPostales) {
   		 String[] rangoCodigoPostal = rangosCodigoPostal.split(" ");
   		
 		//Se validan los rangos.
		 if (codigoPostal.equals("") || !codigoPostal.matches(REGEX_ONLY_NUMBER_REPORTEADOR)    ) {
   			 return lineaNoEncontrada;
   		 }
   		 // Se validan los codigos postales contra el valor
		 if(Integer.valueOf(codigoPostal.trim()) >= Integer.valueOf(rangoCodigoPostal[0])
				 && Integer.valueOf(codigoPostal.trim()) <= Integer.valueOf(rangoCodigoPostal[1])) {
			 
			 lineaEncontrada[0] = linea;
			 lineaEncontrada[1] = rangoCodigoPostal[0]+"-"+rangoCodigoPostal[1];
			 return lineaEncontrada;
		 }
   	 }
			 return lineaNoEncontrada;
	}

	/**
	 * @return the rango
	 */
	public String getRangoReporteador() {
		return rangoReporteador;
	}
	/**
	 * @param rango the rango to set
	 */
	public void setRangoReporteador(String rangoReporteador) {
		this.rangoReporteador = rangoReporteador;
	}
	
	
}
