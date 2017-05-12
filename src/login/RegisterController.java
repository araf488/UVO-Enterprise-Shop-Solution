/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import jdbc.HibernateDao;
import jdbc.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import screenControl.Notification;

/**
 * FXML Controller class
 *
 * @author Arefin
 */
public class RegisterController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField password;
    @FXML
    private JFXComboBox<String> cmbUser;
    @FXML
    private JFXButton btnClose;
    @FXML
    private JFXButton btnRegister;

    /**
     * Initializes the controller class.
     */
    Notification noti = new Notification();
    
    ObservableList<String> rank = FXCollections
            .observableArrayList("Admin", "Employee", "HR Manager");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbUser.setItems(rank);
    }    

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnRegisterOnAction(ActionEvent event) {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc/spring-jdbc.xml");
        HibernateDao hd = (HibernateDao) context.getBean("hibernateDao");
        hd.saveUser(new User(username.getText(), password.getText(), cmbUser.getSelectionModel().getSelectedItem()));
        cmbUser.getSelectionModel().clearSelection();
        username.clear();
        password.clear();
        noti.notificationSave();
    }
    
}
