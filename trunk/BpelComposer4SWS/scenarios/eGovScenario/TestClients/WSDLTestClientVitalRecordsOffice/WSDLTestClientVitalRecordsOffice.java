import localhost.PublicServices.VitalRecordsOffice.XVitalRecords.Acknowledgement;
import localhost.PublicServices.VitalRecordsOffice.XVitalRecords.BirthCertificateOrder;
import localhost.PublicServices.VitalRecordsOffice.XVitalRecords.PaymentReceipt;
import localhost.PublicServices.VitalRecordsOffice.XVitalRecords.Person;
import localhost.PublicServices.VitalRecordsOffice.XVitalRecords.PostalAddress;
import localhost.PublicServices.VitalRecordsOffice.XVitalRecords.VitalRecordsDocumentInfo;
import localhost.PublicServices.VitalRecordsOffice.XVitalRecords.VitalRecordsDocumentType;
import VitalRecordsOffice_pkg.VitalRecordsOffice;
import VitalRecordsOffice_pkg.VitalRecordsOfficeService;
import VitalRecordsOffice_pkg.VitalRecordsOfficeServiceLocator;

public class WSDLTestClientVitalRecordsOffice {

    public static void main(String[] args)  {
    	try{
        System.out.println("*** Start VitalRecordsOffice Web Service client ***");
        VitalRecordsOfficeService vitalRecordsOfficeService = new VitalRecordsOfficeServiceLocator();
        VitalRecordsOffice  vitalRecordsOffice =  vitalRecordsOfficeService.getVitalRecordsOffice();
        
        // getPriceForDocument
        //Input
        VitalRecordsDocumentType docType = new VitalRecordsDocumentType();
        docType.setId("XVitalRecords.BirthCertificate");
        docType.setDescription("Official Birth Certificate holding all relevant information about a person's birth");
      
        //invoke
        System.out.println("*** Invoking VitalRecordsOffice->getVitalRecorsDocumentInfo ... ***");
         VitalRecordsDocumentInfo info  =  vitalRecordsOffice.getVitalRecordsDocumentInfo(docType);
        System.out.println("*** Result received ... ***");
        //result
        System.out.println("*** Printing result ... ***");
        System.out.println("		price: " +info.getPrice());
         System.out.println("		account owner: " +info.getPaymentAccount().getAccountOwner());
          System.out.println("		document type: " +info.getVitalRecordsDocumentType().getId());
        
        // orderBirthCertificate
        //Input
        PaymentReceipt receipt = new PaymentReceipt();
        receipt.setConfirm("ok");
        Person person = new Person();
        person.setFirstName("Hans");
        person.setLastName("Mustermann");
        person.setNameAtBirth("Mustermann");
        person.setDateOfBirth("3/12/1971");
        person.setPlaceOfBirth("Berlin");
        PostalAddress address = new PostalAddress();
        address.setRecipient("Hans Mustermann");
        address.setStreetAddress("Nehringstrasse 17");
        address.setZipcodeAndPostalDistrict("14059 Berlin");
        BirthCertificateOrder order = new BirthCertificateOrder();
        order.setPaymentReceipt(receipt);
        order.setPerson(person);
        order.setPostalAddress(address);
     
        //invoke
        System.out.println("\n*** Invoking VitalRecordsOffice->orderBirthCertificate ... ***");
        Acknowledgement ack =  vitalRecordsOffice.orderBirthCertificate(order);
        System.out.println("*** Result received ... ***");
        //result
        System.out.println("*** Printing result ... ***");
        System.out.println("		ack:");
        System.out.println("			VitalRecordsDocumentType: " +ack.getDocument().getId());
        System.out.println("		recipientPostalAddress:");
        System.out.println("			recipient: " +ack. getRecipientPostalAddress().getRecipient());
        System.out.println("			streetAddress: " +ack. getRecipientPostalAddress().getStreetAddress());
        
        
        System.out.println("*** Finish VitalRecordsOffice Web Service client  ***");
}catch (Exception e){e.printStackTrace();}

    }
}
