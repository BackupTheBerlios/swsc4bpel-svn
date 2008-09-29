/**
 * Payment.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package localhost.PublicServices.PublicServicePayment.XPublicServicePayment;

public class Payment  implements java.io.Serializable {
    private localhost.PublicServices.PublicServicePayment.XPublicServicePayment.Account sourceAccount;
    private localhost.PublicServices.PublicServicePayment.XPublicServicePayment.Account destAccount;
    private java.lang.String amount;
    private java.lang.String reasonForPayment;

    public Payment() {
    }

    public localhost.PublicServices.PublicServicePayment.XPublicServicePayment.Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(localhost.PublicServices.PublicServicePayment.XPublicServicePayment.Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public localhost.PublicServices.PublicServicePayment.XPublicServicePayment.Account getDestAccount() {
        return destAccount;
    }

    public void setDestAccount(localhost.PublicServices.PublicServicePayment.XPublicServicePayment.Account destAccount) {
        this.destAccount = destAccount;
    }

    public java.lang.String getAmount() {
        return amount;
    }

    public void setAmount(java.lang.String amount) {
        this.amount = amount;
    }

    public java.lang.String getReasonForPayment() {
        return reasonForPayment;
    }

    public void setReasonForPayment(java.lang.String reasonForPayment) {
        this.reasonForPayment = reasonForPayment;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Payment)) return false;
        Payment other = (Payment) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.sourceAccount==null && other.getSourceAccount()==null) || 
             (this.sourceAccount!=null &&
              this.sourceAccount.equals(other.getSourceAccount()))) &&
            ((this.destAccount==null && other.getDestAccount()==null) || 
             (this.destAccount!=null &&
              this.destAccount.equals(other.getDestAccount()))) &&
            ((this.amount==null && other.getAmount()==null) || 
             (this.amount!=null &&
              this.amount.equals(other.getAmount()))) &&
            ((this.reasonForPayment==null && other.getReasonForPayment()==null) || 
             (this.reasonForPayment!=null &&
              this.reasonForPayment.equals(other.getReasonForPayment())));
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
        if (getSourceAccount() != null) {
            _hashCode += getSourceAccount().hashCode();
        }
        if (getDestAccount() != null) {
            _hashCode += getDestAccount().hashCode();
        }
        if (getAmount() != null) {
            _hashCode += getAmount().hashCode();
        }
        if (getReasonForPayment() != null) {
            _hashCode += getReasonForPayment().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Payment.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost:8080/PublicServices/PublicServicePayment/XPublicServicePayment", "payment"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:8080/PublicServices/PublicServicePayment/XPublicServicePayment", "account"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "destAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:8080/PublicServices/PublicServicePayment/XPublicServicePayment", "account"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reasonForPayment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reasonForPayment"));
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
