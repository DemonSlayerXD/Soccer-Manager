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

public class AddClubController {
    
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
    private TextField management;

    @FXML
    private TextField support;

    @FXML
    private TextField resourcesa;

    @FXML
    private TextField wins;

    @FXML
    private TextField losses;

    @FXML
    private TextField values;

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
        String query="insert into club values(?,?,?,?,?,?)";
        PreparedStatement pre=con.prepareStatement(query);
        pre.setString(1, cids);
        pre.setString(2, cnames);
        pre.setString(3, homegrounds);
        pre.setString(4, foundeds);
        pre.setString(5, countrys);
        pre.setString(6, coachs);
        pre.executeQuery();
        String query1="insert into club_stats(club_id,management,support,resources,wins,losses,value) values(?,?,?,?,?,?,?)";
        PreparedStatement pre1=con.prepareStatement(query1);
        pre1.setString(1, cids);
        pre1.setString(2, managements);
        pre1.setString(3, supportss);
        pre1.setString(4, resourcess);
        pre1.setString(5, winss);
        pre1.setString(6, lossess);
        pre1.setString(7, valuess);
        pre1.executeQuery();
        String query2="begin clubavg; end;";
        PreparedStatement pre2=con.prepareStatement(query2);
        pre2.executeQuery();
        showInfo("Club Added");
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