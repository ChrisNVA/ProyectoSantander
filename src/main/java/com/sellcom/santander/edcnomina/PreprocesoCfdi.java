package com.sellcom.santander.edcnomina;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import static com.sellcom.santander.edcnomina.Constantes.*;


/* @author Aaron Acosta.
 * @version 1.0.
 * @since 10/12/2019
 * Sellcom Solutions
 * Clase: PreprocesoCfdi.java
 * Descripción: Divide el archivo de referencia de CFDI's en ingresos y egresos.
 * Control de cambios: n/a.
 */
@Slf4j
@Service
public class PreprocesoCfdi {
	//Se crea el objeto environment
    @Autowired
    private Environment environment;
    //Constantes para los campos a leer del archivo.
    //Variable privada de tipo String llamada tipoDocumento
    private static String tipoDocumento=null;
    //Variable privada de tipo String llamada cliente 
    private static String[] cliente= new String[14];
    //Contador de CFDI's.
    private static int contador=0;
    /**
	 * Metodo proceso.
	 * @param fileCFDI archivo CFDI
	 * @throws IOException en caso de problemas al leer.
	 */
    public  void proceso (String fileCFDI) throws IOException{
        log.info(" Procesando archivo CDFI: {}",fileCFDI);

        File entrada = new File( fileCFDI);
        final String salidaArchivo ="/"+ entrada.getName().replaceFirst("[.][^.]+$", "");

        //Se definen los archivos de Salida. "I" para Ingresos, "E" para egresos.
        File salida1 = new File(environment.getProperty(CARPETA_SALIDA) + SEPARADOR + salidaArchivo+".I.TXT");
        File salida2 = new File(environment.getProperty(CARPETA_SALIDA) + SEPARADOR + salidaArchivo+".E.TXT");
        
        // BufferedReader object for file1.txt.
        try(
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(fileCFDI),StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(inputStreamReader)) {
            String cadena=null;
            // Recorremos el archivo de entrada y lo dividimos en 2 salidas, dependiendo si el CFDI es Ingreso o Egreso.
            while((cadena = br.readLine())!=null) {
                arreglo(cadena, salida1,salida2, fileCFDI);
            }
            // El try cierra automaticamente los recursos.
        }
    }
    
    /**
  	 * Metodo creaArchivosCFDI.
  	 * @throws IOException en caso de problemas al crear el archivo.
  	 */
    public void creaArchivosCFDI() {
    	try {
    	File salida1 = new File(environment.getProperty(CARPETA_SALIDA) + SEPARADOR +"EDCINTEGRAL_CFDI.I.TXT");
        File salida2 = new File(environment.getProperty(CARPETA_SALIDA) + SEPARADOR +"EDCINTEGRAL_CFDI.E.TXT");
    	 // Si el archivo no existe es creado
        boolean valor1 = salida1.exists()?false:salida1.createNewFile();
        boolean valor2 = salida2.exists()?false:salida2.createNewFile();
        
			if(valor1) 
				log.info("El archivo EDCINTEGRAL_CFDI.I fue creado exitosamente");
			if(valor2) 
				log.info("El archivo EDCINTEGRAL_CFDI.E fue creado exitosamente");
			
        } catch (IOException e) {
        	//Se envia a log el error
            log.error(e.toString());
            log.error( e.getLocalizedMessage());
            log.info(Constantes.T_ERROR+e.getMessage());
		}
        
    }
    /**
	 * Metodo estatico arreglo.
	 * @param cadena linea del archivo de entrada.
	 * @param salida1 archivo de salida numero 1.
	 * @param salida2 archivo de salida numero 2.
	 * @param fileCFDI Nombre y ruta del archivo de entrada.
	 * @throws IOException en caso de problemas al leer.
	 */
    private static void arreglo(String cadena, File salida1, File salida2, String fileCFDI) throws IOException{
        //Este metodo divide el archivo de entrada en 2.
        try(BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream(salida1, true), StandardCharsets.UTF_8));
            BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream(salida2, true), StandardCharsets.UTF_8));
            BufferedReader br = new BufferedReader(new FileReader( fileCFDI ))) {
            String indicadorRegistro=null;
            String[] line = cadena.split("\\|");
            //Recorremos el arreglo y guardamos la linea en su posicion
            cliente[contador]=cadena;
            indicadorRegistro=line[0];
            //Buscamos el registro que se llama "TIPO_DOCUMENTO" especidicamente.
            if ("TIPO_DOCUMENTO".equals(indicadorRegistro)) {
                tipoDocumento=line[1];
            }
            //Si el campo 1 de dicho registro tiene una "I" guardamos en el archivo 1.
            if ("I".equals(tipoDocumento)) {
                int len = cliente.length;
                //Recorremos el arreglo con los valores guardados.
                for (int i = 0; i < len; i++) {
                    bw1.write(cliente[i] + "\n");
                }
                //Escribimos en el archivo.
                bw1.flush();
                //Reseteamos valores para el siguiente CFDI.
                tipoDocumento=null;
                contador=-1;
            }
            //Si el campo 1 de dicho registro tiene una "E" guardamos en el archivo 2.
            if ("E".equals(tipoDocumento)) {
                int len = cliente.length;
                //Recorremos el arreglo con los valores guardados y escribimos.
                for (int i = 0; i < len; i++) {
                    bw2.write(cliente[i] + "\n");
                }
                //Escribimos en el archivo.
                bw2.flush();
                //Reseteamos valores para el siguiente CFDI.
                tipoDocumento=null;
                contador=-1;
            }
            //Seguimos al siguiente CFDI en posicion 0 del arreglo.
            contador++;
        }
    }
    //El try/catch de arriba cierra automáticamente los archivos.
}