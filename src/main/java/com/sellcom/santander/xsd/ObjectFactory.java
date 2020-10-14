//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2019.11.06 a las 06:13:05 PM CST 
//


package com.sellcom.santander.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.com.santander.xsd package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private static final QName _ProductSettingsMessageItemSettingsField_QNAME = new QName("", "field");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.com.santander.xsd
     * 
     */
    public ObjectFactory() {
    	// Do nothing because of X and Y.
    }

    /**
     * Create an instance of {@link ProductSettings }
     * 
     */
    public ProductSettings createProductSettings() {
        return new ProductSettings();
    }


    /**
     * Create an instance of {@link ProductSettings.Previo }
     * 
     */
    public ProductSettings.Previo createProductSettingsPrevio() {
        return new ProductSettings.Previo();
    }

    /**
     * Create an instance of {@link ProductSettings.Previo.ItemSettings }
     * 
     */
    public ProductSettings.Previo.ItemSettings createProductSettingsPrevioItemSettings() {
        return new ProductSettings.Previo.ItemSettings();
    }

    /**
     * Create an instance of {@link ProductSettings.Segment }
     * 
     */
    public ProductSettings.Segment createProductSettingsSegment() {
        return new ProductSettings.Segment();
    }

    /**
     * Create an instance of {@link ProductSettings.Segment.ItemSettings }
     * 
     */
    public ProductSettings.Segment.ItemSettings createProductSettingsSegmentItemSettings() {
        return new ProductSettings.Segment.ItemSettings();
    }


    /**
     * Create an instance of {@link ProductSettings.Previo.ItemSettings.Field }
     * 
     */
    public ProductSettings.Previo.ItemSettings.Field createProductSettingsPrevioItemSettingsField() {
        return new ProductSettings.Previo.ItemSettings.Field();
    }

    /**
     * Create an instance of {@link ProductSettings.Segment.ItemSettings.Field }
     * 
     */
    public ProductSettings.Segment.ItemSettings.Field createProductSettingsSegmentItemSettingsField() {
        return new ProductSettings.Segment.ItemSettings.Field();
    }


    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProductSettings.Previo.ItemSettings.Field }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ProductSettings.Previo.ItemSettings.Field }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "field", scope = ProductSettings.Previo.ItemSettings.class)
    public JAXBElement<ProductSettings.Previo.ItemSettings.Field> createProductSettingsPrevioItemSettingsField(ProductSettings.Previo.ItemSettings.Field value) {
        return new JAXBElement<>(_ProductSettingsMessageItemSettingsField_QNAME, ProductSettings.Previo.ItemSettings.Field.class, ProductSettings.Previo.ItemSettings.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProductSettings.Segment.ItemSettings.Field }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ProductSettings.Segment.ItemSettings.Field }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "field", scope = ProductSettings.Segment.ItemSettings.class)
    public JAXBElement<ProductSettings.Segment.ItemSettings.Field> createProductSettingsSegmentItemSettingsField(ProductSettings.Segment.ItemSettings.Field value) {
        return new JAXBElement<>(_ProductSettingsMessageItemSettingsField_QNAME, ProductSettings.Segment.ItemSettings.Field.class, ProductSettings.Segment.ItemSettings.class, value);
    }

}
