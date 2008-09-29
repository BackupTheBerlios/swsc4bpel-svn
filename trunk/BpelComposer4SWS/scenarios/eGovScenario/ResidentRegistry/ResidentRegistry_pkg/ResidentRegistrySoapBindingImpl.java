/**
 * ResidentRegistrySoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package ResidentRegistry_pkg;
import localhost.PublicServices.ResidentRegistry.XResidentRegistry.*;

public class ResidentRegistrySoapBindingImpl implements ResidentRegistry_pkg.ResidentRegistry{
    public localhost.PublicServices.ResidentRegistry.XResidentRegistry.ResidentInfo getResidentInfo(localhost.PublicServices.ResidentRegistry.XResidentRegistry.ResidentSearchProfile in0) throws java.rmi.RemoteException {
          System.out.println("<><><><><><><><><><><><><><><><>");
          System.out.println("Running WSDL Web service  getResidentInfo ...");
        
 
          Address address = new Address();
          address.setStreet("Nehringstrasse");
          address.setStreetNumber("17");
          address.setZipCode("14059");
          address.setLocality("Berlin");
          Birth birth = new Birth();
          birth.setDateOfBirth("1949-11-3");
          birth.setPlaceOfBirth("Berlin");
          Death death = null;
          Name name = new Name();
          name.setGivenName(in0.getGivenName());
          name.setMiddleNames("Jochen");
          name.setSurname(in0.getSurname());
          Nationality nationality = new Nationality();
          nationality.setKey("F");
          Religion religion = null;
          ResidentInfo info = new ResidentInfo();
          info.setName(name);
          info.setBirth(birth);
          info.setDeath(death);
          info.setAddress(address);
          info.setNationality(nationality);
          info.setReligion(religion);
            System.out.println("<><><><><><><><><><><><><><><><>");
          return info;
    }

}
