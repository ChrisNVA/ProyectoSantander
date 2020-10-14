package com.sellcom.santander.edcnomina;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sellcom.santander.exceptions.AplicationException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Slf4j
@Component
public class OrdernarRDY {
	//Variable de tipo Long para representar MEGABYTE
    private static final long MEGABYTE = 1024L * 1024L;
    //Variable de tipo String para representar el tamaño en MB
    public static final String SIZE = "Size in MB: ";
    //Variable de tipo String para representar el nombre del archivo a procesar
    public static final String NOMBRE_DEL_ARCHIVO_A_PROCESAR = "Nombre del archivo a procesar: ";
    //Variable de tipo String para representar los segundo que tarda en procesar
    public static final String SEGUNDOS_EN_PROCESAR = "Segundos en procesar: ";
    //Se crea el objeto escritura
    @Autowired
    private Escritura escritura;
    //Se crea el objeto listaArchivosrdy
    List<String> listaArchivosrdy = new ArrayList<>();
    /**
	 * Metodo ordernarArchivosRDY ordena los archivos RDY
	 * @param carpeta ruta de la carpeta donde se encuentra el archivo
	 */
    @SneakyThrows
    public void ordernarArchivosRDY(String carpeta) {
    	try(Stream<Path> file= Files.list(Paths.get(carpeta)).filter(path -> path.getFileName().toString().toLowerCase().endsWith("rdy")).sorted( (o1, o2) -> o1.toFile().length() > o2.toFile().length() ? 1 : -1 )) {
        listaArchivosrdy = escritura.getListaNombresArchivosrdy();
       	file.forEach(path -> {
       		if(listaArchivosrdy.contains(path.getFileName().toString())){
                    log.info(NOMBRE_DEL_ARCHIVO_A_PROCESAR + path.getFileName().toString());
                    try {
                        log.info(SIZE + Files.size(path) / MEGABYTE);
                    } catch (IOException e) {
                    	//Se envia a log el error
                        log.error(e.toString());
                        log.error( e.getLocalizedMessage());
                        log.error( e.getMessage());
                    }
                    long inicioTiempo = System.currentTimeMillis();

                    obtieneArchivoOrdenado(path);

                    long finTiempo = System.currentTimeMillis();
                    log.info(SEGUNDOS_EN_PROCESAR + ((finTiempo - inicioTiempo) / 1000));
                    log.info("Terminando..  ");

       		}
                });
    	}catch (Exception e) {
    		//Se envia a log el error
            log.error(e.toString());
            log.error( e.getLocalizedMessage());
            log.error( e.getMessage());
		}

    }

    /**
	 * Metodo obtieneArchivoOrdenado ordena el archivo
	 * @param archivoRDY ruta del archivo
	 * @return archivo ordenado
	 */
    @SneakyThrows
    private Map<String, IndicesValor> obtieneArchivoOrdenado(Path archivoRDY) {
        final List<String> lineas = Files.readAllLines(archivoRDY);
        log.info("Ordenando " + lineas.size() + " lineas.");
        Map<String, IndicesValor> archivoOrdenado = new TreeMap<>();

        Integer inicioBloque = 0;
        Integer contador = 0;
        String linea = "";
        try {

            for (String lineaActual : lineas) {
                linea = lineaActual;
                if (linea.startsWith("01")) {
                    inicioBloque = contador;
                }
                if (linea.startsWith("S01")) {
                    val segmentos = linea.split(Pattern.quote("|"));
                    String llave = contador.toString();
                    if (segmentos[2] != null && segmentos[2].length() == 8) {
                        llave = segmentos[2].substring(0, 3) + "-" + segmentos[2].substring(6, 8) + "-" + contador;
                    }
                    IndicesValor indicesValor = new IndicesValor();
                    indicesValor.setInicio(inicioBloque);
                    indicesValor.setTermino(contador + 1);

                    archivoOrdenado.put(llave, indicesValor);

                }
                ++contador;
            }
        } catch (Exception e) {
        	 //Se envia a log el error
            log.error(linea);
            log.error(e.toString());
            log.error( e.getLocalizedMessage());
            log.error( e.getMessage());
            throw new AplicationException("Error en ordenamiento",e);
        }
        creaArchivoOrdenado(archivoRDY, archivoOrdenado, lineas);

        return archivoOrdenado;

    }
    
    /**
   	 * Metodo creaArchivoOrdenado crea el archivo
   	 * @param archivo ruta del archivo
   	 * @param archivoOrdenado archivo ordenado
   	 * @param lineas lista de las lineas    	 
   	 */
    private void creaArchivoOrdenado(Path archivo, Map<String, IndicesValor> archivoOrdenado, List<String> lineas) {
        log.info("Escribiendo Archivo");
        borrarArchivo(archivo);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo.toAbsolutePath().toString(), true))){

            Set set = archivoOrdenado.entrySet();
            Iterator it = set.iterator();

            while (it.hasNext()) {
                Map.Entry map = (Map.Entry) it.next();
                IndicesValor indicesMap = (IndicesValor) map.getValue();
                List<String> sublistaLineas = lineas.subList(indicesMap.inicio, indicesMap.termino);
                for (String linea : sublistaLineas) {
                    writer.write(linea + "\n");
                }
            }
           
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }

    /**
   	 * Metodo borrarArchivo borra el archivo
   	 * @param path ruta del archivo  	 
   	 */
    @SneakyThrows
    private void borrarArchivo(Path path) {
        Files.delete(path);
    }
    
    @Data
    class IndicesValor {
    	//variable privada de tipo entero llamada inicio
        private Integer inicio;
        //variable privada de tipo entero llamada termino
        private Integer termino;
    }

}
