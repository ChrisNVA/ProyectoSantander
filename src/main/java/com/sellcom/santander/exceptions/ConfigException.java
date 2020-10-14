package com.sellcom.santander.exceptions;

import com.sellcom.santander.tools.Utils;

/**
 * Clase ConfigException para manejo de excepciones en parseo xml.
 * @author Cesar Vazquez.
 * @version 1.0
 * @since 11/11/2019
 *
 */
public class ConfigException extends Exception {

	private static final long serialVersionUID = 8471971259547587150L;

	/**
	 * Constructor
	 * @param message es el mensaje.
	 */
	public ConfigException(final String message) {
		super(message);
	}

	/**
	 * Constructor
	 * @param message es el mensaje.
	 * @param parameter es el parametro.
	 */
	public ConfigException(final String message, final String parameter) {
		super(formatMessage(message, parameter));
	}

	/**
	 * Constructor 
	 * @param message es el mensaje.
	 * @param e es la excepcion
	 */
	public ConfigException(final String message, final Throwable e) {
		super(message, e);
	}
	
	/**
	 * Constructor 
	 * @param e es la excepcion
	 */
	public ConfigException(final Throwable e) {
		super(e);
	}

	/**
	 * Constructor 
	 * @param message es la excepcion
	 * @param classe sirve para algo.
	 */
	public ConfigException(final String message, final Class<?> classe) {
		super(formatMessage(message, classe.getSimpleName()));
	}
	
	/**
	 * Metodo formatMessage sirve para dar una salida de texto valida
	 * @param message es el mensaje al que se quiere dar formato.
	 * @param parameter es necesario para el mismo mensaje.
	 * @return un texto formateado.
	 */
	private static String formatMessage(final String message, final String parameter) {
		return Utils.replace(message, "parameter?", parameter);
	}
}