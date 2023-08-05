

package login;

import java.io.IOException;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;


public class LoginScreenController implements Initializable {
    
    public static Stage stage = null;
    public static String acc;
    
    @FXML
    private Pane main_area;
    
    @FXML
    private TextField accountno;
    
    @FXML
    private PasswordField pin;
   
    
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
     @FXML
    private void forgotPassword(MouseEvent event) throws IOException {
        
        Parent fxml= FXMLLoader.load(getClass().getResource("/forgotpass/ForgotPassword.fxml"));
        main_area.getChildren().removeAll(); 
        main_area.getChildren().addAll(fxml);
    }
    public void loginAccount(MouseEvent event){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank" , "root", "root");
            String sql = "SELECT * FROM userdata WHERE AccountNo=? and PIN=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, accountno.getText());
            ps.setString(2, pin.getText());
            acc = accountno.getText();
            //JOptionPane.showMessageDialog(null, acc);
             rs = ps.executeQuery();
            if(rs.next()){
             Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Acount Logged In");
                a.setHeaderText("You Have Succesfully Logged In");
                a.setContentText("Design Your Dashboard" );
                a.showAndWait();               
            }
            else
            {   
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setHeaderText("Error in login");
                a.setContentText("Your account number or pin is wrong. Enter again..!!!" );
                a.showAndWait();
            }
            
            
        }catch(Exception e){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setHeaderText("Error in login .");
                a.setContentText("There is some error. PLEASE TRY AGAIN..!!!");
                a.showAndWait();
            
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
