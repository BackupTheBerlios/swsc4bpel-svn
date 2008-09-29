/**
 * Name.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package localhost.PublicServices.ResidentRegistry.XResidentRegistry;

public class Name  implements java.io.Serializable {
    private java.lang.String titles;
    private java.lang.String givenName;
    private java.lang.String middleNames;
    private java.lang.String surname;

    public Name() {
    }

    public java.lang.String getTitles() {
        return titles;
    }

    public void setTitles(java.lang.String titles) {
        this.titles = titles;
    }

    public java.lang.String getGivenName() {
        return givenName;
    }

    public void setGivenName(java.lang.String givenName) {
        this.givenName = givenName;
    }

    public java.lang.String getMiddleNames() {
        return middleNames;
    }

    public void setMiddleNames(java.lang.String middleNames) {
        this.middleNames = middleNames;
    }

    public java.lang.String getSurname() {
        return surname;
    }

    public void setSurname(java.lang.String surname) {
        this.surname = surname;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Name)) return false;
        Name other = (Name) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.titles==null && other.getTitles()==null) || 
             (this.titles!=null &&
              this.titles.equals(other.getTitles()))) &&
            ((this.givenName==null && other.getGivenName()==null) || 
             (this.givenName!=null &&
              this.givenName.equals(other.getGivenName()))) &&
            ((this.middleNames==null && other.getMiddleNames()==null) || 
             (this.middleNames!=null &&
              this.middleNames.equals(other.getMiddleNames()))) &&
            ((this.surname==null && other.getSurname()==null) || 
             (this.surname!=null &&
              this.surname.equals(other.getSurname())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getTitles() != null) {
            _hashCode += getTitles().hashCode();
        }
        if (getGivenName() != null) {
            _hashCode += getGivenName().hashCode();
        }
        if (getMiddleNames() != null) {
            _hashCode += getMiddleNames().hashCode();
        }
        if (getSurname() != null) {
            _hashCode += getSurname().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Name.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost:8080/PublicServices/ResidentRegistry/XResidentRegistry", "name"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titles");
        elemField.setXmlName(new javax.xml.namespace.QName("", "titles"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("givenName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "givenName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("middleNames");
        elemField.setXmlName(new javax.xml.namespace.QName("", "middleNames"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("surname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "surname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
