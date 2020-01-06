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

public class PlayerController {
    
    @FXML
    private TableView<PlayerModel> playerTable;

    @FXML
    private TableColumn<PlayerModel, String> playerid;

    @FXML
    private TableColumn<PlayerModel, String> name;

    @FXML
    private TableColumn<PlayerModel, String> age;

    @FXML
    private TableColumn<PlayerModel, String> position;

    @FXML
    private TableColumn<PlayerModel, String> country;

    @FXML
    private TableColumn<PlayerModel, String> clubid;
    
    @FXML
    private Button addButton;

    @FXML
    private Button viewButton;

    @FXML
    private Button backButton;
    
    @FXML
    private Button refreshButton;
    
    ObservableList<PlayerModel> oblist = FXCollections.observableArrayList();
    
    @FXML
    public void initialize() throws ClassNotFoundException, SQLException{
        if("User".equals(Main.mode))
        {
            addButton.setDisable(true);
        }
        
        Class.forName("oracle.jdbc.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Soccer", "soccer");
            String query="select * from player";
            PreparedStatement pre=con.prepareStatement(query);
            ResultSet re=pre.executeQuery();
        
        while(re.next())
        {
            oblist.add(new PlayerModel(re.getString("player_id"),re.getString("player_name"), re.getString("age"), re.getString("position"), re.getString("country"), re.getString("club_id")));          
        }
        
        playerid.setCellValueFactory(new PropertyValueFactory<>("playerid"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        position.setCellValueFactory(new PropertyValueFactory<>("position"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        clubid.setCellValueFactory(new PropertyValueFactory<>("clubid"));
        
        playerTable.setItems(oblist);
    }
    
    @FXML
    void add(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddPlayer.fxml"));
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
    void view(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewPlayer.fxml"));
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
        Main.id=playerTable.getSelectionModel().getSelectedItem().getPlayerid();
    }
    
    @FXML
    void refresh(ActionEvent event) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Soccer", "soccer");
        String query="select * from player";
        PreparedStatement pre=con.prepareStatement(query);
        ResultSet re=pre.executeQuery();
        
        for ( int i = 0; i<playerTable.getItems().size(); i++) {
            playerTable.getItems().clear();
        }
        
        while(re.next())
        {
            oblist.add(new PlayerModel(re.getString("player_id"),re.getString("player_name"), re.getString("age"), re.getString("position"), re.getString("country"), re.getString("club_id")));          
        }
        
        playerid.setCellValueFactory(new PropertyValueFactory<>("playerid"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        position.setCellValueFactory(new PropertyValueFactory<>("position"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        clubid.setCellValueFactory(new PropertyValueFactory<>("clubid"));
        
        playerTable.setItems(oblist);
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
