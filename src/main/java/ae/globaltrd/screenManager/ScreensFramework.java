package ae.globaltrd.screenManager;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScreensFramework extends Application {
  
  public static String screen1ID = "main";
  public static String screen1File = "/ae/globaltrd/screenManager/Screen1.fxml";
  public static String screen2ID = "screen2";
  public static String screen2File = "/ae/globaltrd/screenManager/Screen2.fxml";
  public static String screen3ID = "Screen3";
  public static String screen3File = "/ae/globaltrd/screenManager/Screen3.fxml";

  @Override
  public void start(Stage primaryStage) throws Exception {
    ScreensController mainController = new ScreensController();
    mainController.loadScreen(screen1ID, screen1File);
    mainController.loadScreen(screen2ID, screen2File);
    mainController.loadScreen(screen3ID, screen3File);
    
    mainController.setScreen(screen1ID);
    
    
    Group root = new Group();
    root.getChildren().addAll(mainController);
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  public static void main(String[] args) {
    launch(args);
  }
 

}
