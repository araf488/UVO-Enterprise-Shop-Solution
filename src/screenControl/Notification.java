/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screenControl;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Arefin
 */
public class Notification {
    @FXML
    public void notificationSave() {
        Image img = new Image("/image/signcheck.png");
        Notifications noti = Notifications.create()
                .title("Success")
                .text("Saved succesfully.....")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(3))
                .position(Pos.BOTTOM_RIGHT);
        noti.darkStyle();
        noti.show();
    }
    
    @FXML
    public void notificationUpdate() {
        Image img = new Image("/image/signadd.png");
        Notifications noti = Notifications.create()
                .title("Success")
                .text("Updated succesfully.....")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(3))
                .position(Pos.BOTTOM_RIGHT);
        noti.darkStyle();
        noti.show();
    }
    
    @FXML
    public void notificationDelete() {
        Image img = new Image("/image/signerror.png");
        Notifications noti = Notifications.create()
                .title("Success")
                .text("Deleted succesfully......")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(3))
                .position(Pos.BOTTOM_RIGHT);
        noti.darkStyle();
        noti.show();
    }

    @FXML
    public void notificationSale() {
        Image img = new Image("/image/signcheck.png");
        Notifications noti = Notifications.create()
                .title("Success")
                .text("Sale has been completed.....")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(3))
                .position(Pos.BOTTOM_RIGHT);
        noti.darkStyle();
        noti.show();
    }
    
    @FXML
    public void notificationSalary() {
        Image img = new Image("/image/signcheck.png");
        Notifications noti = Notifications.create()
                .title("Success")
                .text("Payment completed.....")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(3))
                .position(Pos.BOTTOM_RIGHT);
        noti.darkStyle();
        noti.show();
    }
}
