/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sale;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jdbc.HibernateDao;
import jdbc.Product;
import jdbc.Sale;
import login.Login;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import screenControl.Notification;

/**
 * FXML Controller class
 *
 * @author Arefin
 */
public class SaleOrderController implements Initializable {

    @FXML
    private Button btnClose;
    @FXML
    private JFXButton btnSale;
    @FXML
    private JFXComboBox<String> custnameCombo;
    @FXML
    private JFXTextField pidField;
    @FXML
    private JFXButton btnProductSearch;
    @FXML
    private JFXTextField pnameField;
    @FXML
    private JFXTextField sqtyField;
    @FXML
    private JFXTextField spriceField;
    @FXML
    private JFXTextField warrantyField;
    @FXML
    private JFXTextField totalPriceField;
    @FXML
    private DatePicker sdate;
    @FXML
    private JFXButton btnSale2;
    @FXML
    private TableColumn<Sale, Integer> clmProID;
    @FXML
    private TableColumn<Sale, String> clmProname;
    @FXML
    private TableColumn<Sale, Integer> clmQty;
    @FXML
    private TableColumn<Sale, Double> clmPrice;
    @FXML
    private TableColumn<Sale, Double> clmTotalprice;
    @FXML
    private JFXButton btnSaleUpdate;
    @FXML
    private JFXTextField availQty;
    @FXML
    private TableView<Sale> tblCart;
    @FXML
    private TextField sid;
    @FXML
    private TableColumn<Sale, String> clmsaledate;

    ApplicationContext context = new ClassPathXmlApplicationContext("jdbc/spring-jdbc.xml");
    HibernateDao hd = (HibernateDao) context.getBean("hibernateDao");

    /**
     * Initializes the controller class.
     */
    Notification noti = new Notification();
    String pname;
    String custname;
    String qty;
    String sprice;
    String tprice;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
    private void btnCloseAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnSaleAction(ActionEvent event) throws IOException {
        hd.saveSale(new Sale(Integer.parseInt(pidField.getText()), custnameCombo.getSelectionModel().getSelectedItem(),
                Double.parseDouble(spriceField.getText()), Integer.parseInt(sqtyField.getText()),
                sdate.getValue().toString(), warrantyField.getText(), Double.parseDouble(totalPriceField.getText()),
                pnameField.getText()));
        fillSaleTable();
        saletablecreateColumns();
        noti.notificationSale();
        pname = pnameField.getText();
        custname = custnameCombo.getSelectionModel().getSelectedItem();
        qty = sqtyField.getText();
        sprice = spriceField.getText();
        tprice = totalPriceField.getText();
    }

    public ObservableList<String> getCustName() throws BeansException {
        ObservableList<String> emplist = FXCollections
                .observableArrayList(hd.getCustomerName());
        return emplist;
    }

    @FXML
    private void btnProductSearchAction(ActionEvent event) {
        List<Product> list = hd.getProductByID(Integer.parseInt(pidField.getText()));
        for (Product s : list) {
            pnameField.setText(s.getPname());
            availQty.setText(s.getQty().toString());
            spriceField.setText(s.getSprice().toString());
            warrantyField.setText(s.getWarranty());
        }
    }

    @FXML
    private void btnUpdateAction(ActionEvent event) throws IOException {
        hd.updateSale(new Sale(Integer.parseInt(sid.getText()), Integer.parseInt(pidField.getText()), custnameCombo.getSelectionModel().getSelectedItem(),
                Double.parseDouble(spriceField.getText()), Integer.parseInt(sqtyField.getText()),
                sdate.getValue().toString(), warrantyField.getText(), Double.parseDouble(totalPriceField.getText()),
                pnameField.getText()));
        sid.clear();
        pidField.clear();
        spriceField.clear();
        sqtyField.clear();
        totalPriceField.clear();
        warrantyField.clear();
        custnameCombo.getSelectionModel().clearSelection();
        pnameField.clear();
        sdate.getEditor().clear();
        fillSaleTable();
        noti.notificationUpdate();
    }

    @FXML
    private void btnDeleteAction(ActionEvent event) {
        hd.deleteSale(new Sale(Integer.parseInt(sid.getText())));
        sid.clear();
        pidField.clear();
        spriceField.clear();
        sqtyField.clear();
        totalPriceField.clear();
        warrantyField.clear();
        custnameCombo.getSelectionModel().clearSelection();
        pnameField.clear();
        sdate.getEditor().clear();
        noti.notificationDelete();
    }

    @FXML
    private void addCustomer(ActionEvent event) throws IOException {
        nextStage(Login.AddCustomer, "", true);
    }

    @FXML
    private void totalPrice(MouseEvent event) {
        Integer qty = Integer.parseInt(sqtyField.getText());
        Double price = Double.parseDouble(spriceField.getText());
        Double totalPrice = (qty * price);
        totalPriceField.setText(totalPrice.toString());
    }

    public void saletablecreateColumns() {
        clmProID.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("pid"));
        clmProname.setCellValueFactory(new PropertyValueFactory<Sale, String>("productname"));
        clmQty.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("qty"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<Sale, Double>("sprice"));
        clmTotalprice.setCellValueFactory(new PropertyValueFactory<Sale, Double>("totalprice"));
        clmsaledate.setCellValueFactory(new PropertyValueFactory<Sale, String>("sdate"));
    }

    public ObservableList<Sale> getSaleTable() throws BeansException {
        ObservableList<Sale> emplist = FXCollections
                .observableArrayList(hd.getOrder());
        return emplist;
    }

    public void fillSaleTable() throws BeansException {
        tblCart.setItems(getSaleTable());
    }

    @FXML
    private void setOnSaleField(MouseEvent me) {
        if (me.getClickCount() >= 1) {
            int si = tblCart.getItems().get(tblCart.getSelectionModel().getSelectedIndex()).getId();
            sid.setText(Integer.toString(si));
            int i = tblCart.getItems().get(tblCart.getSelectionModel().getSelectedIndex()).getPid();
            pidField.setText(Integer.toString(i));
            String un = tblCart.getItems().get(tblCart.getSelectionModel().getSelectedIndex()).getProductname();
            pnameField.setText(un);
            int fn = tblCart.getItems().get(tblCart.getSelectionModel().getSelectedIndex()).getQty();
            sqtyField.setText(Integer.toString(fn));
            Double s = tblCart.getItems().get(tblCart.getSelectionModel().getSelectedIndex()).getSprice();
            spriceField.setText(Double.toString(s));
            String e = tblCart.getItems().get(tblCart.getSelectionModel().getSelectedIndex()).getWarranty();
            warrantyField.setText(e);
            String c = tblCart.getItems().get(tblCart.getSelectionModel().getSelectedIndex()).getCustname();
            custnameCombo.setValue(c);
            String sd = tblCart.getItems().get(tblCart.getSelectionModel().getSelectedIndex()).getSdate();
            sdate.getEditor().setText(sd);
        }

    }

    @FXML
    private void custCmbOnClick(MouseEvent event) {
        custnameCombo.setItems(getCustName());
    }

    @FXML
    private void btnRcptOnAction(ActionEvent event) throws IOException {
        custnameCombo.getSelectionModel().clearSelection();
        pidField.clear();
        spriceField.clear();
        sqtyField.clear();
        warrantyField.clear();
        totalPriceField.clear();
        pnameField.clear();
        availQty.clear();
        sdate.getEditor().clear();
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage st = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sale/Receipt.fxml"));
        Parent root = fxmlLoader.load();
        ReceiptController receipt = (ReceiptController) fxmlLoader.getController();
        receipt.setProNameMedia(pname);
        receipt.setCustNameMedia(custname);
        receipt.setQtyMedia(qty);
        receipt.setUPriceMedia(sprice);
        receipt.setTotalPrice(tprice);
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.initStyle(StageStyle.UNDECORATED);
        st.show();
    }

}
