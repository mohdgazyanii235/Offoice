package ae.globaltrd.screenManager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class Screen2Controller implements Initializable, ControlledScreen{
  ScreensController myController;

  @Override
  public void setScreenParent(ScreensController screenParent) {
    myController = screenParent;
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
  private void goToScreen1(ActionEvent event) {
    myController.setScreen(ScreensFramework.screen1ID);
  }

  @FXML
  private void goToScreen3(ActionEvent event) {
    myController.setScreen(ScreensFramework.screen3ID);
  }
}
