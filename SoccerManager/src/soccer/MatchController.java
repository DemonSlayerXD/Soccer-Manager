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

public class MatchController {

    @FXML
    private TableView<MatchModel> matchTable;

    @FXML
    private TableColumn<MatchModel, String> c1id;

    @FXML
    private TableColumn<MatchModel, String> c1name;

    @FXML
    private TableColumn<MatchModel, String> c2id;

    @FXML
    private TableColumn<MatchModel, String> c2name;

    @FXML
    private TableColumn<MatchModel, String> date;

    @FXML
    private TableColumn<MatchModel, String> stadium;

    @FXML
    private Button matchButton;

    @FXML
    private Button editButton;

    @FXML
    private Button refreshButton;

    @FXML
    private Button backButton;
    
    ObservableList<MatchModel> oblist = FXCollections.observableArrayList();
    
    @FXML
    public void initialize() throws ClassNotFoundException, SQLException{
        if("User".equals(Main.mode))
        {
            matchButton.setDisable(true);
            editButton.setDisable(true);
        }
        
        Class.forName("oracle.jdbc.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Soccer", "soccer");
            String query="select m.club_id1,ca.club_name,m.club_id2,cb.club_name as club_name2,m.match_date,m.stadium from match m inner join club ca on m.club_id1=ca.club_id inner join club cb on m.club_id2=cb.club_id";
            PreparedStatement pre=con.prepareStatement(query);
            ResultSet re=pre.executeQuery();
        
        while(re.next())
        {
            oblist.add(new MatchModel(re.getString("club_id1"),re.getString("club_name"), re.getString("club_id2"), re.getString("club_name2"), re.getString("match_date"), re.getString("stadium")));          
        }
        
        c1id.setCellValueFactory(new PropertyValueFactory<>("c1id"));
        c1name.setCellValueFactory(new PropertyValueFactory<>("c1name"));
        c2id.setCellValueFactory(new PropertyValueFactory<>("c2id"));
        c2name.setCellValueFactory(new PropertyValueFactory<>("c2name"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        stadium.setCellValueFactory(new PropertyValueFactory<>("stadium"));
        
        matchTable.setItems(oblist);
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
    void edit(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditMatch.fxml"));
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
    void match(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewMatch.fxml"));
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
    void refresh(ActionEvent event) throws ClassNotFoundException, SQLException {

        Class.forName("oracle.jdbc.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Soccer", "soccer");
            String query="select m.club_id1,ca.club_name,m.club_id2,cb.club_name as club_name2,m.match_date,m.stadium from match m inner join club ca on m.club_id1=ca.club_id inner join club cb on m.club_id2=cb.club_id";
            PreparedStatement pre=con.prepareStatement(query);
            ResultSet re=pre.executeQuery();
        
        for ( int i = 0; i<matchTable.getItems().size(); i++) {
            matchTable.getItems().clear();
        }    
            
        while(re.next())
        {
            oblist.add(new MatchModel(re.getString("club_id1"),re.getString("club_name"), re.getString("club_id2"), re.getString("club_name2"), re.getString("match_date"), re.getString("stadium")));          
        }
        
        c1id.setCellValueFactory(new PropertyValueFactory<>("c1id"));
        c1name.setCellValueFactory(new PropertyValueFactory<>("c1name"));
        c2id.setCellValueFactory(new PropertyValueFactory<>("c2id"));
        c2name.setCellValueFactory(new PropertyValueFactory<>("c2name"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        stadium.setCellValueFactory(new PropertyValueFactory<>("stadium"));
        
        matchTable.setItems(oblist);
    }
    
    @FXML
    void selectRow(MouseEvent event) {
        Main.dates=matchTable.getSelectionModel().getSelectedItem().getDate();
        Main.caid=matchTable.getSelectionModel().getSelectedItem().getC1id();
        Main.cbid=matchTable.getSelectionModel().getSelectedItem().getC2id();
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
