/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sale;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author B14
 */
public class ReceiptController implements Initializable {

    @FXML
    private JFXButton btnClose;
    @FXML
    private Label lblname;
    @FXML
    private Label lblmodel;
    @FXML
    private Label lblqty;
    @FXML
    private Label lbluniprice;
    @FXML
    private Label laltotalprice;
    @FXML
    private Label lblcust;

    /**
     * Initializes the controller class.
     *
     * @param name
     */
    public void setProNameMedia(String name) {
        lblname.setText(name);
    }

    public void setCustNameMedia(String cname) {
        lblcust.setText(cname);
    }

    public void setQtyMedia(String qty) {
        lblqty.setText(qty);
    }

    public void setUPriceMedia(String uprice) {
        lbluniprice.setText(uprice);
    }

    public void setTotalPrice(String toprice) {
        laltotalprice.setText(toprice);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void btnCloseAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnPrintOnAction(ActionEvent event) {
    }

}
