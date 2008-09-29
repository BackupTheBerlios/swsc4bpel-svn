/**
 * VitalRecordsOfficeSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package VitalRecordsOffice_pkg;

public class VitalRecordsOfficeSoapBindingImpl implements VitalRecordsOffice_pkg.VitalRecordsOffice{
    public localhost.PublicServices.VitalRecordsOffice.XVitalRecords.Acknowledgement orderBirthCertificate(localhost.PublicServices.VitalRecordsOffice.XVitalRecords.BirthCertificateOrder in0) throws java.rmi.RemoteException {
          System.out.println("<><><><><><><><><><><><><><><><>");
          System.out.println("Running WSDL Web service  orderBirthCertificate ...");
         
         localhost.PublicServices.VitalRecordsOffice.XVitalRecords.VitalRecordsDocumentType document = new localhost.PublicServices.VitalRecordsOffice.XVitalRecords.VitalRecordsDocumentType();
         document.setId("XVitalRecords.BirthCertificate");
         document.setDescription("Official Birth Certificate holding all relevant information about a person's birth");
         localhost.PublicServices.VitalRecordsOffice.XVitalRecords.Acknowledgement ack = new localhost.PublicServices.VitalRecordsOffice.XVitalRecords.Acknowledgement();
         ack.setDocument(document);
         ack.setRecipientPostalAddress(in0.getPostalAddress());  
         System.out.println("<><><><><><><><><><><><><><><><>");
         return ack;
    }

    public localhost.PublicServices.VitalRecordsOffice.XVitalRecords.VitalRecordsDocumentInfo getVitalRecordsDocumentInfo(localhost.PublicServices.VitalRecordsOffice.XVitalRecords.VitalRecordsDocumentType in0) throws java.rmi.RemoteException {
       
         System.out.println("<><><><><><><><><><><><><><><><>");
         System.out.println("Running WSDL Web service  getVitalRecordsDocumentInfo ...");
       
        localhost.PublicServices.VitalRecordsOffice.XVitalRecords.VitalRecordsDocumentInfo info = new localhost.PublicServices.VitalRecordsOffice.XVitalRecords.VitalRecordsDocumentInfo();
       
       // create static data
        info.setPrice("10.00");
        info.setVitalRecordsDocumentType(in0);
        localhost.PublicServices.PublicServicePayment.XPublicServicePayment.Account paymentAccount = new  localhost.PublicServices.PublicServicePayment.XPublicServicePayment.Account();
        paymentAccount.setAccountOwner("Berlin Vital Records Office");
        paymentAccount.setAccountNumber("700101000");
        paymentAccount.setBankID("10050000");
        info.setPaymentAccount(paymentAccount);
        
        System.out.println("<><><><><><><><><><><><><><><><>");
        
        return info;
    }

}
