import localhost.PublicServices.ResidentRegistry.XResidentRegistry.ResidentInfo;
import localhost.PublicServices.ResidentRegistry.XResidentRegistry.ResidentSearchProfile;
import ResidentRegistry_pkg.ResidentRegistry;
import ResidentRegistry_pkg.ResidentRegistryService;
import ResidentRegistry_pkg.ResidentRegistryServiceLocator;

public class WSDLTestClientResidentRegistry {

    public static void main(String[] args)  {
    	try{
        System.out.println("*** Start ResidentRegistry Web Service client ***");
        ResidentRegistryService residentRegistryService = new ResidentRegistryServiceLocator();
        ResidentRegistry  residentRegistry =  residentRegistryService.getResidentRegistry();
        //Input
        ResidentSearchProfile profile = new ResidentSearchProfile();
        profile.setGivenName("Hans");
        profile.setSurname("Mustermann");
        profile.setDateOfBirth("1949-11-3");
        
        //invoke
        System.out.println("*** Invoking ResidentRegistry->getResidentInfo ... ***");
       ResidentInfo info =  residentRegistry.getResidentInfo(profile);
        System.out.println("*** Result received ... ***");
        //result
        System.out.println("*** Printing result ... ***");
        System.out.println("givenName: " + info.getName().getGivenName());
        System.out.println("surname: " + info.getName().getSurname());
         System.out.println("address: " + info.getAddress().getStreet()+" "+ info.getAddress().getStreetNumber()+" "+ info.getAddress().getZipCode());

        System.out.println("*** Finish ResidentRegistry Web Service client  ***");
}catch (Exception e){e.printStackTrace();}

    }
}
