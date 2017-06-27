//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.17 at 08:41:16 PM IST 
//


package org.sapphire.appconsole.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OperatorType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="OperatorType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="EQUAL"/>
 *     &lt;enumeration value="NOT EQUAL"/>
 *     &lt;enumeration value="LIKE"/>
 *     &lt;enumeration value="NOT LIKE"/>
 *     &lt;enumeration value="START WITH"/>
 *     &lt;enumeration value="END WITH"/>
 *     &lt;enumeration value="EXACT MATCH"/>
 *     &lt;enumeration value="LESS THAN"/>
 *     &lt;enumeration value="GREATER THAN"/>
 *     &lt;enumeration value="GREATER THAN EQUAL"/>
 *     &lt;enumeration value="LESS THAN EQUAL"/>
 *     &lt;enumeration value="AT LEAST"/>
 *     &lt;enumeration value="NO MORE THAN"/>
 *     &lt;enumeration value="IS A"/>
 *     &lt;enumeration value="IS ASSOCIATED WITH FLAG"/>
 *     &lt;enumeration value="IS ASSOCIATED WITH CODE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "OperatorType")
@XmlEnum
public enum OperatorType {

    EQUAL("EQUAL"),
    @XmlEnumValue("NOT EQUAL")
    NOT_EQUAL("NOT EQUAL"),
    LIKE("LIKE"),
    @XmlEnumValue("NOT LIKE")
    NOT_LIKE("NOT LIKE"),
    @XmlEnumValue("START WITH")
    START_WITH("START WITH"),
    @XmlEnumValue("END WITH")
    END_WITH("END WITH"),
    @XmlEnumValue("EXACT MATCH")
    EXACT_MATCH("EXACT MATCH"),
    @XmlEnumValue("LESS THAN")
    LESS_THAN("LESS THAN"),
    @XmlEnumValue("GREATER THAN")
    GREATER_THAN("GREATER THAN"),
    @XmlEnumValue("GREATER THAN EQUAL")
    GREATER_THAN_EQUAL("GREATER THAN EQUAL"),
    @XmlEnumValue("LESS THAN EQUAL")
    LESS_THAN_EQUAL("LESS THAN EQUAL"),
    @XmlEnumValue("AT LEAST")
    AT_LEAST("AT LEAST"),
    @XmlEnumValue("NO MORE THAN")
    NO_MORE_THAN("NO MORE THAN"),
    @XmlEnumValue("IS A")
    IS_A("IS A"),
    @XmlEnumValue("IS ASSOCIATED WITH FLAG")
    IS_ASSOCIATED_WITH_FLAG("IS ASSOCIATED WITH FLAG"),
    @XmlEnumValue("IS ASSOCIATED WITH CODE")
    IS_ASSOCIATED_WITH_CODE("IS ASSOCIATED WITH CODE");
    private final String value;

    OperatorType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OperatorType fromValue(String v) {
        for (OperatorType c: OperatorType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}