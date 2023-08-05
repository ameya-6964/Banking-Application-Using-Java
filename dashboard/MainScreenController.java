
package dashboard;


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import login.LoginScreenController;


public class MainScreenController implements Initializable {

    @FXML
    private Label name;
    
    @FXML
    private Label body;
    
    
    
    public void setInfo(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank" , "root", "root");
            String sql = "SELECT * FROM userdata WHERE AccountNo=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, LoginScreenController.acc);
            
            
             rs = ps.executeQuery();
            if(rs.next()){
               name.setText(rs.getString("Name"));
               
               
                
                
            }
            else
            {   Alert a = new Alert(Alert.AlertType.ERROR);
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
        body.setText("The Blue Foundry Bank Group, Inc. is an American multinational investment bank and financial services company. Founded in 1869, Goldman Sachs is headquartered at 200 West Street in Lower Manhattan, with regional headquarters in London, Warsaw, Bangalore, Hong Kong, Tokyo, Dallas and Salt Lake City, and additional offices in other international financial centers.Goldman Sachs is the second largest investment bank in the world by revenue and is ranked 55th on the Fortune 500 list of the largest United States corporations by total revenue, It is considered a systemically important financial institution by the Financial Stability Board.");
        setInfo();
    }    
    
}
