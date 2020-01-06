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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ViewPlayerController {
    
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
    private TextField overall;

    @FXML
    private TextField stamina;

    @FXML
    private TextField cid;

    @FXML
    private TextField goals;

    @FXML
    private Button saveButton;

    @FXML
    private Button backButton;

    @FXML
    private BarChart<String, Number> playerBar;

    @FXML
    private TextField defence;

    @FXML
    private TextField attack;

    @FXML
    private TextField agility;
    
    @FXML
    public void initialize() throws ClassNotFoundException, SQLException{
        if("User".equals(Main.mode))
        {
            saveButton.setDisable(true);
        }
        
    Class.forName("oracle.jdbc.OracleDriver");
    Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Soccer", "soccer");
    String query="select * from player where player_id=?";
    PreparedStatement pre=con.prepareStatement(query);
    pre.setString(1, Main.id);
    ResultSet re=pre.executeQuery();
    while(re.next())
    {
        pid.setText(re.getString("player_id"));
        pname.setText(re.getString("player_name"));
        age.setText(re.getString("age"));
        position.setText(re.getString("position"));
        country.setText(re.getString("country"));
        cid.setText(re.getString("club_id"));
    }
        
    String query1="select * from player_stats where player_id=?";
    PreparedStatement pre1=con.prepareStatement(query1);
    pre1.setString(1, Main.id);
    ResultSet re1=pre1.executeQuery();
    while(re1.next())
    {
        overall.setText(re1.getString("overall"));
        stamina.setText(re1.getString("stamina"));
        agility.setText(re1.getString("agility"));
        attack.setText(re1.getString("attack"));
        defence.setText(re1.getString("defence"));
        goals.setText(re1.getString("goals"));
    XYChart.Series<String, Number> series1 = new XYChart.Series<>(); 
    series1.getData().add(new XYChart.Data<>("Stamina", re1.getInt("stamina"))); 
    series1.getData().add(new XYChart.Data<>("Agility", re1.getInt("agility"))); 
    series1.getData().add(new XYChart.Data<>("Attack", re1.getInt("attack"))); 
    series1.getData().add(new XYChart.Data<>("Defence", re1.getInt("defence")));
    series1.getData().add(new XYChart.Data<>("Overall", re1.getInt("overall")));
    playerBar.getData().add(series1);
    }
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
    void save(ActionEvent event) throws ClassNotFoundException, SQLException {
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
        String query="update player set player_name=?,age=?,position=?,country=?,club_id=? where player_id=?";
        PreparedStatement pre=con.prepareStatement(query);
        //pre.setString(1, pids);
        pre.setString(1, pnames);
        pre.setString(2, ages);
        pre.setString(3, positions);
        pre.setString(4, countrys);
        pre.setString(5, cids);
        pre.setString(6, Main.id);
        pre.executeQuery();
        String query1="update player_stats set stamina=?,agility=?,attack=?,defence=?,goals=? where player_id=?";
        PreparedStatement pre1=con.prepareStatement(query1);
        //pre1.setString(1, pids);
        pre1.setString(1, staminas);
        pre1.setString(2, agilitys);
        pre1.setString(3, attacks);
        pre1.setString(4, defences);
        pre1.setString(5, goalss);
        pre1.setString(6, Main.id);
        pre1.executeQuery();
        String query2="begin playeravg; end;";
        PreparedStatement pre2=con.prepareStatement(query2);
        pre2.executeQuery();
        showInfo("Changes Saved");
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
