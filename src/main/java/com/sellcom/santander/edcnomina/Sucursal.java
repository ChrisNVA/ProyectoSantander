package com.sellcom.santander.edcnomina;

import com.sellcom.santander.tools.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
public class Sucursal {
	//Se crea el objeto environment
	@Autowired
	private Environment environment;
	//Variable privada tipo String llamada idSucursal
	private String idSucursalSucursal;
	//Variable privada tipo String llamada idGrupo
	private String idGrupoSucursal;
	//Variable privada tipo String llamada idTransportista
	private String idTransportistaSucursal;
	//Variable privada tipo String llamada rango
	private String rangoSucursal;
	//Variable privada para generar una lista de sucursales
	private List<String> lista;
	
	/**
	 * Metodo listaSucursal busca y devuelve la linea de archivo de sucursal que corresponde a la plaza.
	 * @param plaza es el filtro por el cual se busca.
	 * @return linea de archivo donde se encuentra la coincidencia.
	 * @throws IOException en caso de problemas al leer.
	 */
	
	public void listaSucursal() throws IOException {
		 lista = new ArrayList<>();
		try(Scanner scanner = new Scanner(Paths.get(environment.getProperty(Constantes.ARCHIVO_SUCURSALES)))){
			while(scanner.hasNextLine()) {
				lista.add(scanner.nextLine());
			}
			scanner.close();
		}catch(IOException e) {
    		// en caso de error se envía a metodo que lo llama.
    		throw new IOException(e);
    	} 
	}
	/**
	 * Metodo obtenerRegistro busca y devuelve la linea de archivo de sucursal que corresponde a la plaza.
	 * @param plaza es el filtro por el cual se busca.
	 * @return linea de archivo donde se encuentra la coincidencia.
	 */
	public String[] obtenerRegistro(String plaza)    {
		String linea;
		String[] lineaEncontrada = null;
    		for (int i = 0; i < lista.size() && lineaEncontrada==null; i++) {
    			linea = lista.get(i);
		    	 //por cada linea buscar rango de plazas
		    	 List<String> rangosPlazas = obtenerRangosPlazas(linea);
		    	 //por cada rango buscar que se encuentre dentro el codigo postal.
	    		 lineaEncontrada =  Utils.definirRangoPlazas(linea, plaza, rangosPlazas);
			}
  
    	return lineaEncontrada;	
	}

	/**
	 * Metodo obtenerRangosPlazas.
	 * @param linea de archivo donde se busca la plaza.
	 * @return lista de plazas encontradas.
	 */
	private List<String> obtenerRangosPlazas(String linea) {
		List<String> rangos = new ArrayList<>();
		//obtenemos las plazas de la linea de texto
		String plazas = linea.substring(60) ;
		int contador = 0;
		int inicio = 0;
		// Se entra a ciclo de busqueda de plazas
		while (contador <= plazas.length()) {
			//Se almacenan	
			String tmp = plazas.substring(inicio, inicio +17);				
			inicio+=17;
			rangos.add(tmp);
			//Con la siguiente linea evitamos una excepcion
			contador = inicio +17;

		}
		return rangos;
	}

	/**
	 * @return the idSucursal
	 */
	public String getIdSucursalSucursal() {
		return idSucursalSucursal;
	}
	/**
	 * @param idSucursal the idSucursal to set
	 */
	public void setIdSucursalSucursal(String idSucursalSucursal) {
		this.idSucursalSucursal = idSucursalSucursal;
	}
	/**
	 * @return the idGrupo
	 */
	public String getIdGrupoSucursal() {
		return idGrupoSucursal;
	}
	/**
	 * @param idGrupo the idGrupo to set
	 */
	public void setIdGrupoSucursal(String idGrupoSucursal) {
		this.idGrupoSucursal = idGrupoSucursal;
	}
	/**
	 * @return the idTransportista
	 */
	public String getIdTransportistaSucursal() {
		return idTransportistaSucursal;
	}
	/**
	 * @param idTransportista the idTransportista to set
	 */
	public void setIdTransportistaSucursal(String idTransportistaSucursal) {
		this.idTransportistaSucursal = idTransportistaSucursal;
	}
	/**
	 * @return the rango
	 */
	public String getRangoSucursal() {
		return rangoSucursal;
	}
	/**
	 * @param rango the rango to set
	 */
	public void setRangoSucursal(String rangoSucursal) {
		this.rangoSucursal = rangoSucursal;
	}


	
}
