/**
 * Death.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package localhost.PublicServices.ResidentRegistry.XResidentRegistry;

public class Death  implements java.io.Serializable {
    private java.lang.String dateOfDeath;
    private java.lang.String placeOfDeath;

    public Death() {
    }

    public java.lang.String getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(java.lang.String dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public java.lang.String getPlaceOfDeath() {
        return placeOfDeath;
    }

    public void setPlaceOfDeath(java.lang.String placeOfDeath) {
        this.placeOfDeath = placeOfDeath;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Death)) return false;
        Death other = (Death) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dateOfDeath==null && other.getDateOfDeath()==null) || 
             (this.dateOfDeath!=null &&
              this.dateOfDeath.equals(other.getDateOfDeath()))) &&
            ((this.placeOfDeath==null && other.getPlaceOfDeath()==null) || 
             (this.placeOfDeath!=null &&
              this.placeOfDeath.equals(other.getPlaceOfDeath())));
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
        if (getDateOfDeath() != null) {
            _hashCode += getDateOfDeath().hashCode();
        }
        if (getPlaceOfDeath() != null) {
            _hashCode += getPlaceOfDeath().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Death.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost:8080/PublicServices/ResidentRegistry/XResidentRegistry", "death"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateOfDeath");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dateOfDeath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("placeOfDeath");
        elemField.setXmlName(new javax.xml.namespace.QName("", "placeOfDeath"));
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
