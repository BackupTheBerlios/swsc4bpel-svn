/**
 * LookyBookServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package org.dyndns.dinoch.webservices.books;

public interface LookyBookServiceSoap extends java.rmi.Remote {
    public org.dyndns.dinoch.webservices.books.BookInfo getInfo(java.lang.String ISBN) throws java.rmi.RemoteException;
    public org.dyndns.dinoch.webservices.books.ArrayOfBookInfo doKeywordSearch(java.lang.String keyword) throws java.rmi.RemoteException;
    public org.dyndns.dinoch.webservices.books.ArrayOfBookInfo doKeywordSearchEx(java.lang.String keyword, java.lang.String source) throws java.rmi.RemoteException;
}
