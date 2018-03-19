/**
 * CarWebService_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 23, 2006 (07:25:28 PDT) WSDL2Java emitter.
 */

package .ifmo.ws.generated;

public interface CarWebService_PortType extends java.rmi.Remote {
    public ru.b00blik.ifmo.ws.generated.Car[] getCars() throws java.rmi.RemoteException;
    public ru.b00blik.ifmo.ws.generated.Car[] getToyotas() throws java.rmi.RemoteException;
    public int insertNewEntry(java.lang.String label, java.lang.String model, int year, int distance) throws java.rmi.RemoteException;
    public int updateCarEntry(int id, java.lang.String label, java.lang.String model, int year, int distance) throws java.rmi.RemoteException;
    public int removeCarEntry(int id) throws java.rmi.RemoteException;
    public ru.b00blik.ifmo.ws.generated.Car[] getLongDistanceCars() throws java.rmi.RemoteException;
}
