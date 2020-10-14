package com.sellcom.santander.edcnomina.reportes;


import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;


/**
 * Clase LecturaReporteador para leer archivos con registros de nomina santander.
 * @author Cesar Vazquez.
 * @version 1.0.
 * @since 14/04/2020
 * 
 */
@Slf4j
@Service
public class LecturaReporteador {
    //Variable de tipo Long para representar MEGABYTE
	private static final long  MEGABYTE = 1024L * 1024L;
    //Variable de tipo String para representar el tamaño en MB
	public static final String SIZE = "Size in MB: ";
    //Variable de tipo String para representar el nombre del archivo a procesar
	public static final String NOMBRE_DEL_ARCHIVO_A_PROCESAR = "Nombre del archivo a procesar: ";
    //Variable de tipo String para representar los segundo que tarda en procesar
	public static final String SEGUNDOS_EN_PROCESAR = "Segundos en procesar: ";
	//Se crea el objeto mensajeria
	@Autowired
	private MensajeriaReporteador mensajeria;
	//Se crea el objeto reglasNegocioReporteador
	@Autowired
	private ReglasNegocioReporteador reglasNegocioReporteador;

	 /**
		 * Metodo leerBloque que despliega el contenido (linea por linea) del archivo de entrada.
		 * @param fichero es el nombre de archivo de entrada.
		 * @param isPrevios indica si la lectura ser� desde previos.
		 * @return mapa con llaves y valores.
		 * @throws IOException En caso de error al leer archivo de entrada.
		 */
		 public Map leerBloque(String fichero, boolean isPrevios) throws IOException  {

		     String linea;
		     //En este map se almacenan los clientes con su llave 	
		     TreeMap<String, StringBuilder> bloque = new TreeMap<>();
    		 //para limpiar texto
		     if (fichero==null) {
    			 return bloque;
    		 }
    		 
		     StringBuilder texto = new StringBuilder(); 
		     
		     //Aqui se generara la llave
		     String llave = null;
		     //Aqui se almacenaran los folios
		     String [] folios = new String[3];
		     
		     int cont = 0;
		     //para seccion 3
		     int contFolio = 0;
		     Path ficheroPath = Paths.get(fichero);
			 try(Scanner scanner = new Scanner(ficheroPath)) {
				 long inicioTiempo = System.currentTimeMillis();
				 

				 log.info(NOMBRE_DEL_ARCHIVO_A_PROCESAR+ficheroPath.getFileName().toString());
				 log.info(SIZE+Files.size(ficheroPath) /MEGABYTE);
				 
				 while(scanner.hasNextLine()) {
					 linea =   scanner.nextLine() ;
			      	String seccion = reglasNegocioReporteador.obtenerSeccion(linea);
	
					if ( (seccion!=null && seccion.equals(ConstantesReporteador.UNO))  )  {

						bloque = generarRegistro(bloque, llave, cont);
						
						//Se inicializa el buffer 
			      		texto = new StringBuilder();
			      		//Se inicializan folios
			      		folios = new String[2];
			      		//Se inicializan contadores
			      		contFolio = 0;
			      	} 
					if (seccion!=null && seccion.equals(ConstantesReporteador.DOS)) {

						//Se obtiene codigo postal
			      		String codigoPostal = reglasNegocioReporteador.obtenerCodigoPostal(linea);
			      		llave = crearLlave(codigoPostal, cont);
			      	}
					 if (seccion!=null && seccion.equals(ConstantesReporteador.S_UNO)) {

						 val segmentos = linea.split(Pattern.quote("|"));

						 if (segmentos[2] != null && segmentos[2].length() == 8) {
							 llave = segmentos[2].substring(6, 8) + "-" +  segmentos[2].substring(0, 3) +  "-" +cont;
						 }
					 }
					 if (seccion!=null && seccion.equals(ConstantesReporteador.TRES) && contFolio==0) {

						//Se obtiene folio inicial
							folios[0] = reglasNegocioReporteador.obtenerFolio(linea);
			      		contFolio +=1;
			      	}
					if (seccion!=null && seccion.equals(ConstantesReporteador.TRES) && contFolio!=0) {
							//Se obtiene folio final
							folios[1] = reglasNegocioReporteador.obtenerFolio(linea);

			      		contFolio +=1;
			      	}					
					//para evitar caracteres raros al final
					if(linea.length() > 1) {
						texto.append(linea+"\n");
					}
		      		cont++;
			     }
				 long finTiempo = System.currentTimeMillis();
				 log.info( SEGUNDOS_EN_PROCESAR +  ((finTiempo - inicioTiempo)/1000) );
			     //Agregamos el ultimo registro al bloque			     
			     if (texto!= null && texto.length() > 0) {
			    	 bloque = generarRegistro(bloque, llave, cont);
			     }

		     } catch (Exception e) {
		    	 throw new IOException(e);
		     }
		     
	      	return  bloque;
		 }	 
		 

	 /**
	  * Metodo generarRegistro modificar que hace agrega el texto de segmento al final del bloque.
	  * @param bloque es el mapa con los bloques.
	  * @param llave es el orden.
	  * @param texto las lineas del bloque.
	  * @param cont mayor a cero indica si ya se puede guardar otro valor.
	  * @return el mapa con o sin nuevo dato.
	  */
		private TreeMap<String, StringBuilder> generarRegistro(TreeMap<String, StringBuilder> bloque, String llave,
				 int cont) {
			StringBuilder textoModificable = new StringBuilder();			

			if (cont  > 0 && llave!=null) {
				
				textoModificable.append(" |").append(llave.split("-")[0])
								.append("|").append(llave.split("-")[1])
								.append("|").append(String.format("%06d", 1)) //INICIO
								.append("|").append(String.format("%06d", 0)) //FIN
								.append("|").append(ConstantesReporteador.SEIS_CEROS) //SOBRES
								.append("|").append(ConstantesReporteador.SEIS_CEROS) //PAGS (000012)
								.append("|").append(ConstantesReporteador.SEIS_CEROS) //INS 1
								.append("|").append(ConstantesReporteador.SEIS_CEROS) //INS 2
								.append("|").append(ConstantesReporteador.SEIS_CEROS) //INS 3
								.append("|").append(ConstantesReporteador.SEIS_CEROS) //CAR 1
								.append("|").append(ConstantesReporteador.SEIS_CEROS) //CAR 2
								.append("|").append(ConstantesReporteador.SEIS_CEROS) //CAR 3
								.append("|").append(ConstantesReporteador.SEIS_CEROS) //CAR 4
								.append("|").append(ConstantesReporteador.SEIS_CEROS) //CAR 5
								.append("|").append(ConstantesReporteador.SEIS_CEROS) //CEN 1
								.append("|").append(ConstantesReporteador.SEIS_CEROS) //CEN 2
								.append("|").append(ConstantesReporteador.SEIS_CEROS) //CEN 3
								.append("|").append(ConstantesReporteador.SEIS_CEROS) //CEN 4
								.append("|").append(ConstantesReporteador.SEIS_CEROS) //CEN 5
								.append("|").append(ConstantesReporteador.SEIS_CEROS) //ALC
								.append("|").append(ConstantesReporteador.SEIS_CEROS) //PGD
								;

				bloque.put( llave,textoModificable);
			}
			return bloque;
		}

	/**
	 * Metodo crearLlave que genera la llave que se usara en el map para ordenar.
	 * @param codigoPostal es el filtro de busqueda de la l�nea en el archivo de mensajer�a.
	 * @param indice para no perder registros.
	 * @return la llave transportista, rango, mensajeria.
	 * @throws IOException en caso de no leer o escribir.
	 */
	public String crearLlave(String codigoPostal, int indice) throws  IOException {

		// Se obtienen los registros
		String[] linea = mensajeria.obtenerRegistro(codigoPostal, mensajeria);
		// Si se obtuvieron entones se asignan
		if(linea!=null && linea[0] != null) {
			mensajeria.setIdTransportista(linea[0].substring(8, 10));
			mensajeria.setIdMensajeria(linea[0].substring(0, 3));
			mensajeria.setIdGrupo(linea[0].substring(4, 7));
			mensajeria.setRango(linea[1]);
		}
		// regresa la llave generada
		return  reglasNegocioReporteador.generarLlave(mensajeria, indice);
		
	}
	
}