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

public class ViewClubController {
    
    public static void showInfo(String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Information");
        alert.setHeaderText(title);

        alert.showAndWait();
    }

    @FXML
    private TextField cid;

    @FXML
    private TextField cname;

    @FXML
    private TextField coach;

    @FXML
    private TextField country;

    @FXML
    private TextField founded;

    @FXML
    private TextField homeground;

    @FXML
    private TextField overall;

    @FXML
    private TextField wins;

    @FXML
    private TextField losses;

    @FXML
    private TextField values;

    @FXML
    private Button saveButton;

    @FXML
    private Button backButton;

    @FXML
    private BarChart<String, Number> clubBar;

    @FXML
    private TextField resourcesa;

    @FXML
    private TextField support;

    @FXML
    private TextField management;
    
    @FXML
    public void initialize() throws ClassNotFoundException, SQLException{
        if("User".equals(Main.mode))
        {
            saveButton.setDisable(true);
        }
        
    Class.forName("oracle.jdbc.OracleDriver");
    Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Soccer", "soccer");
    String query="select * from club where club_id=?";
    PreparedStatement pre=con.prepareStatement(query);
    pre.setString(1, Main.id);
    ResultSet re=pre.executeQuery();
    while(re.next())
    {
        cid.setText(re.getString("club_id"));
        cname.setText(re.getString("club_name"));
        coach.setText(re.getString("coach"));
        country.setText(re.getString("country"));
        founded.setText(re.getString("year_found"));
        homeground.setText(re.getString("home_ground"));
    }
        
    String query1="select * from club_stats where club_id=?";
    PreparedStatement pre1=con.prepareStatement(query1);
    pre1.setString(1, Main.id);
    ResultSet re1=pre1.executeQuery();
    while(re1.next())
    {
        overall.setText(re1.getString("overall"));
        management.setText(re1.getString("management"));
        support.setText(re1.getString("support"));
        resourcesa.setText(re1.getString("resources"));
        wins.setText(re1.getString("wins"));
        losses.setText(re1.getString("losses"));
        values.setText(re1.getString("value"));
    XYChart.Series<String, Number> series1 = new XYChart.Series<>(); 
    series1.getData().add(new XYChart.Data<>("Management", re1.getInt("management"))); 
    series1.getData().add(new XYChart.Data<>("Support", re1.getInt("support"))); 
    series1.getData().add(new XYChart.Data<>("Resources", re1.getInt("resources"))); 
    series1.getData().add(new XYChart.Data<>("Overall", re1.getInt("overall")));
    clubBar.getData().add(series1);
    }
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Club.fxml"));
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

        String cids=cid.getText();
        String cnames=cname.getText();
        String coachs=coach.getText();
        String countrys=country.getText();
        String foundeds=founded.getText();
        String homegrounds=homeground.getText();
        String managements=management.getText();
        String supportss=support.getText();
        String resourcess=resourcesa.getText();
        String winss=wins.getText();
        String lossess=losses.getText();
        String valuess=values.getText();
        
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Soccer", "soccer");
        String query="update club set club_name=?,home_ground=?,year_found=?,country=?,coach=? where club_id=?";
        PreparedStatement pre=con.prepareStatement(query);
        //pre.setString(1, cids);
        pre.setString(1, cnames);
        pre.setString(2, homegrounds);
        pre.setString(3, foundeds);
        pre.setString(4, countrys);
        pre.setString(5, coachs);
        pre.setString(6, Main.id);
        pre.executeQuery();
        String query1="update club_stats set management=?,support=?,resources=?,wins=?,losses=?,value=? where club_id=?";
        PreparedStatement pre1=con.prepareStatement(query1);
        //pre1.setString(1, cids);
        pre1.setString(1, managements);
        pre1.setString(2, supportss);
        pre1.setString(3, resourcess);
        pre1.setString(4, winss);
        pre1.setString(5, lossess);
        pre1.setString(6, valuess);
        pre1.setString(7, Main.id);
        pre1.executeQuery();
        String query2="begin clubavg; end;";
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