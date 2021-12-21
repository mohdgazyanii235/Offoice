package ae.globaltrd;

public class InvoiceCharge {
  private String chargeName;
  private double cost;
  private double quantity;
  private double total;
  
  public InvoiceCharge(String chargeName, double cost, double quantity) {
    this.chargeName = chargeName;
    this.cost = cost;
    this.quantity = quantity;
    this.total = this.cost * this.quantity;
  }
  
  public double getTotal() {
    return this.total;
  }

  public String getChargeName() {
    return this.chargeName;
  }

  public double getChargeCost() {
    return this.cost;
  }

  public double getChargeQuantity() {
    return this.quantity;
  }
  
}
