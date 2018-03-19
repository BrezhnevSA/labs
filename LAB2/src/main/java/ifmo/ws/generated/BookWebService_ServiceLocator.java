/**
 * BookWebService_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 23, 2006 (07:25:28 PDT) WSDL2Java emitter.
 */

package ifmo.ws.generated;

public class BookWebService_ServiceLocator extends org.apache.axis.client.Service implements ifmo.ws.generated.BookWebService_Service {

    public BookWebService_ServiceLocator() {
    }


    public BookWebService_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public BookWebService_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BookWebServicePort
    private java.lang.String BookWebServicePort_address = "http://localhost:8081/lab2";

    public java.lang.String getBookWebServicePortAddress() {
        return BookWebServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BookWebServicePortWSDDServiceName = "BookWebServicePort";

    public java.lang.String getBookWebServicePortWSDDServiceName() {
        return BookWebServicePortWSDDServiceName;
    }

    public void setBookWebServicePortWSDDServiceName(java.lang.String name) {
        BookWebServicePortWSDDServiceName = name;
    }

    public ifmo.ws.generated.BookWebService_PortType getBookWebServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BookWebServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBookWebServicePort(endpoint);
    }

    public ifmo.ws.generated.BookWebService_PortType getBookWebServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ifmo.ws.generated.BookWebServicePortBindingStub _stub = new ifmo.ws.generated.BookWebServicePortBindingStub(portAddress, this);
            _stub.setPortName(getBookWebServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBookWebServicePortEndpointAddress(java.lang.String address) {
        BookWebServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (ifmo.ws.generated.BookWebService_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                ifmo.ws.generated.BookWebServicePortBindingStub _stub = new ifmo.ws.generated.BookWebServicePortBindingStub(new java.net.URL(BookWebServicePort_address), this);
                _stub.setPortName(getBookWebServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("BookWebServicePort".equals(inputPortName)) {
            return getBookWebServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.ws.ifmo/", "BookWebService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.ws.ifmo/", "BookWebServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BookWebServicePort".equals(portName)) {
            setBookWebServicePortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
