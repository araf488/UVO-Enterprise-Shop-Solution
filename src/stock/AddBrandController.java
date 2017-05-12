/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import jdbc.Brand;
import jdbc.HibernateDao;
import jdbc.Supplier;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import screenControl.Notification;

/**
 * FXML Controller class
 *
 * @author Arefin
 */
public class AddBrandController implements Initializable {
    
    Supplier sup;

    @FXML
    private JFXButton btnClose;
    @FXML
    private JFXTextField brandName;
    @FXML
    private JFXTextArea brandDesc;
    @FXML
    private JFXComboBox<String> supplierName;
    /**
     * Initializes the controller class.
     */
    Notification noti = new Notification();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        supplierName.setItems(getSupplierName());
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
        hd.saveBrand(new Brand(supplierName.getSelectionModel().getSelectedItem(), brandName.getText(), brandDesc.getText()));
        supplierName.getSelectionModel().clearSelection();
        brandName.clear();
        brandDesc.clear();
        noti.notificationSave();
    }

    public ObservableList<String> getSupplierName() throws BeansException {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc/spring-jdbc.xml");
        HibernateDao hd = (HibernateDao) context.getBean("hibernateDao");
        ObservableList<String> emplist = FXCollections
                .observableArrayList(hd.getSuppName());
        return emplist;
    }
}
