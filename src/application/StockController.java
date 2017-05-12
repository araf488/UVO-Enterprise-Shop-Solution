/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import jdbc.Brand;
import jdbc.Category;
import jdbc.HibernateDao;
import jdbc.Product;
import jdbc.Supplier;
import jdbc.Unit;
import jdbc.Warranty;
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
public class StockController implements Initializable {

    @FXML
    private StackPane spProductContent;
    @FXML
    private TableView<Product> tblViewCurrentStore;
    @FXML
    private TableColumn<Product, Integer> tblClmProductId;
    @FXML
    private TableColumn<Product, String> tblClmProductName;
    @FXML
    private TableColumn<Product, Integer> tblClmProductquantity;
    @FXML
    private TableColumn<Product, String> tblClmProductUnit;
    @FXML
    private TableColumn<Product, String> tblClmProductBrand;
    @FXML
    private TableColumn<Product, String> tblClmProductCatagory;
    @FXML
    private TableView<Supplier> tblSupplyer;
    @FXML
    private TableColumn<Supplier, String> clmSupplyerName;
    @FXML
    private TableColumn<Supplier, String> clmSupplyerAddress;
    @FXML
    private TableColumn<Supplier, String> clmSupplyerJoining;
    @FXML
    private JFXButton btnAddBrand;
    @FXML
    private TableView<Brand> tblBrand;
    @FXML
    private TableView<Category> tblCatagory;
    @FXML
    private TableView<Unit> tblViewUnit;
    @FXML
    private JFXButton btnaddNew;
    @FXML
    private JFXButton btnAddSuppliers;
    @FXML
    private JFXButton btnAddCategory;
    @FXML
    private JFXButton btnAddUnit;
    @FXML
    private JFXButton btnAddWaranty;
    @FXML
    private JFXButton btnUpdateSuppliers;
    @FXML
    private JFXButton btnDeleteSuppliers;
    @FXML
    private JFXButton btnUpdateBrand;
    @FXML
    private JFXButton btnDeleteBrand;
    @FXML
    private JFXButton btnDeleteCategory;
    @FXML
    private JFXButton btnUpdateUnit;
    @FXML
    private JFXButton btnDeleteUnit;
    @FXML
    private JFXButton btnUpdateWaranty;
    @FXML
    private JFXButton btnDeleteWaranty;
    @FXML
    private TextField productsearch;
    @FXML
    private TableColumn<Product, String> tblClmProductWarranty;
    @FXML
    private TextField supplierSearch;
    @FXML
    private TableColumn<Supplier, Integer> clmSupplyerId;
    @FXML
    private TableColumn<Supplier, String> clmSupplyerContact;
    @FXML
    private TextField searchBrand;
    @FXML
    private TableColumn<Brand, Integer> clmBrandId;
    @FXML
    private TableColumn<Brand, String> clmBrandName;
    @FXML
    private TableColumn<Brand, String> clmBrandSuplierName;
    @FXML
    private TableColumn<Brand, String> clmBrandDescription;
    @FXML
    private TextField searchCategory;
    @FXML
    private TableColumn<Category, String> clmCategorySupplyerName;
    @FXML
    private TableColumn<Category, String> clmCatagoryBrandName;
    @FXML
    private TextField searchUnit;
    @FXML
    private TextField searchWarranty;
    @FXML
    private JFXTextField productID;
    @FXML
    private JFXTextField productBrand;
    @FXML
    private JFXTextField productName;
    @FXML
    private JFXTextField productCategory;
    @FXML
    private JFXTextField productQuantity;
    @FXML
    private JFXTextField productPPrice;
    @FXML
    private JFXTextField productSPrice;
    @FXML
    private JFXTextArea productDescription;
    @FXML
    private JFXTextField productSupplier;
    @FXML
    private JFXTextField productUnit;
    @FXML
    private JFXTextField productWarranty;
    @FXML
    private TableColumn<Product, Double> tblClmProductPPrice;
    @FXML
    private TableColumn<Product, Double> tblClmProductSPrice;
    @FXML
    private TableColumn<Product, String> tblClmProductDescription;
    @FXML
    private TableColumn<Product, String> tblClmProductSupplier;
    @FXML
    private TableColumn<Product, String> tblClmProductPDate;
    @FXML
    private JFXTextField supSupplierID;
    @FXML
    private JFXTextField supSupplierName;
    @FXML
    private JFXTextField supSupplerMobile;
    @FXML
    private JFXTextField supSupplierDate;
    @FXML
    private JFXTextArea supSupplierAddress;
    @FXML
    private JFXTextField brandBrandID;
    @FXML
    private JFXTextField brandBrandName;
    @FXML
    private JFXTextField brandBrandSupplier;
    @FXML
    private JFXTextArea brandBrandDescription;
    @FXML
    private JFXButton btnUpdateCategory;
    @FXML
    private JFXTextField catCategoryID;
    @FXML
    private JFXTextField catCategoryName;
    @FXML
    private JFXTextField catBrandName;
    @FXML
    private JFXTextField catSupplierName;
    @FXML
    private JFXTextArea catCategoryDesc;
    @FXML
    private TableColumn<Category, Integer> clmcatCatagoryId;
    @FXML
    private TableColumn<Category, String> clmcatCatagoryName;
    @FXML
    private TableColumn<Category, String> clmcatCatagoryDescription;
    @FXML
    private JFXTextField unitUnitID;
    @FXML
    private JFXTextField unitUnitName;
    @FXML
    private JFXTextField unitUnitSize;
    @FXML
    private TableColumn<Unit, Integer> clmunitUnitId;
    @FXML
    private TableColumn<Unit, String> clmunitUnitName;
    @FXML
    private TableColumn<Unit, Integer> clmunitUnitSize;
    @FXML
    private JFXTextField warWarrantyID;
    @FXML
    private JFXTextField warWarrantyType;
    @FXML
    private JFXTextField warWarrantyDays;
    @FXML
    private JFXTextArea warWarrantyDesc;
    @FXML
    private TableColumn<Warranty, Integer> clmwarWarrantyId;
    @FXML
    private TableColumn<Warranty, String> clmwarWarrantyType;
    @FXML
    private TableColumn<Warranty, String> clmwarWarrantyDays;
    @FXML
    private TableColumn<Warranty, String> clmwarWarrantyDescription;
    @FXML
    private TableView<Warranty> tblViewWarranty;
    @FXML
    private JFXTextField productPDate;
    @FXML
    private JFXButton btnUpdateProduct;
    @FXML
    private JFXButton btnDeleteProduct;
    @FXML
    private ImageView imgView;
    @FXML
    private JFXButton btnImage;
    /**
     * Initializes the controller class.
     */
    Notification noti = new Notification();
    ApplicationContext context = new ClassPathXmlApplicationContext("jdbc/spring-jdbc.xml");
    HibernateDao hd = (HibernateDao) context.getBean("hibernateDao");
    private String imagePath;
    private byte[] product_image;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        searchWarranty.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filterWarrantyList((String) oldValue, (String) newValue);
            }
        });
        fillWarrantyTable();
        warrantycreateColumns();

        productsearch.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filterProductList((String) oldValue, (String) newValue);
            }
        });
        fillProductTable();
        productcreateColumns();

        supplierSearch.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filterSupplierList((String) oldValue, (String) newValue);
            }
        });
        fillSupplierTable();
        suppliercreateColumns();

        searchBrand.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filterBrandList((String) oldValue, (String) newValue);
            }
        });
        fillBrandTable();
        brandcreateColumns();

        searchCategory.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filterCategoryList((String) oldValue, (String) newValue);
            }
        });
        fillCategoryTable();
        categorycreateColumns();

        searchUnit.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filterUnitList((String) oldValue, (String) newValue);
            }
        });
        fillUnitTable();
        unitcreateColumns();
    }

    public void filterWarrantyList(String oldValue, String newValue) {
        ObservableList<Warranty> filteredList = FXCollections.observableArrayList();
        if (searchWarranty == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tblViewWarranty.setItems(getWarrantyTable());
        } else {
            newValue = newValue.toUpperCase();
            for (Warranty war : tblViewWarranty.getItems()) {
                String filterName = war.getWarrantytype();
                String filterId = Integer.toString(war.getId());
                if (filterName.toUpperCase().contains(newValue) || filterId.toUpperCase().contains(newValue)) {
                    filteredList.add(war);
                }
            }
            tblViewWarranty.setItems(filteredList);
        }
    }

    public void filterUnitList(String oldValue, String newValue) {
        ObservableList<Unit> filteredList = FXCollections.observableArrayList();
        if (tblViewUnit == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tblViewUnit.setItems(getUnitTable());
        } else {
            newValue = newValue.toUpperCase();
            for (Unit uni : tblViewUnit.getItems()) {
                String filterName = uni.getUnitname();
                String filterId = Integer.toString(uni.getId());
                if (filterName.toUpperCase().contains(newValue) || filterId.toUpperCase().contains(newValue)) {
                    filteredList.add(uni);
                }
            }
            tblViewUnit.setItems(filteredList);
        }
    }

    public void filterCategoryList(String oldValue, String newValue) {
        ObservableList<Category> filteredList = FXCollections.observableArrayList();
        if (tblCatagory == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tblCatagory.setItems(getCategoryTable());
        } else {
            newValue = newValue.toUpperCase();
            for (Category cat : tblCatagory.getItems()) {
                String filterName = cat.getCatname();
                String filterId = Integer.toString(cat.getId());
                if (filterName.toUpperCase().contains(newValue) || filterId.toUpperCase().contains(newValue)) {
                    filteredList.add(cat);
                }
            }
            tblCatagory.setItems(filteredList);
        }
    }

    public void filterBrandList(String oldValue, String newValue) {
        ObservableList<Brand> filteredList = FXCollections.observableArrayList();
        if (tblBrand == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tblBrand.setItems(getBrandTable());
        } else {
            newValue = newValue.toUpperCase();
            for (Brand br : tblBrand.getItems()) {
                String filterName = br.getBrandname();
                String filterId = Integer.toString(br.getId());
                if (filterName.toUpperCase().contains(newValue) || filterId.toUpperCase().contains(newValue)) {
                    filteredList.add(br);
                }
            }
            tblBrand.setItems(filteredList);
        }
    }

    public void filterSupplierList(String oldValue, String newValue) {
        ObservableList<Supplier> filteredList = FXCollections.observableArrayList();
        if (tblSupplyer == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tblSupplyer.setItems(getSupplierTable());
        } else {
            newValue = newValue.toUpperCase();
            for (Supplier sup : tblSupplyer.getItems()) {
                String filterName = sup.getSuppliername();
                String filterId = Integer.toString(sup.getId());
                if (filterName.toUpperCase().contains(newValue) || filterId.toUpperCase().contains(newValue)) {
                    filteredList.add(sup);
                }
            }
            tblSupplyer.setItems(filteredList);
        }
    }

    public void filterProductList(String oldValue, String newValue) {
        ObservableList<Product> filteredList = FXCollections.observableArrayList();
        if (tblViewCurrentStore == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tblViewCurrentStore.setItems(getProductTable());
        } else {
            newValue = newValue.toUpperCase();
            for (Product pro : tblViewCurrentStore.getItems()) {
                String filterName = pro.getPname();
                String filterBrand = pro.getBrandname();
                String filterCat = pro.getCatname();
                if (filterName.toUpperCase().contains(newValue) || filterBrand.toUpperCase().contains(newValue) || filterCat.toUpperCase().contains(newValue)) {
                    filteredList.add(pro);
                }
            }
            tblViewCurrentStore.setItems(filteredList);
        }
    }

    @FXML
    private void setWarrantyOnField(MouseEvent me) {
        if (me.getClickCount() >= 1) {
            int i = tblViewWarranty.getItems().get(tblViewWarranty.getSelectionModel().getSelectedIndex()).getId();
            warWarrantyID.setText(Integer.toString(i));
            String un = tblViewWarranty.getItems().get(tblViewWarranty.getSelectionModel().getSelectedIndex()).getWarrantytype();
            warWarrantyType.setText(un);
            String fn = tblViewWarranty.getItems().get(tblViewWarranty.getSelectionModel().getSelectedIndex()).getWarrantydays();
            warWarrantyDays.setText(fn);
            String n = tblViewWarranty.getItems().get(tblViewWarranty.getSelectionModel().getSelectedIndex()).getDescription();
            warWarrantyDesc.setText(n);
        }
    }

    public void fillWarrantyTable() throws BeansException {
        tblViewWarranty.setItems(getWarrantyTable());
    }

    public void fillUnitTable() throws BeansException {
        tblViewUnit.setItems(getUnitTable());
    }

    public void fillCategoryTable() throws BeansException {
        tblCatagory.setItems(getCategoryTable());
    }

    public void fillBrandTable() throws BeansException {
        tblBrand.setItems(getBrandTable());
    }

    public void fillSupplierTable() throws BeansException {
        tblSupplyer.setItems(getSupplierTable());
    }

    public void fillProductTable() throws BeansException {
        tblViewCurrentStore.setItems(getProductTable());
    }

    public void warrantycreateColumns() {
        clmwarWarrantyId.setCellValueFactory(new PropertyValueFactory<Warranty, Integer>("id"));
        clmwarWarrantyType.setCellValueFactory(new PropertyValueFactory<Warranty, String>("warrantytype"));
        clmwarWarrantyDays.setCellValueFactory(new PropertyValueFactory<Warranty, String>("warrantydays"));
        clmwarWarrantyDescription.setCellValueFactory(new PropertyValueFactory<Warranty, String>("description"));
    }

    public void unitcreateColumns() {
        clmunitUnitId.setCellValueFactory(new PropertyValueFactory<Unit, Integer>("id"));
        clmunitUnitName.setCellValueFactory(new PropertyValueFactory<Unit, String>("unitname"));
        clmunitUnitSize.setCellValueFactory(new PropertyValueFactory<Unit, Integer>("unitsize"));
    }

    public void categorycreateColumns() {
        clmcatCatagoryId.setCellValueFactory(new PropertyValueFactory<Category, Integer>("id"));
        clmcatCatagoryName.setCellValueFactory(new PropertyValueFactory<Category, String>("catname"));
        clmCategorySupplyerName.setCellValueFactory(new PropertyValueFactory<Category, String>("suppliername"));
        clmCatagoryBrandName.setCellValueFactory(new PropertyValueFactory<Category, String>("brandname"));
        clmcatCatagoryDescription.setCellValueFactory(new PropertyValueFactory<Category, String>("description"));
    }

    public void brandcreateColumns() {
        clmBrandId.setCellValueFactory(new PropertyValueFactory<Brand, Integer>("id"));
        clmBrandName.setCellValueFactory(new PropertyValueFactory<Brand, String>("brandname"));
        clmBrandSuplierName.setCellValueFactory(new PropertyValueFactory<Brand, String>("suppliername"));
        clmBrandDescription.setCellValueFactory(new PropertyValueFactory<Brand, String>("description"));
    }

    public void suppliercreateColumns() {
        clmSupplyerId.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("id"));
        clmSupplyerName.setCellValueFactory(new PropertyValueFactory<Supplier, String>("suppliername"));
        clmSupplyerContact.setCellValueFactory(new PropertyValueFactory<Supplier, String>("contactno"));
        clmSupplyerAddress.setCellValueFactory(new PropertyValueFactory<Supplier, String>("address"));
        clmSupplyerJoining.setCellValueFactory(new PropertyValueFactory<Supplier, String>("suppsince"));
    }

    public void productcreateColumns() {
        tblClmProductId.setCellValueFactory(new PropertyValueFactory<Product, Integer>("pid"));
        tblClmProductName.setCellValueFactory(new PropertyValueFactory<Product, String>("pname"));
        tblClmProductquantity.setCellValueFactory(new PropertyValueFactory<Product, Integer>("qty"));
        tblClmProductPPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("pprice"));
        tblClmProductSPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("sprice"));
        tblClmProductDescription.setCellValueFactory(new PropertyValueFactory<Product, String>("pdescription"));
        tblClmProductSupplier.setCellValueFactory(new PropertyValueFactory<Product, String>("suppliername"));
        tblClmProductBrand.setCellValueFactory(new PropertyValueFactory<Product, String>("brandname"));
        tblClmProductCatagory.setCellValueFactory(new PropertyValueFactory<Product, String>("catname"));
        tblClmProductUnit.setCellValueFactory(new PropertyValueFactory<Product, String>("unit"));
        tblClmProductWarranty.setCellValueFactory(new PropertyValueFactory<Product, String>("warranty"));
        tblClmProductPDate.setCellValueFactory(new PropertyValueFactory<Product, String>("pdate"));
    }

    public ObservableList<Warranty> getWarrantyTable() throws BeansException {
        ObservableList<Warranty> emplist = FXCollections
                .observableArrayList(hd.getWarranty());
        return emplist;
    }

    public ObservableList<Unit> getUnitTable() throws BeansException {
        ObservableList<Unit> emplist = FXCollections
                .observableArrayList(hd.getUnit());
        return emplist;
    }

    public ObservableList<Supplier> getSupplierTable() throws BeansException {
        ObservableList<Supplier> emplist = FXCollections
                .observableArrayList(hd.getSupplier());
        return emplist;
    }

    public ObservableList<Product> getProductTable() throws BeansException {
        ObservableList<Product> emplist = FXCollections
                .observableArrayList(hd.getProduct());
        return emplist;
    }

    public ObservableList<Category> getCategoryTable() throws BeansException {
        ObservableList<Category> emplist = FXCollections
                .observableArrayList(hd.getCategory());
        return emplist;
    }

    public ObservableList<Brand> getBrandTable() throws BeansException {
        ObservableList<Brand> emplist = FXCollections
                .observableArrayList(hd.getBrand());
        return emplist;
    }

    @FXML
    private void tblViewCurrentStoreOnClick(MouseEvent event) {
        if (event.getClickCount() >= 1) {
            int i = tblViewCurrentStore.getItems().get(tblViewCurrentStore.getSelectionModel().getSelectedIndex()).getPid();
            productID.setText(Integer.toString(i));
            String un = tblViewCurrentStore.getItems().get(tblViewCurrentStore.getSelectionModel().getSelectedIndex()).getPname();
            productName.setText(un);
            String fn = tblViewCurrentStore.getItems().get(tblViewCurrentStore.getSelectionModel().getSelectedIndex()).getBrandname();
            productBrand.setText(fn);
            String n = tblViewCurrentStore.getItems().get(tblViewCurrentStore.getSelectionModel().getSelectedIndex()).getCatname();
            productCategory.setText(n);
            int qn = tblViewCurrentStore.getItems().get(tblViewCurrentStore.getSelectionModel().getSelectedIndex()).getQty();
            productQuantity.setText(Integer.toString(qn));
            double pp = tblViewCurrentStore.getItems().get(tblViewCurrentStore.getSelectionModel().getSelectedIndex()).getPprice();
            productPPrice.setText(Double.toString(pp));
            double sp = tblViewCurrentStore.getItems().get(tblViewCurrentStore.getSelectionModel().getSelectedIndex()).getSprice();
            productSPrice.setText(Double.toString(sp));
            String ds = tblViewCurrentStore.getItems().get(tblViewCurrentStore.getSelectionModel().getSelectedIndex()).getPdescription();
            productDescription.setText(ds);
            String sn = tblViewCurrentStore.getItems().get(tblViewCurrentStore.getSelectionModel().getSelectedIndex()).getSuppliername();
            productSupplier.setText(sn);
            String uun = tblViewCurrentStore.getItems().get(tblViewCurrentStore.getSelectionModel().getSelectedIndex()).getUnit();
            productUnit.setText(uun);
            String wr = tblViewCurrentStore.getItems().get(tblViewCurrentStore.getSelectionModel().getSelectedIndex()).getWarranty();
            productWarranty.setText(wr);
            String pd = tblViewCurrentStore.getItems().get(tblViewCurrentStore.getSelectionModel().getSelectedIndex()).getPdate();
            productPDate.setText(pd);
        }
    }

    @FXML
    private void tblViewCurrentStoreOnScroll(ScrollEvent event) {
    }

    @FXML
    private void tblSupplyerOnClick(MouseEvent event) {
        if (event.getClickCount() >= 1) {
            int i = tblSupplyer.getItems().get(tblSupplyer.getSelectionModel().getSelectedIndex()).getId();
            supSupplierID.setText(Integer.toString(i));
            String un = tblSupplyer.getItems().get(tblSupplyer.getSelectionModel().getSelectedIndex()).getSuppliername();
            supSupplierName.setText(un);
            String fn = tblSupplyer.getItems().get(tblSupplyer.getSelectionModel().getSelectedIndex()).getContactno();
            supSupplerMobile.setText(fn);
            String n = tblSupplyer.getItems().get(tblSupplyer.getSelectionModel().getSelectedIndex()).getAddress();
            supSupplierAddress.setText(n);
            String ss = tblSupplyer.getItems().get(tblSupplyer.getSelectionModel().getSelectedIndex()).getSuppsince();
            supSupplierDate.setText(n);
        }
    }

    @FXML
    private void tfSearchOnAction(ActionEvent event) {
    }

    @FXML
    private void tblBrandOnClick(MouseEvent event) {
        if (event.getClickCount() >= 1) {
            int i = tblBrand.getItems().get(tblBrand.getSelectionModel().getSelectedIndex()).getId();
            brandBrandID.setText(Integer.toString(i));
            String un = tblBrand.getItems().get(tblBrand.getSelectionModel().getSelectedIndex()).getBrandname();
            brandBrandName.setText(un);
            String fn = tblBrand.getItems().get(tblBrand.getSelectionModel().getSelectedIndex()).getSuppliername();
            brandBrandSupplier.setText(fn);
            String n = tblBrand.getItems().get(tblBrand.getSelectionModel().getSelectedIndex()).getDescription();
            brandBrandDescription.setText(n);
        }
    }

    @FXML
    private void tblCatagoryOnClick(MouseEvent event) {
        if (event.getClickCount() >= 1) {
            int i = tblCatagory.getItems().get(tblCatagory.getSelectionModel().getSelectedIndex()).getId();
            catCategoryID.setText(Integer.toString(i));
            String cn = tblCatagory.getItems().get(tblCatagory.getSelectionModel().getSelectedIndex()).getCatname();
            catCategoryName.setText(cn);
            String un = tblCatagory.getItems().get(tblCatagory.getSelectionModel().getSelectedIndex()).getBrandname();
            catBrandName.setText(un);
            String fn = tblCatagory.getItems().get(tblCatagory.getSelectionModel().getSelectedIndex()).getSuppliername();
            catSupplierName.setText(fn);
            String n = tblCatagory.getItems().get(tblCatagory.getSelectionModel().getSelectedIndex()).getDescription();
            catCategoryDesc.setText(n);
        }
    }

    @FXML
    private void tblViewUnitOnClick(MouseEvent event) {
        if (event.getClickCount() >= 1) {
            int i = tblViewUnit.getItems().get(tblViewUnit.getSelectionModel().getSelectedIndex()).getId();
            unitUnitID.setText(Integer.toString(i));
            String cn = tblViewUnit.getItems().get(tblViewUnit.getSelectionModel().getSelectedIndex()).getUnitname();
            unitUnitName.setText(cn);
            int un = tblViewUnit.getItems().get(tblViewUnit.getSelectionModel().getSelectedIndex()).getUnitsize();
            unitUnitSize.setText(Integer.toString(un));
        }
    }

    @FXML
    private void btnaddNewAction(ActionEvent event) throws IOException {
        nextStage(Login.AddNewProduct, "", true);
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
    private void btnAddSuppliersAction(ActionEvent event) throws IOException {
        nextStage(Login.AddSuppliers, "", true);
    }

    @FXML
    private void btnAddBrandAction(ActionEvent event) throws IOException {
        nextStage(Login.AddBrand, "", true);
    }

    @FXML
    private void btnAddCategoryAction(ActionEvent event) throws IOException {
        nextStage(Login.AddCategory, "", true);
    }

    @FXML
    private void btnAddUnitAction(ActionEvent event) throws IOException {
        nextStage(Login.AddUnit, "", true);
    }

    @FXML
    private void btnAddWarrantyAction(ActionEvent event) throws IOException {
        nextStage(Login.AddWarranty, "", true);
    }

    @FXML
    private void btnUpdateSuppliersAction(ActionEvent event) {
        hd.updateSupplier(new Supplier(Integer.parseInt(supSupplierID.getText()), supSupplierName.getText(),
                supSupplerMobile.getText(), supSupplierAddress.getText(), supSupplierDate.getText()));
        supSupplerMobile.clear();
        supSupplierAddress.clear();
        supSupplierDate.clear();
        supSupplierID.clear();
        supSupplierName.clear();
        fillSupplierTable();
        suppliercreateColumns();
        noti.notificationUpdate();
    }

    @FXML
    private void btnDeleteSuppliersAction(ActionEvent event) {
        hd.deleteSupplier(new Supplier(Integer.parseInt(supSupplierID.getText())));
        supSupplerMobile.clear();
        supSupplierAddress.clear();
        supSupplierDate.clear();
        supSupplierID.clear();
        supSupplierName.clear();
        fillSupplierTable();
        suppliercreateColumns();
        noti.notificationDelete();
    }

    @FXML
    private void btnUpdateBrandAction(ActionEvent event) {
        hd.updateBrand(new Brand(Integer.parseInt(brandBrandID.getText()), brandBrandSupplier.getText(),
                brandBrandName.getText(), brandBrandDescription.getText()));
        brandBrandDescription.clear();
        brandBrandID.clear();
        brandBrandName.clear();
        brandBrandSupplier.clear();
        fillBrandTable();
        brandcreateColumns();
        noti.notificationUpdate();
    }

    @FXML
    private void btnDeleteBrandAction(ActionEvent event) {
        hd.deleteBrand(new Brand(Integer.parseInt(brandBrandID.getText())));
        brandBrandDescription.clear();
        brandBrandID.clear();
        brandBrandName.clear();
        brandBrandSupplier.clear();
        fillBrandTable();
        brandcreateColumns();
        noti.notificationDelete();
    }

    @FXML
    private void btnUpdateCategoryAction(ActionEvent event) {
        hd.updateCat(new Category(Integer.parseInt(catCategoryID.getText()), catSupplierName.getText(), catBrandName.getText(),
                catCategoryName.getText(), catCategoryDesc.getText()));
        catBrandName.clear();
        catCategoryDesc.clear();
        catCategoryID.clear();
        catCategoryName.clear();
        catSupplierName.clear();
        fillCategoryTable();
        categorycreateColumns();
        noti.notificationUpdate();
    }

    @FXML
    private void btnDeleteCategoryAction(ActionEvent event) {
        hd.deleteCat(new Category(Integer.parseInt(catCategoryID.getText())));
        catBrandName.clear();
        catCategoryDesc.clear();
        catCategoryID.clear();
        catCategoryName.clear();
        catSupplierName.clear();
        fillCategoryTable();
        categorycreateColumns();
        noti.notificationDelete();
    }

    @FXML
    private void btnUpdateUnitAction(ActionEvent event) {
        hd.updateUnit(new Unit(Integer.parseInt(unitUnitID.getText()), unitUnitName.getText(),
                Integer.parseInt(unitUnitSize.getText())));
        unitUnitID.clear();
        unitUnitName.clear();
        unitUnitSize.clear();
        fillUnitTable();
        unitcreateColumns();
        noti.notificationUpdate();
    }

    @FXML
    private void btnDeleteUnitAction(ActionEvent event) {
        hd.deleteUnit(new Unit(Integer.parseInt(unitUnitID.getText())));
        unitUnitID.clear();
        unitUnitName.clear();
        unitUnitSize.clear();
        fillUnitTable();
        unitcreateColumns();
        noti.notificationDelete();
    }

    @FXML
    private void btnUpdateWarrantyAction(ActionEvent event) {
        hd.updateWarranty(new Warranty(Integer.parseInt(warWarrantyID.getText()), warWarrantyType.getText(),
                warWarrantyDays.getText(), warWarrantyDesc.getText()));
        warWarrantyDays.clear();
        warWarrantyDesc.clear();
        warWarrantyID.clear();
        warWarrantyType.clear();
        fillWarrantyTable();
        warrantycreateColumns();
        noti.notificationUpdate();
    }

    @FXML
    private void btnDeleteWarrantyAction(ActionEvent event) {
        hd.deleteWarranty(new Warranty(Integer.parseInt(warWarrantyID.getText())));
        warWarrantyDays.clear();
        warWarrantyDesc.clear();
        warWarrantyID.clear();
        warWarrantyType.clear();
        fillWarrantyTable();
        warrantycreateColumns();
        noti.notificationDelete();
    }

    @FXML
    private void searchAction(ActionEvent event) {
    }

    @FXML
    private void refreshProductTable(ActionEvent event) {
        fillProductTable();
        productcreateColumns();
        productID.clear();
        productBrand.clear();
        productName.clear();
        productCategory.clear();
        productQuantity.clear();
        productPPrice.clear();
        productSPrice.clear();
        productDescription.clear();
        productSupplier.clear();
        productUnit.clear();
        productWarranty.clear();
        productPDate.clear();
    }

    @FXML
    private void refreshSupplierTable(ActionEvent event) {
        fillSupplierTable();
        suppliercreateColumns();
        supSupplerMobile.clear();
        supSupplierAddress.clear();
        supSupplierDate.clear();
        supSupplierID.clear();
        supSupplierName.clear();
    }

    @FXML
    private void refreshBrandTable(ActionEvent event) {
        fillBrandTable();
        brandcreateColumns();
        brandBrandDescription.clear();
        brandBrandID.clear();
        brandBrandName.clear();
        brandBrandSupplier.clear();
    }

    @FXML
    private void refreshCategoryTable(ActionEvent event) {
        fillCategoryTable();
        categorycreateColumns();
        catCategoryID.clear();
        catBrandName.clear();
        catCategoryDesc.clear();
        catCategoryName.clear();
        catSupplierName.clear();
    }

    @FXML
    private void refreshUnitTable(ActionEvent event) {
        fillUnitTable();
        unitcreateColumns();
        unitUnitID.clear();
        unitUnitName.clear();
        unitUnitSize.clear();
    }

    @FXML
    private void refreshWarrantyTable(ActionEvent event) {
        fillWarrantyTable();
        warrantycreateColumns();
        warWarrantyDays.clear();
        warWarrantyDesc.clear();
        warWarrantyID.clear();
        warWarrantyType.clear();
    }

    @FXML
    private void btnUpdateProductAction(ActionEvent event) {
        hd.updateProduct(new Product(Integer.parseInt(productID.getText()), productName.getText(), Integer.parseInt(productQuantity.getText()), 
                Double.parseDouble(productPPrice.getText()), Double.parseDouble(productSPrice.getText()), productDescription.getText(), 
                productSupplier.getText(), productBrand.getText(), 
                productCategory.getText(), productUnit.getText(), productWarranty.getText(), 
                productPDate.getText(), product_image));
        productBrand.clear();
        productCategory.clear();
        productDescription.clear();
        productID.clear();
        productName.clear();
        productPDate.clear();
        productPPrice.clear();
        productQuantity.clear();
        productSPrice.clear();
        productSupplier.clear();
        productUnit.clear();
        productWarranty.clear();
        imgView.setImage(null);
        fillProductTable();
        productcreateColumns();
        noti.notificationUpdate();
    }

    @FXML
    private void btnDeleteProductAction(ActionEvent event) {
        hd.deleteProduct(new Product(Integer.parseInt(productID.getText())));
        productBrand.clear();
        productCategory.clear();
        productDescription.clear();
        productID.clear();
        productName.clear();
        productPDate.clear();
        productPPrice.clear();
        productQuantity.clear();
        productSPrice.clear();
        productSupplier.clear();
        productUnit.clear();
        productWarranty.clear();
        fillProductTable();
        productcreateColumns();
        noti.notificationDelete();
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
            product_image = bos.toByteArray();
        } catch (Exception e) {
        }
    }

}
