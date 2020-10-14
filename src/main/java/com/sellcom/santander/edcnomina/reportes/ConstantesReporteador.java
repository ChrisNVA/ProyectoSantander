package com.sellcom.santander.edcnomina.reportes;

/**
 * Se genera clase para constantes del codigo en reportes.
 * @author Cesar Vazquez.
 *
 */
public final class ConstantesReporteador {

	//Encabezados de columnas
	public static final String COL_TR = "TR";	//transportista
	public static final String COL_MEN = "MEN";  //mensajeria
	public static final String COL_INICIO = "INICIO"; //folio inicial
	public static final String COL_FIN = "FIN";   //folio final
	public static final String COL_SOBRES = "SOBRES"; // Columna SOBRES
	public static final String COL_PAGS = "PAGS"; //Paginas
	public static final String COL_INS_1 = "INS 1"; //Columna INS 1 
	public static final String COL_INS_2 = "INS 2"; //Columna INS 2
	public static final String COL_INS_3 = "INS 3"; //Columna INS 3
	public static final String COL_CAR_1 = "CAR 1"; //Columna CAR 1
	public static final String COL_CAR_2 = "CAR 2"; //Columna CAR 2
	public static final String COL_CAR_3 = "CAR 3"; //Columna CAR 3
	public static final String COL_CAR_4 = "CAR 4"; //Columna CAR 4
	public static final String COL_CAR_5 = "CAR 5"; //Columna CAR 5
	public static final String COL_CEN_1 = "CEN 1"; //Columna CEN 1
	public static final String COL_CEN_2 = "CEN 2"; //Columna CEN 2
	public static final String COL_CEN_3 = "CEN 3"; //Columna CEN 3
	public static final String COL_CEN_4 = "CEN 4"; //Columna CEN 4
	public static final String COL_CEN_5 = "CEN 5"; //Columna CEN 5
	public static final String COL_ALC   = "ALC"  ; //Columna ALC
	public static final String COL_PAGD  = "PAGD" ; //Columna PAGD
	
	//numeros
	public static final String UNO = "01"; //cero uno 01
	public static final String DOS = "02";  //cero dos 02
	public static final String TRES = "03"; //cero tres 03
	public static final String CUATRO = "04"; //cero cuatro 04
	public static final String CINCO = "05"; //cero cinco 05
	public static final String S_UNO = "S01"; //ese cero uno S01
	
	//carpetas
	public static final String CARPETA_SALIDA_REPORTES = "carpeta_salida_reportes"; //Carpeta de salida de reportes
	public static final String ARCHIVO_MENSAJERIA = "archivo_mensajeria"; // carpeta archivo de mensajeria 
	public static final String ARCHIVO_SUCURSALES = "archivo_sucursales"; // carpeta archivo de sucrusales
	public static final String ARCHIVO_SEGMENTOS = "archivo_segmentos"; // carpeta archivos segmentos
	public static final String ARCHIVO_PREVIOS = "archivo_previos"; // carpeta archivos previos
	public static final String SEPARADOR = "/";
	
	//caracteres para campos en desglose xml
	public static final String T_DATA_FIELD = "\t Data field: ";  //texto en busqueda de campo xml
	public static final String T_DATA_TYPE = "Data type: ";	//texto en busqueda de tipo de dato xml
	public static final String T_T_DATA = "\t\t Data: ";	// texto de busqueda de dato en xml
	public static final String ERROR_OBJETO_PARAMETER = "Error al crear el objeto parameter?";	// error en parametros
	public static final String OBJETO_PARAMETER_NULO = "El objeto parameter? es nulo";  // error en objeto
	public static final String ITEM_SETTINGS_VALUE = "item_settings value: ";	// valor de los elementos
	
//	//archivos
	public static final String ARCHIVO_01 = "archivo_01"; // archivo_01
	public static final String ARCHIVO_02 = "archivo_02"; // archivo_02
	public static final String ARCHIVO_03 = "archivo_03"; // archivo_03
	public static final String ARCHIVO_04 = "archivo_04"; // archivo_04
	public static final String ARCHIVO_05 = "archivo_05"; // archivo_05
	public static final String ARCHIVO_06 = "archivo_06"; // archivo_06
	public static final String ARCHIVO_07 = "archivo_07"; // archivo_07
	public static final String ARCHIVO_08 = "archivo_08"; // archivo_08
	public static final String ARCHIVO_09 = "archivo_09"; // archivo_09
	
	//Se agrega ruta de archivo de parametros.
	public static final String RUTA_PARAMETROS = ".";
	//Archivo properties para los parametros
	public static final String ARCHIVO_PARAMETROS = "formateo.properties";
	//Ceros para Para reportes
	public static final String SEIS_CEROS = "000000";
	//Tabulador
	public static final String TABULADOR = " \t";

	private ConstantesReporteador() {
		//Constructor de clase
	}
}
