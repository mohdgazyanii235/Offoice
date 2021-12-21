package ae.globaltrd;

import java.util.ArrayList;

public class Invoice {
  /*
   * Contents of an invoice: 1) Title -> our company name and invoice. 2) Invoicee -> invoicee
   * address and company name. 3) Invoicer -> our phone number and and address. 4) Delivery details
   * -> container details and port of docking.
   * 
   * Attributes: invoice number -> String date -> String/Date company name -> String company Address
   * -> ArrayList<String> invoicesEntries -> ArrayList<InvoiceEntries>
   */

  private String invoiceNumber;
  private String date;
  private String invoiceeName;
  private ArrayList<String> invoiceeAddress;
  private ArrayList<InvoiceEntry> invoiceEntries;
  private ArrayList<InvoiceCharge> invoiceCharges;
  private String containerDetails;
  private String portOfDocking;
  private String BlNumber;
  private double invoiceTotal;


  public Invoice(String invoiceNumber) {
    this.invoiceNumber = invoiceNumber;
    this.invoiceeAddress = new ArrayList<String>();
    this.invoiceEntries = new ArrayList<InvoiceEntry>();
    this.invoiceCharges = new ArrayList<InvoiceCharge>();
    this.BlNumber = "";
    this.date = "";
    this.portOfDocking = "";
    this.containerDetails = "";
    this.invoiceTotal = 0;
  }
  
  public Invoice() {
    this.invoiceNumber = "";
    this.BlNumber = "";
    this.date = "";
    this.portOfDocking = "";
    this.containerDetails = "";
    this.invoiceTotal = 0;
    this.invoiceeAddress = new ArrayList<String>();
    this.invoiceEntries = new ArrayList<InvoiceEntry>();
    this.invoiceCharges = new ArrayList<InvoiceCharge>();
  }


  public String getInvoiceNumber() {
    return invoiceNumber;
  }

  public void setInvoiceNumber(String invoiceNumber) {
    this.invoiceNumber = invoiceNumber;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getInvoiceeName() {
    return invoiceeName;
  }

  public void setInvoiceeName(String invoiceeName) {
    this.invoiceeName = invoiceeName;
  }

  public ArrayList<String> getInvoiceeAddress() {
    return this.invoiceeAddress;
  }

  public void setInvoiceeAddress(ArrayList<String> invoiceeAddress) {
    this.invoiceeAddress = invoiceeAddress;
  }

  public ArrayList<InvoiceEntry> getInvoiceEntries() {
    return this.invoiceEntries;
  }

  public void addInvoiceEntries(InvoiceEntry invoiceEntry) {
    this.invoiceEntries.add(invoiceEntry);
  }
  
  public ArrayList<InvoiceCharge> getInvoiceCharges(){
    return this.invoiceCharges;
  }
  
  public void addInvoiceCharges(InvoiceCharge invoiceCharge) {
    this.invoiceCharges.add(invoiceCharge);
  }

  public String getContainerDetails() {
    return containerDetails;
  }

  public void setContainerDetails(String containerDetails) {
    this.containerDetails = containerDetails;
  }

  public String getPortOfDocking() {
    return portOfDocking;
  }

  public void setPortOfDocking(String portOfDocking) {
    this.portOfDocking = portOfDocking;
  }

  public void generatePdf(String location) throws Exception {
    PdfGenerator pdfGenerator = new PdfGenerator(this, location);
    pdfGenerator.start();
  }

  public String getBlNumber() {
    return this.BlNumber;
  }

  public void setBlNumber(String blNumber) {
    this.BlNumber = blNumber;
  }

  public double getInvoiceTotal() {
    for(int i = 0; i < this.invoiceEntries.size(); i++) {
      this.invoiceTotal += this.invoiceEntries.get(i).getTotal();
    }
    return this.invoiceTotal;
  }

}
