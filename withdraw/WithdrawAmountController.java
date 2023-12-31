
package withdraw;


import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import login.LoginScreenController;


public class WithdrawAmountController implements Initializable {
    
    
    
    @FXML 
    private Label account_no;
    
    @FXML 
    private Label balance;
    
    @FXML 
    private TextField amt_field;
    
    @FXML 
    private TextField pin_field;
    
    Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    int hour = cal.get(Calendar.HOUR);
    int minutes = cal.get(Calendar.MINUTE);
    int seconds = cal.get(Calendar.SECOND);
    int daynight = cal.get(Calendar.AM_PM);
    
    DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
    Date d = new Date();
    String date = dateformat.format(d);
    
    LocalTime localTime = LocalTime.now();
    DateTimeFormatter dt = DateTimeFormatter.ofPattern("hh:mm:ss a");
    String time = localTime.format(dt);
            
    
    

    public void setInfo(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank" , "root", "root");
            String sql = "SELECT * FROM userdata WHERE AccountNo=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, LoginScreenController.acc);
            
            
             rs = ps.executeQuery();
            if(rs.next()){
               account_no.setText(rs.getString("AccountNo"));
               balance.setText(rs.getString("Balance"));
               
               
                
                
            }
            
            
            
        }catch(Exception e){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setHeaderText("Error in login .");
                a.setContentText("There is some error. PLEASE TRY AGAIN..!!!"+e.getMessage());
                a.showAndWait();
            
        }
    }
    
    public void widhdrawButton(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank" , "root", "root");
            String sql = "SELECT * FROM userdata WHERE AccountNo=? AND PIN=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, LoginScreenController.acc);
            ps.setString(2,pin_field.getText());
            
            
             rs = ps.executeQuery();
            if(rs.next()){
               int wda = Integer.parseInt(amt_field.getText());
               int ta = Integer.parseInt(balance.getText());
               if(wda>ta){
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Error");
                    a.setHeaderText("Error in Withdraw");
                    a.setContentText("Your account does not have enough balance ..!!!" );
                    a.showAndWait(); 
               }
               else{
                   int total = ta-wda;
                   String sql1 = "UPDATE userdata SET Balance='"+total+"' WHERE AccountNo ='"+LoginScreenController.acc+"'";
                   ps = con.prepareStatement(sql1);
                   ps.execute();
                   String sql2 ="INSERT INTO withdraw(AccountNo, withdrawAmount, RemainingAmount, Date, Time) VALUES (?,?,?,?,?)";
                   ps = con.prepareStatement(sql2);
                   ps.setString(1,LoginScreenController.acc);
                   ps.setString(2,String.valueOf(wda));
                   ps.setString(3,String.valueOf(total));
                   ps.setString(4,date);
                   ps.setString(5,time);
                   
                   int i = ps.executeUpdate();
                   if(i>0){
                       Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Amount Withdraw");
                a.setHeaderText("Amount Withdrawal Sucessfull ");
                a.setContentText("Amount "+ wda+" has been sucessfully withdrawn "+"\nCurrent Balance = "+total );
                a.showAndWait();
                
                amt_field.setText("");
                pin_field.setText("");
                balance.setText(String.valueOf(total));
                   }
                   
               }
          
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
                a.setContentText("There is some error. PLEASE TRY AGAIN..!!!"+e.getMessage());
                a.showAndWait();
            
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setInfo();
    }    
    
}
