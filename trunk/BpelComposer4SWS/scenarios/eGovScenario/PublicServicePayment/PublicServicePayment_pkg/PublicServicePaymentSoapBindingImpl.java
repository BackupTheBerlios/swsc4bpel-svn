/**
 * PublicServicePaymentSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package PublicServicePayment_pkg;

public class PublicServicePaymentSoapBindingImpl implements PublicServicePayment_pkg.PublicServicePayment{
    public localhost.PublicServices.PublicServicePayment.XPublicServicePayment.Receipt makePayment(localhost.PublicServices.PublicServicePayment.XPublicServicePayment.Payment in0) throws java.rmi.RemoteException {
        System.out.println("<><><><><><><><><><><><><><><><>");
        System.out.println("Running WSDL Web service makePayment ...");
        
        localhost.PublicServices.PublicServicePayment.XPublicServicePayment.Receipt receipt = new  localhost.PublicServices.PublicServicePayment.XPublicServicePayment.Receipt();
        receipt.setConfirmedPayment(in0);
        receipt.setConfirmation("RSH#7A3H92E77C42B7A3H92E77C42B7A3H92E77C42B7A3H92E77C42B");
        System.out.println("<><><><><><><><><><><><><><><><>");
        return receipt;
    }

}

