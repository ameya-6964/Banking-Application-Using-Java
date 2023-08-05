
package login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Banking extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        try {
             Parent root = FXMLLoader.load(Banking.class.getResource("LoginScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        } catch (Exception e) {
            System.out.println("The Error Is : " + e.getStackTrace());
        }
       
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
