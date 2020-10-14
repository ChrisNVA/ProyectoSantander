package com.sellcom.santander.tools;

import com.sellcom.santander.edcnomina.Constantes;
import com.sellcom.santander.exceptions.ConfigException;
import com.sellcom.santander.xsd.ProductSettings;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * Clase usada con los archivos xsd para el parseo de documentos xml.
 * @author C�sar V�zquez.
 * @since 11/11/2019.
 * @version 1.0.
 */
@Slf4j
public class XMLParser {

	private static JAXBContext jaxbContext;

	static {

		try {
			jaxbContext = JAXBContext.newInstance(ProductSettings.class);
		} catch (JAXBException e) {
			log.error("Ocurrio un error al crear la instancia para el parseo del XML");
			jaxbContext = null;
			log.error(e.getMessage());
		}
	}

	/**
	 * Constructor de clase.
	 */
	private XMLParser() {
	}
	
	/**
	 * Metodo unmarshalFileToObject
	 * @param xml es el archivo.
	 * @return objeto con estructura.
	 * @throws ConfigException en caso de error.
	 */
	public static ProductSettings unmarshalFileToObject(final InputStream xml) throws ConfigException {
		if (jaxbContext == null) {
			throw new ConfigException(Constantes.OBJETO_PARAMETER_NULO, JAXBContext.class);
		} else {
			try {
				final Unmarshaller u = jaxbContext.createUnmarshaller();
				return (ProductSettings) u.unmarshal(xml);

			} catch (JAXBException e) {
				throw new ConfigException(Constantes.ERROR_OBJETO_PARAMETER, Unmarshaller.class);
			}
		}
	}

	/**
	 * Metodo unmarshalFileToObject para descomponer en la estructura.
	 * @param xml el texto.
	 * @return objeto con la estructura.
	 * @throws ConfigException en caso de error.
	 */
	public static ProductSettings unmarshalFileToObject(final File xml) throws ConfigException {

		if (jaxbContext == null) {
			throw new ConfigException(Constantes.OBJETO_PARAMETER_NULO, JAXBContext.class);
		} else {
			try {
				final Unmarshaller u = jaxbContext.createUnmarshaller();
				return (ProductSettings) u.unmarshal(xml);
			} catch (JAXBException e) {
				throw new ConfigException(Constantes.ERROR_OBJETO_PARAMETER, Unmarshaller.class);
			}
		}
	}

	/**
	 * Metodo marshalFileToString para descomponer el xml.
	 * @param xml el texto a descomponer.
	 * @return objeto ya en estructura.
	 * @throws ConfigException en caso de error alguno.
	 */
	public static String marshalFileToString(final File xml) throws ConfigException {

		if (jaxbContext == null) {
			throw new ConfigException(Constantes.OBJETO_PARAMETER_NULO, JAXBContext.class);
		} else {
			final StringWriter stringWriter = new StringWriter();

			try {
				final Unmarshaller u = jaxbContext.createUnmarshaller();
				final ProductSettings productSettings = (ProductSettings) u.unmarshal(xml);

				final Marshaller m = jaxbContext.createMarshaller();
				m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
				m.marshal(productSettings, stringWriter);
				return stringWriter.toString();

			} catch (JAXBException e) {
				throw new ConfigException(Constantes.ERROR_OBJETO_PARAMETER, Unmarshaller.class);
			} finally {
				try {
					stringWriter.close();
				} catch (IOException e) {
					log.error(e.getMessage());
				}
			}
		}
	}
}
