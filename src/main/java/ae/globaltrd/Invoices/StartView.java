package ae.globaltrd.Invoices;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartView extends Application{
  
  private static volatile StartView instance = null;
  
  @FXML
  void initialize() {
    instance = this;
  }
  
  public static synchronized StartView getInstance() {
    if (instance == null) {
      new Thread(() -> Application.launch(StartView.class)).start();
      while (instance == null) {
      }
    }
    return instance;
  }
  
  // Singleton stuff above this.
  
  private Stage stage;
  private Parent start;
  
  
  @FXML
  void generateButtonPressed() throws IOException {
    
  }
  
  @FXML 
  void searchButtonPressed(){
    
  }
  
  
  // start page code above this.

  @Override
  public void start(Stage primaryStage) throws Exception {
    this.stage = primaryStage;
    this.start = FXMLLoader.load(getClass().getResource("/ae/globaltrd/StartView.fxml"));
    Scene scene = new Scene(this.start, 500, 700);
    this.stage.setScene(scene);
    this.stage.setResizable(false);
    this.stage.show();
  }

  public void runJavaFx() {
    System.out.println("Starting OFFOICE");
  }
  
  

}


















