package com.sellcom.santander.edcnomina;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.sellcom.santander.exceptions.AplicationException;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

@Slf4j
@Service
public class ReporteInformativo {
	//Variable final de tipo String REGEX
    public static final String REGEX = "[.][^.]+$";
    //Se crea el objeto environment
    @Autowired
    private Environment environment;
    //Getter y Setter de fecha
    @Setter
    @Getter
    private String fecha;
    //Getter y Setter de registro
    @Setter
    @Getter
    private String registro;
    //Se crea el objeto mensajeria
    @Autowired
    private Mensajeria mensajeria;

    //Variable estatica de tipo string llamada CODIGO_DE_CLIENTE
    private String codigoDeCliente;
    //Variable estatica de tipo string llamada CONTRATO
    private String numeroDeCuenta;
    //Variable estatica de tipo string llamada INDICADOR_MAILCIFRADO
    private String indicadorDeMailCifrado;
    //Variable estatica de tipo string llamada INSTRUCCION_CORRESPONDENCIA
    private String instruccionDeCorrespondencia;
    //Variable estatica de tipo string llamada CODIGO_POSTAL
    private String cp;
    //Variable estatica de tipo string llamada PLAZA
    private String numeroDePlazaTitular;
    //Variable estatica de tipo string llamada NUMERO_DE_SUCURSAL_TITULAR
    private String numeroDeSucursalTitular;
    //Variable estatica de tipo string llamada SEGMENTO
    private String segmentos;
    /**
	 * Metodo init para inicializar las variables.
	 */
    private void init() {
        codigoDeCliente = environment.getProperty("BUC");
        numeroDeCuenta = environment.getProperty("NUMERO_DE_CUENTA");
        indicadorDeMailCifrado = environment.getProperty("INDICADOR_MAILCIFRADO");
        instruccionDeCorrespondencia = environment.getProperty("INSTRUCCION_CORRESPONDENCIA");
        cp = environment.getProperty("CODIGO_POSTAL");
        numeroDePlazaTitular = environment.getProperty("NUMERO_DE_PLAZA_TITULAR");
        numeroDeSucursalTitular = environment.getProperty("NUMERO_DE_SUCURSAL_TITULAR");
        segmentos = environment.getProperty("SEGMENTO");
    }

    /**
	 * Metodo imprimirReporte para imprimir un reporte
	 * @param registro 
	 * @param archivoOrigen archivo de origen.
	 * @param archivoCreado archivo creado.
	 */
    public void imprimirReporte(String registro, String archivoOrigen, String archivoCreado) {
        init();

        File fileOrigen = new File(archivoOrigen);
        File fileCreado = new File(archivoCreado);

        try (
                PrintWriter pw = new PrintWriter(new FileWriter(fileCreado.getParent() + "/" + fileOrigen.getName().replaceFirst(REGEX, "") + "_" + fecha + ".txt", true))
        ) {
            this.registro  = registro;

            final String codigoCliente = obtienerValor(codigoDeCliente);
            final String contrato = obtienerValor(numeroDeCuenta);
            final String indicador_mailcifrado = obtienerValor(indicadorDeMailCifrado);
            final String instruccionCorrespondencia = obtienerValor(instruccionDeCorrespondencia);
            final String codigoPostal = obtienerValor(cp);
            final String plaza = obtienerValor(numeroDePlazaTitular);
            final String numeroSucursalTitular = obtienerValor(numeroDeSucursalTitular);
            final String segmento = obtienerValor(segmentos);
            final String mensajerialine = obtenerMensajeria(codigoPostal);
            final String linea = String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s|%s", codigoCliente, contrato, indicador_mailcifrado, instruccionCorrespondencia, codigoPostal, plaza, numeroSucursalTitular, segmento, mensajerialine, fileCreado.getName());

            pw.println(linea);

        } catch (Exception e) {
            //Se envia a log el error
            log.error(e.toString());
            log.error( e.getLocalizedMessage());
            log.error( e.getMessage());
            log.error("Error: " + registro);
        }

    }
    /**
   	 * Metodo obtenerMensajeria obtener registro de mensajeria.
   	 * @param codigoPostal cadena de codigo postal.
   	 * @return codigo de mensaje.
   	 * @throws Exception en caso de obtener un error.
   	 */
    private String obtenerMensajeria(String codigoPostal) throws AplicationException{
        String mensajeriaCodigo = "";
        try {
            mensajeriaCodigo = mensajeria.obtenerRegistroMensajeria(codigoPostal)[0].split(" ")[0];
        } catch (Exception e) {
        	//En caso de excepcion no hace nada

        }
        return mensajeriaCodigo;
    }
    /**
   	 * Metodo obtienerValor obtiene el valor del campo.
   	 * @param field campo para buscar el valor.
   	 * @return el valor del campo.
   	 */
    private String obtienerValor(String field) {
        String[] xy = field.split("_");
        return buscarValorCampo(xy, registro);
    }

    /**
     * Metodo buscarValorCampo para encontrar valores.
     *
     * @param xy seccion y campo.
     * @param texto son datos del cliente.
     * @return el valor del campo.
     */
    private String buscarValorCampo(String[] xy, String texto) {

        String campo ="";
        String[] secciones = null;

        try {
            // se dividen las secciones.
            secciones = texto.split("\n");

            //Recorremos cada seccion para buscar el valor del campo
            for (String seccion : secciones) {
                if (xy[0].equals(seccion.split("\\|")[0])) {
                    int posicion = Integer.parseInt(xy[1]);
                    campo = seccion.split("\\|")[posicion];
                }
            }
        } catch (Exception e) {
        	//En caso de excepcion no hace nada
        }
        return campo;
    }

}
