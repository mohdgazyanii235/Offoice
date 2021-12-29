package ae.globaltrd.screenManager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class Screen1Controller implements Initializable, ControlledScreen{
  
  ScreensController myController;

  @Override
  public void setScreenParent(ScreensController screenParent) {
    this.myController = screenParent;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO : add something here later.
  }
  
  
  @FXML
  private Button bo1;
  @FXML
  private Button bo2;
  
  @FXML
  private void goToScreen2(ActionEvent event) {
    myController.setScreen(ScreensFramework.screen2ID);
  }
  
  @FXML
  private void goToScreen3(ActionEvent event) {
    myController.setScreen(ScreensFramework.screen3ID);
  }

}
