/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Arefin
 */
public class Login extends Application {
    public static Boolean isSplashLoad = false;
    public static final String Login = "/login/Login.fxml";
    public static final String Registration = "/login/Register.fxml";
    public static final String Main = "/application/Main.fxml";
    public static final String AddNewProduct = "/stock/AddNewProduct.fxml";
    public static final String AddSuppliers = "/stock/AddSuppliers.fxml";
    public static final String AddBrand = "/stock/AddBrand.fxml";
    public static final String AddCategory = "/stock/AddCategory.fxml";
    public static final String AddUnit = "/stock/AddUnit.fxml";
    public static final String AddWarranty = "/stock/AddWarrenty.fxml";
    public static final String AddCustomer = "/sale/AddCustomer.fxml";
    public static final String SaleOrder = "/sale/SaleOrder.fxml";
    public static final String NewEmployee = "/employee/NewEmployee.fxml";
    public static final String Receipt = "/sale/Receipt.fxml";
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/login/SplashScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
//        stage.setMaximized(true);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image("/image/icon.png"));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
