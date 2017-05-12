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
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import jdbc.HibernateDao;
import jdbc.Product;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import screenControl.Notification;

/**
 * FXML Controller class
 *
 * @author Arefin
 */
public class AddNewProductController implements Initializable {

    @FXML
    private JFXTextField productName;
    @FXML
    private JFXTextField productQuantity;
    @FXML
    private JFXTextField proPPrice;
    @FXML
    private JFXTextField proSPrice;
    @FXML
    private JFXTextArea proDesc;
    @FXML
    private JFXButton btnSave;
    @FXML
    private DatePicker proPDate;
    @FXML
    private JFXComboBox<String> cmbSupplier;
    @FXML
    private JFXComboBox<String> cmbBrand;
    @FXML
    private JFXComboBox<String> cmbCategory;
    @FXML
    private JFXComboBox<String> cmbUnit;
    @FXML
    private JFXComboBox<String> cmbWarranty;
    @FXML
    private ImageView imgView;
    
    byte[] product_image = null;
    
    ApplicationContext context = new ClassPathXmlApplicationContext("jdbc/spring-jdbc.xml");
    HibernateDao hd = (HibernateDao) context.getBean("hibernateDao");
    private String imagePath;
    @FXML
    private JFXButton btnClose;

    /**
     * Initializes the controller class.
     */
    Notification noti = new Notification();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbSupplier.setItems(getSupplierName());
        cmbUnit.setItems(getUnitName());
        cmbWarranty.setItems(getWarrantyType());
    }

    @FXML
    private void attachImage(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        FileChooser.ExtensionFilter extFilterJPEG = new FileChooser.ExtensionFilter("JPEG files (*.jpeg)", "*.JPEG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG, extFilterJPEG);
        fileChooser.setTitle("Choose a Image File");
        
        File file = fileChooser.showOpenDialog(null);
        BufferedImage bufferedImage = ImageIO.read(file);
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        imgView.setImage(image);
        imagePath = file.getAbsolutePath();
        
        try {
            File io = new File(imagePath);
            FileInputStream fis = new FileInputStream(io);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];

            for (int readNum; (readNum = fis.read(buf)) != -1;) {

                bos.write(buf, 0, readNum);
            }
            product_image = bos.toByteArray();
        } catch (Exception e) {
        }
    }

    @FXML
    private void saveProduct(ActionEvent event) {
        hd.saveProduct(new Product(productName.getText(), Integer.parseInt(productQuantity.getText()), 
                Double.parseDouble(proPPrice.getText()), Double.parseDouble(proSPrice.getText()), proDesc.getText(), 
                cmbSupplier.getSelectionModel().getSelectedItem(), cmbBrand.getSelectionModel().getSelectedItem(), 
                cmbCategory.getSelectionModel().getSelectedItem(), cmbUnit.getSelectionModel().getSelectedItem(), cmbWarranty.getSelectionModel().getSelectedItem(), 
                proPDate.getValue().toString(), product_image));
        productQuantity.clear();
        proPPrice.clear();
        proSPrice.clear();
        proDesc.clear();
        cmbCategory.getSelectionModel().clearSelection();
        cmbUnit.getSelectionModel().clearSelection();
        cmbWarranty.getSelectionModel().clearSelection();
        proPDate.getEditor().clear();
        imgView.setImage(null);
        cmbSupplier.getSelectionModel().clearSelection();
        cmbBrand.getSelectionModel().clearSelection();
        productName.clear();
        noti.notificationSave();
    }
    
    public ObservableList<String> getSupplierName() throws BeansException {
        ObservableList<String> emplist = FXCollections
                .observableArrayList(hd.getSuppName());
        return emplist;
    }

    public ObservableList<String> getUnitName() throws BeansException {
        ObservableList<String> emplist = FXCollections
                .observableArrayList(hd.getUnitName());
        return emplist;
    }
    
    public ObservableList<String> getWarrantyType() throws BeansException {
        ObservableList<String> emplist = FXCollections
                .observableArrayList(hd.getWarrantyName());
        return emplist;
    }

    @FXML
    private void btnCloseAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cmbBrandOnAction(MouseEvent event) {
        ObservableList<String> emplist = FXCollections
                .observableArrayList(hd.getBrandname(cmbSupplier.getSelectionModel().getSelectedItem()));
        cmbBrand.setItems(emplist);
    }

    @FXML
    private void cmbCategoryOnAction(MouseEvent event) {
        ObservableList<String> emplist = FXCollections
                .observableArrayList(hd.getCatName(cmbBrand.getSelectionModel().getSelectedItem()));
        cmbCategory.setItems(emplist);
    }
}
