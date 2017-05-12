/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import jdbc.HibernateDao;
import jdbc.Supplier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import screenControl.Notification;

/**
 * FXML Controller class
 *
 * @author Arefin
 */
public class AddSuppliersController implements Initializable {

    @FXML
    private JFXButton btnClose;
    @FXML
    private JFXTextField supplierName;
    @FXML
    private JFXTextField supplierMobile;
    @FXML
    private JFXTextField supplierAddress;
    @FXML
    private DatePicker suppliedDate;

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
    private void btnSaveAction(ActionEvent event) {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc/spring-jdbc.xml");
        HibernateDao hd = (HibernateDao) context.getBean("hibernateDao");
        hd.saveSupplier(new Supplier(supplierName.getText(), supplierMobile.getText(), supplierAddress.getText(), suppliedDate.getValue().toString()));
        supplierName.clear();
        supplierMobile.clear();
        supplierAddress.clear();
        suppliedDate.getEditor().clear();
        noti.notificationSave();
    }
    
}
