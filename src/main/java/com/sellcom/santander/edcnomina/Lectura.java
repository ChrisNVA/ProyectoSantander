package com.sellcom.santander.edcnomina;

import com.sellcom.santander.edcnomina.reportes.ContadorGeneral;
import com.sellcom.santander.exceptions.AplicationException;
import com.sellcom.santander.init.LecturaPreviosXML;
import com.sellcom.santander.init.LecturaSegmentosXML;
import com.sellcom.santander.tools.Utils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Clase Lectura para leer archivos con registros de nomina santander.
 *
 * @author Cesar Vazquez.
 * @version 1.0.
 * @since 29/10/2019
 */
@Slf4j
@Service
public class Lectura {
    //Variable de tipo Long para representar MEGABYTE
    private static final long MEGABYTE = 1024L * 1024L;
    //Variable de tipo String para representar el tamaño en MB
    public static final String SIZE = "Size in MB: ";
    //Variable de tipo String para representar el nombre del archivo a procesar
    public static final String NOMBRE_DEL_ARCHIVO_A_PROCESAR = "Nombre del archivo a procesar: ";
    //Variable de tipo String para representar los segundo que tarda en procesar
    public static final String SEGUNDOS_EN_PROCESAR = "Segundos en procesar: ";

    //Setter y Getter de fechaParametro
    @Setter
    @Getter
    private String fechaParametro;

    //Se crea el objeto contadorGeneral
    @Autowired
    private ContadorGeneral contadorGeneral;
    
    //Se crea el objeto utils
    @Autowired
    private Utils utils;

    //Se crea el objeto escritura
    @Autowired
    private Escritura escritura;

    //Se crea el objeto environment
    @Autowired
    private Environment environment;

    //Se crea objeto reglasNegocio
    @Autowired
    private ReglasNegocio reglasNegocio;
    
    //Se crea objeto testLectura2XML
    @Autowired
    private LecturaSegmentosXML testLectura2XML;
    
    //Se crea objeto testLectura2XML
    @Autowired
    private LecturaPreviosXML testLecturaPrevioXML;

    //Para mail cifrado
    private int contadorMailCifrado = 0;
    //Listado de sucursales
    @Autowired
    private Sucursal listaSucursal;

    /**
     * Metodo leerBloque que despliega el contenido (linea por linea) del archivo de entrada.
     *
     * @param fichero        es el nombre de archivo de entrada.
     * @param fechaParametro enviado desde linea de comandos.
     * @throws Exception En caso de error al leer archivo de entrada.
     */
    public void leerBloque(String fichero, String fechaParametro) throws AplicationException {
        this.fechaParametro = fechaParametro;

        String linea = null;
        String lineaPlaza = null;

        StringBuilder texto = new StringBuilder();
        StringBuilder textoPrevio = new StringBuilder();


        //Aqui se genera la llave
        String llave = null;
        //Aqui se genera la sucursal
        String sucursal = null;
        int cont = 0;
        try ( Scanner scanner = new Scanner(Paths.get(fichero))){

            long inicioTiempo = System.currentTimeMillis();
            Path ficheroPath = Paths.get(fichero);

            log.info(NOMBRE_DEL_ARCHIVO_A_PROCESAR + ficheroPath.getFileName().toString());
            log.info(SIZE + Files.size(ficheroPath) / MEGABYTE);
            listaSucursal.listaSucursal();
            while (scanner.hasNextLine()) {
                linea = utils.limpiarLineaDeTexto(scanner.nextLine());

                String seccion = reglasNegocio.obtenerSeccion(linea);

                if ((seccion != null && seccion.equals(Constantes.UNO))) {
                    lineaPlaza = linea;
                    escribirRegistroBloque(texto.toString(), llave, sucursal, fichero, textoPrevio.toString(), cont, false);
                    texto = new StringBuilder();
                    textoPrevio = new StringBuilder();
                }else if (seccion != null && seccion.equals(Constantes.DOS)) {

                    String plaza = reglasNegocio.obtenerPlaza(lineaPlaza);
                    sucursal = reglasNegocio.obtenerSucursal(plaza);
                    
                    //Se obtiene codigo postal
                    String codigoPostal = reglasNegocio.obtenerCodigoPostal(linea);
                    llave = reglasNegocio.crearLlave(codigoPostal, cont);

                    //Incrementa el mail cifrado si es que su campo lo indica
                    setContadorMailCifrado(getContadorMailCifrado() + reglasNegocio.incrementaMailCifrado(linea));

                }else if (seccion != null && seccion.equals(Constantes.TRES)) {
                    linea = reglasNegocio.modificarPorCampo( linea);

                }

                //para evitar caracteres raros al final
                if (linea.length() > 1 && texto != null) {
                    texto.append(linea + "\n");
                    textoPrevio.append(linea + "\n");
                }
                cont++;
            }

            long finTiempo = System.currentTimeMillis();
            log.info(SEGUNDOS_EN_PROCESAR + ((finTiempo - inicioTiempo) / 1000));
            
            escribirUltimoRegistroBloque(texto, llave, sucursal, fichero, textoPrevio, cont, false);

        }catch (Exception e) {
        	//Se envia a log el error
            log.error(e.toString());
            log.error( e.getLocalizedMessage());
            log.error( e.getMessage());
            log.error(linea);
            throw new AplicationException(e);
        } 

    }

    /**
     * Metodo escribirRegistroBloque
     *
     * @param textoEscribir registro a escribir
     * @param llave
     * @param sucursal del registro
     * @param fichero es el nombre y ruta
     * @param textoPrevioEscribir registro previo a escribir
     * @param cont contador de registros
     * @param omitirTipo valor para omitir tipos 1 y 3
     * 
     */
    public void escribirRegistroBloque(String textoEscribir,String llave,String sucursal,String fichero,String textoPrevioEscribir,int cont,boolean omitirTipo) {
    	String[] valores=new String[2];
    	valores[0]=llave;
    	valores[1]=sucursal;
    	escribirRegistro(textoEscribir, valores, fichero, textoPrevioEscribir, cont,omitirTipo," ");
        escribirRegistroPrevio(textoEscribir, llave, sucursal, fichero, textoPrevioEscribir, cont,"");
        }
    /**
     * Metodo escribirRegistro
     *
     * @param textoEscribir registro a escribir
     * @param llave
     * @param sucursal del registro
     * @param fichero es el nombre y ruta
     * @param textoPrevioEscribir registro previo a escribir
     * @param cont contador de registros
     * @param omitirTipo valor para omitir tipos 1 y 3
     * @param omitirID id procesados omitir,registros con mas coicidencias
     * 
     */
	public void escribirRegistro(String textoEscribir, String[] valores, String fichero,
			String textoPrevioEscribir, int cont,boolean omitirTipo, String omitirID) {
		StringBuilder texto = new StringBuilder();
		texto.append(textoEscribir);
		String[] salida = modificar(valores, texto, cont, obtenerUnicamenteNombre(fichero), fechaParametro, omitirTipo,
				omitirID);
			
		if (!texto.toString().trim().equals("") && validacionEscribir(omitirID, salida)) {
			escritura.escribirLinea(environment.getProperty(Constantes.CARPETA_SALIDA) + Constantes.SEPARADOR,
					texto.toString(), fechaParametro, false, fichero);
		}
		
		if (salida != null && salida[1]!=null && !salida[1].equalsIgnoreCase("END")) {
			omitirID = omitirID + salida[1]+",";
			omitirTipo =validarOmitirTipo(omitirTipo, salida);
			escribirRegistro(textoEscribir, valores, fichero, textoPrevioEscribir, cont,omitirTipo, omitirID);
		}
		
	}
	
    /**
     * Metodo validacionEscribir
     *
     * @param salida array de respuesta de metodo modificar 
     * @param omitirID id procesados omitir,registros con mas coicidencias
     * 
     */
    public boolean validacionEscribir(String omitirID,String[] salida) {
    	boolean validacion=true;
    	if("END".equalsIgnoreCase(salida[1])) {
    		if(salida[0].isEmpty()) {
    			return omitirID.length()<3;
    		}
    		return validacion;
		}
    	return validacion;
    }
    
    public boolean validarOmitirTipo(boolean omitirTipo,String[] salida) {
    	if(!omitirTipo) {
    		omitirTipo=(salida[2].equalsIgnoreCase("1") || salida[2].equalsIgnoreCase("3"));
    	}
    	return omitirTipo;
    }

    
    public void escribirRegistroPrevio(String textoEscribir,String llave,String sucursal,String fichero,String textoPrevioEscribir,int cont,String omitirID) {
    	StringBuilder textoPrevio= new StringBuilder();
    	textoPrevio.append(textoPrevioEscribir);
    	String idProcesado=modificarPrevio(llave, textoPrevio, cont,obtenerUnicamenteNombre(fichero),omitirID);
        if (!textoPrevio.toString().trim().equals("")){
            escritura.escribirLinea(environment.getProperty(Constantes.CARPETA_SALIDA)
                    + Constantes.SEPARADOR, textoPrevio.toString(), fechaParametro, true, fichero);
        }
    	omitirID=omitirID+idProcesado+",";
        if(!idProcesado.equalsIgnoreCase("END")&& idProcesado!="") {
        	escribirRegistroPrevio(textoEscribir, llave, sucursal, fichero, textoPrevioEscribir, cont, omitirID);
        }
    }

    /**
     * Metodo escribirUltimoRegistroBloque
     *
     * @param textoEscribir registro a escribir
     * @param llave
     * @param sucursal del registro
     * @param fichero es el nombre y ruta
     * @param textoPrevioEscribir registro previo a escribir
     * @param cont contador de registros
     * @param segmentoPrevio valor para omitir segmentos previos
     * 
     */
	public void escribirUltimoRegistroBloque(StringBuilder texto, String llave, String sucursal, String fichero,
			StringBuilder textoPrevio, int cont, boolean omitirTipo) {
			String[] valores=new String[2];
			valores[0]=llave;
			valores[1]=sucursal;
		// Agregamos el ultimo registro al bloque
		if (texto != null && texto.length() > 0) {
			modificar(valores, texto, cont, obtenerUnicamenteNombre(fichero), fechaParametro, omitirTipo, "");

			if (!texto.toString().trim().equals("")) {
				escritura.escribirLinea(environment.getProperty(Constantes.CARPETA_SALIDA) + Constantes.SEPARADOR,
						texto.toString(), fechaParametro, false, fichero);
			}
		}

		if (textoPrevio != null && textoPrevio.length() > 0) {
			modificarPrevio(llave, textoPrevio, cont, obtenerUnicamenteNombre(fichero), "");
			if (!textoPrevio.toString().trim().equals("")) {
				escritura.escribirLinea(environment.getProperty(Constantes.CARPETA_SALIDA) + Constantes.SEPARADOR,
						textoPrevio.toString(), fechaParametro, true, fichero);
			}
		}

	}
    
    /**
     * Metodo obtenerUnicamenteNombre
     *
     * @param fichero es el nombr y ruta
     * @return solo el nombre.
     */
    private String obtenerUnicamenteNombre(String fichero) {
        String[] nombre = fichero.split("/");
        return nombre[nombre.length - 1];
    }

    /**
     * Metodo modificar que agrega texto de archivo origen
     *
     * @param llave          encontrada en mensajeria.
     * @param texto          cliente obtenido de la interfaz.
     * @param cont           incrementable.
     * @param sucursal       encontrada.
     * @param archivoOrigen  cambio solicitado por el engine.
     * @param fechaParametro enviado desde linea de comandos.
     * @return mapa con los registros modificados con seccion S01
     */
    private String[] modificar(String[] valores,
                                    final StringBuilder texto, int cont, String archivoOrigen, String fechaParametro,boolean omitirTipo,String omitirID) {
        StringBuilder textoModificable = texto;
        String[] salida=new String[3];
        String[] salidaNoEncontrada=null;
        String llave=valores[0];
        String sucursal=valores[1];
        if (cont > 0 && llave != null) {
            //Si el bloque ya tiene datos buscamos coincidencias de segmento
            salida= testLectura2XML.obtenerTipoEnSegmento(texto.toString(), llave, sucursal, archivoOrigen, fechaParametro,omitirTipo,omitirID);
            String tipoEnSegmento=salida[0];           
            if (null == tipoEnSegmento) {
                return salidaNoEncontrada;
            }
            contadorGeneral.aumentar();
            textoModificable.append("S01|").append(cont).append("|").append(tipoEnSegmento);
            textoModificable.append("|0|").append(contadorGeneral.getContGeneral());
        }
        
        return salida;
    }

    /**
     * Metodo modificarPrevio para agregar seccion S01 a previos
     *
     * @param llave         encontrada en mensajeria.
     * @param texto         cliente obtenido de la interfaz.
     * @param cont          incrementable.
     * @param archivoOrigen Cambio solicitado por el equipo Engine.
     * @return seccion S01 agregada.
     */
    private String modificarPrevio(String llave,
                                          StringBuilder texto, int cont,String archivoOrigen,String omitirID) {
    	
    	String[] tipoEnPrevioSalida = null;
        if (cont > 0 && llave != null) {
			//Si el bloque ya tiene datos buscamos coincidencias de previo
        	tipoEnPrevioSalida = testLecturaPrevioXML.obtenerTipoEnPrevio(texto.toString(), archivoOrigen,omitirID);
			String tipoEnPrevio=tipoEnPrevioSalida!=null?tipoEnPrevioSalida[0]:null;
			if ((tipoEnPrevio!=null) && !("".equals(tipoEnPrevio.trim())) ) {
				texto.append("S01|").append(cont).append("|").append(tipoEnPrevio);
			} else {
				texto.append("S01|").append(tipoEnPrevio);
			}
            //Si el bloque ya tiene datos buscamos coincidencias de previo
            texto.append("|0|").append(contadorGeneral.getContGeneral());
			
        }
        return tipoEnPrevioSalida!=null?tipoEnPrevioSalida[1]:""; 
    }

    /**
	 * @return the contadorMailCifrado
	 */
    public int getContadorMailCifrado() {
        return contadorMailCifrado;
    }

    /**
	 * @param contadorMailCifrado the contadorMailCifrado to set
	 */
    public void setContadorMailCifrado(int contadorMailCifrado) {
        this.contadorMailCifrado = contadorMailCifrado;
    }

}