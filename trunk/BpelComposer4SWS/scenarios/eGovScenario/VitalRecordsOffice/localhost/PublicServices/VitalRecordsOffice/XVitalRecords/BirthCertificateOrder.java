/**
 * BirthCertificateOrder.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package localhost.PublicServices.VitalRecordsOffice.XVitalRecords;

public class BirthCertificateOrder  implements java.io.Serializable {
    private localhost.PublicServices.VitalRecordsOffice.XVitalRecords.PaymentReceipt paymentReceipt;
    private localhost.PublicServices.VitalRecordsOffice.XVitalRecords.Person person;
    private localhost.PublicServices.VitalRecordsOffice.XVitalRecords.PostalAddress postalAddress;

    public BirthCertificateOrder() {
    }

    public localhost.PublicServices.VitalRecordsOffice.XVitalRecords.PaymentReceipt getPaymentReceipt() {
        return paymentReceipt;
    }

    public void setPaymentReceipt(localhost.PublicServices.VitalRecordsOffice.XVitalRecords.PaymentReceipt paymentReceipt) {
        this.paymentReceipt = paymentReceipt;
    }

    public localhost.PublicServices.VitalRecordsOffice.XVitalRecords.Person getPerson() {
        return person;
    }

    public void setPerson(localhost.PublicServices.VitalRecordsOffice.XVitalRecords.Person person) {
        this.person = person;
    }

    public localhost.PublicServices.VitalRecordsOffice.XVitalRecords.PostalAddress getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(localhost.PublicServices.VitalRecordsOffice.XVitalRecords.PostalAddress postalAddress) {
        this.postalAddress = postalAddress;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BirthCertificateOrder)) return false;
        BirthCertificateOrder other = (BirthCertificateOrder) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.paymentReceipt==null && other.getPaymentReceipt()==null) || 
             (this.paymentReceipt!=null &&
              this.paymentReceipt.equals(other.getPaymentReceipt()))) &&
            ((this.person==null && other.getPerson()==null) || 
             (this.person!=null &&
              this.person.equals(other.getPerson()))) &&
            ((this.postalAddress==null && other.getPostalAddress()==null) || 
             (this.postalAddress!=null &&
              this.postalAddress.equals(other.getPostalAddress())));
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
        if (getPaymentReceipt() != null) {
            _hashCode += getPaymentReceipt().hashCode();
        }
        if (getPerson() != null) {
            _hashCode += getPerson().hashCode();
        }
        if (getPostalAddress() != null) {
            _hashCode += getPostalAddress().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BirthCertificateOrder.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecords", "birthCertificateOrder"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentReceipt");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paymentReceipt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecords", "paymentReceipt"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("person");
        elemField.setXmlName(new javax.xml.namespace.QName("", "person"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecords", "person"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postalAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "postalAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecords", "postalAddress"));
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
