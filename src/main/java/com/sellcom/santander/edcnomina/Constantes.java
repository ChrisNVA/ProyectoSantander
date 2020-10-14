package com.sellcom.santander.edcnomina;

/**
 * Clase Constantes para evitar codigo duro.
 * @author Cesar Vazquez.
 * @version 1.0.
 * @since 29/10/2019.
 * 
 * Control de cambios: 18/12/2019 Se elimina constante  de carpeta de entrada.
 */
public final class Constantes {
	
	//Tipos de producto
	//santander plus
	public static final String TP_SANTANDER_PLUS = "SP";
	//vip
	public static final String TP_VIP = "VP";
	//elite
	public static final String TP_ELITE = "EL";
	//be
	public static final String TP_BEI = "BE";
	// tipo producto 01
	public static final String TP_01 = "01";
	// tipo producto 02
	public static final String TP_02 = "02";
	// tipo producto 03
	public static final String TP_03 = "03";
	// tipo producto 04
	public static final String TP_04 = "04";
	// tipo producto 05
	public static final String TP_05 = "05";
	// tipo producto 06
	public static final String TP_06 = "06";
	// tipo producto 07
	public static final String TP_07 = "07";
	// tipo producto 08
	public static final String TP_08 = "08";
	// tipo producto 09
	public static final String TP_09 = "09";	
	
	// tipos de producto con letra
	// tipo producto A
	public static final String TP_A = "A";
	// tipo producto BG
	public static final String TP_BG = "BG";
	// tipo producto VP
	public static final String TP_VP = "VP";
	// tipo producto SP
	public static final String TP_SP = "SP";
	// tipo producto EL
	public static final String TP_EL = "EL";
	// tipo producto B1
	public static final String TP_B1 = "B1";	

	
	//Carpetas y archivos
	// archivo principal de propiedades.
	public static final String ARCHIVO_PARAMETROS = "formateo.properties";
	// carpeta de salida de los archivos.
	public static final String CARPETA_SALIDA = "carpeta_salida";
	// archivo de entrada
	public static final String ARCHIVO_ENTRADA = "archivo_entrada";
	//archivo de salida
	public static final String ARCHIVO_SALIDA = "archivo_salida";
	//separador de carpetas
	public static final String SEPARADOR = "/";
	// carpeta de origen de los archivos
	public static final String CARPETA_ORIGEN = "carpeta_origen";


	public static final String TEMPORAL = "tmp";
	
	//Nombres de archivos
	public static final String ARCHIVO_01 = "archivo_01";
	public static final String ARCHIVO_02 = "archivo_02";
	public static final String ARCHIVO_03 = "archivo_03";
	public static final String ARCHIVO_04 = "archivo_04";
	public static final String ARCHIVO_05 = "archivo_05";
	public static final String ARCHIVO_06 = "archivo_06";
	public static final String ARCHIVO_07 = "archivo_07";
	public static final String ARCHIVO_08 = "archivo_08";
	public static final String ARCHIVO_09 = "archivo_09";
	public static final String ARCHIVO_10 = "archivo_10";
	//archivo de configuracion de mensajeria
	public static final String ARCHIVO_MENSAJERIA = "archivo_mensajeria";
	//archivo de configuracion de sucursales
	public static final String ARCHIVO_SUCURSALES = "archivo_sucursales";
	//archivo de configuracion de segmentos
	public static final String ARCHIVO_SEGMENTOS = "archivo_segmentos";
	//archivo de configuracion de previos
	public static final String ARCHIVO_PREVIOS = "archivo_previos";
	//archivo de configuracion de inhibidos
	public static final String ARCHIVO_INHIBIDOS = "archivo_inhibidos";
	//archivo de configuracion de mensajes de salida en log.
	public static final String ARCHIVO_MENSAJES = "archivo_mensajes";
	
	//archivo cfdi
	public static final String ARCHIVO_CFDI = "archivo_cfdi";
	
	//caracteres para campos en desglose xml
	public static final String T_DATA_FIELD = "\t Data field: ";
	public static final String T_DATA_TYPE = "Data type: ";
	public static final String T_T_DATA = "\t\t Data: ";
	public static final String ERROR_OBJETO_PARAMETER = "Error al crear el objeto parameter?";
	public static final String OBJETO_PARAMETER_NULO = "El objeto parameter? es nulo";
	public static final String ITEM_SETTINGS_VALUE = "item_settings value: ";
	//Para numeros 
	public static final String UNO = "01";
	public static final String DOS = "02";
	public static final Object TRES = "03";
	public static final Object CUATRO = "04";
	public static final Object CINCO = "05";
	
	//Se agrega ruta de archivo de parametros.
	public static final String RUTA_PARAMETROS = ".";
	
	//Seccion Uno

	public static final String CODIGO_DE_CLIENTE = "01_01";
	public static final String RFC_CLIENTE = "01_02";
	public static final String NUMERO_DE_SUCURSAL_TITULAR = "01_03";
	public static final String NOMBRE_DE_SUCURSAL_TITULAR ="01_04";
	public static final String ENTIDAD = "01_05";
	public static final String CONTRATO_NUMERO_DE_CUENTA = "01_06";
	public static final String CUENTA_CLABE = "01_07";
	public static final String CUENTA_EJE ="01_08";
	public static final String ESTATUS_CONTRATO = "01_09";
	public static final String FECHA_APERTURA_CONTRATO = "01_10";
	public static final String FECHA_CANCELACION_CONTRATO ="01_11";
	public static final String FECHA_ULTIMA_OPERACION ="01_12";
	public static final String TIPO_DE_PRODUCTO = "01_13";
	public static final String TIPO_DE_CAMBIO = "01_14";
	public static final String DIVISA_01 = "01_15";
	public static final String PRODUCTO_01 = "01_16";
	public static final String SUBPRODUCTO_01 = "01_17";
	public static final String PLAN_DE_COMISIONES = "01_18";
	public static final String NOMBRE_PRODUCTO ="01_19";
	public static final String ULTIMO_EXTRACTO = "01_20";
	public static final String MES_EXTRACTO = "01_21";
	public static final String SALDO_PROMEDIO = "01_22";
	public static final String SALDO_MINIMO_REQUERIDO = "01_23";
	public static final String SALDO_INICIAL = "01_24";
	public static final String SALDO_ACTUAL = "01_25";
	public static final String TIN = "01_26";
	public static final String CURP = "01_27";
	public static final String CUENTA_CLAVE_VIRTUAL = "01_28";

	//Seccion Dos
	public static final String NOMBRE_DEL_CLIENTE = "02_01";
	public static final String DOMICILIO_DEL_CLIENTE_CALLE = "02_07";
	public static final String DOMICILIO_DEL_CLIENTE_COLONIA = "02_07";
	public static final String DOMICILIO_DEL_CLIENTE_CIUDAD = "02_04";
	public static final String DOMICILIO_DEL_CLIENTE_ESTADO = "02_05";
	public static final String DOMICILIO_DEL_CLIENTE_MUNICIPIO = "02_06";
	public static final String CODIGO_POSTAL = "02_07";
	public static final String SEGMENTO = "02_08";
	public static final String FECHA_DE_NACIMIENTO = "02_09";
	public static final String SEXO = "02_10";
	public static final String PERSONALIDAD = "02_11";
	public static final String CODIGO_DE_CAMPANA = "02_12";
	public static final String FECHA_ALTA_CLIENTE = "02_13";
	public static final String INDICADOR_PAPERLESS = "02_14";
	public static final String INDICADOR_MAILCIFRADO = "02_15";
	public static final String CORREO_ELECTRONICO = "02_16";
	public static final String PAIS_DE_RESIDENCIA = "02_17";
	public static final String INSTRUCCION_CORRESPONDENCIA = "02_18";
	public static final String INSTRUCCION_CORRESPONDENCIA_II = "02_19";
	public static final String FECHA_PAPERLESS = "02_20";
	
	//Seccion Tres
	public static final String FECHA_DE_MOVIMIENTO = "03_01";
	public static final String FOLIO_DE_MOVIMIENTO = "03_02";
	public static final String CODIGO_DE_OPERACION = "CODIGO_DE_OPERACION";
	public static final String TIPO_DE_MOVIMIENTO = "TIPO_DE_MOVIMIENTO";
	public static final String IMPORTE_DE_MOVIMIENTO = "03_05";
	public static final String DESCRIPCION_DEL_MOVIMIENTO = "03_06";
	public static final String OBSERVACIONES_MOVIMIENTO = "OBSERVACIONES_MOVIMIENTO";
	public static final String SECUENCIA_DE_RENOVACION = "03_08";
	public static final String PRODUCTO_03 = "03_09";
	public static final String SUBPRODUCTO_03 = "03_10";
	public static final String PLAZO = "03_11";
	public static final String DIVISA_03 = "03_12";
	public static final String TIPO_INTERES = "03_13";
	public static final String INDICA_SI_CANCELA_AL_VENCIMIENTO = "03_14";
	public static final String INDICA_SI_CAPITALIZA_INTERESES_EN_RENOV = "03_15";
	public static final String INDICA_SI_CAPITALIZA_REAJUSTES_EN_RENOV = "03_16";
	public static final String INDICADOR_FISCAL = "03_17";
	
	//Seccion Cuatro
	public static final String FECHA_DEL_IMPAGO = "04_01";
	public static final String SECUENCIA_DEL_IMPAGO = "04_02";
	public static final String CONCEPTO = "04_03";
	public static final String PRIORIDAD = "04_04";
	public static final String IMPORTE = "04_05";
	public static final String IMPORTE_TOTAL_COBRADO = "04_06";
	public static final String ESTADO_DEL_IMPAGO = "04_07";
	
	//Seccion Cinco
	public static final String INTERESES_BRUTOS = "05_01";
	public static final String COMISIONES_COBRADAS = "05_02";
	public static final String GAT_NOMINAL = "05_03";
	public static final String GAT_REAL = "05_04";
	public static final String TASA_BRUTA_INTERESES = "05_05";
	public static final String DEPOSITOS = "05_06";
	public static final String RETIROS = "05_07";
	public static final String DIAS_DEL_PERIODO = "05_08";
	public static final String INTERESES_AL_VENCIMIENTO = "05_09";
	public static final String TASA_ISR = "05_10";
	public static final String ISR_RETENIDO = "05_11";
	
	//Otros
	// constante para identificar la retencion
	public static final String TIPO_RETENCION = "R";
	// extension para archivos destino
	public static final String EXTENSION_RDY = ".rdy";
	// No se pudo parsear
	public static final String NO_SE_PUDO_PARSEAR = "No se pudo parsear el archivo xml";
	//Error parseando
	public static final String ERROR_PARSEANDO = "Ocurrio un error parseando el archivo: ";
	//encontrado
	public static final String ENCONTRADO = "ENCONTRADO";
	//instruccion_correspondencia
	public static final String CAMPO_INSTRUCCION_CORRESPONDENCIA = "INSTRUCCION_CORRESPONDENCIA";
	//codigo_cliente
	public static final String CAMPO_CODIGO_DE_CLIENTE = "CODIGO_DE_CLIENTE";
		
	//no encontrado
	public static final String NO_ENCONTRADO = "NO_ENCONTRADO";
	//prioridad
	public static final String T_PRIORIDAD = "prioridad: ";
	//"TipoSegmento :: "
	public static final String T_TIPO_SEGMENTO = "TipoSegmento :: ";
	// Errores
	public static final String T_ERROR = "Error: ";

	public static final String BUC = "BUC";

	public static final String NUMERO_DE_CUENTA = "NUMERO_DE_CUENTA";

	public static final String FECHA_ARGUMENT = "Fecha Argumento : ";


	public static final String FREE_MEMORY = "Free Memory   : ";
	public static final String CODIFICACION = "Codificacion: ";
	public static final long  MB = (1024L * 1024L);
	public static final String SEGUNDOS_EN_PROCESAR = "Segundos en procesar aplicacion: ";
	public static final String USED_MEMORY = "Used Memory   :  ";
	public static final String TOTAL_MEMORY = "Total Memory  : ";
	public static final String MAX_MEMORY = "Max Memory    : ";
	public static final String FILE_ENCODING = "file.encoding";
	public static final String UTF_8 = "UTF-8";
	public static final String MB1 = " MB";
	public static final String DIRECTORIO_DEL_APLICATIVO = "Directorio del aplicativo: ";
	public static final String SO = "SO: ";
	public static final String OS_NAME = "os.name";
	public static final String OS_VERSION = "os.version";
	public static final String JAVA_VERSION = "java.version";
	public static final String USER_DIR = "user.dir";
	public static final String GUION = "-";
	
	public static final String OMITIR_PREVIOS="nomenclatura.previos";
	
	private Constantes() {
		//Constructor de clase
	}

	
}
