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
import javafx.stage.Stage;
import jdbc.HibernateDao;
import jdbc.Warranty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import screenControl.Notification;

/**
 * FXML Controller class
 *
 * @author Arefin
 */
public class AddWarrentyController implements Initializable {

    @FXML
    private JFXButton btnClose;
    @FXML
    private JFXTextField warrantyType;
    @FXML
    private JFXTextField warrantyDays;
    @FXML
    private JFXTextField warrantyDesc;

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
        hd.saveWarranty(new Warranty(warrantyType.getText(), warrantyDays.getText(), warrantyDesc.getText()));
        warrantyType.clear();
        warrantyDays.clear();
        warrantyDesc.clear();
        noti.notificationSave();
    }
    
}
