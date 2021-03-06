//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.12.20 at 01:10:50 AM CET 
//


package pei.java.jse.lab.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PLANT" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="COMMON" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="BOTANICAL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZONE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="LIGHT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="PRICE">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>float">
 *                           &lt;attribute name="currency" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="AVAILABILITY" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "plant"
})
@XmlRootElement(name = "CATALOG")
public class CATALOG {

    @XmlElement(name = "PLANT")
    protected List<CATALOG.PLANT> plant;

    /**
     * Gets the value of the plant property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the plant property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPLANT().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CATALOG.PLANT }
     * 
     * 
     */
    public List<CATALOG.PLANT> getPLANT() {
        if (plant == null) {
            plant = new ArrayList<CATALOG.PLANT>();
        }
        return this.plant;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="COMMON" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="BOTANICAL" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZONE" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="LIGHT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="PRICE">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>float">
     *                 &lt;attribute name="currency" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="AVAILABILITY" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *       &lt;/sequence>
     *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "common",
        "botanical",
        "zone",
        "light",
        "price",
        "availability"
    })
    public static class PLANT {

        @XmlElement(name = "COMMON", required = true)
        protected String common;
        @XmlElement(name = "BOTANICAL", required = true)
        protected String botanical;
        @XmlElement(name = "ZONE", required = true)
        protected String zone;
        @XmlElement(name = "LIGHT", required = true)
        protected String light;
        @XmlElement(name = "PRICE", required = true)
        protected CATALOG.PLANT.PRICE price;
        @XmlElement(name = "AVAILABILITY")
        protected int availability;
        @XmlAttribute(name = "id")
        protected Byte id;

        /**
         * Gets the value of the common property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCOMMON() {
            return common;
        }

        /**
         * Sets the value of the common property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCOMMON(String value) {
            this.common = value;
        }

        /**
         * Gets the value of the botanical property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBOTANICAL() {
            return botanical;
        }

        /**
         * Sets the value of the botanical property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBOTANICAL(String value) {
            this.botanical = value;
        }

        /**
         * Gets the value of the zone property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getZONE() {
            return zone;
        }

        /**
         * Sets the value of the zone property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setZONE(String value) {
            this.zone = value;
        }

        /**
         * Gets the value of the light property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLIGHT() {
            return light;
        }

        /**
         * Sets the value of the light property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLIGHT(String value) {
            this.light = value;
        }

        /**
         * Gets the value of the price property.
         * 
         * @return
         *     possible object is
         *     {@link CATALOG.PLANT.PRICE }
         *     
         */
        public CATALOG.PLANT.PRICE getPRICE() {
            return price;
        }

        /**
         * Sets the value of the price property.
         * 
         * @param value
         *     allowed object is
         *     {@link CATALOG.PLANT.PRICE }
         *     
         */
        public void setPRICE(CATALOG.PLANT.PRICE value) {
            this.price = value;
        }

        /**
         * Gets the value of the availability property.
         * 
         */
        public int getAVAILABILITY() {
            return availability;
        }

        /**
         * Sets the value of the availability property.
         * 
         */
        public void setAVAILABILITY(int value) {
            this.availability = value;
        }

        /**
         * Gets the value of the id property.
         * 
         * @return
         *     possible object is
         *     {@link Byte }
         *     
         */
        public Byte getId() {
            return id;
        }

        /**
         * Sets the value of the id property.
         * 
         * @param value
         *     allowed object is
         *     {@link Byte }
         *     
         */
        public void setId(Byte value) {
            this.id = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>float">
         *       &lt;attribute name="currency" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class PRICE {

            @XmlValue
            protected float value;
            @XmlAttribute(name = "currency")
            protected String currency;

            /**
             * Gets the value of the value property.
             * 
             */
            public float getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             */
            public void setValue(float value) {
                this.value = value;
            }

            /**
             * Gets the value of the currency property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCurrency() {
                return currency;
            }

            /**
             * Sets the value of the currency property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCurrency(String value) {
                this.currency = value;
            }

        }

    }

}
