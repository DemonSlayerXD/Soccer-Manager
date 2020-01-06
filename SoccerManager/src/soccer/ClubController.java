package soccer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ClubController {
    
    @FXML
    private TableView<ClubModel> clubTable;

    @FXML
    private TableColumn<ClubModel, String> clubid;

    @FXML
    private TableColumn<ClubModel, String> club;

    @FXML
    private TableColumn<ClubModel, String> country;

    @FXML
    private TableColumn<ClubModel, String> coach;

    @FXML
    private TableColumn<ClubModel, String> homeground;

    @FXML
    private TableColumn<ClubModel, String> yearfound;
    
    @FXML
    private Button backButton;
    
    @FXML
    private Button addButton;

    @FXML
    private Button viewButton;
    
    @FXML
    private Button refreshButton;
    
    ObservableList<ClubModel> oblist = FXCollections.observableArrayList();
    
    @FXML
    public void initialize() throws ClassNotFoundException, SQLException{
        if("User".equals(Main.mode))
        {
            addButton.setDisable(true);
        }
        
        Class.forName("oracle.jdbc.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Soccer", "soccer");
            String query="select * from club";
            PreparedStatement pre=con.prepareStatement(query);
            ResultSet re=pre.executeQuery();
        
        while(re.next())
        {
            oblist.add(new ClubModel(re.getString("club_id"),re.getString("club_name"), re.getString("country"), re.getString("coach"), re.getString("home_ground"), re.getString("year_found")));          
        }
        
        clubid.setCellValueFactory(new PropertyValueFactory<>("clubid"));
        club.setCellValueFactory(new PropertyValueFactory<>("club"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        coach.setCellValueFactory(new PropertyValueFactory<>("coach"));
        homeground.setCellValueFactory(new PropertyValueFactory<>("home"));
        yearfound.setCellValueFactory(new PropertyValueFactory<>("year"));
        
        clubTable.setItems(oblist);
    }
    
    @FXML
    void add(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddClub.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.show();
        Stage stage1 = (Stage) addButton.getScene().getWindow();
        stage1.close();
    }
    
    @FXML
    void view(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewClub.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.show();
        Stage stage1 = (Stage) viewButton.getScene().getWindow();
        stage1.close();

    }

    @FXML
    void back(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
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
    void selectRow(MouseEvent event) {
        Main.id=clubTable.getSelectionModel().getSelectedItem().getClubid();
    }

    @FXML
    void refresh(ActionEvent event) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Soccer", "soccer");
        String query="select * from club";
        PreparedStatement pre=con.prepareStatement(query);
        ResultSet re=pre.executeQuery();
            
        for ( int i = 0; i<clubTable.getItems().size(); i++) {
            clubTable.getItems().clear();
        }
        
        while(re.next())
        {
            oblist.add(new ClubModel(re.getString("club_id"),re.getString("club_name"), re.getString("country"), re.getString("coach"), re.getString("home_ground"), re.getString("year_found")));          
        }
        
        clubid.setCellValueFactory(new PropertyValueFactory<>("clubid"));
        club.setCellValueFactory(new PropertyValueFactory<>("club"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        coach.setCellValueFactory(new PropertyValueFactory<>("coach"));
        homeground.setCellValueFactory(new PropertyValueFactory<>("home"));
        yearfound.setCellValueFactory(new PropertyValueFactory<>("year"));
        
        clubTable.setItems(oblist);
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
