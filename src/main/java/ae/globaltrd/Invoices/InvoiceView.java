package ae.globaltrd.Invoices;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InvoiceView {
  
  private Stage stage;
  private Parent parent;
  
  public InvoiceView(Stage stage, Parent parent) {
    this.stage = stage;
    this.parent = parent;
  }
  
  public void start() {
    this.stage.setScene(new Scene(this.parent, 500, 700));
    this.stage.setResizable(false);
    this.stage.show();
  }

}
