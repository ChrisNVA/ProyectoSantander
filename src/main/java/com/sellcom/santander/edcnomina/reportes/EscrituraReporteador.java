package com.sellcom.santander.edcnomina.reportes;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Clase Escritura con metodos para escribir los archivos con registros de nomina Santander.
 * @author Cesar Vazquez.
 * @version 2.0.
 * @since 29/10/2019
 * 
 * Control de cambios: 5/12/2019 Se agrega ruta de carpeta para archivo de parametros.
 * Control de cambios: 11/12/2019 Se agrega metodos para crear nombre y para agregar segmento.
 */
public class EscrituraReporteador {

	/**
	 * Metodo escribirLinea env�a registro al archivo indicado.
	 * @param archivo es el nombre de archivo a escribir.
	 * @param registro es la linea a escribir.
	 *
	 */
	public void escribirLinea(String archivo, String registro) {

		boolean agregar = true;

    	String nomenclatura = ".ctr";
    	String archivoDestino = archivo.replace( ".rdy", nomenclatura);
    	
        try (
        		PrintWriter pw = new PrintWriter(new FileWriter(archivoDestino, agregar))
        	) {

	        	//Se imprime en archivo el registro completo
	            pw.println(registro);
            
        } catch (IOException e) {
        	//En caso de excepcion no hace nada se continua con el flujo
        }

	}

}
