package com.sellcom.santander.edcnomina.reportes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Clase Reporteador usada para generar reporte de los archivos RDY generadas
 * con el preproceso
 * @author Cesar Vazquez.
 *
 */

@Slf4j
@Service
public class Reporteador {
	//Se crea el objeto 	lecturaReporteador
	@Autowired
	private LecturaReporteador lecturaReporteador;
	//Se crea el objeto escrituraReporteador
	private EscrituraReporteador escrituraReporteador = new EscrituraReporteador();
	
	/**
	 * Metodo generarReporte para leer rdy's y generar reportes.
	 * @param argsRdy ruta y nombre del archivo.
	 */
	public void generarReporte(String argsRdy) {
		int totalSobres = 0;
		String archivoOrigen = "";
		// si el nombre de archivo existe 
		if (argsRdy != null) {
			archivoOrigen = argsRdy;
			//reemplazamos extension
			//Se escribe la linea de encabezado en el reporte
			escrituraReporteador.escribirLinea(archivoOrigen, "INPRINT="+archivoOrigen.replace(".rdy", ".ctr"));
			//escribimos encabezados de tabla
			escrituraReporteador.escribirLinea(archivoOrigen, " |TR|MEN|INICIO|FIN   |SOBRES|PAGS  |INS 1 |INS 2 |INS 3 |CAR 1 |CAR 2 |CAR 3 |CAR 4 |CAR 5 |CEN 1 |CEN 2 |CEN 3 |CEN 4 |CEN 5 |ALC   |PAGD  |");
		} else {
			// se envía el error
			return ;
		}
		
		try {
			Map bloqueDefinitivo = new TreeMap<>(); //Aqu� se almacena el folio final

			Map bloque = lecturaReporteador.leerBloque(archivoOrigen, false);

			int contadorClientes = 0;
			String llave = "";
			//obtenemos iteracion de mapa con secciones
			Iterator it = bloque.entrySet().iterator();
			while (it.hasNext()) { 
				Map.Entry pairs = (Map.Entry)it.next(); 
				String par = pairs.getValue().toString().substring(2,8).split("\\|")[1]+pairs.getValue().toString().substring(2,8).split("\\|")[0];
				if(llave.contentEquals("") && contadorClientes==0) {//primer recorrido
					contadorClientes++;
				} else if(llave.contentEquals(par) ) {//cuando se repite el cliente
					contadorClientes++;
				} else {
					//Aquí se reinicia el conteo, con los totales podemos calcular
					contadorClientes = 1;
				}
				// el par es la llave
				llave = par;
				it.remove(); // se elimina seccion
				// Agregamos folios con llave
				bloqueDefinitivo.put(llave, sustituirFolioFinal(pairs.getValue().toString(), contadorClientes ));
				
			}
			
			int folioInicial = 0; // primer campo de folio
			int folioFinal = 0; // segundo campo de folio
			int sobres = 0;	//campo con total de clientes

			int conteoFinal = 0; //inicializamos contador
			Iterator itDefinitivo = bloqueDefinitivo.entrySet().iterator(); //iteracion nueva
			//comienza el recorrido del ciclo
			while (itDefinitivo.hasNext()) { 
				Map.Entry pairsDefinitvo = (Map.Entry)itDefinitivo.next(); 
				//Sacamos los nulos de la escritura
				if ( (pairsDefinitvo.getValue().toString().split("\\|")[1].contentEquals("null") 
						|| pairsDefinitvo.getValue().toString().split("\\|")[2].contentEquals("null"))
						&& itDefinitivo.hasNext()) {
					//No lo muestra
						pairsDefinitvo = (Map.Entry)itDefinitivo.next(); 
					
				} else if ( (pairsDefinitvo.getValue().toString().split("\\|")[1].contentEquals("null") 
						|| pairsDefinitvo.getValue().toString().split("\\|")[2].contentEquals("null"))
						&& !itDefinitivo.hasNext()){
					// no hay mas registros
					break;
				}
				// obtenemos total de clientes
				sobres = Integer.parseInt(pairsDefinitvo.getValue().toString().split("\\|")[5]);
				
				//En la primera ocasion no cambia nada.
				if (conteoFinal == 0) {
					folioInicial = Integer.parseInt(pairsDefinitvo.getValue().toString().split("\\|")[3]);
					folioFinal = Integer.parseInt(pairsDefinitvo.getValue().toString().split("\\|")[4]);
					// incrementa conteo finbal
					conteoFinal++;
					
				}
				// El folio final solo se altera despues del primer recorrido
				if (conteoFinal > 0) {
					folioFinal = sobres + folioInicial -1;
				}
				//A partir de segunda linea escribimos con la sustitucion.
				escrituraReporteador.escribirLinea(archivoOrigen, sustituirFolios( pairsDefinitvo.getValue().toString(), folioInicial, folioFinal));
				folioInicial = folioFinal + 1;	// incrementa contador de folios
				
				//La siguiente linea se usara despues de salir del iterador
				totalSobres+=Integer.parseInt(pairsDefinitvo.getValue().toString().split("\\|")[5]);
				itDefinitivo.remove();
				// incrementa el conteo final
				conteoFinal++;
			}
			// Se escribe el total
			escrituraReporteador.escribirLinea(archivoOrigen,"TOTAL FICHERO S000|||||"+String.format("%06d", totalSobres)+"|000000|000000|000000|000000|000000|000000|000000|000000|000000|000000|000000|000000|000000|000000|000000|000000|");

		} catch (IOException e) {
			log.error(e.getMessage());
		}		

	}
	/**
	 * Metodo sustituirFolioFinal para sustituir el folio final.
	 * @param lineaReporte linea del reporte.
	 * @param contadorClientes contador de los clientes.
	 * @return linea de folio final.
	 */
	private String sustituirFolioFinal(String lineaReporte, int contadorClientes) {
		String[] nuevaLn = lineaReporte.split("\\|");
		int posicionFF = 4;
		int posicionCC = 5;
		
		StringBuilder lineaFolioFinal = new StringBuilder();
		//recorremos ciclo
		for(int i=0; i<nuevaLn.length; i++ ) {
			// evaluamos el regreso
			if(i==posicionFF) {
				lineaFolioFinal.append(String.format("%06d", contadorClientes)).append("|");
			} else if(i==posicionCC) {
				lineaFolioFinal.append(String.format("%06d", contadorClientes)).append("|");
			} else {
				lineaFolioFinal.append(nuevaLn[i]).append("|");
			}
		}
		
		// regresamos el folio final
		return lineaFolioFinal.toString();
	}

	/**
	 * Metodo sustituirFolios para sustituir un rango de folios.
	 * @param lineaReporte linea del reporte.
	 * @param folioInicial numero del folio donde se inicia el rango.
	 * @param folioFinal numero del folio donde se termina el rango.
	 * @return linea de retorno de los folios.
	 */
	private String sustituirFolios(String lineaReporte, int folioInicial, int folioFinal) {
		String[] nuevaLinea = lineaReporte.split("\\|");
		
		int posicionFolioInicial = 3;
		int posicionFolioFinal = 4;
		StringBuilder lineaRetorno = new StringBuilder();
		// comienza el ciclo de recorrido
		for(int i=0; i<nuevaLinea.length; i++ ) {
			lineaRetorno=agregarLineaRetorno(posicionFolioInicial, posicionFolioFinal, folioInicial, folioFinal, nuevaLinea, lineaRetorno, i);
		}
		// regresamos los folios
		return lineaRetorno.toString();
	}
	
	public StringBuilder agregarLineaRetorno(int posicionFolioInicial,int posicionFolioFinal, int folioInicial, int folioFinal,String[] nuevaLinea,StringBuilder lineaRetorno,int i) {
		// evaluamos
		if(i==posicionFolioInicial) {
			lineaRetorno.append(String.format("%06d", folioInicial)).append("|");
		} else if(i==posicionFolioFinal) {
			lineaRetorno.append(String.format("%06d", folioFinal)).append("|");
		} else {
			lineaRetorno.append(nuevaLinea[i]).append("|");
		}
		
		return lineaRetorno;
	}
	
}
