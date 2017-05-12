/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import jdbc.Brand;
import jdbc.Category;
import jdbc.HibernateDao;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import screenControl.Notification;

/**
 * FXML Controller class
 *
 * @author Arefin
 */
public class AddCategoryController implements Initializable {

    @FXML
    private JFXButton btnClose;
    @FXML
    private JFXTextField categoryName;
    @FXML
    private JFXTextField categoryDesc;
    @FXML
    private JFXComboBox<String> cmbSupplier;
    @FXML
    private JFXComboBox<String> cmbBrand;

    ApplicationContext context = new ClassPathXmlApplicationContext("jdbc/spring-jdbc.xml");
    HibernateDao hd = (HibernateDao) context.getBean("hibernateDao");

    /**
     * Initializes the controller class.
     */
    Notification noti = new Notification();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbSupplier.setItems(getSupplierName());
    }

    @FXML
    private void btnCloseAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnSaveAction(ActionEvent event) {
        hd.saveCat(new Category(cmbSupplier.getSelectionModel().getSelectedItem(), cmbBrand.getSelectionModel().getSelectedItem(), 
                categoryName.getText(), categoryDesc.getText()));
        cmbSupplier.getSelectionModel().clearSelection();
        cmbBrand.getSelectionModel().clearSelection();
        categoryName.clear();
        categoryDesc.clear();
        noti.notificationSave();
    }

    public ObservableList<String> getSupplierName() throws BeansException {
        ObservableList<String> emplist = FXCollections
                .observableArrayList(hd.getSuppName());
        return emplist;
    }

    @FXML
    private void cmbBrandOnAction(MouseEvent event) {
        ObservableList<String> emplist = FXCollections
                .observableArrayList(hd.getBrandname(cmbSupplier.getSelectionModel().getSelectedItem()));
        cmbBrand.setItems(emplist);
    }
    
}
