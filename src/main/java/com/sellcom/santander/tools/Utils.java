package com.sellcom.santander.tools;

import com.sellcom.santander.edcnomina.Constantes;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Clase Utils encargada de metodos de conversion y reemplazo en cadenas.
 * @author Cesar Vazquez.
 * @since 11/11/2019
 *
 */
@Slf4j
@Service
public class Utils {
	//variable final de tipo string llamada REGEX_ONLY_NUMBER para aceptar solo numeros
	private static final String REGEX_ONLY_NUMBER = "\\d+";
	//Se crea el objeto environment
	@Autowired
	private Environment environment;
	//variable privada de tipo string llamada rango
	private String rangoUtils;
	/**
	 * Metodo replace usado para cambiar textos.
	 * @param source origen.
	 * @param os texto a buscar.
	 * @param ns texto a reemplazar.
	 * @return texto modificado.
	 */
	public static String replace(String source, final String os, final String ns) {
		if (source == null) {
			return null;
		}
		int i = 0;
		if ((i = source.indexOf(os, i)) >= 0) {
			final char[] sourceArray = source.toCharArray();
			final char[] nsArray = ns.toCharArray();
			final int oLength = os.length();
			final StringBuilder buf = new StringBuilder(sourceArray.length);
			buf.append(sourceArray, 0, i).append(nsArray);
			i += oLength;
			int j = i;
			while ((i = source.indexOf(os, i)) > 0) {
				buf.append(sourceArray, j, i - j).append(nsArray);
				i += oLength;
				j = i;
			}
			buf.append(sourceArray, j, sourceArray.length - j);
			source = buf.toString();
			buf.setLength(0);
		}
		return source;
	}

	/**
	 * Metodo definirRangoCodigosPostales 
	 * @param linea es la extraida del archivo origin.
	 * @param codigoPostal es el argumento a evaluar.
	 * @param rangosCodigosPostales para saber si pertenece a este.
	 * @return el rango en caso de pertenecer.
	 */
	public static String[] definirRangoCodigosPostalesUtils(String linea, String codigoPostal, List<String> rangosCodigosPostales) {
	 String[] lineaEncontrada = new String[2];
	 String[] lineaNoEncontrada = null; 
   	 //por cada rango buscar que se encuentre dentro el codigo postal.
   	 for(String rangosCodigoPostal:rangosCodigosPostales) {
   		 String[] rangoCodigoPostal = rangosCodigoPostal.split(" ");
   		
 		//Se validan los rangos.
   		 if (codigoPostal.equals("") || !codigoPostal.matches(REGEX_ONLY_NUMBER)    ) {
   			 return lineaNoEncontrada;
   		 }
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
	 * Metodo definirRangoPlazas nos funciona como 
	 * @param linea es la extraida del archivo origin.
	 * @param plaza es el argumento a evaluar.
	 * @param rangosPlazas para saber si pertenece a este.
	 * @return el rango en caso de pertenecer.
	 */
	public static String[] definirRangoPlazas(String linea, String plaza, List<String> rangosPlazas) {
	 String[] lineaEncontrada = new String[2];
	 String[] lineaNoEncontrada = null;
   	 //por cada rango buscar que se encuentre dentro la plaza.
   	 for(String rangosPlaza:rangosPlazas) {
   		 String[] rangoPlaza = rangosPlaza.split(" ");
 		//Se validan los rangos.
   		 if (rangosPlaza.equals("")) {
   			 return lineaNoEncontrada;
   		 }
   		 
   		 if (plaza==null) {
   			 return lineaNoEncontrada;
   		 }
   		 if( plaza.equals("")){
			 plaza = "000";
		 }
   		 
		 if(Integer.valueOf(plaza) >= Integer.valueOf(rangoPlaza[0])
				 && Integer.valueOf(plaza) <= Integer.valueOf(rangoPlaza[1])) {
			 lineaEncontrada[0] = linea;
			 lineaEncontrada[1] = rangoPlaza[0]+"-"+rangoPlaza[1];
			 return lineaEncontrada;
		 }
   	 }
			 return lineaNoEncontrada;
	}

	/**
	 * Metodo obtenerArchivosOrigen para obtener lista de archivos
	 * @return listado de archivos encontrados.
	 */
	public String[] obtenerArchivosOrigen() {

		String[] archivoEntrada = new String[15];
		archivoEntrada[0] = environment.getProperty(Constantes.ARCHIVO_01);
		archivoEntrada[1] = environment.getProperty(Constantes.ARCHIVO_02);
		archivoEntrada[2] = environment.getProperty( Constantes.ARCHIVO_03);
		archivoEntrada[3] = environment.getProperty(Constantes.ARCHIVO_04);
		archivoEntrada[4] = environment.getProperty(Constantes.ARCHIVO_05);
		archivoEntrada[5] = environment.getProperty(Constantes.ARCHIVO_06);
		archivoEntrada[6] = environment.getProperty(Constantes.ARCHIVO_07);
		archivoEntrada[7] = environment.getProperty(Constantes.ARCHIVO_08);
		archivoEntrada[8] = environment.getProperty(Constantes.ARCHIVO_09);
		archivoEntrada[9] = environment.getProperty(Constantes.ARCHIVO_10);
		return archivoEntrada;
	}
		
	/**
	 * Metodo obtenerCodigosClientes funciona para obtener la lista desde archivo.
	 * @param archivo donde se buscaran los codigos.
	 * @return los codigos de los clientes.
	 */
	public String[] obtenerCodigosClientes(String archivo) {
	    String fichero = archivo;
	    String [] lineaNoEncontrada = null;
	    
	    try (
	  	      FileReader fr = new FileReader(fichero);
	  	      BufferedReader br = new BufferedReader(fr);
	    ){
	 
	      String linea;
	      
	      StringBuilder lineaCompleta = new StringBuilder();
	      
	      while(( linea = br.readLine()) != null) {
	    	  lineaCompleta.append(linea+"|");
	    	  
	      }
	      
  		  return lineaCompleta.toString().split("\\|");
	    }
	    catch(IOException e) {
	    	//En caso de excepcion no hace nada se continua con el flujo
	    } 
	    
	    return lineaNoEncontrada;
	}
	
	/**
	 * Metodo limpiarLineaDeTexto elimina caracteres raros.
	 * @param lineaOriginal es la cadena a limpiar
	 * @return la cadena limpia
	 */
	public String limpiarLineaDeTexto(String lineaOriginal) {
		StringBuilder lineaFinal = new StringBuilder("");
		if(lineaOriginal == null) {
			return null;
		}

		for(int i= 0; i <= lineaOriginal.length()-1 ;i++) {
			
			if( lineaOriginal.codePointAt(i) > 127 ||lineaOriginal.codePointAt(i) == 1  ) {
				lineaFinal.append(" ");
			}else if (  lineaOriginal.codePointAt(i) == 94   ){
				lineaFinal.append("");
			}
			else {
				lineaFinal.append(lineaOriginal.substring(i,i+1));
			}
		}
		return lineaFinal.toString();
	}
	
	/**
	 * @return the rango
	 */
	public String getRangoUtils() {
		return rangoUtils;
	}
	/**
	 * @param rango the rango to set
	 */
	public void setRangoUtils(String rangoUtils) {
		this.rangoUtils = rangoUtils;
	}
	
	
}
