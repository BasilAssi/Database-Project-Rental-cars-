package controller;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Employee;
import model.RentalCarsCompany;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {


    public TextField txtEmployeeName;
    public TextField txtEmployeeAddress;
    public TextField txtEmployeePhone;
    public DatePicker txtDateOfBirth;
    public TextField txtEmployeeId;
    public TextField txtEmployeeEmail;
    public TextField txtEmployeeSalary;
    public ComboBox comboCompanyId;
    public TableView<Employee> tableEmployees;
    public TableColumn<Employee, Integer> colEmployeeId;
    public TableColumn<Employee, String> colEmployeeName;
    public TableColumn<Employee, String> colEmployeeAddress;
    public TableColumn<Employee, String> colEmployeePhone;
    public TableColumn<Employee, Date> colDateOfBirth;
    public TableColumn<Employee, Double> colSalary;
    public TableColumn<Employee, String> colEmail;
    public TableColumn<Employee, Integer> colCompany;
    public TextField filterField;

    ObservableList<Employee> listM;
    ObservableList<Employee> dataList;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    @FXML
    void addEmployee(ActionEvent event) {
        conn = Database.ConnectDb();
        String sql = "insert into rentalemployee (re_name,re_phone,re_address,re_dateOfBirth,salary,re_email,re_rCompanyID)" +
                "values(?,?,?,?,?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtEmployeeName.getText());
            pst.setString(2, txtEmployeePhone.getText());
            pst.setString(3, txtEmployeeAddress.getText());
            pst.setString(4, String.valueOf(txtDateOfBirth.getValue()));
            pst.setDouble(5, Double.valueOf(txtEmployeeSalary.getText()));
            pst.setString(6, txtEmployeeEmail.getText());
            pst.setString(7, String.valueOf(comboCompanyId.getValue()));
            pst.execute();
            JOptionPane.showMessageDialog(null, "Employee Add success");
            updateTable();
            searchEmployee();
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    @FXML
    void deleteEmployee(ActionEvent event) {

        conn = Database.ConnectDb();
        String sql = "delete from rentalemployee where re_id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtEmployeeId.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Employee Deleted");
            updateTable();
            searchEmployee();
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    void editEmployee(ActionEvent event) {
        try {
            conn = Database.ConnectDb();

            String sql = "update rentalemployee set re_name= ?,re_phone= ?,re_address= ?,re_dateOfBirth= ?," +
                    "salary= ?,re_email=?,re_rCompanyID=? where re_id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, String.valueOf(txtEmployeeName.getText()));
            pst.setString(2, String.valueOf(txtEmployeePhone.getText()));
            pst.setString(3, String.valueOf(txtEmployeeAddress.getText()));
            pst.setString(4, String.valueOf(txtDateOfBirth.getValue()));
            pst.setDouble(5, Double.valueOf(txtEmployeeSalary.getText()));
            pst.setString(6, String.valueOf(txtEmployeeEmail.getText()));
            pst.setInt(7, Integer.valueOf(comboCompanyId.getValue().toString()));
            pst.setInt(8, Integer.valueOf(txtEmployeeId.getText()));
            pst.execute();
            JOptionPane.showMessageDialog(null, "Employee Updated");
            updateTable();
            searchEmployee();
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    public void getSelected(MouseEvent event) {

        index = tableEmployees.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        txtEmployeeId.setText(colEmployeeId.getCellData(index).toString());
        txtEmployeeName.setText(colEmployeeName.getCellData(index).toString());
        txtEmployeePhone.setText(colEmployeePhone.getCellData(index).toString());
        txtEmployeeAddress.setText(colEmployeeAddress.getCellData(index).toString());
        txtDateOfBirth.setValue(LocalDate.parse(colDateOfBirth.getCellData(index).toString()));
        txtEmployeeSalary.setText(colSalary.getCellData(index).toString());
        txtEmployeeEmail.setText(colEmail.getCellData(index).toString());
        comboCompanyId.setValue(colCompany.getCellData(index).toString());
    }


    public void updateTable() {

        colEmployeeId.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("employeeId"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeName"));
        colEmployeeAddress.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeAddress"));
        colEmployeePhone.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeePhone"));
        colDateOfBirth.setCellValueFactory(new PropertyValueFactory<Employee, Date>("dateOfBirth"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeEmail"));
        colSalary.setCellValueFactory(new PropertyValueFactory<Employee, Double>("salary"));
        colCompany.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("companyId"));
        listM = Database.getEmployees();
        tableEmployees.setItems(listM);
    }

    public void clear() {
        txtEmployeeId.setText("");
        txtEmployeeName.setText("");
        txtEmployeeAddress.setText("");
        txtEmployeePhone.setText("");
        txtEmployeeEmail.setText("");
        txtEmployeeSalary.setText("");
        txtDateOfBirth.setValue(null);
        comboCompanyId.setValue("Company");
    }


    public void searchEmployee() {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("employeeId"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeName"));
        colEmployeeAddress.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeAddress"));
        colEmployeePhone.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeePhone"));
        colDateOfBirth.setCellValueFactory(new PropertyValueFactory<Employee, Date>("dateOfBirth"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeEmail"));
        colSalary.setCellValueFactory(new PropertyValueFactory<Employee, Double>("salary"));
        colCompany.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("companyId"));

        dataList = Database.getEmployees();


        tableEmployees.setItems(dataList);
        FilteredList<Employee> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(employee -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(employee.getEmployeeId()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (employee.getEmployeeName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (employee.getEmployeeAddress().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (employee.getEmployeeEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(employee.getDateOfBirth()).indexOf(lowerCaseFilter) != -1)
                    return true;

                else
                    return false; // Does not match.
            });
        });
        SortedList<Employee> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableEmployees.comparatorProperty());
        tableEmployees.setItems(sortedData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> companyIdList = Database.getCompanyList();
        comboCompanyId.setItems(companyIdList);
        updateTable();
        searchEmployee();
    }

}
