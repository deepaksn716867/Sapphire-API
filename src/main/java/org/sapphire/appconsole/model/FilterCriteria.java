//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.17 at 08:41:16 PM IST 
//


package org.sapphire.appconsole.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FilterCriteria complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FilterCriteria">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Filters" type="{appconsole}Filter" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Condition" type="{appconsole}ConditionType"/>
 *         &lt;element name="FilterCriterias" type="{appconsole}FilterCriteria" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FilterCriteria", propOrder = {
    "id",
    "filters",
    "condition",
    "filterCriterias"
})
public class FilterCriteria {

    @XmlElement(name = "ID", required = true)
    protected String id;
    @XmlElement(name = "Filters")
    protected List<Filter> filters;
    @XmlElement(name = "Condition", required = true)
    protected ConditionType condition;
    @XmlElement(name = "FilterCriterias")
    protected List<FilterCriteria> filterCriterias;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the filters property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the filters property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFilters().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Filter }
     * 
     * 
     */
    public List<Filter> getFilters() {
        if (filters == null) {
            filters = new ArrayList<Filter>();
        }
        return this.filters;
    }

    /**
     * Gets the value of the condition property.
     * 
     * @return
     *     possible object is
     *     {@link ConditionType }
     *     
     */
    public ConditionType getCondition() {
        return condition;
    }

    /**
     * Sets the value of the condition property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConditionType }
     *     
     */
    public void setCondition(ConditionType value) {
        this.condition = value;
    }

    /**
     * Gets the value of the filterCriterias property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the filterCriterias property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFilterCriterias().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FilterCriteria }
     * 
     * 
     */
    public List<FilterCriteria> getFilterCriterias() {
        if (filterCriterias == null) {
            filterCriterias = new ArrayList<FilterCriteria>();
        }
        return this.filterCriterias;
    }

}
