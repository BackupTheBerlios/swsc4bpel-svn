/**
 * PostalAddress.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package localhost.PublicServices.VitalRecordsOffice.XVitalRecords;

public class PostalAddress  implements java.io.Serializable {
    private java.lang.String recipient;
    private java.lang.String streetAddress;
    private java.lang.String zipcodeAndPostalDistrict;

    public PostalAddress() {
    }

    public java.lang.String getRecipient() {
        return recipient;
    }

    public void setRecipient(java.lang.String recipient) {
        this.recipient = recipient;
    }

    public java.lang.String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(java.lang.String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public java.lang.String getZipcodeAndPostalDistrict() {
        return zipcodeAndPostalDistrict;
    }

    public void setZipcodeAndPostalDistrict(java.lang.String zipcodeAndPostalDistrict) {
        this.zipcodeAndPostalDistrict = zipcodeAndPostalDistrict;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PostalAddress)) return false;
        PostalAddress other = (PostalAddress) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.recipient==null && other.getRecipient()==null) || 
             (this.recipient!=null &&
              this.recipient.equals(other.getRecipient()))) &&
            ((this.streetAddress==null && other.getStreetAddress()==null) || 
             (this.streetAddress!=null &&
              this.streetAddress.equals(other.getStreetAddress()))) &&
            ((this.zipcodeAndPostalDistrict==null && other.getZipcodeAndPostalDistrict()==null) || 
             (this.zipcodeAndPostalDistrict!=null &&
              this.zipcodeAndPostalDistrict.equals(other.getZipcodeAndPostalDistrict())));
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
        if (getRecipient() != null) {
            _hashCode += getRecipient().hashCode();
        }
        if (getStreetAddress() != null) {
            _hashCode += getStreetAddress().hashCode();
        }
        if (getZipcodeAndPostalDistrict() != null) {
            _hashCode += getZipcodeAndPostalDistrict().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PostalAddress.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecords", "postalAddress"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recipient");
        elemField.setXmlName(new javax.xml.namespace.QName("", "recipient"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("streetAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "streetAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("zipcodeAndPostalDistrict");
        elemField.setXmlName(new javax.xml.namespace.QName("", "zipcodeAndPostalDistrict"));
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
