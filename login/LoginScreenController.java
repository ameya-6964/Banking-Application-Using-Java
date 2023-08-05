

package login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class LoginScreenController implements Initializable {
    
    @FXML
    private Pane main_area;
   
    
    @FXML
    private void closeApp(MouseEvent event) {
        Platform.exit();
        System.exit(0);
        
    }
    
     @FXML
    private void createAccount(MouseEvent event) throws IOException {
        
        Parent fxml= FXMLLoader.load(getClass().getResource("/createaccount/CreateAccount.fxml"));
         main_area.getChildren().removeAll();
         main_area.getChildren().addAll(fxml);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
