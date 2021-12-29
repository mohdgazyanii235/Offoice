package ae.globaltrd.Invoices;

public class InvoiceEntry {

  private String productName;
  private double productQuantity;
  private double productPrice;
  private double productTotal;


  public InvoiceEntry() {}

  public InvoiceEntry(String productName) {
    this.productName = productName;
  }

  public InvoiceEntry(String productName, double productQuantity, double productPrice) {
    this.productName = productName;
    this.productQuantity = productQuantity;
    this.productPrice = productPrice;
    this.productTotal = this.productPrice * this.productQuantity;
  }

  public String getProductName() {
    return this.productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public double getProductQuantity() {
    return this.productQuantity;
  }

  public void setProductQuantity(double productQuantity) {
    this.productQuantity = productQuantity;
  }

  public double getProductPrice() {
    return this.productPrice;
  }

  public void setProductPrice(double productPrice) {
    this.productPrice = productPrice;
  }

  public double getTotal() {
    return this.productTotal;
  }
  
}
