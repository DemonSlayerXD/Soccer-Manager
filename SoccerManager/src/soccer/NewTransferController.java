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

public class NewTransferController {
    
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
    private Button transferButton;

    @FXML
    private Button backButton;

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
    void transfer(ActionEvent event) throws ClassNotFoundException, SQLException {
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
            String query="insert into transfer values(?,?,?,?)";
            PreparedStatement pre=con.prepareStatement(query);
            pre.setString(1, pids);
            pre.setString(2, ocids);
            pre.setString(3, ncids);
            pre.setString(4, transferfees);
            pre.executeQuery();
            showInfo("Player Transfered");
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
