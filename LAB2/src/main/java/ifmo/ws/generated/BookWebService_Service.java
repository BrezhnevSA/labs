/**
 * BookWebService_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 23, 2006 (07:25:28 PDT) WSDL2Java emitter.
 */

package ru.b00blik.ifmo.ws.generated;

public interface BookWebService_Service extends javax.xml.rpc.Service {
    public java.lang.String getBookWebServicePortAddress();

    public ru.b00blik.ifmo.ws.generated.BookWebService_PortType getBookWebServicePort() throws javax.xml.rpc.ServiceException;

    public ru.b00blik.ifmo.ws.generated.BookWebService_PortType getBookWebServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
