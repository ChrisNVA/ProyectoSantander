//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2019.11.06 a las 06:13:05 PM CST 
//


package com.sellcom.santander.xsd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="segment"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="item_settings" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="field" maxOccurs="unbounded" minOccurs="0"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                                     &lt;/sequence&gt;
 *                                     &lt;attribute name="data_field" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                                     &lt;attribute name="data_type" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                                     &lt;attribute name="include" type="{http://www.w3.org/2001/XMLSchema}byte" /&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                           &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}short" /&gt;
 *                           &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}byte" /&gt;
 *                           &lt;attribute name="prodlibe" type="{http://www.w3.org/2001/XMLSchema}byte" /&gt;
 *                           &lt;attribute name="uselogistic" type="{http://www.w3.org/2001/XMLSchema}byte" /&gt;
 *                           &lt;attribute name="priority" type="{http://www.w3.org/2001/XMLSchema}byte" /&gt;
 *                           &lt;attribute name="logic" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                           &lt;attribute name="start_date" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                           &lt;attribute name="end_date" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="previo"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="item_settings" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="field"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                                     &lt;/sequence&gt;
 *                                     &lt;attribute name="data_field" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                                     &lt;attribute name="data_type" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                           &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}short" /&gt;
 *                           &lt;attribute name="start_date" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                           &lt;attribute name="end_date" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="inhibir"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="item_settings"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="field"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                                     &lt;/sequence&gt;
 *                                     &lt;attribute name="data_field" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                                     &lt;attribute name="data_type" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                           &lt;attribute name="start_date" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                           &lt;attribute name="end_date" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="message"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="item_settings" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="field"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                                     &lt;/sequence&gt;
 *                                     &lt;attribute name="data_field" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                                     &lt;attribute name="data_type" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                                     &lt;attribute name="include" type="{http://www.w3.org/2001/XMLSchema}byte" /&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                           &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}byte" /&gt;
 *                           &lt;attribute name="position" type="{http://www.w3.org/2001/XMLSchema}byte" /&gt;
 *                           &lt;attribute name="flag" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                           &lt;attribute name="logic" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                           &lt;attribute name="start_date" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                           &lt;attribute name="end_date" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "segment",
    "previo"/*,
    "inhibir",
    "message"*/
})
@XmlRootElement(name = "product_settings")
public class ProductSettings {

    @XmlElement(required = true)
    protected ProductSettings.Segment segment;
    @XmlElement(required = true)
    protected ProductSettings.Previo previo;


    /**
     * Obtiene el valor de la propiedad segment.
     * 
     * @return
     *     possible object is
     *     {@link ProductSettings.Segment }
     *     
     */
    public ProductSettings.Segment getSegment() {
        return segment;
    }

    /**
     * Define el valor de la propiedad segment.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductSettings.Segment }
     *     
     */
    public void setSegment(ProductSettings.Segment value) {
        this.segment = value;
    }

    /**
     * Obtiene el valor de la propiedad previo.
     * 
     * @return
     *     possible object is
     *     {@link ProductSettings.Previo }
     *     
     */
    public ProductSettings.Previo getPrevio() {
        return previo;
    }

    /**
     * Define el valor de la propiedad previo.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductSettings.Previo }
     *     
     */
    public void setPrevio(ProductSettings.Previo value) {
        this.previo = value;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="item_settings"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="field"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
     *                           &lt;/sequence&gt;
     *                           &lt;attribute name="data_field" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *                           &lt;attribute name="data_type" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *                 &lt;attribute name="start_date" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *                 &lt;attribute name="end_date" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "itemSettings"
    })
    public static class Inhibir {

        @XmlElement(name = "item_settings", required = true)
        protected ProductSettings.Inhibir.ItemSettings itemSettings;

        /**
         * Obtiene el valor de la propiedad itemSettings.
         * 
         * @return
         *     possible object is
         *     {@link ProductSettings.Inhibir.ItemSettings }
         *     
         */
        public ProductSettings.Inhibir.ItemSettings getItemSettings() {
            return itemSettings;
        }

        /**
         * Define el valor de la propiedad itemSettings.
         * 
         * @param value
         *     allowed object is
         *     {@link ProductSettings.Inhibir.ItemSettings }
         *     
         */
        public void setItemSettings(ProductSettings.Inhibir.ItemSettings value) {
            this.itemSettings = value;
        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="field"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
         *                 &lt;/sequence&gt;
         *                 &lt;attribute name="data_field" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *                 &lt;attribute name="data_type" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *       &lt;/sequence&gt;
         *       &lt;attribute name="start_date" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *       &lt;attribute name="end_date" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "field"
        })
        public static class ItemSettings {

            @XmlElement(required = true)
            protected ProductSettings.Inhibir.ItemSettings.Field field;
            @XmlAttribute(name = "start_date")
            protected String startDate;
            @XmlAttribute(name = "end_date")
            protected String endDate;

            /**
             * Obtiene el valor de la propiedad field.
             * 
             * @return
             *     possible object is
             *     {@link ProductSettings.Inhibir.ItemSettings.Field }
             *     
             */
            public ProductSettings.Inhibir.ItemSettings.Field getField() {
                return field;
            }

            /**
             * Define el valor de la propiedad field.
             * 
             * @param value
             *     allowed object is
             *     {@link ProductSettings.Inhibir.ItemSettings.Field }
             *     
             */
            public void setField(ProductSettings.Inhibir.ItemSettings.Field value) {
                this.field = value;
            }

            /**
             * Obtiene el valor de la propiedad startDate.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStartDate() {
                return startDate;
            }

            /**
             * Define el valor de la propiedad startDate.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStartDate(String value) {
                this.startDate = value;
            }

            /**
             * Obtiene el valor de la propiedad endDate.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEndDate() {
                return endDate;
            }

            /**
             * Define el valor de la propiedad endDate.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEndDate(String value) {
                this.endDate = value;
            }


            /**
             * <p>Clase Java para anonymous complex type.
             * 
             * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *       &lt;sequence&gt;
             *         &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
             *       &lt;/sequence&gt;
             *       &lt;attribute name="data_field" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
             *       &lt;attribute name="data_type" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "data"
            })
            public static class Field {

                protected List<String> data;
                @XmlAttribute(name = "data_field")
                protected String dataField;
                @XmlAttribute(name = "data_type")
                protected String dataType;

                /**
                 * Gets the value of the data property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the data property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getData().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link String }
                 * 
                 * 
                 */
                public List<String> getData() {
                    if (data == null) {
                        data = new ArrayList<>();
                    }
                    return this.data;
                }

                /**
                 * Obtiene el valor de la propiedad dataField.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDataField() {
                    return dataField;
                }

                /**
                 * Define el valor de la propiedad dataField.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDataField(String value) {
                    this.dataField = value;
                }

                /**
                 * Obtiene el valor de la propiedad dataType.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDataType() {
                    return dataType;
                }

                /**
                 * Define el valor de la propiedad dataType.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDataType(String value) {
                    this.dataType = value;
                }

            }

        }

    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="item_settings" maxOccurs="unbounded" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="field"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
     *                           &lt;/sequence&gt;
     *                           &lt;attribute name="data_field" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *                           &lt;attribute name="data_type" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *                           &lt;attribute name="include" type="{http://www.w3.org/2001/XMLSchema}byte" /&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *                 &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}byte" /&gt;
     *                 &lt;attribute name="position" type="{http://www.w3.org/2001/XMLSchema}byte" /&gt;
     *                 &lt;attribute name="flag" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *                 &lt;attribute name="logic" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *                 &lt;attribute name="start_date" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *                 &lt;attribute name="end_date" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "itemSettings"
    })
    public static class Message {

        @XmlElement(name = "item_settings")
        protected List<ProductSettings.Message.ItemSettings> itemSettings;

        /**
         * Gets the value of the itemSettings property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the itemSettings property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getItemSettings().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ProductSettings.Message.ItemSettings }
         * 
         * 
         */
        public List<ProductSettings.Message.ItemSettings> getItemSettings() {
            if (itemSettings == null) {
                itemSettings = new ArrayList<>();
            }
            return this.itemSettings;
        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="field"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
         *                 &lt;/sequence&gt;
         *                 &lt;attribute name="data_field" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *                 &lt;attribute name="data_type" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *                 &lt;attribute name="include" type="{http://www.w3.org/2001/XMLSchema}byte" /&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *       &lt;/sequence&gt;
         *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}byte" /&gt;
         *       &lt;attribute name="position" type="{http://www.w3.org/2001/XMLSchema}byte" /&gt;
         *       &lt;attribute name="flag" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *       &lt;attribute name="logic" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *       &lt;attribute name="start_date" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *       &lt;attribute name="end_date" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "content"
        })
        public static class ItemSettings {

            @XmlElementRef(name = "field", type = JAXBElement.class)
            @XmlMixed
            protected List<Serializable> content;
            @XmlAttribute(name = "type")
            protected Byte type;
            @XmlAttribute(name = "position")
            protected Byte position;
            @XmlAttribute(name = "flag")
            protected String flag;
            @XmlAttribute(name = "logic")
            protected String logic;
            @XmlAttribute(name = "start_date")
            protected String startDate;
            @XmlAttribute(name = "end_date")
            protected String endDate;

            /**
             * Gets the value of the content property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the content property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getContent().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link JAXBElement }{@code <}{@link ProductSettings.Message.ItemSettings.Field }{@code >}
             * {@link String }
             * 
             * 
             */
            public List<Serializable> getContent() {
                if (content == null) {
                    content = new ArrayList<>();
                }
                return this.content;
            }

            /**
             * Obtiene el valor de la propiedad type.
             * 
             * @return
             *     possible object is
             *     {@link Byte }
             *     
             */
            public Byte getType() {
                return type;
            }

            /**
             * Define el valor de la propiedad type.
             * 
             * @param value
             *     allowed object is
             *     {@link Byte }
             *     
             */
            public void setType(Byte value) {
                this.type = value;
            }

            /**
             * Obtiene el valor de la propiedad position.
             * 
             * @return
             *     possible object is
             *     {@link Byte }
             *     
             */
            public Byte getPosition() {
                return position;
            }

            /**
             * Define el valor de la propiedad position.
             * 
             * @param value
             *     allowed object is
             *     {@link Byte }
             *     
             */
            public void setPosition(Byte value) {
                this.position = value;
            }

            /**
             * Obtiene el valor de la propiedad flag.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFlag() {
                return flag;
            }

            /**
             * Define el valor de la propiedad flag.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFlag(String value) {
                this.flag = value;
            }

            /**
             * Obtiene el valor de la propiedad logic.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLogic() {
                return logic;
            }

            /**
             * Define el valor de la propiedad logic.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLogic(String value) {
                this.logic = value;
            }

            /**
             * Obtiene el valor de la propiedad startDate.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStartDate() {
                return startDate;
            }

            /**
             * Define el valor de la propiedad startDate.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStartDate(String value) {
                this.startDate = value;
            }

            /**
             * Obtiene el valor de la propiedad endDate.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEndDate() {
                return endDate;
            }

            /**
             * Define el valor de la propiedad endDate.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEndDate(String value) {
                this.endDate = value;
            }


            /**
             * <p>Clase Java para anonymous complex type.
             * 
             * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *       &lt;sequence&gt;
             *         &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
             *       &lt;/sequence&gt;
             *       &lt;attribute name="data_field" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
             *       &lt;attribute name="data_type" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
             *       &lt;attribute name="include" type="{http://www.w3.org/2001/XMLSchema}byte" /&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "data"
            })
            public static class Field {

                protected List<String> data;
                @XmlAttribute(name = "data_field")
                protected String dataField;
                @XmlAttribute(name = "data_type")
                protected String dataType;
                @XmlAttribute(name = "include")
                protected Byte include;

                /**
                 * Gets the value of the data property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the data property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getData().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link String }
                 * 
                 * 
                 */
                public List<String> getData() {
                    if (data == null) {
                        data = new ArrayList<>();
                    }
                    return this.data;
                }

                /**
                 * Obtiene el valor de la propiedad dataField.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDataField() {
                    return dataField;
                }

                /**
                 * Define el valor de la propiedad dataField.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDataField(String value) {
                    this.dataField = value;
                }

                /**
                 * Obtiene el valor de la propiedad dataType.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDataType() {
                    return dataType;
                }

                /**
                 * Define el valor de la propiedad dataType.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDataType(String value) {
                    this.dataType = value;
                }

                /**
                 * Obtiene el valor de la propiedad include.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Byte }
                 *     
                 */
                public Byte getInclude() {
                    return include;
                }

                /**
                 * Define el valor de la propiedad include.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Byte }
                 *     
                 */
                public void setInclude(Byte value) {
                    this.include = value;
                }

            }

        }

    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="item_settings" maxOccurs="unbounded" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="field"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                           &lt;/sequence&gt;
     *                           &lt;attribute name="data_field" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *                           &lt;attribute name="data_type" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}short" /&gt;
     *                 &lt;attribute name="start_date" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *                 &lt;attribute name="end_date" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "itemSettings"
    })
    public static class Previo {

        @XmlElement(name = "item_settings")
        protected List<ProductSettings.Previo.ItemSettings> itemSettings;

        /**
         * Gets the value of the itemSettings property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the itemSettings property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getItemSettings().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ProductSettings.Previo.ItemSettings }
         * 
         * 
         */
        public List<ProductSettings.Previo.ItemSettings> getItemSettings() {
            if (itemSettings == null) {
                itemSettings = new ArrayList<>();
            }
            return this.itemSettings;
        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="field"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *                 &lt;/sequence&gt;
         *                 &lt;attribute name="data_field" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *                 &lt;attribute name="data_type" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *       &lt;/sequence&gt;
         *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}short" /&gt;
         *       &lt;attribute name="start_date" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *       &lt;attribute name="end_date" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "content"
        })
        public static class ItemSettings {

            @XmlElementRef(name = "field", type = JAXBElement.class)
            @XmlMixed
            protected List<Serializable> content;
            @XmlAttribute(name = "id")
            protected Short id;
            @XmlAttribute(name = "start_date")
            protected String startDate;
            @XmlAttribute(name = "end_date")
            protected String endDate;

            /**
             * Gets the value of the content property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the content property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getContent().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link JAXBElement }{@code <}{@link ProductSettings.Previo.ItemSettings.Field }{@code >}
             * {@link String }
             * 
             * 
             */
            public List<Serializable> getContent() {
                if (content == null) {
                    content = new ArrayList<>();
                }
                return this.content;
            }

            /**
             * Obtiene el valor de la propiedad id.
             * 
             * @return
             *     possible object is
             *     {@link Short }
             *     
             */
            public Short getId() {
                return id;
            }

            /**
             * Define el valor de la propiedad id.
             * 
             * @param value
             *     allowed object is
             *     {@link Short }
             *     
             */
            public void setId(Short value) {
                this.id = value;
            }

            /**
             * Obtiene el valor de la propiedad startDate.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStartDate() {
                return startDate;
            }

            /**
             * Define el valor de la propiedad startDate.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStartDate(String value) {
                this.startDate = value;
            }

            /**
             * Obtiene el valor de la propiedad endDate.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEndDate() {
                return endDate;
            }

            /**
             * Define el valor de la propiedad endDate.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEndDate(String value) {
                this.endDate = value;
            }


            /**
             * <p>Clase Java para anonymous complex type.
             * 
             * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *       &lt;sequence&gt;
             *         &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
             *       &lt;/sequence&gt;
             *       &lt;attribute name="data_field" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
             *       &lt;attribute name="data_type" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "data"
            })
            public static class Field {

                @XmlElement(required = true)
                protected List <String> data;
                @XmlAttribute(name = "data_field")
                protected String dataField;
                @XmlAttribute(name = "data_type")
                protected String dataType;
                @XmlAttribute(name = "include")
                protected String include;


                /**
                 * Obtiene el valor de la propiedad include.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getInclude() {
                    return include;
                }

                /**
                 * Define el valor de la propiedad include.
                 *
                 * @param include
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setInclude(String include) {
                    this.include = include;
                }


                /**
                 * Obtiene el valor de la propiedad data.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public List <String> getData() {
                    return data;
                }

                /**
                 * Define el valor de la propiedad data.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setData(List <String> value) {
                    this.data = value;
                }

                /**
                 * Obtiene el valor de la propiedad dataField.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDataField() {
                    return dataField;
                }

                /**
                 * Define el valor de la propiedad dataField.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDataField(String value) {
                    this.dataField = value;
                }

                /**
                 * Obtiene el valor de la propiedad dataType.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDataType() {
                    return dataType;
                }

                /**
                 * Define el valor de la propiedad dataType.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDataType(String value) {
                    this.dataType = value;
                }

            }

        }

    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="item_settings" maxOccurs="unbounded" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="field" maxOccurs="unbounded" minOccurs="0"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
     *                           &lt;/sequence&gt;
     *                           &lt;attribute name="data_field" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *                           &lt;attribute name="data_type" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *                           &lt;attribute name="include" type="{http://www.w3.org/2001/XMLSchema}byte" /&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}short" /&gt;
     *                 &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}byte" /&gt;
     *                 &lt;attribute name="prodlibe" type="{http://www.w3.org/2001/XMLSchema}byte" /&gt;
     *                 &lt;attribute name="uselogistic" type="{http://www.w3.org/2001/XMLSchema}byte" /&gt;
     *                 &lt;attribute name="priority" type="{http://www.w3.org/2001/XMLSchema}byte" /&gt;
     *                 &lt;attribute name="logic" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *                 &lt;attribute name="start_date" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *                 &lt;attribute name="end_date" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "itemSettings"
    })
    public static class Segment {

        @XmlElement(name = "item_settings")
        protected List<ProductSettings.Segment.ItemSettings> itemSettings;

        /**
         * Gets the value of the itemSettings property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the itemSettings property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getItemSettings().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ProductSettings.Segment.ItemSettings }
         * 
         * 
         */
        public List<ProductSettings.Segment.ItemSettings> getItemSettings() {
            if (itemSettings == null) {
                itemSettings = new ArrayList<>();
            }
            return this.itemSettings;
        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="field" maxOccurs="unbounded" minOccurs="0"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
         *                 &lt;/sequence&gt;
         *                 &lt;attribute name="data_field" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *                 &lt;attribute name="data_type" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *                 &lt;attribute name="include" type="{http://www.w3.org/2001/XMLSchema}byte" /&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *       &lt;/sequence&gt;
         *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}short" /&gt;
         *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}byte" /&gt;
         *       &lt;attribute name="prodlibe" type="{http://www.w3.org/2001/XMLSchema}byte" /&gt;
         *       &lt;attribute name="uselogistic" type="{http://www.w3.org/2001/XMLSchema}byte" /&gt;
         *       &lt;attribute name="priority" type="{http://www.w3.org/2001/XMLSchema}byte" /&gt;
         *       &lt;attribute name="logic" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *       &lt;attribute name="start_date" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *       &lt;attribute name="end_date" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "content"
        })
        public static class ItemSettings {

            @XmlElementRef(name = "field", type = JAXBElement.class, required = false)
            @XmlMixed
            protected List<Serializable> content;
            @XmlAttribute(name = "id")
            protected Short id;
            @XmlAttribute(name = "type")
            protected Byte type;
            @XmlAttribute(name = "prodlibe")
            protected Byte prodlibe;
            @XmlAttribute(name = "uselogistic")
            protected Byte uselogistic;
            @XmlAttribute(name = "priority")
            protected Byte priority;
            @XmlAttribute(name = "logic")
            protected String logic;
            @XmlAttribute(name = "start_date")
            protected String startDate;
            @XmlAttribute(name = "end_date")
            protected String endDate;

            /**
             * Gets the value of the content property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the content property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getContent().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link JAXBElement }{@code <}{@link ProductSettings.Segment.ItemSettings.Field }{@code >}
             * {@link String }
             * 
             * 
             */
            public List<Serializable> getContent() {
                if (content == null) {
                    content = new ArrayList<>();
                }
                return this.content;
            }

            /**
             * Obtiene el valor de la propiedad id.
             * 
             * @return
             *     possible object is
             *     {@link Short }
             *     
             */
            public Short getId() {
                return id;
            }

            /**
             * Define el valor de la propiedad id.
             * 
             * @param value
             *     allowed object is
             *     {@link Short }
             *     
             */
            public void setId(Short value) {
                this.id = value;
            }

            /**
             * Obtiene el valor de la propiedad type.
             * 
             * @return
             *     possible object is
             *     {@link Byte }
             *     
             */
            public Byte getType() {
                return type;
            }

            /**
             * Define el valor de la propiedad type.
             * 
             * @param value
             *     allowed object is
             *     {@link Byte }
             *     
             */
            public void setType(Byte value) {
                this.type = value;
            }

            /**
             * Obtiene el valor de la propiedad prodlibe.
             * 
             * @return
             *     possible object is
             *     {@link Byte }
             *     
             */
            public Byte getProdlibe() {
                return prodlibe;
            }

            /**
             * Define el valor de la propiedad prodlibe.
             * 
             * @param value
             *     allowed object is
             *     {@link Byte }
             *     
             */
            public void setProdlibe(Byte value) {
                this.prodlibe = value;
            }

            /**
             * Obtiene el valor de la propiedad uselogistic.
             * 
             * @return
             *     possible object is
             *     {@link Byte }
             *     
             */
            public Byte getUselogistic() {
                return uselogistic;
            }

            /**
             * Define el valor de la propiedad uselogistic.
             * 
             * @param value
             *     allowed object is
             *     {@link Byte }
             *     
             */
            public void setUselogistic(Byte value) {
                this.uselogistic = value;
            }

            /**
             * Obtiene el valor de la propiedad priority.
             * 
             * @return
             *     possible object is
             *     {@link Byte }
             *     
             */
            public Byte getPriority() {
                return priority;
            }

            /**
             * Define el valor de la propiedad priority.
             * 
             * @param value
             *     allowed object is
             *     {@link Byte }
             *     
             */
            public void setPriority(Byte value) {
                this.priority = value;
            }

            /**
             * Obtiene el valor de la propiedad logic.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLogic() {
                return logic;
            }

            /**
             * Define el valor de la propiedad logic.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLogic(String value) {
                this.logic = value;
            }

            /**
             * Obtiene el valor de la propiedad startDate.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStartDate() {
                return startDate;
            }

            /**
             * Define el valor de la propiedad startDate.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStartDate(String value) {
                this.startDate = value;
            }

            /**
             * Obtiene el valor de la propiedad endDate.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEndDate() {
                return endDate;
            }

            /**
             * Define el valor de la propiedad endDate.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEndDate(String value) {
                this.endDate = value;
            }


            /**
             * <p>Clase Java para anonymous complex type.
             * 
             * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *       &lt;sequence&gt;
             *         &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
             *       &lt;/sequence&gt;
             *       &lt;attribute name="data_field" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
             *       &lt;attribute name="data_type" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
             *       &lt;attribute name="include" type="{http://www.w3.org/2001/XMLSchema}byte" /&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "data"
            })
            public static class Field {

                protected List<String> data;
                @XmlAttribute(name = "data_field")
                protected String dataField;
                @XmlAttribute(name = "data_type")
                protected String dataType;
                @XmlAttribute(name = "include")
                protected Byte include;

                /**
                 * Gets the value of the data property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the data property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getData().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link String }
                 * 
                 * 
                 */
                public List<String> getData() {
                    if (data == null) {
                        data = new ArrayList<>();
                    }
                    return this.data;
                }

                /**
                 * Obtiene el valor de la propiedad dataField.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDataField() {
                    return dataField;
                }

                /**
                 * Define el valor de la propiedad dataField.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDataField(String value) {
                    this.dataField = value;
                }

                /**
                 * Obtiene el valor de la propiedad dataType.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDataType() {
                    return dataType;
                }

                /**
                 * Define el valor de la propiedad dataType.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDataType(String value) {
                    this.dataType = value;
                }

                /**
                 * Obtiene el valor de la propiedad include.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Byte }
                 *     
                 */
                public Byte getInclude() {
                    return include;
                }

                /**
                 * Define el valor de la propiedad include.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Byte }
                 *     
                 */
                public void setInclude(Byte value) {
                    this.include = value;
                }

            }

        }

    }

}
