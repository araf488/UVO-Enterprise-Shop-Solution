/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jdbc.UserName;
import login.Login;

/**
 * FXML Controller class
 *
 * @author Arefin
 */
public class MainController implements Initializable {

    private static Stage primaryStage;
    private static BorderPane mainLayout;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXButton home;
    @FXML
    private JFXButton stock;
    @FXML
    private JFXButton employee;
    @FXML
    private JFXButton sale;
    @FXML
    private Label loggedUser;
    
    String name;
    @FXML
    private JFXButton btnLogout;
    
    public void setUsrNameMedia(String name) {
        loggedUser.setText(name);
    }

    /**
     * Initializes the controller class.
     */
    private void nextStage(String fxml, String title, boolean resizable) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(resizable);
        stage.show();

    }
    private void nextStageHome(String fxml, String title, boolean resizable) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setTitle(title);
        stage.getIcons().add(new Image("/image/icon.png"));
        stage.setResizable(resizable);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("/application/Home.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void homeClick(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Home.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void stockClick(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Stock.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void employeeClick(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Employee.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void saleClick(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/Sale.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void btnLogoutOnAction(ActionEvent event) throws IOException {
        Stage current = (Stage) btnLogout.getScene().getWindow();
        current.hide();
        nextStage(Login.Login, "", true);
    }

}
