package com.sellcom.santander.edcnomina.reportes.cache;

import com.sellcom.santander.edcnomina.Constantes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CacheMensajeria {
    @Autowired
    private Environment environment;

    public static final String ARCHIVO_MENSAJERIA = "archivoMensajeria";

    @Cacheable(value = ARCHIVO_MENSAJERIA)
    public Map<String, List<String>> leerArchivoMensajeria() throws IOException {
        Map<String, List<String>> fileMap = new HashMap<>();
        String linea;
        log.info("Archivo Mensajeria Cachado");
        try (
                BufferedReader br = new BufferedReader(new FileReader(environment.getProperty(Constantes.ARCHIVO_MENSAJERIA)))
        ) {
            // ciclo para buscar codigos postales
            while ((linea = br.readLine()) != null) {

                // Se almacenan en una lista
                fileMap.putAll(obtenerRangosCodigosPostales(linea));
            }

        } catch (Exception e) {
            throw new IOException(e);
        }
        return fileMap;
    }

    /**
     * Metodo obtenerRangosCodigosPostales.
     *
     * @param linea de archivo donde se busca el codigo postal.
     * @return lista de codigos postales encontrados.
     */
    private Map<String, List<String>> obtenerRangosCodigosPostales(String linea) {
        List<String> rangos = new ArrayList<>();
        Map<String, List<String>> fileMap = new HashMap();
        // Se buscan en la columna 60 del archivo de mensajeria
        String codigosPostales = linea.substring(60);
        int contador = 0;
        int inicio = 0;
        // inicia el ciclo de comparacione
        while (contador <= codigosPostales.length()) {
            //Se almacenan codigos postales
            String tmp = codigosPostales.substring(inicio, inicio + 11);
            inicio += 11;
            rangos.add(tmp);
            //Se agregan a la lista los codigos encontrados
            //Con la siguiente linea evitamos una excepcion
            contador = inicio + 11;

        }
        fileMap.put(linea, rangos);
        return fileMap;
    }
}
