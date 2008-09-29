/**
 * ArrayOfBookInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package org.dyndns.dinoch.webservices.books;

public class ArrayOfBookInfo  implements java.io.Serializable {
    private org.dyndns.dinoch.webservices.books.BookInfo[] bookInfo;

    public ArrayOfBookInfo() {
    }

    public org.dyndns.dinoch.webservices.books.BookInfo[] getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(org.dyndns.dinoch.webservices.books.BookInfo[] bookInfo) {
        this.bookInfo = bookInfo;
    }

    public org.dyndns.dinoch.webservices.books.BookInfo getBookInfo(int i) {
        return bookInfo[i];
    }

    public void setBookInfo(int i, org.dyndns.dinoch.webservices.books.BookInfo value) {
        this.bookInfo[i] = value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ArrayOfBookInfo)) return false;
        ArrayOfBookInfo other = (ArrayOfBookInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bookInfo==null && other.getBookInfo()==null) || 
             (this.bookInfo!=null &&
              java.util.Arrays.equals(this.bookInfo, other.getBookInfo())));
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
        if (getBookInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBookInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getBookInfo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ArrayOfBookInfo.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "ArrayOfBookInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bookInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "bookInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "bookInfo"));
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
