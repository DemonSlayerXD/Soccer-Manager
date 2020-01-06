package soccer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
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

public class NewMatchController {
    
    public static void showInfo(String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Information");
        alert.setHeaderText(title);

        alert.showAndWait();
    }

    @FXML
    private TextField c1id;

    @FXML
    private TextField date;

    @FXML
    private TextField stadium;

    @FXML
    private TextField c1name;

    @FXML
    private TextField c2id;

    @FXML
    private TextField c2name;

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    @FXML
    void add(ActionEvent event) throws SQLException, ClassNotFoundException, ParseException {
        String c1ids=c1id.getText();
        String c2ids=c2id.getText();
        String dates=date.getText();
        String stadiums=stadium.getText();
        if(c1ids.equals(c2ids)){
            showInfo("Club ID cannot be the same");
        }
        else
        {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Soccer", "soccer");
            String query="insert into match values(?,?,?,?)";
            PreparedStatement pre=con.prepareStatement(query);
            pre.setString(1, c1ids);
            pre.setString(2, c2ids);
            pre.setString(3, stadiums);
            pre.setString(4, dates);
            pre.executeQuery();
            showInfo("Match Added");
        }
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Match.fxml"));
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
    void updatec1name(ActionEvent event) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Soccer", "soccer");
        String query="select club_name from club where club_id=?";
        PreparedStatement pre=con.prepareStatement(query);
        pre.setString(1, c1id.getText());
        ResultSet re=pre.executeQuery();
        while(re.next())
        {
            c1name.setText(re.getString("club_name"));
        }
    }

    @FXML
    void updatec2name(ActionEvent event) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Soccer", "soccer");
        String query="select club_name from club where club_id=?";
        PreparedStatement pre=con.prepareStatement(query);
        pre.setString(1, c2id.getText());
        ResultSet re=pre.executeQuery();
        while(re.next())
        {
            c2name.setText(re.getString("club_name"));
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
