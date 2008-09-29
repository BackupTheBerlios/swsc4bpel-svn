/**
 * BookInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package org.dyndns.dinoch.webservices.books;

public class BookInfo  implements java.io.Serializable {
    private java.lang.String isbn;
    private java.lang.String title;
    private java.lang.String author;
    private java.lang.String pubdate;
    private java.lang.String publisher;
    private java.lang.String format;
    private java.lang.String imgUrl;
    private java.lang.String timestamp;
    private org.dyndns.dinoch.webservices.books.BookInfoVendorprice[] vendorprice;

    public BookInfo() {
    }

    public java.lang.String getIsbn() {
        return isbn;
    }

    public void setIsbn(java.lang.String isbn) {
        this.isbn = isbn;
    }

    public java.lang.String getTitle() {
        return title;
    }

    public void setTitle(java.lang.String title) {
        this.title = title;
    }

    public java.lang.String getAuthor() {
        return author;
    }

    public void setAuthor(java.lang.String author) {
        this.author = author;
    }

    public java.lang.String getPubdate() {
        return pubdate;
    }

    public void setPubdate(java.lang.String pubdate) {
        this.pubdate = pubdate;
    }

    public java.lang.String getPublisher() {
        return publisher;
    }

    public void setPublisher(java.lang.String publisher) {
        this.publisher = publisher;
    }

    public java.lang.String getFormat() {
        return format;
    }

    public void setFormat(java.lang.String format) {
        this.format = format;
    }

    public java.lang.String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(java.lang.String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public java.lang.String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(java.lang.String timestamp) {
        this.timestamp = timestamp;
    }

    public org.dyndns.dinoch.webservices.books.BookInfoVendorprice[] getVendorprice() {
        return vendorprice;
    }

    public void setVendorprice(org.dyndns.dinoch.webservices.books.BookInfoVendorprice[] vendorprice) {
        this.vendorprice = vendorprice;
    }

    public org.dyndns.dinoch.webservices.books.BookInfoVendorprice getVendorprice(int i) {
        return vendorprice[i];
    }

    public void setVendorprice(int i, org.dyndns.dinoch.webservices.books.BookInfoVendorprice value) {
        this.vendorprice[i] = value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BookInfo)) return false;
        BookInfo other = (BookInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.isbn==null && other.getIsbn()==null) || 
             (this.isbn!=null &&
              this.isbn.equals(other.getIsbn()))) &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.author==null && other.getAuthor()==null) || 
             (this.author!=null &&
              this.author.equals(other.getAuthor()))) &&
            ((this.pubdate==null && other.getPubdate()==null) || 
             (this.pubdate!=null &&
              this.pubdate.equals(other.getPubdate()))) &&
            ((this.publisher==null && other.getPublisher()==null) || 
             (this.publisher!=null &&
              this.publisher.equals(other.getPublisher()))) &&
            ((this.format==null && other.getFormat()==null) || 
             (this.format!=null &&
              this.format.equals(other.getFormat()))) &&
            ((this.imgUrl==null && other.getImgUrl()==null) || 
             (this.imgUrl!=null &&
              this.imgUrl.equals(other.getImgUrl()))) &&
            ((this.timestamp==null && other.getTimestamp()==null) || 
             (this.timestamp!=null &&
              this.timestamp.equals(other.getTimestamp()))) &&
            ((this.vendorprice==null && other.getVendorprice()==null) || 
             (this.vendorprice!=null &&
              java.util.Arrays.equals(this.vendorprice, other.getVendorprice())));
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
        if (getIsbn() != null) {
            _hashCode += getIsbn().hashCode();
        }
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getAuthor() != null) {
            _hashCode += getAuthor().hashCode();
        }
        if (getPubdate() != null) {
            _hashCode += getPubdate().hashCode();
        }
        if (getPublisher() != null) {
            _hashCode += getPublisher().hashCode();
        }
        if (getFormat() != null) {
            _hashCode += getFormat().hashCode();
        }
        if (getImgUrl() != null) {
            _hashCode += getImgUrl().hashCode();
        }
        if (getTimestamp() != null) {
            _hashCode += getTimestamp().hashCode();
        }
        if (getVendorprice() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getVendorprice());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getVendorprice(), i);
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
        new org.apache.axis.description.TypeDesc(BookInfo.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "bookInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isbn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "isbn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("author");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "author"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pubdate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "pubdate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("publisher");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "publisher"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("format");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "format"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imgUrl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "imgUrl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestamp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "timestamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vendorprice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "vendorprice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "bookInfoVendorprice"));
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
