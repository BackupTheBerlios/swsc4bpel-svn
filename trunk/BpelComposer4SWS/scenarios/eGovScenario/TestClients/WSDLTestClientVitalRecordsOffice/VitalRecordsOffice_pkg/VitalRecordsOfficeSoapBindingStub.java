/**
 * VitalRecordsOfficeSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package VitalRecordsOffice_pkg;

public class VitalRecordsOfficeSoapBindingStub extends org.apache.axis.client.Stub implements VitalRecordsOffice_pkg.VitalRecordsOffice {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[2];
        org.apache.axis.description.OperationDesc oper;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("orderBirthCertificate");
        oper.addParameter(new javax.xml.namespace.QName("", "in0"), new javax.xml.namespace.QName("http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecords", "birthCertificateOrder"), localhost.PublicServices.VitalRecordsOffice.XVitalRecords.BirthCertificateOrder.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.setReturnType(new javax.xml.namespace.QName("http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecords", "acknowledgement"));
        oper.setReturnClass(localhost.PublicServices.VitalRecordsOffice.XVitalRecords.Acknowledgement.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "orderBirthCertificateReturn"));
        oper.setStyle(org.apache.axis.enum.Style.RPC);
        oper.setUse(org.apache.axis.enum.Use.ENCODED);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getVitalRecordsDocumentInfo");
        oper.addParameter(new javax.xml.namespace.QName("", "in0"), new javax.xml.namespace.QName("http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecords", "vitalRecordsDocumentType"), localhost.PublicServices.VitalRecordsOffice.XVitalRecords.VitalRecordsDocumentType.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.setReturnType(new javax.xml.namespace.QName("http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecords", "vitalRecordsDocumentInfo"));
        oper.setReturnClass(localhost.PublicServices.VitalRecordsOffice.XVitalRecords.VitalRecordsDocumentInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getVitalRecordsDocumentInfoReturn"));
        oper.setStyle(org.apache.axis.enum.Style.RPC);
        oper.setUse(org.apache.axis.enum.Use.ENCODED);
        _operations[1] = oper;

    }

    public VitalRecordsOfficeSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public VitalRecordsOfficeSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public VitalRecordsOfficeSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecords", "paymentReceipt");
            cachedSerQNames.add(qName);
            cls = localhost.PublicServices.VitalRecordsOffice.XVitalRecords.PaymentReceipt.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecords", "person");
            cachedSerQNames.add(qName);
            cls = localhost.PublicServices.VitalRecordsOffice.XVitalRecords.Person.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecords", "postalAddress");
            cachedSerQNames.add(qName);
            cls = localhost.PublicServices.VitalRecordsOffice.XVitalRecords.PostalAddress.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecords", "acknowledgement");
            cachedSerQNames.add(qName);
            cls = localhost.PublicServices.VitalRecordsOffice.XVitalRecords.Acknowledgement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecords", "vitalRecordsDocumentType");
            cachedSerQNames.add(qName);
            cls = localhost.PublicServices.VitalRecordsOffice.XVitalRecords.VitalRecordsDocumentType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecords", "birthCertificateOrder");
            cachedSerQNames.add(qName);
            cls = localhost.PublicServices.VitalRecordsOffice.XVitalRecords.BirthCertificateOrder.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://localhost:8080/PublicServices/VitalRecordsOffice/XVitalRecords", "vitalRecordsDocumentInfo");
            cachedSerQNames.add(qName);
            cls = localhost.PublicServices.VitalRecordsOffice.XVitalRecords.VitalRecordsDocumentInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://localhost:8080/PublicServices/PublicServicePayment/XPublicServicePayment", "account");
            cachedSerQNames.add(qName);
            cls = localhost.PublicServices.PublicServicePayment.XPublicServicePayment.Account.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    private org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call =
                    (org.apache.axis.client.Call) super.service.createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
                    _call.setEncodingStyle(org.apache.axis.Constants.URI_SOAP11_ENC);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                        java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                        _call.registerTypeMapping(cls, qName, sf, df, false);
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", t);
        }
    }

    public localhost.PublicServices.VitalRecordsOffice.XVitalRecords.Acknowledgement orderBirthCertificate(localhost.PublicServices.VitalRecordsOffice.XVitalRecords.BirthCertificateOrder in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:VitalRecordsOffice", "orderBirthCertificate"));

        setRequestHeaders(_call);
        setAttachments(_call);
        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (localhost.PublicServices.VitalRecordsOffice.XVitalRecords.Acknowledgement) _resp;
            } catch (java.lang.Exception _exception) {
                return (localhost.PublicServices.VitalRecordsOffice.XVitalRecords.Acknowledgement) org.apache.axis.utils.JavaUtils.convert(_resp, localhost.PublicServices.VitalRecordsOffice.XVitalRecords.Acknowledgement.class);
            }
        }
    }

    public localhost.PublicServices.VitalRecordsOffice.XVitalRecords.VitalRecordsDocumentInfo getVitalRecordsDocumentInfo(localhost.PublicServices.VitalRecordsOffice.XVitalRecords.VitalRecordsDocumentType in0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:VitalRecordsOffice", "getVitalRecordsDocumentInfo"));

        setRequestHeaders(_call);
        setAttachments(_call);
        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (localhost.PublicServices.VitalRecordsOffice.XVitalRecords.VitalRecordsDocumentInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (localhost.PublicServices.VitalRecordsOffice.XVitalRecords.VitalRecordsDocumentInfo) org.apache.axis.utils.JavaUtils.convert(_resp, localhost.PublicServices.VitalRecordsOffice.XVitalRecords.VitalRecordsDocumentInfo.class);
            }
        }
    }

}
