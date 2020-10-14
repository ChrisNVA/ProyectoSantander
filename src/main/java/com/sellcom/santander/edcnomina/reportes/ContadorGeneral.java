package com.sellcom.santander.edcnomina.reportes;


import org.springframework.stereotype.Component;

@Component
public class ContadorGeneral {
	//variable privada de tipo Integer llamada contadorGeneral
    private Integer contGeneral = 0;
    /**
	 * Metodo aumentar para incrementar el contador general.
	 */
    public void aumentar() {
    	contGeneral = contGeneral + 1;
        if (contGeneral > 9999999) {
        	contGeneral = 0;
        }
    }

    /**
	 * Metodo getContadorGeneral para obtener el valor del contador general.
	 * @return el valor del contador con formato.
	 */
    public String getContGeneral() {
        return String.format("%07d", contGeneral);
    }
}
