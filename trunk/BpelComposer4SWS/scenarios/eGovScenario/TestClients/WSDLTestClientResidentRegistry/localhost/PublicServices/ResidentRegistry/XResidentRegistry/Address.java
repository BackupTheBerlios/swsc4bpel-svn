/**
 * Address.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package localhost.PublicServices.ResidentRegistry.XResidentRegistry;

public class Address  implements java.io.Serializable {
    private java.lang.String street;
    private java.lang.String streetNumber;
    private java.lang.String moreAddress;
    private java.lang.String zipCode;
    private java.lang.String locality;

    public Address() {
    }

    public java.lang.String getStreet() {
        return street;
    }

    public void setStreet(java.lang.String street) {
        this.street = street;
    }

    public java.lang.String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(java.lang.String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public java.lang.String getMoreAddress() {
        return moreAddress;
    }

    public void setMoreAddress(java.lang.String moreAddress) {
        this.moreAddress = moreAddress;
    }

    public java.lang.String getZipCode() {
        return zipCode;
    }

    public void setZipCode(java.lang.String zipCode) {
        this.zipCode = zipCode;
    }

    public java.lang.String getLocality() {
        return locality;
    }

    public void setLocality(java.lang.String locality) {
        this.locality = locality;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Address)) return false;
        Address other = (Address) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.street==null && other.getStreet()==null) || 
             (this.street!=null &&
              this.street.equals(other.getStreet()))) &&
            ((this.streetNumber==null && other.getStreetNumber()==null) || 
             (this.streetNumber!=null &&
              this.streetNumber.equals(other.getStreetNumber()))) &&
            ((this.moreAddress==null && other.getMoreAddress()==null) || 
             (this.moreAddress!=null &&
              this.moreAddress.equals(other.getMoreAddress()))) &&
            ((this.zipCode==null && other.getZipCode()==null) || 
             (this.zipCode!=null &&
              this.zipCode.equals(other.getZipCode()))) &&
            ((this.locality==null && other.getLocality()==null) || 
             (this.locality!=null &&
              this.locality.equals(other.getLocality())));
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
        if (getStreet() != null) {
            _hashCode += getStreet().hashCode();
        }
        if (getStreetNumber() != null) {
            _hashCode += getStreetNumber().hashCode();
        }
        if (getMoreAddress() != null) {
            _hashCode += getMoreAddress().hashCode();
        }
        if (getZipCode() != null) {
            _hashCode += getZipCode().hashCode();
        }
        if (getLocality() != null) {
            _hashCode += getLocality().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Address.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost:8080/PublicServices/ResidentRegistry/XResidentRegistry", "address"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("street");
        elemField.setXmlName(new javax.xml.namespace.QName("", "street"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("streetNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "streetNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("moreAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "moreAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("zipCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "zipCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("locality");
        elemField.setXmlName(new javax.xml.namespace.QName("", "locality"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
