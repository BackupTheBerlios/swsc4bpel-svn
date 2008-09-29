/**
 * Acknowledgement.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package localhost.PublicServices.VitalRecordsOffice.XVitalRecords;

public class Acknowledgement  implements java.io.Serializable {
    private localhost.PublicServices.VitalRecordsOffice.XVitalRecords.VitalRecordsDocumentType document;
    private localhost.PublicServices.VitalRecordsOffice.XVitalRecords.PostalAddress recipientPostalAddress;

    public Acknowledgement() {
    }

    public localhost.PublicServices.VitalRecordsOffice.XVitalRecords.VitalRecordsDocumentType getDocument() {
        return document;
    }

    public void setDocument(localhost.PublicServices.VitalRecordsOffice.XVitalRecords.VitalRecordsDocumentType document) {
        this.document = document;
    }

    public localhost.PublicServices.VitalRecordsOffice.XVitalRecords.PostalAddress getRecipientPostalAddress() {
        return recipientPostalAddress;
    }

    public void setRecipientPostalAddress(localhost.PublicServices.VitalRecordsOffice.XVitalRecords.PostalAddress recipientPostalAddress) {
        this.recipientPostalAddress = recipientPostalAddress;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Acknowledgement)) return false;
        Acknowledgement other = (Acknowledgement) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.document==null && other.getDocument()==null) || 
             (this.document!=null &&
              this.document.equals(other.getDocument()))) &&
            ((this.recipientPostalAddress==null && other.getRecipientPostalAddress()==null) || 
             (this.recipientPostalAddress!=null &&
              this.recipientPostalAddress.equals(other.getRecipientPostalAddress())));
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
        if (getDocument() != null) {
            _hashCode += getDocument().hashCode();
        }
        if (getRecipientPostalAddress() != null) {
            _hashCode += getRecipientPostalAddress().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Acknowledgement.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecords", "acknowledgement"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("document");
        elemField.setXmlName(new javax.xml.namespace.QName("", "document"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecords", "vitalRecordsDocumentType"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recipientPostalAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "recipientPostalAddress"));
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
