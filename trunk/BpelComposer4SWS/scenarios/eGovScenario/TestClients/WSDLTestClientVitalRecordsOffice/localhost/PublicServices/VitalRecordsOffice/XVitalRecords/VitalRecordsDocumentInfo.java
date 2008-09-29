/**
 * VitalRecordsDocumentInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package localhost.PublicServices.VitalRecordsOffice.XVitalRecords;

public class VitalRecordsDocumentInfo  implements java.io.Serializable {
    private java.lang.String price;
    private localhost.PublicServices.VitalRecordsOffice.XVitalRecords.VitalRecordsDocumentType vitalRecordsDocumentType;
    private localhost.PublicServices.PublicServicePayment.XPublicServicePayment.Account paymentAccount;

    public VitalRecordsDocumentInfo() {
    }

    public java.lang.String getPrice() {
        return price;
    }

    public void setPrice(java.lang.String price) {
        this.price = price;
    }

    public localhost.PublicServices.VitalRecordsOffice.XVitalRecords.VitalRecordsDocumentType getVitalRecordsDocumentType() {
        return vitalRecordsDocumentType;
    }

    public void setVitalRecordsDocumentType(localhost.PublicServices.VitalRecordsOffice.XVitalRecords.VitalRecordsDocumentType vitalRecordsDocumentType) {
        this.vitalRecordsDocumentType = vitalRecordsDocumentType;
    }

    public localhost.PublicServices.PublicServicePayment.XPublicServicePayment.Account getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(localhost.PublicServices.PublicServicePayment.XPublicServicePayment.Account paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VitalRecordsDocumentInfo)) return false;
        VitalRecordsDocumentInfo other = (VitalRecordsDocumentInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.price==null && other.getPrice()==null) || 
             (this.price!=null &&
              this.price.equals(other.getPrice()))) &&
            ((this.vitalRecordsDocumentType==null && other.getVitalRecordsDocumentType()==null) || 
             (this.vitalRecordsDocumentType!=null &&
              this.vitalRecordsDocumentType.equals(other.getVitalRecordsDocumentType()))) &&
            ((this.paymentAccount==null && other.getPaymentAccount()==null) || 
             (this.paymentAccount!=null &&
              this.paymentAccount.equals(other.getPaymentAccount())));
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
        if (getPrice() != null) {
            _hashCode += getPrice().hashCode();
        }
        if (getVitalRecordsDocumentType() != null) {
            _hashCode += getVitalRecordsDocumentType().hashCode();
        }
        if (getPaymentAccount() != null) {
            _hashCode += getPaymentAccount().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VitalRecordsDocumentInfo.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecords", "vitalRecordsDocumentInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("price");
        elemField.setXmlName(new javax.xml.namespace.QName("", "price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vitalRecordsDocumentType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "vitalRecordsDocumentType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecords", "vitalRecordsDocumentType"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paymentAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:8080/PublicServices/PublicServicePayment/XPublicServicePayment", "account"));
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
