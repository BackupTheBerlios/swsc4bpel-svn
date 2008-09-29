

public class WSDLTestClientBookFinder {

    public static void main(String[] args)  {
    	try{
        System.out.println("*** Start Book Finder ***");
        org.dyndns.dinoch.webservices.books.LookyBookService lookyBookService = new    org.dyndns.dinoch.webservices.books.LookyBookServiceLocator();
         org.dyndns.dinoch.webservices.books.LookyBookServiceSoap servicesoap = lookyBookService.getLookyBookServiceSoap();
        org.dyndns.dinoch.webservices.books.ArrayOfBookInfo bookInfo =  servicesoap.doKeywordSearch("Bible");
        
      
      
     

        System.out.println("*** Result received ... ***");
    System.out.println("BookInfo: "+ bookInfo.getBookInfo()[0].getTitle());
        
        System.out.println("*** Finish VitalRecordsOffice Web Service client  ***");
}catch (Exception e){e.printStackTrace();}

    }
}
