package soccer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

public class AddPlayerController {
    
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
    private TextField age;

    @FXML
    private TextField country;

    @FXML
    private TextField position;

    @FXML
    private TextField cid;

    @FXML
    private TextField stamina;

    @FXML
    private TextField agility;

    @FXML
    private TextField attack;

    @FXML
    private TextField defence;

    @FXML
    private TextField goals;

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;
    
    @FXML
    public void initialize() throws ClassNotFoundException, SQLException{
        if("User".equals(Main.mode))
        {
            addButton.setDisable(true);
        }
    }

    @FXML
    void add(ActionEvent event) throws ClassNotFoundException, SQLException {
        String pids=pid.getText();
        String pnames=pname.getText();
        String ages=age.getText();
        String positions=position.getText();
        String countrys=country.getText();
        String cids=cid.getText();
        String staminas=stamina.getText();
        String agilitys=agility.getText();
        String attacks=attack.getText();
        String defences=defence.getText();
        String goalss=goals.getText();
        
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Soccer", "soccer");
        String query="insert into player values(?,?,?,?,?,?)";
        PreparedStatement pre=con.prepareStatement(query);
        pre.setString(1, pids);
        pre.setString(2, pnames);
        pre.setString(3, ages);
        pre.setString(4, positions);
        pre.setString(5, countrys);
        pre.setString(6, cids);
        pre.executeQuery();
        String query1="insert into player_stats(player_id,stamina,agility,attack,defence,goals) values(?,?,?,?,?,?)";
        PreparedStatement pre1=con.prepareStatement(query1);
        pre1.setString(1, pids);
        pre1.setString(2, staminas);
        pre1.setString(3, agilitys);
        pre1.setString(4, attacks);
        pre1.setString(5, defences);
        pre1.setString(6, goalss);
        pre1.executeQuery();
        String query2="begin playeravg; end;";
        PreparedStatement pre2=con.prepareStatement(query2);
        pre2.executeQuery();
        showInfo("Player Added");
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Player.fxml"));
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
