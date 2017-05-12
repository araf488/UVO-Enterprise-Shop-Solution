/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jdbc.Customer;
import jdbc.HibernateDao;
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
public class SaleController implements Initializable {

    @FXML
    private TableView<Sale> tblSellView;
    @FXML
    private TableColumn<Sale, Integer> tblClmSellId;
    @FXML
    private TableColumn<Sale, Integer> tblClmProductId;
    @FXML
    private TableColumn<Sale, Double> tblClmSellPrice;
    @FXML
    private TableColumn<Sale, Integer> tblClmQuantity;
    @FXML
    private TableColumn<Sale, Double> tblClmTotalPrice;
    @FXML
    private TableColumn<Sale, String> tblClmWarrenty;
    @FXML
    private TableColumn<Sale, String> tblClmSaleCustomerName;
    @FXML
    private TableColumn<Sale, String> tblClmSaleDate;
    @FXML
    private TableColumn<Sale, String> tblClmsaleProductName;
    @FXML
    private AnchorPane acCustomerMainContent;
    @FXML
    private TableView<Customer> tblCustomer;
    @FXML
    private JFXButton btnSaleUpdate;
    @FXML
    private JFXButton btnSaleOrder;
    @FXML
    private JFXButton btnCustomerUpdate;
    @FXML
    private JFXButton btnCustomerDelete;
    @FXML
    private JFXButton btnNewCusomer;
    @FXML
    private JFXTextField sellIDField;
    @FXML
    private JFXTextField productIdField;
    @FXML
    private JFXTextField saleCustomerNameField;
    @FXML
    private JFXTextField sellingPriceField;
    @FXML
    private JFXTextField quantityField;
    @FXML
    private TextField warrantyField;
    @FXML
    private JFXTextField totalPriceField;
    @FXML
    private DatePicker sellingDateField;
    @FXML
    private TextField searchSell;
    @FXML
    private JFXTextField customerIDField;
    @FXML
    private JFXTextField customerNameField;
    @FXML
    private JFXTextArea customerAddressField;
    @FXML
    private TextField searchCustomer;
    @FXML
    private TableColumn<Customer, Integer> tblClmCustID;
    @FXML
    private TableColumn<Customer, String> tblClmCustName;
    @FXML
    private TableColumn<Customer, String> tblClmCustNumber;
    @FXML
    private TableColumn<Customer, String> tblClmCustAddress;
    @FXML
    private JFXTextField saleProductNameField;
    @FXML
    private JFXTextField contactno;
    
    Notification noti = new Notification();
    
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
    
    ApplicationContext context = new ClassPathXmlApplicationContext("jdbc/spring-jdbc.xml");
    HibernateDao hd = (HibernateDao) context.getBean("hibernateDao");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        searchSell.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filterSaleList((String) oldValue, (String) newValue);
            }
        });
        
        fillSaleTable();
        saletablecreateColumns();
        
        searchCustomer.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filterCustList((String) oldValue, (String) newValue);
            }
        });
        
        fillCustTable();
        custtablecreateColumns();
    }

    public ObservableList<Sale> getSaleTable() throws BeansException {
        ObservableList<Sale> emplist = FXCollections
                .observableArrayList(hd.getSale());
        return emplist;
    }
    
    public ObservableList<Customer> getCustTable() throws BeansException {
        ObservableList<Customer> emplist = FXCollections
                .observableArrayList(hd.getCustomer());
        return emplist;
    }
    
    public void fillSaleTable() throws BeansException {
        tblSellView.setItems(getSaleTable());
    }
    
    public void fillCustTable() throws BeansException {
        tblCustomer.setItems(getCustTable());
    }
    
    public void saletablecreateColumns() {
        tblClmSellId.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("id"));
        tblClmProductId.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("pid"));
        tblClmsaleProductName.setCellValueFactory(new PropertyValueFactory<Sale, String>("productname"));
        tblClmSaleCustomerName.setCellValueFactory(new PropertyValueFactory<Sale, String>("custname"));
        tblClmQuantity.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("qty"));
        tblClmSellPrice.setCellValueFactory(new PropertyValueFactory<Sale, Double>("sprice"));
        tblClmSaleDate.setCellValueFactory(new PropertyValueFactory<Sale, String>("sdate"));
        tblClmWarrenty.setCellValueFactory(new PropertyValueFactory<Sale, String>("warranty"));
        tblClmTotalPrice.setCellValueFactory(new PropertyValueFactory<Sale, Double>("totalprice"));
    }
    
    public void custtablecreateColumns() {
        tblClmCustID.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        tblClmCustName.setCellValueFactory(new PropertyValueFactory<Customer, String>("custname"));
        tblClmCustNumber.setCellValueFactory(new PropertyValueFactory<Customer, String>("contactno"));
        tblClmCustAddress.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
    }
    
    public void filterSaleList(String oldValue, String newValue) {
        ObservableList<Sale> filteredList = FXCollections.observableArrayList();
        if (searchSell == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tblSellView.setItems(getSaleTable());
        } else {
            newValue = newValue.toUpperCase();
            for (Sale sale : tblSellView.getItems()) {
                String filterName = Integer.toString(sale.getPid());
                String filterId = Integer.toString(sale.getId());
                if (filterName.toUpperCase().contains(newValue) || filterId.toUpperCase().contains(newValue)) {
                    filteredList.add(sale);
                }
            }
            tblSellView.setItems(filteredList);
        }
    }
    
    public void filterCustList(String oldValue, String newValue) {
        ObservableList<Customer> filteredList = FXCollections.observableArrayList();
        if (searchCustomer == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tblCustomer.setItems(getCustTable());
        } else {
            newValue = newValue.toUpperCase();
            for (Customer cust : tblCustomer.getItems()) {
                String filterName = cust.getCustname();
                String filterId = Integer.toString(cust.getId());
                if (filterName.toUpperCase().contains(newValue) || filterId.toUpperCase().contains(newValue)) {
                    filteredList.add(cust);
                }
            }
            tblCustomer.setItems(filteredList);
        }
    }

    @FXML
    private void tblCustomerOnClick(MouseEvent event) {
        if (event.getClickCount() >= 1) {
            int i = tblCustomer.getItems().get(tblCustomer.getSelectionModel().getSelectedIndex()).getId();
            customerIDField.setText(Integer.toString(i));
            String pn = tblCustomer.getItems().get(tblCustomer.getSelectionModel().getSelectedIndex()).getCustname();
            customerNameField.setText(pn);
            String cn = tblCustomer.getItems().get(tblCustomer.getSelectionModel().getSelectedIndex()).getContactno();
            contactno.setText(cn);
            String sd = tblCustomer.getItems().get(tblCustomer.getSelectionModel().getSelectedIndex()).getAddress();
            customerAddressField.setText(sd);
        }
    }

    @FXML
    private void btnSaleUpdateAction(ActionEvent event) {
        hd.updateSale(new Sale(Integer.parseInt(sellIDField.getText()), Integer.parseInt(productIdField.getText()), saleCustomerNameField.getText(), 
                Double.parseDouble(sellingPriceField.getText()), Integer.parseInt(quantityField.getText()), 
                sellingDateField.getValue().toString(), warrantyField.getText(), Double.parseDouble(totalPriceField.getText()), 
                saleProductNameField.getText()));
        saleCustomerNameField.clear();
        sellIDField.clear();
        productIdField.clear();
        sellingPriceField.clear();
        quantityField.clear();
        warrantyField.clear();
        totalPriceField.clear();
        saleProductNameField.clear();
        sellingDateField.getEditor().clear();
        fillSaleTable();
        noti.notificationUpdate();
    }
    
    private void totalPrice(MouseEvent event) {
        Integer qty = Integer.parseInt(quantityField.getText());
        Double price = Double.parseDouble(sellingPriceField.getText());
        Double totalPrice = (qty*price);
        totalPriceField.setText(totalPrice.toString());
    }


    @FXML
    private void btnSaleOrderAction(ActionEvent event) throws IOException {
        nextStage(Login.SaleOrder, "", true);
    }

    @FXML
    private void btnCustomerUpdateAction(ActionEvent event){
        hd.updateCustomer(new Customer(Integer.parseInt(customerIDField.getText()), customerNameField.getText(), 
                contactno.getText(), customerAddressField.getText()));
        customerIDField.clear();
        customerAddressField.clear();
        customerNameField.clear();
        contactno.clear();
        fillCustTable();
        noti.notificationUpdate();
    }

    @FXML
    private void btnCustomerDeleteAction(ActionEvent event) {
        hd.deleteCustomer(new Customer(Integer.parseInt(customerIDField.getText())));
        customerIDField.clear();
        customerAddressField.clear();
        customerNameField.clear();
        contactno.clear();
        fillCustTable();
        noti.notificationDelete();
    }

    @FXML
    private void btnNewCustomerAction(ActionEvent event) throws IOException {
        nextStage(Login.AddCustomer, "", true);
    }

    @FXML
    private void refreshSaleTable(ActionEvent event) {
        fillSaleTable();
        saletablecreateColumns();
        sellIDField.clear();
        productIdField.clear();
        saleProductNameField.clear();
        saleCustomerNameField.clear();
        sellingPriceField.clear();
        quantityField.clear();
        warrantyField.clear();
        totalPriceField.clear();
        sellingDateField.getEditor().clear();
    }

    @FXML
    private void tblSaleOnClick(MouseEvent event) {
        if (event.getClickCount() >= 1) {
            int i = tblSellView.getItems().get(tblSellView.getSelectionModel().getSelectedIndex()).getId();
            sellIDField.setText(Integer.toString(i));
            int pid = tblSellView.getItems().get(tblSellView.getSelectionModel().getSelectedIndex()).getPid();
            productIdField.setText(Integer.toString(pid));
            String pn = tblSellView.getItems().get(tblSellView.getSelectionModel().getSelectedIndex()).getProductname();
            saleProductNameField.setText(pn);
            String cn = tblSellView.getItems().get(tblSellView.getSelectionModel().getSelectedIndex()).getCustname();
            saleCustomerNameField.setText(cn);
            int fn = tblSellView.getItems().get(tblSellView.getSelectionModel().getSelectedIndex()).getQty();
            quantityField.setText(Integer.toString(fn));
            Double s = tblSellView.getItems().get(tblSellView.getSelectionModel().getSelectedIndex()).getSprice();
            sellingPriceField.setText(Double.toString(s));
            String sd = tblSellView.getItems().get(tblSellView.getSelectionModel().getSelectedIndex()).getSdate();
            sellingDateField.getEditor().setText(sd);
            String e = tblSellView.getItems().get(tblSellView.getSelectionModel().getSelectedIndex()).getWarranty();
            warrantyField.setText(e);
            Double c = tblSellView.getItems().get(tblSellView.getSelectionModel().getSelectedIndex()).getTotalprice();
            totalPriceField.setText(Double.toString(c));
        }
    }

    @FXML
    private void refreshCustTable(ActionEvent event) {
        fillCustTable();
        custtablecreateColumns();
        customerIDField.clear();
        customerAddressField.clear();
        customerNameField.clear();
        contactno.clear();
    }

    @FXML
    private void btnSaleDeleteAction(ActionEvent event) {
        hd.deleteSale(new Sale(Integer.parseInt(sellIDField.getText())));
        sellIDField.clear();
        productIdField.clear();
        saleProductNameField.clear();
        saleCustomerNameField.clear();
        sellingPriceField.clear();
        quantityField.clear();
        warrantyField.clear();
        totalPriceField.clear();
        sellingDateField.getEditor().clear();
        fillSaleTable();
        noti.notificationDelete();
    }
    
}
