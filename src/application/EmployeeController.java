/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

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
import java.util.List;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import jdbc.Employee;
import jdbc.HibernateDao;
import jdbc.Salary;
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
public class EmployeeController implements Initializable {

    @FXML
    private TextField searchEmployee;
    @FXML
    private JFXButton btnNewEmployee;
    @FXML
    private TableView<Employee> tblEmployee;
    @FXML
    private TableColumn<Employee, Integer> clmEmployeeId;
    @FXML
    private TableColumn<Employee, String> clmFullName;
    @FXML
    private TableColumn<Employee, String> clmMobile;
    @FXML
    private TableColumn<Employee, String> clmEmail;
    @FXML
    private TableColumn<Employee, String> clmEmpAddress;
    @FXML
    private TableColumn<Employee, String> clmEmpPosition;
    @FXML
    private TableColumn<Employee, Double> clmEmpSalary;
    @FXML
    private TableColumn<Employee, String> clmJoinDate;
    @FXML
    private JFXButton btnEmployeeUpdate;
    @FXML
    private JFXButton btnEmployeeDelete;
    @FXML
    private TableView<Salary> tblSalary;
    @FXML
    private TableColumn<Salary, String> clmSalaryEmpName;
    @FXML
    private TableColumn<Salary, String> clmSalaryPosition;
    @FXML
    private TableColumn<Salary, Double> clmSalarySalary;
    @FXML
    private TableColumn<Salary, String> clmSalaryMonth;
    @FXML
    private TableColumn<Salary, String> clmSalaryYear;
    @FXML
    private JFXButton btnSalryPayment;
    @FXML
    private JFXButton btnSearchEmployeeId;
    @FXML
    private TextField searchSalaryEmployee;
    @FXML
    private TableColumn<Employee, String> clmUserName;
    @FXML
    private JFXTextField empIdUpdateField;
    @FXML
    private JFXTextField empMobileUpdateField;
    @FXML
    private JFXTextField empEmailUpdateField;
    @FXML
    private JFXTextArea empAddressUpdateField;
    @FXML
    private JFXTextField empSalaryUpdateField;
    @FXML
    private JFXComboBox<String> empPositionUpdateField;
    @FXML
    private JFXTextField empIdSalaryField;
    @FXML
    private JFXTextField empNameSalaryField;
    @FXML
    private JFXTextField empPositionSalaryField;
    @FXML
    private JFXTextField empSalarySalaryField;
    @FXML
    private JFXComboBox<String> empMonthSalaryField;
    @FXML
    private JFXComboBox<String> empYearSalaryField;
    @FXML
    private JFXTextField empUserNameUpdateField;
    @FXML
    private JFXTextField empFullNameUpdateField;
    @FXML
    private JFXTextField empJoingingDateUpdateField;
    @FXML
    private ImageView imgView;
    @FXML
    private JFXButton btnImage;
    private String imagePath;
    private byte[] employee_image;
    
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

    ObservableList<String> position = FXCollections
            .observableArrayList("Trainee Officer", "Asst. Officer", "Sr. Officer",
                    "Accountant", "Hr. Manager", "Manager");

    ObservableList<String> Month = FXCollections
            .observableArrayList("January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December");

    ObservableList<String> Year = FXCollections
            .observableArrayList("2017", "2018", "2109", "2020", "2021", "2022", "2023", "2024",
                    "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        searchEmployee.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filterEmployeeList((String) oldValue, (String) newValue);
            }
        });
        
        searchSalaryEmployee.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filterSalaryList((String) oldValue, (String) newValue);
            }
        });
        empPositionUpdateField.setItems(position);
        empMonthSalaryField.setItems(Month);
        empYearSalaryField.setItems(Year);
        emptablecreateColumns();
        fillTable();
        fillSalaryTable();
        salarytablecreateColumns();
    }

    public void filterEmployeeList(String oldValue, String newValue) {
        ObservableList<Employee> filteredList = FXCollections.observableArrayList();
        if (searchEmployee == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tblEmployee.setItems(getEmployeeTable());
        } else {
            newValue = newValue.toUpperCase();
            for (Employee employee : tblEmployee.getItems()) {
                String filterName = employee.getUname();
                String filterId = Integer.toString(employee.getId());
                if (filterName.toUpperCase().contains(newValue) || filterId.toUpperCase().contains(newValue)) {
                    filteredList.add(employee);
                }
            }
            tblEmployee.setItems(filteredList);
        }
    }
    
    public void filterSalaryList(String oldValue, String newValue) {
        ObservableList<Salary> filteredList = FXCollections.observableArrayList();
        if (searchSalaryEmployee == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tblSalary.setItems(getSalaryTable());
        } else {
            newValue = newValue.toUpperCase();
            for (Salary sal : tblSalary.getItems()) {
                String filterName = sal.getEmpname();
                String filterMonth = sal.getMonth();
                String filterId = Integer.toString(sal.getId());
                if (filterName.toUpperCase().contains(newValue) || filterId.toUpperCase().contains(newValue) || filterMonth.toUpperCase().contains(newValue)) {
                    filteredList.add(sal);
                }
            }
            tblSalary.setItems(filteredList);
        }
    }

    @FXML
    private void setOnField(MouseEvent me) {
        if (me.getClickCount() >= 1) {
            int i = tblEmployee.getItems().get(tblEmployee.getSelectionModel().getSelectedIndex()).getId();
            empIdUpdateField.setText(Integer.toString(i));
            String un = tblEmployee.getItems().get(tblEmployee.getSelectionModel().getSelectedIndex()).getUname();
            empUserNameUpdateField.setText(un);
            String fn = tblEmployee.getItems().get(tblEmployee.getSelectionModel().getSelectedIndex()).getFullname();
            empFullNameUpdateField.setText(fn);
            String n = tblEmployee.getItems().get(tblEmployee.getSelectionModel().getSelectedIndex()).getMobile();
            empMobileUpdateField.setText(n);
            Double s = tblEmployee.getItems().get(tblEmployee.getSelectionModel().getSelectedIndex()).getSalary();
            empSalaryUpdateField.setText(Double.toString(s));
            String e = tblEmployee.getItems().get(tblEmployee.getSelectionModel().getSelectedIndex()).getEmail();
            empEmailUpdateField.setText(e);
            String ad = tblEmployee.getItems().get(tblEmployee.getSelectionModel().getSelectedIndex()).getAddress();
            empAddressUpdateField.setText(ad);
            String jd = tblEmployee.getItems().get(tblEmployee.getSelectionModel().getSelectedIndex()).getJoindate();
            empJoingingDateUpdateField.setText(jd);
            String po = tblEmployee.getItems().get(tblEmployee.getSelectionModel().getSelectedIndex()).getPosition();
            empPositionUpdateField.setValue(po);
        }

    }
    
    @FXML
    private void setOnSalaryField(MouseEvent me) {
        if (me.getClickCount() >= 1) {
            int i = tblSalary.getItems().get(tblSalary.getSelectionModel().getSelectedIndex()).getId();
            empIdSalaryField.setText(Integer.toString(i));
            String un = tblSalary.getItems().get(tblSalary.getSelectionModel().getSelectedIndex()).getEmpname();
            empNameSalaryField.setText(un);
            String fn = tblSalary.getItems().get(tblSalary.getSelectionModel().getSelectedIndex()).getPosition();
            empPositionSalaryField.setText(fn);
            String s = tblSalary.getItems().get(tblSalary.getSelectionModel().getSelectedIndex()).getSalary();
            empSalarySalaryField.setText(s);
            String m = tblSalary.getItems().get(tblSalary.getSelectionModel().getSelectedIndex()).getMonth();
            empMonthSalaryField.setValue(m);
            String e = tblSalary.getItems().get(tblSalary.getSelectionModel().getSelectedIndex()).getYear();
            empYearSalaryField.setValue(e);
        }

    }

    public void fillTable() throws BeansException {
        tblEmployee.setItems(getEmployeeTable());
    }
    
    public void fillSalaryTable() throws BeansException {
        tblSalary.setItems(getSalaryTable());
    }

    public void emptablecreateColumns() {
        clmEmployeeId.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
        clmUserName.setCellValueFactory(new PropertyValueFactory<Employee, String>("uname"));
        clmFullName.setCellValueFactory(new PropertyValueFactory<Employee, String>("fullname"));
        clmMobile.setCellValueFactory(new PropertyValueFactory<Employee, String>("mobile"));
        clmEmail.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
        clmEmpAddress.setCellValueFactory(new PropertyValueFactory<Employee, String>("address"));
        clmEmpPosition.setCellValueFactory(new PropertyValueFactory<Employee, String>("position"));
        clmEmpSalary.setCellValueFactory(new PropertyValueFactory<Employee, Double>("salary"));
        clmJoinDate.setCellValueFactory(new PropertyValueFactory<Employee, String>("joindate"));
    }

    public ObservableList<Employee> getEmployeeTable() throws BeansException {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc/spring-jdbc.xml");
        HibernateDao hd = (HibernateDao) context.getBean("hibernateDao");
        ObservableList<Employee> emplist = FXCollections
                .observableArrayList(hd.getEmployee());
        return emplist;
    }

    @FXML
    private void btnNewEmployeeAction(ActionEvent event) throws IOException {
        nextStage(Login.NewEmployee, "", true);
    }

    @FXML
    private void btnEmployeeUpdateAction(ActionEvent event) {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc/spring-jdbc.xml");
        HibernateDao hd = (HibernateDao) context.getBean("hibernateDao");
        hd.updateEmployee(new Employee(Integer.parseInt(empIdUpdateField.getText()), empUserNameUpdateField.getText(),
                empFullNameUpdateField.getText(), empMobileUpdateField.getText(), empEmailUpdateField.getText(),
                empAddressUpdateField.getText(), empPositionUpdateField.getSelectionModel().getSelectedItem(),
                Double.parseDouble(empSalaryUpdateField.getText()), empJoingingDateUpdateField.getText(), employee_image));
        empIdUpdateField.clear();
        empUserNameUpdateField.clear();
        empFullNameUpdateField.clear();
        empMobileUpdateField.clear();
        empEmailUpdateField.clear();
        empAddressUpdateField.clear();
        empSalaryUpdateField.clear();
        empJoingingDateUpdateField.clear();
        empPositionUpdateField.getSelectionModel().clearSelection();
        imgView.setImage(null);
        fillTable();
        noti.notificationUpdate();
    }

    @FXML
    private void btnEmployeeDeleteAction(ActionEvent event) {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc/spring-jdbc.xml");
        HibernateDao hd = (HibernateDao) context.getBean("hibernateDao");
        hd.deleteEmployee(new Employee(Integer.parseInt(empIdUpdateField.getText())));
        empIdUpdateField.clear();
        empUserNameUpdateField.clear();
        empFullNameUpdateField.clear();
        empMobileUpdateField.clear();
        empEmailUpdateField.clear();
        empAddressUpdateField.clear();
        empSalaryUpdateField.clear();
        empJoingingDateUpdateField.clear();
        imgView.setImage(null);
        fillTable();
        noti.notificationDelete();
    }

    @FXML
    private void btnSalryPaymentAction(ActionEvent event) {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc/spring-jdbc.xml");
        HibernateDao hd = (HibernateDao) context.getBean("hibernateDao");
        hd.saveSalary(new Salary(empNameSalaryField.getText(), empPositionSalaryField.getText(), 
                empSalarySalaryField.getText(), 
                empMonthSalaryField.getSelectionModel().getSelectedItem(), 
                empYearSalaryField.getSelectionModel().getSelectedItem()));
        empIdSalaryField.clear();
        empNameSalaryField.clear();
        empSalarySalaryField.clear();
        empPositionSalaryField.clear();
        empMonthSalaryField.getSelectionModel().clearSelection();
        empYearSalaryField.getSelectionModel().clearSelection();
        noti.notificationSalary();
    }

    @FXML
    private void btnSearchEmployeeIdAction(ActionEvent event) {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc/spring-jdbc.xml");
        HibernateDao hd = (HibernateDao) context.getBean("hibernateDao");
        List<Employee> list = hd.getSingleValue(Integer.parseInt(empIdSalaryField.getText()));
        for (Employee s : list) {
            empNameSalaryField.setText(s.getFullname());
            empPositionSalaryField.setText(s.getPosition());
            empSalarySalaryField.setText(s.getSalary().toString());
        }
    }
    
    public void salarytablecreateColumns() {
        clmSalaryEmpName.setCellValueFactory(new PropertyValueFactory<Salary, String>("empname"));
        clmSalaryPosition.setCellValueFactory(new PropertyValueFactory<Salary, String>("position"));
        clmSalarySalary.setCellValueFactory(new PropertyValueFactory<Salary, Double>("salary"));
        clmSalaryMonth.setCellValueFactory(new PropertyValueFactory<Salary, String>("month"));
        clmSalaryYear.setCellValueFactory(new PropertyValueFactory<Salary, String>("year"));
    }
    
    public ObservableList<Salary> getSalaryTable() throws BeansException {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc/spring-jdbc.xml");
        HibernateDao hd = (HibernateDao) context.getBean("hibernateDao");
        ObservableList<Salary> emplist = FXCollections
                .observableArrayList(hd.getSalary());
        return emplist;
    }


    @FXML
    private void refreshEmployee(ActionEvent event) {
        emptablecreateColumns();
        fillTable();
        empIdUpdateField.clear();
        empUserNameUpdateField.clear();
        empFullNameUpdateField.clear();
        empMobileUpdateField.clear();
        empEmailUpdateField.clear();
        empAddressUpdateField.clear();
        empSalaryUpdateField.clear();
        empJoingingDateUpdateField.clear();
        empPositionUpdateField.getSelectionModel().clearSelection();
        imgView.setImage(null);
    }

    @FXML
    private void refreshSalary(ActionEvent event) {
        fillSalaryTable();
        salarytablecreateColumns();
        empIdSalaryField.clear();
        empNameSalaryField.clear();
        empSalarySalaryField.clear();
        empPositionSalaryField.clear();
        empMonthSalaryField.getSelectionModel().clearSelection();
        empYearSalaryField.getSelectionModel().clearSelection();
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
