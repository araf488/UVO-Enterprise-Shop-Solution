/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee;

import application.EmployeeController;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import jdbc.Employee;
import jdbc.HibernateDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import screenControl.Notification;

/**
 * FXML Controller class
 *
 * @author Arefin
 */
public class NewEmployeeController implements Initializable {

    @FXML
    private Button btnClose;
    @FXML
    private JFXButton btnSaveEmployee;
    @FXML
    private JFXTextField unameField;
    @FXML
    private JFXTextField fullnameField;
    @FXML
    private JFXTextField mobileField;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXTextArea addressField;
    @FXML
    private JFXComboBox<String> positionField;
    @FXML
    private JFXTextField salaryField;
    @FXML
    private DatePicker dateField;
    @FXML
    private ImageView imgView;
    @FXML
    private JFXButton btnImage;
    private String imagePath;
    private byte[] employee_image;

    /**
     * Initializes the controller class.
     */
    Notification noti = new Notification();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        positionField.setItems(position);
    }
    
    EmployeeController empcon = new EmployeeController();

    ObservableList<String> position = FXCollections
            .observableArrayList("Trainee Officer", "Asst. Officer", "Sr. Officer", 
                    "Accountant", "Hr. Manager", "Manager");
    
    
    @FXML
    private void btnCloseAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnSaveEmployeeAction(ActionEvent event) {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc/spring-jdbc.xml");
        HibernateDao hd = (HibernateDao) context.getBean("hibernateDao");
        hd.saveEmployee(new Employee(unameField.getText(), fullnameField.getText(), mobileField.getText(), 
                emailField.getText(), addressField.getText(), positionField.getSelectionModel().getSelectedItem(), 
                Double.parseDouble(salaryField.getText()), dateField.getValue().toString(), employee_image));
        unameField.clear();
        fullnameField.clear();
        mobileField.clear();
        emailField.clear();
        addressField.clear();
        positionField.getSelectionModel().clearSelection();
        salaryField.clear();
        dateField.getEditor().clear();
        imgView.setImage(null);
        noti.notificationSave();
    }

    @FXML
    private void btnImageOnAction(ActionEvent event) throws IOException {
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
            employee_image = bos.toByteArray();
        } catch (Exception e) {
        }
    }
    
}
