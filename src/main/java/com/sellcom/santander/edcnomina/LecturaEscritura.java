package com.sellcom.santander.edcnomina;

import com.sellcom.santander.edcnomina.reportes.Reporteador;
import com.sellcom.santander.exceptions.AplicationException;
import com.sellcom.santander.tools.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase LecturaEscritura para las pruebas de ordenamiento de bloques de cliente.
 *
 * @author Cesar Vazquez.
 * @version 2.0.
 * @since 4/11/2019
 * <p>
 * Control de cambios: 5/12/2019 Se agrega ruta de carpeta para archivo de parametros.
 * Control de cambios: 26/04/2020 Se agregan metodos para reportes y mail cifrado.
 */
@Slf4j
@Component
public class LecturaEscritura {
	//Se crea el objeto reporteador
    @Autowired
    private Reporteador reporteador;
    //Se crea el objeto environment
    @Autowired
    private Environment environment;
    //Se crea el objeto utils
    @Autowired
    private Utils utils;
    //Se crea el objeto lectura
    @Autowired
    private Lectura lectura;
    //Se crea el objeto escritura
    @Autowired
    private Escritura escritura;
    //Se crea el objeto reporteInformativo
    @Autowired
    private ReporteInformativo reporteInformativo;
    //Se crea el objeto preprocesoCfdi
    @Autowired
    private PreprocesoCfdi preprocesoCfdi;
    //Se crea el objeto ordernarRDY
    @Autowired
    private OrdernarRDY ordernarRDY;

    /**
     * Metodo main como punto de entrada a la aplicacion.
     *
     * @param args lista de argumentos desde linea de comando.
     * @throws Exception para el manejo de errores.
     */

    public void ejecucion(String[] args) throws AplicationException {

        // Se instancia escritura
        log.info("Iniciando ...:: " + new Date());
        String paramFecha;
        // si no tiene argumentos no continua
        if (args.length < 1) {
            return;
        } else {
            // el argumento de entrada es la fecha
            paramFecha = "20200829";//args[0];
        }

        try {
            reporteInformativo.setFecha( paramFecha);

            // Se ejecuta metodo que regresa los archivos de entrada desde el properties
            String[] archivoOrigen = utils.obtenerArchivosOrigen();
            if (archivoOrigen == null) {
                log.info("No hay ficheros en el archivo de propiedades  ");
            } else {
                // Ciclo para ir archivo por archivo
                for (int x = 0; x < archivoOrigen.length; x++) {
                    if (esValidoArchivoOrigen(archivoOrigen, x)) {
                        continue;
                    }
                    log.info("Leyendo " + archivoOrigen[x]);
                    //Comienza la lectura de archivo de segmentos
                    lectura.leerBloque(archivoOrigen[x], paramFecha);

                    log.info("Terminado  " + archivoOrigen[x]);

                }

            }

            String archivoCFDI = environment.getProperty(Constantes.ARCHIVO_CFDI);
            if (archivoCFDI != null && !archivoCFDI.trim().isEmpty()) {
            	 File file = new File(archivoCFDI);
                 if(file.length()!=0) {
                	 preprocesoCfdi.proceso(archivoCFDI);
                 }else {
                	 preprocesoCfdi.creaArchivosCFDI();
                 }
                
            }else {
            	preprocesoCfdi.creaArchivosCFDI();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new AplicationException(e);
        } finally {
            log.info("... Terminando:: " + new Date());

            ordernarRDY.ordernarArchivosRDY(  environment.getProperty(Constantes.CARPETA_SALIDA) );
            //Se almacena el mail cifrado
            escritura.escribirMailCifrado(environment.getProperty(Constantes.CARPETA_SALIDA) + "/REPORTE_MAIL_CIFRADO_" + paramFecha + ".TXT", lectura.getContadorMailCifrado());
            //reportes
            reportear();

        }


    }
    
    /**
     * Metodo esValidoArchivoOrigen valida el archivo origen
     *
     * @param archivoOrigen archivo origen
     * @param x posicion del archivo
     * @return variable de tipo boolean
     */
    private boolean esValidoArchivoOrigen(String[] archivoOrigen, int x) {
        return archivoOrigen[x] == null?true: false;
    }

    /**
     * Metodo reportear genera un archivo de reporte 
     *
     */
    private void reportear() {
    	List<String> lista = new ArrayList<>();
    	
		lista = escritura.getListaNombresArchivosrdy();
        File carpeta = new File(environment.getProperty(Constantes.CARPETA_SALIDA));
        
        FilenameFilter onlyRDY = (File dir, String name) -> name.toLowerCase().endsWith(Constantes.EXTENSION_RDY);
        
        log.info("   -   -   -   Generando reportes   -   -   -   ");
        // ruta donde se generara el archivo de reporte
        String ruta = carpeta.getPath();
        for (String archivo : carpeta.list(onlyRDY)) {
            // Se envia la instruccion para generar el reporte
        	if(lista.contains(archivo)) {
        		reporteador.generarReporte(ruta + "/" + archivo);
        	}
        	
        }
    }
}
