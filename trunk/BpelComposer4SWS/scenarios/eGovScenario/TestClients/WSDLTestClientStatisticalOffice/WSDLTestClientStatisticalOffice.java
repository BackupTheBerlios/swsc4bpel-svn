import localhost.PublicServices.StatisticalOffice.XStatistics.Acknowledgement;
import localhost.PublicServices.StatisticalOffice.XStatistics.OfficialCitizenDocumentType;
import StatisticalOffice_pkg.StatisticalOffice;
import StatisticalOffice_pkg.StatisticalOfficeService;
import StatisticalOffice_pkg.StatisticalOfficeServiceLocator;

public class WSDLTestClientStatisticalOffice {

    public static void main(String[] args)  {
    	try{
        System.out.println("*** Start StatisticalOffice Web Service client ***");
        StatisticalOfficeService statisticalOfficeService = new StatisticalOfficeServiceLocator();
        StatisticalOffice  statisticalOffice = statisticalOfficeService.getStatisticalOffice();
        //Input
        OfficialCitizenDocumentType documentType = new OfficialCitizenDocumentType();
        documentType.setIdentifier("XVitalRecords.BirthCertificate");

        //invoke
        System.out.println("*** Invoking StatisticalOffice->addToOfficialCitizenDocumentsStatistict ... ***");
        Acknowledgement ack = statisticalOffice.addToOfficialCitizenDocumentsStatistic(documentType);
        System.out.println("	Acknowledgement: "+ack.getMessage());
        System.out.println("*** FinishPublicServicePayment Web Service client  ***");
}catch (Exception e){e.printStackTrace();}

    }
}
