package soccer;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainPageController {

    @FXML
    private Button clubButton;

    @FXML
    private Button playerButton;

    @FXML
    private Button transferButton;

    @FXML
    private Button matchButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button exitButton;

    @FXML
    void club(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Club.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.show();
        Stage stage1 = (Stage) clubButton.getScene().getWindow();
        stage1.close();
    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        System.exit(0);
    }

    @FXML
    void match(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Match.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.show();
        Stage stage1 = (Stage) matchButton.getScene().getWindow();
        stage1.close();
    }

    @FXML
    void player(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Player.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.show();
        Stage stage1 = (Stage) playerButton.getScene().getWindow();
        stage1.close();
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminLogin.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.show();
        Stage stage1 = (Stage) logoutButton.getScene().getWindow();
        stage1.close();
    }

    @FXML
    void transfer(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Transfer.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.show();
        Stage stage1 = (Stage) transferButton.getScene().getWindow();
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
