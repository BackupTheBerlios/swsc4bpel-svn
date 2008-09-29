/**
 * ResidentInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package localhost.PublicServices.ResidentRegistry.XResidentRegistry;

public class ResidentInfo  implements java.io.Serializable {
    private localhost.PublicServices.ResidentRegistry.XResidentRegistry.Name name;
    private localhost.PublicServices.ResidentRegistry.XResidentRegistry.Birth birth;
    private localhost.PublicServices.ResidentRegistry.XResidentRegistry.Death death;
    private localhost.PublicServices.ResidentRegistry.XResidentRegistry.Address address;
    private localhost.PublicServices.ResidentRegistry.XResidentRegistry.Nationality nationality;
    private localhost.PublicServices.ResidentRegistry.XResidentRegistry.Religion religion;

    public ResidentInfo() {
    }

    public localhost.PublicServices.ResidentRegistry.XResidentRegistry.Name getName() {
        return name;
    }

    public void setName(localhost.PublicServices.ResidentRegistry.XResidentRegistry.Name name) {
        this.name = name;
    }

    public localhost.PublicServices.ResidentRegistry.XResidentRegistry.Birth getBirth() {
        return birth;
    }

    public void setBirth(localhost.PublicServices.ResidentRegistry.XResidentRegistry.Birth birth) {
        this.birth = birth;
    }

    public localhost.PublicServices.ResidentRegistry.XResidentRegistry.Death getDeath() {
        return death;
    }

    public void setDeath(localhost.PublicServices.ResidentRegistry.XResidentRegistry.Death death) {
        this.death = death;
    }

    public localhost.PublicServices.ResidentRegistry.XResidentRegistry.Address getAddress() {
        return address;
    }

    public void setAddress(localhost.PublicServices.ResidentRegistry.XResidentRegistry.Address address) {
        this.address = address;
    }

    public localhost.PublicServices.ResidentRegistry.XResidentRegistry.Nationality getNationality() {
        return nationality;
    }

    public void setNationality(localhost.PublicServices.ResidentRegistry.XResidentRegistry.Nationality nationality) {
        this.nationality = nationality;
    }

    public localhost.PublicServices.ResidentRegistry.XResidentRegistry.Religion getReligion() {
        return religion;
    }

    public void setReligion(localhost.PublicServices.ResidentRegistry.XResidentRegistry.Religion religion) {
        this.religion = religion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResidentInfo)) return false;
        ResidentInfo other = (ResidentInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.birth==null && other.getBirth()==null) || 
             (this.birth!=null &&
              this.birth.equals(other.getBirth()))) &&
            ((this.death==null && other.getDeath()==null) || 
             (this.death!=null &&
              this.death.equals(other.getDeath()))) &&
            ((this.address==null && other.getAddress()==null) || 
             (this.address!=null &&
              this.address.equals(other.getAddress()))) &&
            ((this.nationality==null && other.getNationality()==null) || 
             (this.nationality!=null &&
              this.nationality.equals(other.getNationality()))) &&
            ((this.religion==null && other.getReligion()==null) || 
             (this.religion!=null &&
              this.religion.equals(other.getReligion())));
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
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getBirth() != null) {
            _hashCode += getBirth().hashCode();
        }
        if (getDeath() != null) {
            _hashCode += getDeath().hashCode();
        }
        if (getAddress() != null) {
            _hashCode += getAddress().hashCode();
        }
        if (getNationality() != null) {
            _hashCode += getNationality().hashCode();
        }
        if (getReligion() != null) {
            _hashCode += getReligion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResidentInfo.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost:8080/PublicServices/ResidentRegistry/XResidentRegistry", "residentInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:8080/PublicServices/ResidentRegistry/XResidentRegistry", "name"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("birth");
        elemField.setXmlName(new javax.xml.namespace.QName("", "birth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:8080/PublicServices/ResidentRegistry/XResidentRegistry", "birth"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("death");
        elemField.setXmlName(new javax.xml.namespace.QName("", "death"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:8080/PublicServices/ResidentRegistry/XResidentRegistry", "death"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address");
        elemField.setXmlName(new javax.xml.namespace.QName("", "address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:8080/PublicServices/ResidentRegistry/XResidentRegistry", "address"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nationality");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nationality"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:8080/PublicServices/ResidentRegistry/XResidentRegistry", "nationality"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("religion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "religion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:8080/PublicServices/ResidentRegistry/XResidentRegistry", "religion"));
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
