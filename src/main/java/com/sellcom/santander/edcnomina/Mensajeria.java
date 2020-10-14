package com.sellcom.santander.edcnomina;

import com.sellcom.santander.edcnomina.reportes.cache.CacheMensajeria;
import com.sellcom.santander.exceptions.AplicationException;
import com.sellcom.santander.tools.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Clase Mensajeria para obtener registros divididos en campos del archivo de mensajeria
 * @author C�sar V�zquez.
 * @version 1.0
 * @since 14/11/2019
 *
 * Control de cambios: 5/12/2019 Se agrega ruta de carpeta para archivo de parametros.
 * Control de cambios: 18/12/2019 Se elimina ruta de carpeta para archivo de mensajeria.
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@EnableCaching
public class Mensajeria {
	//Variable de tipo String para representar un error al procesar rango de codigos postales
	public static final String ERROR_AL_PROCESAR_RANGO_DE_CODIGOS_POSTALES = "Error al procesar rango de codigos postales";
	
	//Se crea el objeto cacheMensajeria
	@Autowired
	private CacheMensajeria cacheMensajeria;
	//Variable de tipo String idMensajeria
	private String idMensajeriaMensajeria;
	//Variable de tipo String idGrupo
	private String idGrupoMensajeria;
	//Variable de tipo String idTransportista
	private String idTransportistaMensajeria;
	//Variable de tipo String rango
	private String rangoMensajeria;


	/**
	 * Metodo obtenerRegistro busca y devuelve la linea de archivo de mensajeria que corresponde al codigo postal.
	 * @param codigoPostal es el filtro por el cual se busca.
	 * @return linea de archivo donde se encuentra la coincidencia.
	 * @throws IOException en caso de problemas al leer.
	 */
	@SuppressWarnings("resource")
	public String[] obtenerRegistroMensajeria(String codigoPostal ) throws IOException, AplicationException {
		String[] lineaEncontrada = null;
		Map<String, List <String> > rangosCodigosPostales2 = cacheMensajeria.leerArchivoMensajeria();

		try {
			for (Map.Entry<String, List<String> > entry : rangosCodigosPostales2.entrySet()) {
				lineaEncontrada =  Utils.definirRangoCodigosPostalesUtils(entry.getKey(), codigoPostal, entry.getValue());
				if (lineaEncontrada !=null){
					break;
				}
			}
		} catch (Exception e) {
			throw new AplicationException(ERROR_AL_PROCESAR_RANGO_DE_CODIGOS_POSTALES,e);
		}
		return lineaEncontrada;

	}

	/**
	 * @return the idMensajeria
	 */
	public String getIdMensajeriaMensajeria() {
		return idMensajeriaMensajeria;
	}
	/**
	 * @param idMensajeria the idMensajeria to set
	 */
	public void setIdMensajeriaMensajeria(String idMensajeriaMensajeria) {
		this.idMensajeriaMensajeria = idMensajeriaMensajeria;
	}
	/**
	 * @return the idGrupo
	 */
	public String getIdGrupoMensajeria() {
		return idGrupoMensajeria;
	}
	/**
	 * @param idGrupo the idGrupo to set
	 */
	public void setIdGrupoMensajeria(String idGrupoMensajeria) {
		this.idGrupoMensajeria = idGrupoMensajeria;
	}
	/**
	 * @return the idTransportista
	 */
	public String getIdTransportistaMensajeria() {
		return idTransportistaMensajeria;
	}
	/**
	 * @param idTransportista the idTransportista to set
	 */
	public void setIdTransportistaMensajeria(String idTransportistaMensajeria) {
		this.idTransportistaMensajeria = idTransportistaMensajeria;
	}
	/**
	 * @return the rango
	 */
	public String getRangoMensajeria() {
		return rangoMensajeria;
	}
	/**
	 * @param rango the rango to set
	 */
	public void setRangoMensajeria(String rangoMensajeria) {
		this.rangoMensajeria = rangoMensajeria;
	}


	
}
