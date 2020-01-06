package soccer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EditTransferController {
    
    public static void showInfo(String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Information");
        alert.setHeaderText(title);

        alert.showAndWait();
    }

    @FXML
    private TextField pid;

    @FXML
    private TextField pname;

    @FXML
    private TextField ncid;

    @FXML
    private TextField transferfee;

    @FXML
    private TextField ocname;

    @FXML
    private TextField ocid;

    @FXML
    private TextField ncname;

    @FXML
    private Button editButton;

    @FXML
    private Button backButton;
    
    @FXML
    public void initialize() throws ClassNotFoundException, SQLException{
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Soccer", "soccer");
        String query="select * from transfer where player_id=? and old_club_id=? and new_club_id=?";
        PreparedStatement pre=con.prepareStatement(query);
        pre.setString(1, Main.id);
        pre.setString(2, Main.caid);
        pre.setString(3, Main.cbid);
        ResultSet re=pre.executeQuery();
        while(re.next())
        {
            pid.setText(re.getString("player_id"));
            ocid.setText(re.getString("old_club_id"));
            ncid.setText(re.getString("new_club_id"));
            transferfee.setText(re.getString("transfer_fee"));
        }
        
        String query1="select club_name from club where club_id=?";
        PreparedStatement pre1=con.prepareStatement(query1);
        pre1.setString(1, ncid.getText());
        ResultSet re1=pre1.executeQuery();
        while(re1.next())
        {
            ncname.setText(re1.getString("club_name"));
        }
        
        String query2="select club_name from club where club_id=?";
        PreparedStatement pre2=con.prepareStatement(query2);
        pre2.setString(1, ocid.getText());
        ResultSet re2=pre2.executeQuery();
        while(re2.next())
        {
            ocname.setText(re2.getString("club_name"));
        }
        
        String query3="select player_name from player where player_id=?";
        PreparedStatement pre3=con.prepareStatement(query3);
        pre3.setString(1, pid.getText());
        ResultSet re3=pre3.executeQuery();
        while(re3.next())
        {
            pname.setText(re3.getString("player_name"));
        }
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Transfer.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.show();
        Stage stage1 = (Stage) backButton.getScene().getWindow();
        stage1.close();
    }

    @FXML
    void edit(ActionEvent event) throws SQLException, ClassNotFoundException {
        String pids=pid.getText();
        String ocids=ocid.getText();
        String ncids=ncid.getText();
        String transferfees=transferfee.getText();
        if(ocids.equals(ncids)){
            showInfo("Club ID cannot be the same");
        }
        else
        {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Soccer", "soccer");
            String query="update transfer set player_id=?,old_club_id=?,new_club_id=?,transfer_fee=? where player_id=? and old_club_id=? and new_club_id=?";
            PreparedStatement pre=con.prepareStatement(query);
            pre.setString(1, pids);
            pre.setString(2, ocids);
            pre.setString(3, ncids);
            pre.setString(4, transferfees);
            pre.setString(5, Main.id);
            pre.setString(6, Main.caid);
            pre.setString(7, Main.cbid);
            pre.executeQuery();
            showInfo("Changes Saved");
        }
    }
    
    @FXML
    void updatencname(ActionEvent event) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Soccer", "soccer");
        String query="select club_name from club where club_id=?";
        PreparedStatement pre=con.prepareStatement(query);
        pre.setString(1, ncid.getText());
        ResultSet re=pre.executeQuery();
        while(re.next())
        {
            ncname.setText(re.getString("club_name"));
        }
    }

    @FXML
    void updateocname(ActionEvent event) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Soccer", "soccer");
        String query="select club_name from club where club_id=?";
        PreparedStatement pre=con.prepareStatement(query);
        pre.setString(1, ocid.getText());
        ResultSet re=pre.executeQuery();
        while(re.next())
        {
            ocname.setText(re.getString("club_name"));
        }
    }

    @FXML
    void updatepname(ActionEvent event) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Soccer", "soccer");
        String query="select player_name from player where player_id=?";
        PreparedStatement pre=con.prepareStatement(query);
        pre.setString(1, pid.getText());
        ResultSet re=pre.executeQuery();
        while(re.next())
        {
            pname.setText(re.getString("player_name"));
        }
    }
    
    @FXML
    private Label closeLabel;

    @FXML
    private Label minimizeLabel;
    
    @FXML
    void close(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void minimize(MouseEvent event) {
        Stage stage = (Stage) minimizeLabel.getScene().getWindow();
        stage.setIconified(true);
    }

}