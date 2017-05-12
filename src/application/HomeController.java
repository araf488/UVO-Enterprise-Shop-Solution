/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import jdbc.Employee;
import jdbc.HibernateDao;
import jdbc.Product;
import login.Login;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * FXML Controller class
 *
 * @author Arefin
 */
public class HomeController implements Initializable {

    @FXML
    private JFXButton btnProduct;
    @FXML
    private JFXButton btnEmployee;
    @FXML
    private JFXTextField searchProduct;
    @FXML
    private JFXTextField searchEmployee;
    @FXML
    private JFXButton btnrefresh;
    @FXML
    private Label lbl1;
    @FXML
    private Label lbl2;
    @FXML
    private Label lbl3;
    @FXML
    private Label lbl4;
    @FXML
    private Label lbl5;
    @FXML
    private Label lbl6;
    @FXML
    private Label lbl7;
    @FXML
    private ImageView contentImg;
    @FXML
    private Label lbl8;
    
    private WritableImage image;
    private byte[] myImg;
    private byte[] proImg;
    @FXML
    private Label lbl9;
    @FXML
    private Label lbl10;
    @FXML
    private Label lbl11;
    @FXML
    private Label lbl12;
    @FXML
    private Label lbl13;
    @FXML
    private Label lbl14;
    @FXML
    private Label lbl15;
    @FXML
    private Label lbl16;
    @FXML
    private Label lbl17;
    @FXML
    private Label lbl18;
    @FXML
    private Label lbl19;
    @FXML
    private Label lbl20;
    @FXML
    private Hyperlink registerLisnk;
    
    ApplicationContext context = new ClassPathXmlApplicationContext("jdbc/spring-jdbc.xml");
    HibernateDao hd = (HibernateDao) context.getBean("hibernateDao");
    int totalEmployee = hd.getCountEmployee();
    int countSale = hd.getCountSale();
    int countProduct = hd.getCountProduct();
    int totalProductQty = hd.totalQuantity();
    double totalSale = hd.totalSale();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl16.setText(""+countProduct);
        lbl17.setText(""+totalProductQty);
        lbl18.setText(""+countSale);
        lbl19.setText(""+totalSale+" Tk");
        lbl20.setText(""+totalEmployee);
    }
    
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

    @FXML
    private void btnProductOnAction(ActionEvent event) throws IOException {
        List<Product> list = hd.getProductByID(Integer.parseInt(searchProduct.getText()));
        for (Product s : list) {
            lbl1.setText("Product Name:");
            lbl9.setText(s.getPname());
            lbl2.setText("Available Quantity:");
            lbl10.setText(s.getQty().toString());
            lbl3.setText("Purchase Price:");
            lbl11.setText(s.getPprice().toString());
            lbl4.setText("Selling Price:");
            lbl12.setText(s.getSprice().toString());
            lbl5.setText("Brand Name:");
            lbl13.setText(s.getBrandname());
            lbl6.setText("Warranty:");
            lbl14.setText(s.getWarranty());
            lbl7.setText("Purchase Date:");
            lbl15.setText(s.getPdate());
            lbl8.setText("Product Image");
            proImg = s.getPimage();
            ByteArrayInputStream bis = new ByteArrayInputStream(proImg);
            BufferedImage bimg = ImageIO.read(bis);
            int width = bimg.getWidth();
            int height = bimg.getHeight();
            image = SwingFXUtils.toFXImage(bimg, null);
            
            contentImg.setImage(image);
        }
    }

    @FXML
    private void btnEmployeeOnAction(ActionEvent event) throws IOException {
        List<Employee> list = hd.getSingleValue(Integer.parseInt(searchEmployee.getText()));
        for (Employee s : list) {
            lbl1.setText("Employee Name:");
            lbl9.setText(s.getFullname());
            lbl2.setText("Contact Number:");
            lbl10.setText(s.getMobile());
            lbl3.setText("Email:");
            lbl11.setText(s.getEmail());
            lbl4.setText("Address:");
            lbl12.setText(s.getAddress());
            lbl5.setText("Position:");
            lbl13.setText(s.getPosition());
            lbl6.setText("Salary:");
            lbl14.setText(s.getSalary().toString());
            lbl7.setText("Joining Date:");
            lbl15.setText(s.getJoindate());
            lbl8.setText("Employee Image");
            myImg = s.getImage();
            ByteArrayInputStream bis = new ByteArrayInputStream(myImg);
            BufferedImage bimg = ImageIO.read(bis);
            int width = bimg.getWidth();
            int height = bimg.getHeight();
            image = SwingFXUtils.toFXImage(bimg, null);
            contentImg.setImage(image);
        }
    }

    @FXML
    private void btnrefreshOnAction(ActionEvent event) {
        searchEmployee.clear();
        searchProduct.clear();
        lbl1.setText("");
        lbl2.setText("");
        lbl3.setText("");
        lbl4.setText("");
        lbl5.setText("");
        lbl6.setText("");
        lbl7.setText("");
        lbl8.setText("");
        lbl9.setText("");
        lbl10.setText("");
        lbl11.setText("");
        lbl12.setText("");
        lbl13.setText("");
        lbl14.setText("");
        lbl15.setText("");
        contentImg.setImage(null);
    }
    
    public void convertToJavaFXImage(byte[] raw, final int width, final int height) {
        image = new WritableImage(width, height);
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(myImg);
            BufferedImage read = ImageIO.read(bis);
            image = SwingFXUtils.toFXImage(read, null);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void registerLisnkAction(ActionEvent event) throws IOException {
        nextStage(Login.Registration, "", true);
    }

}
