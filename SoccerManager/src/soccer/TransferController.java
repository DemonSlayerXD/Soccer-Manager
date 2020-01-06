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

public class TransferController {

    @FXML
    private TableView<TransferModel> transferTable;

    @FXML
    private TableColumn<TransferModel, String> playerid;

    @FXML
    private TableColumn<TransferModel, String> playername;

    @FXML
    private TableColumn<TransferModel, String> ocid;

    @FXML
    private TableColumn<TransferModel, String> ocname;

    @FXML
    private TableColumn<TransferModel, String> ncid;

    @FXML
    private TableColumn<TransferModel, String> ncname;

    @FXML
    private TableColumn<TransferModel, String> transferfee;

    @FXML
    private Button transferButton;

    @FXML
    private Button editButton;

    @FXML
    private Button refreshButton;

    @FXML
    private Button backButton;
    
    ObservableList<TransferModel> oblist = FXCollections.observableArrayList();
    
    @FXML
    public void initialize() throws ClassNotFoundException, SQLException{
        if("User".equals(Main.mode))
        {
            transferButton.setDisable(true);
            editButton.setDisable(true);
        }
        
        Class.forName("oracle.jdbc.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Soccer", "soccer");
            String query="select t.player_id,p.player_name,t.old_club_id,ca.club_name,t.new_club_id,cb.club_name as club_name2,t.transfer_fee from transfer t inner join player p on t.player_id=p.player_id inner join club ca on t.old_club_id=ca.club_id inner join club cb on t.new_club_id=cb.club_id";
            PreparedStatement pre=con.prepareStatement(query);
            ResultSet re=pre.executeQuery();
        
        while(re.next())
        {
            oblist.add(new TransferModel(re.getString("player_id"),re.getString("player_name"), re.getString("old_club_id"), re.getString("club_name"), re.getString("new_club_id"), re.getString("club_name2"), re.getString("transfer_fee")));          
        }
        
        playerid.setCellValueFactory(new PropertyValueFactory<>("playerid"));
        playername.setCellValueFactory(new PropertyValueFactory<>("playername"));
        ocid.setCellValueFactory(new PropertyValueFactory<>("ocid"));
        ocname.setCellValueFactory(new PropertyValueFactory<>("ocname"));
        ncid.setCellValueFactory(new PropertyValueFactory<>("ncid"));
        ncname.setCellValueFactory(new PropertyValueFactory<>("ncname"));
        transferfee.setCellValueFactory(new PropertyValueFactory<>("transferfee"));
        
        transferTable.setItems(oblist);
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditTransfer.fxml"));
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
            String query="select t.player_id,p.player_name,t.old_club_id,ca.club_name,t.new_club_id,cb.club_name as club_name2,t.transfer_fee from transfer t inner join player p on t.player_id=p.player_id inner join club ca on t.old_club_id=ca.club_id inner join club cb on t.new_club_id=cb.club_id";
            PreparedStatement pre=con.prepareStatement(query);
            ResultSet re=pre.executeQuery();
            
        for ( int i = 0; i<transferTable.getItems().size(); i++) {
            transferTable.getItems().clear();
        }
        
        while(re.next())
        {
            oblist.add(new TransferModel(re.getString("player_id"),re.getString("player_name"), re.getString("old_club_id"), re.getString("club_name"), re.getString("new_club_id"), re.getString("club_name2"), re.getString("transfer_fee")));          
        }
        
        playerid.setCellValueFactory(new PropertyValueFactory<>("playerid"));
        playername.setCellValueFactory(new PropertyValueFactory<>("playername"));
        ocid.setCellValueFactory(new PropertyValueFactory<>("ocid"));
        ocname.setCellValueFactory(new PropertyValueFactory<>("ocname"));
        ncid.setCellValueFactory(new PropertyValueFactory<>("ncid"));
        ncname.setCellValueFactory(new PropertyValueFactory<>("ncname"));
        transferfee.setCellValueFactory(new PropertyValueFactory<>("transferfee"));
        
        transferTable.setItems(oblist);
    }

    @FXML
    void transfer(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewTransfer.fxml"));
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
        Main.id=transferTable.getSelectionModel().getSelectedItem().getPlayerid();
        Main.caid=transferTable.getSelectionModel().getSelectedItem().getOcid();
        Main.cbid=transferTable.getSelectionModel().getSelectedItem().getNcid();
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
