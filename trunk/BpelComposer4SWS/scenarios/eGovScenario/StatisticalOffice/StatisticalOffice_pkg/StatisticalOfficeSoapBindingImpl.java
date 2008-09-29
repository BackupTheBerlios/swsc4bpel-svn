/**
 * StatisticalOfficeSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package StatisticalOffice_pkg;

public class StatisticalOfficeSoapBindingImpl implements StatisticalOffice_pkg.StatisticalOffice{
    public localhost.PublicServices.StatisticalOffice.XStatistics.Acknowledgement addToOfficialCitizenDocumentsStatistic(localhost.PublicServices.StatisticalOffice.XStatistics.OfficialCitizenDocumentType in0) throws java.rmi.RemoteException {
         
        System.out.println("<><><><><><><><><><><><><><><><>");
        System.out.println("Running WSDL Web service addToOfficialCitizenDocumentsStatistic ...");
         
         localhost.PublicServices.StatisticalOffice.XStatistics.Acknowledgement ack = new  localhost.PublicServices.StatisticalOffice.XStatistics.Acknowledgement();
        ack.setMessage("OfficialCitizenDocumentType "+in0.getIdentifier()+ " has been received by Statistical Office"); 
        
        System.out.println("<><><><><><><><><><><><><><><><>");
        return ack;
    }

}
