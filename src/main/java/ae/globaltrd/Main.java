package ae.globaltrd;

import java.util.ArrayList;

public class Main {

  public static void main(String[] args) throws Exception {
    Invoice firstInvoice = new Invoice();
    
   
    ArrayList<String> companyAddress = new ArrayList<String>();
    companyAddress.add("Cape Town");
    companyAddress.add("South Africa");
    
    
    
    
    firstInvoice.setInvoiceeName("Brean Imports");
    firstInvoice.setInvoiceeAddress(companyAddress);
    firstInvoice.setContainerDetails("1 X 40 hc");
    firstInvoice.setPortOfDocking("port elizabeth");
    firstInvoice.setInvoiceNumber("GLT/063/2021");
    firstInvoice.addInvoiceEntries(new InvoiceEntry("plywood", 5, 20));
    firstInvoice.addInvoiceEntries(new InvoiceEntry("chipboard", 60, 2));
    firstInvoice.addInvoiceEntries(new InvoiceEntry("idk", 80, 2));
    
    firstInvoice.addInvoiceCharges(new InvoiceCharge("transport charges", 1006, 1));
    firstInvoice.addInvoiceCharges(new InvoiceCharge("seafreight", 2550, 1));
    
    
    
    // run every thing.
    firstInvoice.generatePdf("src/main/resources/test.xls");
    
    
  }

}


