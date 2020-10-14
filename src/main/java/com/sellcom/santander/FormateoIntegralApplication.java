package com.sellcom.santander;


import com.sellcom.santander.edcnomina.LecturaEscritura;
import com.sellcom.santander.exceptions.AplicationException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.sellcom.santander.edcnomina.Constantes.CODIFICACION;
import static com.sellcom.santander.edcnomina.Constantes.DIRECTORIO_DEL_APLICATIVO;
import static com.sellcom.santander.edcnomina.Constantes.FECHA_ARGUMENT;
import static com.sellcom.santander.edcnomina.Constantes.FILE_ENCODING;
import static com.sellcom.santander.edcnomina.Constantes.FREE_MEMORY;
import static com.sellcom.santander.edcnomina.Constantes.GUION;
import static com.sellcom.santander.edcnomina.Constantes.JAVA_VERSION;
import static com.sellcom.santander.edcnomina.Constantes.MAX_MEMORY;
import static com.sellcom.santander.edcnomina.Constantes.MB;
import static com.sellcom.santander.edcnomina.Constantes.MB1;
import static com.sellcom.santander.edcnomina.Constantes.OS_NAME;
import static com.sellcom.santander.edcnomina.Constantes.OS_VERSION;
import static com.sellcom.santander.edcnomina.Constantes.SEGUNDOS_EN_PROCESAR;
import static com.sellcom.santander.edcnomina.Constantes.SO;
import static com.sellcom.santander.edcnomina.Constantes.TOTAL_MEMORY;
import static com.sellcom.santander.edcnomina.Constantes.USED_MEMORY;
import static com.sellcom.santander.edcnomina.Constantes.USER_DIR;
import static com.sellcom.santander.edcnomina.Constantes.UTF_8;



/**
 * Clase de ejecucion del deserrollo
 * @author Luis Lopez.
 * @version 1.0.
 * @since 28/10/2019.
 *
 */
@Slf4j
@SpringBootApplication

public class FormateoIntegralApplication implements CommandLineRunner {

    @Autowired
    private LecturaEscritura lecturaEscrituraMapTest;

    public static void main(String[] args) {

        SpringApplication.run(FormateoIntegralApplication.class, args).close();
    }

    @Override
    public void run(String... args) throws AplicationException {
        System.setProperty(FILE_ENCODING, UTF_8);
        log.info( SO + System.getProperty(OS_NAME) + GUION + System.getProperty(OS_VERSION));
        log.info( "Version JAVA: " + System.getProperty(JAVA_VERSION));
        log.info( CODIFICACION + System.getProperty(FILE_ENCODING));
        log.info(DIRECTORIO_DEL_APLICATIVO + System.getProperty(USER_DIR));
        log.info(USED_MEMORY + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) /MB + MB1);
        log.info(FREE_MEMORY + Runtime.getRuntime().freeMemory() /MB + MB1);
        log.info(TOTAL_MEMORY + Runtime.getRuntime().totalMemory() /MB+ MB1);
        log.info(MAX_MEMORY + Runtime.getRuntime().maxMemory() /MB + MB1);

        log.info(FECHA_ARGUMENT + args[0]);

        long inicioTiempo = System.currentTimeMillis();
        lecturaEscrituraMapTest.ejecucion(args);
        long finTiempo = System.currentTimeMillis();
        log.info( SEGUNDOS_EN_PROCESAR +  ((finTiempo - inicioTiempo)/1000) );
    }
}
