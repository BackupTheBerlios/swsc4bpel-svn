/**
 * LookyBookServiceSoapStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package org.dyndns.dinoch.webservices.books;

public class LookyBookServiceSoapStub extends org.apache.axis.client.Stub implements org.dyndns.dinoch.webservices.books.LookyBookServiceSoap {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[3];
        org.apache.axis.description.OperationDesc oper;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetInfo");
        oper.addParameter(new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "ISBN"), new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.setReturnType(new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "bookInfo"));
        oper.setReturnClass(org.dyndns.dinoch.webservices.books.BookInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "GetInfoResult"));
        oper.setStyle(org.apache.axis.enum.Style.WRAPPED);
        oper.setUse(org.apache.axis.enum.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("DoKeywordSearch");
        oper.addParameter(new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "keyword"), new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.setReturnType(new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "ArrayOfBookInfo"));
        oper.setReturnClass(org.dyndns.dinoch.webservices.books.ArrayOfBookInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "DoKeywordSearchResult"));
        oper.setStyle(org.apache.axis.enum.Style.WRAPPED);
        oper.setUse(org.apache.axis.enum.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("DoKeywordSearchEx");
        oper.addParameter(new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "keyword"), new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.addParameter(new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "source"), new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.setReturnType(new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "ArrayOfBookInfo"));
        oper.setReturnClass(org.dyndns.dinoch.webservices.books.ArrayOfBookInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "DoKeywordSearchExResult"));
        oper.setStyle(org.apache.axis.enum.Style.WRAPPED);
        oper.setUse(org.apache.axis.enum.Use.LITERAL);
        _operations[2] = oper;

    }

    public LookyBookServiceSoapStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public LookyBookServiceSoapStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public LookyBookServiceSoapStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "bookInfoVendorprice");
            cachedSerQNames.add(qName);
            cls = org.dyndns.dinoch.webservices.books.BookInfoVendorprice.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "bookInfo");
            cachedSerQNames.add(qName);
            cls = org.dyndns.dinoch.webservices.books.BookInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "ArrayOfBookInfo");
            cachedSerQNames.add(qName);
            cls = org.dyndns.dinoch.webservices.books.ArrayOfBookInfo.class;
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
                    _call.setEncodingStyle(null);
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

    public org.dyndns.dinoch.webservices.books.BookInfo getInfo(java.lang.String ISBN) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://dinoch.dyndns.org/webservices/books/GetInfo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "GetInfo"));

        setRequestHeaders(_call);
        setAttachments(_call);
        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {ISBN});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.dyndns.dinoch.webservices.books.BookInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.dyndns.dinoch.webservices.books.BookInfo) org.apache.axis.utils.JavaUtils.convert(_resp, org.dyndns.dinoch.webservices.books.BookInfo.class);
            }
        }
    }

    public org.dyndns.dinoch.webservices.books.ArrayOfBookInfo doKeywordSearch(java.lang.String keyword) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://dinoch.dyndns.org/webservices/books/DoKeywordSearch");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "DoKeywordSearch"));

        setRequestHeaders(_call);
        setAttachments(_call);
        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {keyword});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.dyndns.dinoch.webservices.books.ArrayOfBookInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.dyndns.dinoch.webservices.books.ArrayOfBookInfo) org.apache.axis.utils.JavaUtils.convert(_resp, org.dyndns.dinoch.webservices.books.ArrayOfBookInfo.class);
            }
        }
    }

    public org.dyndns.dinoch.webservices.books.ArrayOfBookInfo doKeywordSearchEx(java.lang.String keyword, java.lang.String source) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://dinoch.dyndns.org/webservices/books/DoKeywordSearchEx");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dinoch.dyndns.org/webservices/books", "DoKeywordSearchEx"));

        setRequestHeaders(_call);
        setAttachments(_call);
        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {keyword, source});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.dyndns.dinoch.webservices.books.ArrayOfBookInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.dyndns.dinoch.webservices.books.ArrayOfBookInfo) org.apache.axis.utils.JavaUtils.convert(_resp, org.dyndns.dinoch.webservices.books.ArrayOfBookInfo.class);
            }
        }
    }

}
