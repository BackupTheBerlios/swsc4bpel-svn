/**
 * Receipt.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package localhost.PublicServices.PublicServicePayment.XPublicServicePayment;

public class Receipt  implements java.io.Serializable {
    private localhost.PublicServices.PublicServicePayment.XPublicServicePayment.Payment confirmedPayment;
    private java.lang.String confirmation;

    public Receipt() {
    }

    public localhost.PublicServices.PublicServicePayment.XPublicServicePayment.Payment getConfirmedPayment() {
        return confirmedPayment;
    }

    public void setConfirmedPayment(localhost.PublicServices.PublicServicePayment.XPublicServicePayment.Payment confirmedPayment) {
        this.confirmedPayment = confirmedPayment;
    }

    public java.lang.String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(java.lang.String confirmation) {
        this.confirmation = confirmation;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Receipt)) return false;
        Receipt other = (Receipt) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.confirmedPayment==null && other.getConfirmedPayment()==null) || 
             (this.confirmedPayment!=null &&
              this.confirmedPayment.equals(other.getConfirmedPayment()))) &&
            ((this.confirmation==null && other.getConfirmation()==null) || 
             (this.confirmation!=null &&
              this.confirmation.equals(other.getConfirmation())));
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
        if (getConfirmedPayment() != null) {
            _hashCode += getConfirmedPayment().hashCode();
        }
        if (getConfirmation() != null) {
            _hashCode += getConfirmation().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Receipt.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost:8080/PublicServices/PublicServicePayment/XPublicServicePayment", "receipt"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("confirmedPayment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "confirmedPayment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:8080/PublicServices/PublicServicePayment/XPublicServicePayment", "payment"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("confirmation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "confirmation"));
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
