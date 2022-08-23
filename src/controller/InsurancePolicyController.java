package controller;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.InsurancePolicy;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class InsurancePolicyController implements Initializable {


    public TextField txtPlateNumber;
    public TextField txtType;
    public TextField txtInsuranceId;
    public TextField txtFees;
    public TextField txtModel;
    public TextField txtRenterPhone;
    public TextField txtTenantsLicense;
    public TextField txtPhoneNumber;
    public DatePicker txtInsuranceDate;
    public TableView<InsurancePolicy> tableInsurancePolicies;
    public TableColumn<InsurancePolicy, Integer> colInsuranceId;
    public TableColumn<InsurancePolicy, String> colPlateNumber;
    public TableColumn<InsurancePolicy, String> colModel;
    public TableColumn<InsurancePolicy, String> colType;
    public TableColumn<InsurancePolicy, Double> colFees;
    public TableColumn<InsurancePolicy, String> colRenterPhone;
    public TableColumn<InsurancePolicy, String> colPhoneNumber;
    public TableColumn<InsurancePolicy, String> colTenantsLicense;
    public TableColumn<InsurancePolicy, Date> colInsuranceDate;
    public TextField filterField;


    ObservableList<InsurancePolicy> listM;
    ObservableList<InsurancePolicy> dataList;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    public void addInsurance(ActionEvent actionEvent) {
        conn = Database.ConnectDb();
        String sql = "insert into insurancepolicy (car_I_plateNumber,I_model,I_type,fees,I_Renterphone,phoneNumber," +
                "tenantsLicense,I_insuranceDate)values(?,?,?,?,?,?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtPlateNumber.getText());
            pst.setString(2, txtModel.getText());
            pst.setString(3, txtType.getText());
            pst.setDouble(4, Double.valueOf(txtFees.getText()));
            pst.setString(5, txtRenterPhone.getText());
            pst.setString(6, txtPhoneNumber.getText());
            pst.setString(7, txtTenantsLicense.getText());
            pst.setString(8, String.valueOf(txtInsuranceDate.getValue()));
            pst.execute();

            JOptionPane.showMessageDialog(null, "Insurance Policy Add succes");
            updateTable();
            searchInsurance();
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void editInsurance(ActionEvent actionEvent) {
        try {
            conn = Database.ConnectDb();

            String sql = "update insurancepolicy set car_I_plateNumber= ?,I_model= ?,I_type= ?,fees= ?, I_Renterphone=?," +
                    "phoneNumber=?,tenantsLicense=?,I_insuranceDate=? where I_id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtPlateNumber.getText());
            pst.setString(2, txtModel.getText());
            pst.setString(3, txtType.getText());
            pst.setDouble(4, Double.valueOf(txtFees.getText()));
            pst.setString(5, txtRenterPhone.getText());
            pst.setString(6, txtPhoneNumber.getText());
            pst.setString(7, txtTenantsLicense.getText());
            pst.setString(8, String.valueOf(txtInsuranceDate.getValue()));
            pst.setInt(9, Integer.valueOf(txtInsuranceId.getText()));
            pst.execute();
            JOptionPane.showMessageDialog(null, "Insurance Policy Updated");
            updateTable();
            searchInsurance();
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void deleteInsurance(ActionEvent actionEvent) {
        conn = Database.ConnectDb();
        String sql = "delete from insurancepolicy where I_id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.valueOf(txtInsuranceId.getText()));
            pst.execute();
            JOptionPane.showMessageDialog(null, "Insurance Policy Deleted");
            updateTable();
            searchInsurance();
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void searchInsurance() {
        colInsuranceId.setCellValueFactory(new PropertyValueFactory<InsurancePolicy, Integer>("insuranceId"));
        colPlateNumber.setCellValueFactory(new PropertyValueFactory<InsurancePolicy, String>("plateNumber"));
        colModel.setCellValueFactory(new PropertyValueFactory<InsurancePolicy, String>("model"));
        colType.setCellValueFactory(new PropertyValueFactory<InsurancePolicy, String>("type"));
        colFees.setCellValueFactory(new PropertyValueFactory<InsurancePolicy, Double>("fees"));
        colRenterPhone.setCellValueFactory(new PropertyValueFactory<InsurancePolicy, String>("renterPhone"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<InsurancePolicy, String>("phoneNumber"));
        colTenantsLicense.setCellValueFactory(new PropertyValueFactory<InsurancePolicy, String>("tenantsLicense"));
        colInsuranceDate.setCellValueFactory(new PropertyValueFactory<InsurancePolicy, Date>("insuranceDate"));

        dataList = Database.getInsurancePolicies();
        tableInsurancePolicies.setItems(dataList);
        FilteredList<InsurancePolicy> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(payment -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(payment.getInsuranceId()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
                } else if (payment.getPlateNumber().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (payment.getModel().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (payment.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (payment.getRenterPhone().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (payment.getPhoneNumber().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (payment.getTenantsLicense().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (String.valueOf(payment.getInsuranceDate()).indexOf(lowerCaseFilter) != -1)
                    return true;// Filter matches email

                else
                    return false; // Does not match.
            });
        });
        SortedList<InsurancePolicy> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableInsurancePolicies.comparatorProperty());
        tableInsurancePolicies.setItems(sortedData);
    }

    public void getSelected(MouseEvent mouseEvent) {
        index = tableInsurancePolicies.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        txtInsuranceId.setText(colInsuranceId.getCellData(index).toString());
        txtPlateNumber.setText(colPlateNumber.getCellData(index).toString());
        txtModel.setText(colModel.getCellData(index).toString());
        txtType.setText(colType.getCellData(index).toString());
        txtFees.setText(colFees.getCellData(index).toString());
        txtRenterPhone.setText(colRenterPhone.getCellData(index).toString());
        txtPhoneNumber.setText(colPhoneNumber.getCellData(index).toString());
        txtTenantsLicense.setText(colTenantsLicense.getCellData(index).toString());
        txtInsuranceDate.setValue(LocalDate.parse(colInsuranceDate.getCellData(index).toString()));


    }

    public void updateTable() {

        colInsuranceId.setCellValueFactory(new PropertyValueFactory<InsurancePolicy, Integer>("insuranceId"));
        colPlateNumber.setCellValueFactory(new PropertyValueFactory<InsurancePolicy, String>("plateNumber"));
        colModel.setCellValueFactory(new PropertyValueFactory<InsurancePolicy, String>("model"));
        colType.setCellValueFactory(new PropertyValueFactory<InsurancePolicy, String>("type"));
        colFees.setCellValueFactory(new PropertyValueFactory<InsurancePolicy, Double>("fees"));
        colRenterPhone.setCellValueFactory(new PropertyValueFactory<InsurancePolicy, String>("renterPhone"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<InsurancePolicy, String>("phoneNumber"));
        colTenantsLicense.setCellValueFactory(new PropertyValueFactory<InsurancePolicy, String>("tenantsLicense"));
        colInsuranceDate.setCellValueFactory(new PropertyValueFactory<InsurancePolicy, Date>("insuranceDate"));
        listM = Database.getInsurancePolicies();
        tableInsurancePolicies.setItems(listM);
    }

    public void clear() {
        txtInsuranceId.clear();
        txtPlateNumber.clear();
        txtModel.clear();
        txtType.clear();
        txtFees.clear();
        txtRenterPhone.clear();
        txtPhoneNumber.clear();
        txtTenantsLicense.clear();
        txtInsuranceDate.setValue(null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTable();
        searchInsurance();
    }
}
