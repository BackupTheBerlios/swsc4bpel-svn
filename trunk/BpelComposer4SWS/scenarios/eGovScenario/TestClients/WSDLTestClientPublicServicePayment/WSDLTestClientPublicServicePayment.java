import localhost.PublicServices.PublicServicePayment.XPublicServicePayment.Account;
import localhost.PublicServices.PublicServicePayment.XPublicServicePayment.Payment;
import localhost.PublicServices.PublicServicePayment.XPublicServicePayment.Receipt;
import PublicServicePayment_pkg.PublicServicePayment;
import PublicServicePayment_pkg.PublicServicePaymentService;
import PublicServicePayment_pkg.PublicServicePaymentServiceLocator;

public class WSDLTestClientPublicServicePayment {

    public static void main(String[] args)  {
    	try{
        System.out.println("*** Start PublicServicePayment Web Service client ***");
        PublicServicePaymentService publicServicePaymentService = new PublicServicePaymentServiceLocator();
        PublicServicePayment  publicServicePayment =  publicServicePaymentService.getPublicServicePayment();
        //Input
        Account sourceAccount = new Account();
        sourceAccount.setAccountOwner("Prof. Dr. Hans Mustermann");
        sourceAccount.setAccountNumber("720103445");
        sourceAccount.setBankID("10070000");
        
        Account destAccount = new Account();
        destAccount.setAccountOwner("Office of Vital Records");
        destAccount.setAccountNumber("545470005");
        destAccount.setBankID("10070000");
        
        Payment payment = new Payment();
        payment.setSourceAccount(sourceAccount);
        payment.setDestAccount(destAccount);
        payment.setAmount("10.00");
        payment.setReasonForPayment("BirthCertificate");
      
        
        //invoke
        System.out.println("*** Invoking PublicServicePayment->makePayment ... ***");
       Receipt receipt =  publicServicePayment.makePayment(payment);
        System.out.println("*** Result received ... ***");
        //result
        System.out.println("*** Printing result ... ***");
        System.out.println("confirmation: " + receipt.getConfirmation());
        System.out.println("source: " + receipt.getConfirmedPayment().getSourceAccount().getAccountOwner());
         System.out.println("amount: " +  receipt.getConfirmedPayment().getAmount());

        System.out.println("*** FinishPublicServicePayment Web Service client  ***");
}catch (Exception e){e.printStackTrace();}

    }
}
