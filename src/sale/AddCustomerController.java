/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sale;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import jdbc.Customer;
import jdbc.HibernateDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import screenControl.Notification;

/**
 * FXML Controller class
 *
 * @author Arefin
 */
public class AddCustomerController implements Initializable {
    
    @FXML
    private JFXButton btnSaveCustomer;
    @FXML
    private JFXButton btnClose;
    @FXML
    private JFXTextField custName;
    @FXML
    private JFXTextField contactNo;
    @FXML
    private JFXTextArea address;

    /**
     * Initializes the controller class.
     */
    Notification noti = new Notification();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnCloseAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnSaveCustomerAction(ActionEvent event) {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc/spring-jdbc.xml");
        HibernateDao hd = (HibernateDao) context.getBean("hibernateDao");
        hd.saveCustomer(new Customer(custName.getText(), contactNo.getText(), address.getText()));
        custName.clear();
        contactNo.clear();
        address.clear();
        noti.notificationSave();
    }

    
}
