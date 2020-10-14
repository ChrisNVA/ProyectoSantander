package com.sellcom.santander.edcnomina;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sellcom.santander.tools.Utils;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase Escritura con metodos para escribir los archivos con registros de nomina Santander.
 * @author Cesar Vazquez.
 * @version 2.0.
 * @since 29/10/2019
 * 
 * Control de cambios: 5/12/2019 Se agrega ruta de carpeta para archivo de parametros.
 * Control de cambios: 11/12/2019 Se agrega metodos para crear nombre y para agregar segmento.
 * Control de cambios: 26/04/2020 Se agrega metodo escribirMailCifrado
 */
@Slf4j
@Service
public class Escritura {
	//Se crea el objeto reporteInformativo
	@Autowired
	private ReporteInformativo reporteInformativo;
    //Se crea objeto reglasNegocio
    @Autowired
    private ReglasNegocio reglasNegocio;
    //Se crea el objeto utils
    @Autowired
    private Utils utils;
    @Getter
    @Setter
	private List<String> listaNombresArchivosrdy = new ArrayList<String>();;
	/**
	 * Metodo escribirLinea env�a registro al archivo indicado.
	 * @param archivo es el nombre de archivo a escribir.
	 * @param registro es la linea a escribir.
	 * @param paramFecha sirve para dar nombre al archivo.
	 * @param isPrevio para identificar si es proceso para previos.
	 * @param archivoOrigen sirve para identificar de donde viene la información.
	 * 
	 */
	public String escribirLinea(String archivo, String registro, String paramFecha, boolean isPrevio, String archivoOrigen) {
		//inicializamos el booleano para agregar registros en archivo.
		boolean agregar = true;
		//Se genera el nombre en base al tipo de producto encontrado.
    	String nomenclatura = crearNombreArchivo(registro, paramFecha);
    	String nomenclaturaNoGenerada =null;
    	archivo += nomenclatura;
    	
    	//Los desconocidos en previos no se escriben
    	if(isPrevio && archivo.contains("UNKNOWN")) {
    		return nomenclatura;
    	}
    	
    	//Los previos que ya existan en el archivo no se escriben nuevamente
    	if(validarExisteCliente(isPrevio,archivo,registro)) {
    		return nomenclatura;
    	}
    	
    		 try (
    	        		// se crea el objeto para escribir en archivo
    	        		PrintWriter pw = new PrintWriter(new FileWriter(archivo,agregar))
    	        	) {

    					reporteInformativo.imprimirReporte(registro,archivoOrigen , archivo );
    		        	//Se imprime en archivo el registro completo
    		        	String[] archivoInicial = archivoOrigen.split("/");
    		        	String[] archivoFinal = archivo.split("/");
    		        	//Cuando no existe tipo de segmento es desconocido y entonces agregamos nombre de archivo del mismo
    		        	if (!registro.contains(archivoInicial[archivoInicial.length -1])) {
    		        		registro += "|"+archivoFinal[archivoFinal.length -1]+"|";
    		        	} else {
    		        		String archivo1 = archivoInicial[archivoInicial.length -1].trim();
    		        		String archivo2 = archivoFinal[archivoFinal.length -1].trim(); 
    		        		registro = registro.replaceAll(archivo1, archivo2);
    		        	}

    		            pw.println(registro);
    		            listaNombresArchivosrdy.add(nomenclatura);
    		            return nomenclatura;
    	            
    	        } catch (IOException e) {
    	        	//Se envia a log el error
    	            log.error(e.toString());
    	            log.error( e.getLocalizedMessage());
    	            log.info(Constantes.T_ERROR+e.getMessage());
    	            return nomenclaturaNoGenerada;
    	        }
       

	}
	
	/**
	 * Metodo validarExisteCliente valida si existe un cliente en archivos previos
	 * @param isPrevio valida si un archivo es previo o no.
	 * @param archivo ruta de nuestro archivo a utilizar.
	 * @param registro registro en el que buscamos al cliente.
	 * @return valor booleano si encontro o no el cliente en el registro.
	 */
	private boolean validarExisteCliente(boolean isPrevio, String archivo, String registro) {
		String linea = null;
		
		//Los previos que ya existan en el archivo no se escriben nuevamente
    	if(isPrevio) {
    		File file = new File(archivo);
    		if(file.exists()) {
    			try ( Scanner scanner = new Scanner(Paths.get(archivo))){
        			while (scanner.hasNextLine()) {
        				linea = utils.limpiarLineaDeTexto(scanner.nextLine());
        				String seccion = reglasNegocio.obtenerSeccion(linea);
        				if ((seccion != null && seccion.equals(Constantes.UNO))) {
        					String[] clienteArchivo = linea.split("\\|");
        					String[] clienteRegistro = registro.split("\\|");
        					//Valida si el cliente existe en el archivo
        					if(clienteArchivo[6].equals(clienteRegistro[6])) {
        						return true;
        					}
        				}
        			}
        		}catch (Exception e) {
                	//Se envia a log el error
                    log.error(e.toString());
                    log.error( e.getLocalizedMessage());
                    log.error( e.getMessage());
                }
    		}
    	 
    	}
    	
    	return false;
	}

	/**
	 * Metodo crearNombreArchivo.
	 * @param registro es el texto completo del cliente.
	 * @param paramFecha sirve para dar nombre al archivo.
	 * @return el ultimo campo donde se localiza el segmento.
	 */
	private String crearNombreArchivo(String registro, String paramFecha) {
		
		String[] campofinal = registro.split("\\|");
		String nombreSegmentoTmp="01MNUNKNOWN";
		String nombreSegmento = "";
		String tipoDeProceso = "";
		String tipoDeImpresion = "I";
		// N: No impresi�n, para no impresi�n y previos
		if (campofinal[campofinal.length-3].contentEquals("A")
				|| campofinal[campofinal.length-4].contains("NOIMP")) {
			tipoDeImpresion = "N";
		}
		//Agregamos nomenclatura al UNKNOWN
			if (!campofinal[campofinal.length-3].contentEquals("A") && !campofinal[campofinal.length-3].contentEquals("M")) {
				
				nombreSegmento=nombreSegmentoTmp +=paramFecha+".01.rdy";
			} else {
				tipoDeProceso = campofinal[campofinal.length-3];
				nombreSegmento = Constantes.UNO+tipoDeProceso+tipoDeImpresion+campofinal[campofinal.length-4];
				nombreSegmento += paramFecha+".01.rdy";
			}

		return nombreSegmento;
	}
	
	/**
	 * Metodo escribirBloque para enviar a archivo los datos ordenados.
	 * @param bloque es el map.
	 * @param archivo es el nombre donde escribir.bloque
	 * @param paramFecha sirve para dar nombre al archivo.
	 * @param isPrevio indica si estamos en el proceso de previos.
	 * @param archivoOrigen nombre de archivo.
	 */
	public void escribirBloque(Map bloque, String archivo, String paramFecha, boolean isPrevio, String archivoOrigen) {

	    Set set = bloque.entrySet();
	    Iterator it = set.iterator(); // se itera el mapa para separar registros
	 
	    // Despliega cada par, valor.
	    while(it.hasNext()) {
	      Map.Entry me = (Map.Entry)it.next();
    	  escribirLinea(archivo, me.getValue().toString(), paramFecha, isPrevio, archivoOrigen);

	    } 
		
	}

	/**
	 * Metodo escribirMailCifrado para escribir el reporte.
	 * @param archivo donde escribir
	 * @param contadorMailCifrado el total
	 */
	public void escribirMailCifrado(String archivo, int contadorMailCifrado) {
		
        try (
        		//objeto para escribir mail cifrado
        		PrintWriter pw = new PrintWriter(new FileWriter(archivo, false))
        	) {
        	
	        	log.info(">>>>>>>>>>< MAIL CIFRADO = "+contadorMailCifrado+" EN '"+archivo+"' ");
	        	//Se imprime en archivo el registro completo
	            pw.println(String.format("%09d", contadorMailCifrado));
            
        } catch (IOException e) {
        	//Se envia a log el error
            log.error(e.toString());
            log.error( e.getLocalizedMessage());
            log.info(Constantes.T_ERROR+e.getMessage());
        }
		
	}
	
   /* public List<String> getListaNombresArchivosrdy() {
		return listaNombresArchivosrdy;
	}

	public void setListaNombresArchivosrdy(List<String> listaNombresArchivosrdy) {
		this.listaNombresArchivosrdy = listaNombresArchivosrdy;
	}*/

}
