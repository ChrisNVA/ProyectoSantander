package com.sellcom.santander.edcnomina;

import com.sellcom.santander.exceptions.AplicationException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Clase ReglasNegocio donde se encuentran los metodos que permiten la logica de negocio.
 *
 * @author Cesar Vazquez.
 * @version 1.0.
 * @since 28/10/2019.
 */
@Slf4j
@Service
public class ReglasNegocio {
	//Se crea el objeto environment
    @Autowired
    private Environment environment;
    //Se crea el objeto sucursal
    @Autowired
    private Sucursal sucursal;
    //Se crea el objeto mensajeria
    @Autowired
    private Mensajeria mensajeria;
    //Getter correspondenciaSucursal
    @Getter
    private String correspondenciaSucursal;
    //Getter correspondenciaMensajeria
    @Getter
    private String correspondenciaMensajeria;

    /**
     * Metodo obtenerSeccion para conocer la linea en la que nos encontramos.
     *
     * @param registro linea de archivo a leer.
     * @return La seccion encontrada.
     */
    public String obtenerSeccion(String registro) {
        if (registro.split("\\|").length > 1) {
            return registro.split("\\|")[0];
        }
        return null;

    }

    /**
     * Metodo obtenerCodigoPostal funciona para traer el campo del cp.
     *
     * @param registro linea de archivo a leer.
     * @return El tipo de prosucto encontrado.
     */
    public String obtenerCodigoPostal(String registro) {
        if (registro.split("|").length > 12) {
            return registro.split("\\|")[7];
        }
        return null;
    }

    /**
     * Metodo obtenerPlaza funciona para traer el campo del cp.
     *
     * @param registro linea de archivo a leer.
     * @return El tipo de producto encontrado.
     */
    public String obtenerPlaza(String registro) {
        try {
            if (registro.split("\\|").length > 28) {
                return registro.split("\\|")[29] + registro.split("\\|")[3];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            log.info(Constantes.T_ERROR + e);
            return null;
        }
        return null;
    }

    /**
     * Metodo modificarPorCampo funciona para modificar por campo.
     *
     * @param linea linea de archivo a leer.
     * @return El tipo de producto encontrado.
     */
    public String modificarPorCampo(String linea) {
        try {
            val posicionCodigoOperacion = obtenerPosicionCampo(Constantes.CODIGO_DE_OPERACION);

            val valorCodigoOperacion = buscarValorCampo(posicionCodigoOperacion, linea);
            if (valorCodigoOperacion != null ) {
                val posicionobservaciones = obtenerPosicionCampo(Constantes.OBSERVACIONES_MOVIMIENTO);
                val valorObservacionesMovimiento = buscarValorCampo(posicionobservaciones, linea);

                if (valorCodigoOperacion.toString().equals("0287") && (valorObservacionesMovimiento != null && valorObservacionesMovimiento.length() >= 92)) {
                        final String lineaReemplazar = valorObservacionesMovimiento.substring(0, 89) +" " +  valorObservacionesMovimiento.substring(92, valorObservacionesMovimiento.length());
                        linea = linea.replace(valorObservacionesMovimiento, lineaReemplazar);
                }
                if (valorCodigoOperacion.toString().equals("0291") && (valorObservacionesMovimiento != null && valorObservacionesMovimiento.length() >= 92)) {
                        final String lineaReemplazar = valorObservacionesMovimiento.substring(0, 89) +" " +valorObservacionesMovimiento.substring(92, valorObservacionesMovimiento.length());
                        linea = linea.replace(valorObservacionesMovimiento, lineaReemplazar);
                }

            }

        } catch (ArrayIndexOutOfBoundsException e) {
        	//Se envia a log el error
            log.error(e.toString());
            log.error(e.getLocalizedMessage());
            log.error(e.getMessage());
            log.error(Constantes.T_ERROR + e);
            return null;
        }
        return linea;
    }

    /**
     * Metodo generarLlave para crear ids.
     *
     * @param mensajeria es el objeto que contiene los datos de generacion.
     * @return llave generada con transportista, rango, mensajeria.
     */
    public String generarLlave(Mensajeria mensajeria) {
        return mensajeria.getIdTransportistaMensajeria() + "-" + mensajeria.getRangoMensajeria() + "-" + mensajeria.getIdMensajeriaMensajeria();
    }

    /**
     * Metodo generarLlave para crear ids.
     *
     * @param mensajeria es el objeto que contiene los datos de generacion.
     * @param indice     para no perder registros.
     * @return llave generada con transportista, rango, mensajeria.
     */
    public String generarLlave(Mensajeria mensajeria, int indice) {
        return mensajeria.getIdTransportistaMensajeria() + "-" + mensajeria.getRangoMensajeria() + "-" + mensajeria.getIdMensajeriaMensajeria() + "-" + indice;
    }

    /**
     * Metodo incrementaMailCifrado
     *
     * @param linea donde buscar
     * @return contador
     */
    public int incrementaMailCifrado(String linea) {
        int incremento = 0;
        if (linea != null && linea.split("\\|").length >= 15) {
            final String mailCifrado = linea.split("\\|")[15];
            if (mailCifrado.trim().contentEquals("S") || mailCifrado.trim().contentEquals("1")) {
                incremento = 1;
            }
        }
        return incremento;
    }

    /**
     * Metodo crearLlave que genera la llave que se usara en el map para ordenar.
     *
     * @param codigoPostal es el filtro de busqueda de la l�nea en el archivo de mensajer�a.
     * @param indice       para no perder registros.
     * @return la llave transportista, rango, mensajeria.
     * @throws IOException en caso de no leer o escribir.
     */
    public String crearLlave(String codigoPostal, int indice) throws IOException, AplicationException {


        String[] linea = mensajeria.obtenerRegistroMensajeria(codigoPostal);

        if (linea != null && linea[0] != null) {

            this.correspondenciaMensajeria = linea[0].substring(0, 10).replace(" ", "");
            mensajeria.setIdTransportistaMensajeria(linea[0].substring(0, 3));
            mensajeria.setIdMensajeriaMensajeria(linea[0].substring(8, 10));
            mensajeria.setIdGrupoMensajeria(linea[0].substring(4, 7));
            mensajeria.setRangoMensajeria(linea[1]);
        }
        return generarLlave(mensajeria, indice);

    }

    /**
     * Metodo obtenerSucursal que obtiene la sucursal.
     *
     * @param plaza es el filtro de busqueda del id en el archivo de sucursal.
     * @return la sucursal.
     * @throws IOException en caso de no leer o escribir.
     */
    public String obtenerSucursal(String plaza) throws IOException {
        String[] linea = sucursal.obtenerRegistro(plaza);
        if (linea != null && linea[0] != null) {
            this.correspondenciaSucursal = linea[0].substring(0, 10).replace(" ", "");
            return linea[0].split(" ")[0];
        } else {
            return null;
        }

    }

    /**
     * Metodo obtenerPosicionCampo para obtener seccion y posicion del campo.
     *
     * @param dataField nombre
     * @return Arreglo de texto (0-seccion, 1-posicion)
     */
    private String[] obtenerPosicionCampo(String dataField) {
        String propiedad = environment.getProperty(dataField);
        if (propiedad != null) {
            return propiedad.split("_");
        } else {
            return new String[2];
        }
    }

    /**
     * Metodo buscarValorCampo para encontrar valores.
     *
     * @param xy seccion y campo.
     * @param texto son datos del cliente.
     * @return el valor del campo.
     */
    private String buscarValorCampo(String[] xy, String texto) {
        String[] secciones = null;
        // se dividen las secciones.
        secciones = texto.split("\n");

        //Recorremos cada seccion para buscar el valor del campo
        for (String seccion : secciones) {
            if (xy[0].equals(seccion.split("\\|")[0])) {
                int posicion = Integer.parseInt(xy[1]);
                if (seccion.split("\\|").length - 1 >= posicion)
                    return seccion.split("\\|")[posicion];
            }
        }
        return null;
    }


}
