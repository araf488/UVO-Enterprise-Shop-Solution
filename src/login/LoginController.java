/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import application.MainController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import jdbc.HibernateDao;
import jdbc.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import screenControl.ScreenControl;

/**
 * FXML Controller class
 *
 * @author Arefin
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField uname;
    @FXML
    private JFXPasswordField pass;
    @FXML
    private JFXButton login;
    @FXML
    private AnchorPane rootPane;

    ScreenControl scrcon = new ScreenControl();
    @FXML
    private ComboBox<String> userChoice;

    ObservableList<String> rank = FXCollections
            .observableArrayList("Admin", "Employee", "HR Manager");
    @FXML
    private JFXButton btnClose;

    private void nextStage(String fxml, String title, boolean resizable) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setTitle(title);
        stage.getIcons().add(new Image("/image/icon.png"));
        stage.setResizable(resizable);
        stage.show();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userChoice.setItems(rank);
    }

    private void registration(ActionEvent event) throws IOException {
        Stage current = (Stage) login.getScene().getWindow();
        scrcon.nextStage(Login.Registration, "REGISTER PANEL", true);
        current.hide();
    }

    @FXML
    private void signin(ActionEvent event) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc/spring-jdbc.xml");
        HibernateDao hd = (HibernateDao) context.getBean("hibernateDao");
        if (hd.login(new User(uname.getText(), pass.getText(), userChoice.getSelectionModel().getSelectedItem()))) {
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage st = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/Main.fxml"));
            Parent root = fxmlLoader.load();
            MainController main = (MainController)fxmlLoader.getController();
            main.setUsrNameMedia(uname.getText());
            Scene scene = new Scene(root);
            st.setScene(scene);
            st.setMaximized(true);
            st.setTitle("UVO");
            st.getIcons().add(new Image("/image/icon.png"));
            st.setResizable(true);
            st.show();
            notificationSuccess();
//            Stage current = (Stage) login.getScene().getWindow();
//            current.hide();
        } else {
            uname.clear();
            pass.clear();
            userChoice.getSelectionModel().clearSelection();
            notificationFailed();
        }
    }

    @FXML
    private void btnCloseAction(ActionEvent event) {
        System.exit(0);
    }

    public void notificationSuccess() {
        Image img1 = new Image("/image/signcheck.png");
        Notifications noti = Notifications.create()
                .title("Success")
                .text("Login succesfully")
                .graphic(new ImageView(img1))
                .hideAfter(Duration.seconds(3))
                .position(Pos.BOTTOM_RIGHT);
        noti.darkStyle();
        noti.show();
    }

    public void notificationFailed() {
        Image img2 = new Image("/image/shielderror.png");
        Notifications noti = Notifications.create()
                .title("Failed")
                .text("Username or Password wrong")
                .graphic(new ImageView(img2))
                .hideAfter(Duration.seconds(3))
                .position(Pos.BOTTOM_RIGHT);
        noti.darkStyle();
        noti.show();
    }

}
