import localhost.PublicServices.SemanticBridgeTest.XTest.Address;
import localhost.PublicServices.SemanticBridgeTest.XTest.Letter;
import Test_pkg.Test;
import Test_pkg.TestService;
import Test_pkg.TestServiceLocator;

public class WSDLTestClientSemanticBridgeTest {

    public static void main(String[] args)  {
    	try{
        System.out.println("*** Start Test Web Service client ***");
        TestService testService = new TestServiceLocator();
        Test  test =  testService.getTest();
        
        // getPriceForDocument
        //Input
        Address address = new Address();
        address.setRecipient("Hans Meier");
        address.setStreetAddress("Nieburstr. 3");
        address.setZipcodeAndPostalDistrict("10629 Berlin");
      
        //invoke
        System.out.println("*** Invoking Test->performTestt ... ***");
        Letter letter =  test.performTest(address);
        System.out.println("*** Result received ... ***");
        //result
        System.out.println("*** Printing result ... ***");
        System.out.println("title: " +letter.getTitle());
        System.out.println("content: " +letter.getContent());
         
       
        System.out.println("*** Finish VitalRecordsOffice Web Service client  ***");
}catch (Exception e){e.printStackTrace();}

    }
}
