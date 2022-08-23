package controller;


import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.RentalCarsCompany;
import model.SupplierCompany;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;
import java.util.ResourceBundle;

public class SupplierCompanyController implements Initializable{


    public TextField txtAddress;
    public TextField txtPhone;
    public TextField txtCompanyId;
    public TextField txtEmail;
    public TableView<SupplierCompany> tableSupplierCompanies;
    public TableColumn<SupplierCompany,Integer> colCompanyId;
    public TableColumn<SupplierCompany,String> colAddress;
    public TableColumn<SupplierCompany,String> colPhone;
    public TableColumn<SupplierCompany,String> colEmail;
    public TextField filterField;
    public TextField txtCarType;
    public ComboBox comboRentalCompanyId;
    public TableColumn<SupplierCompany,Integer> colRentalCompanyId;
    public TableColumn<SupplierCompany,String> colCarType;

    ObservableList<SupplierCompany> listM;
    ObservableList<SupplierCompany> dataList;
    
    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    
    
    @FXML
    void addCompany(ActionEvent event) {
    	 conn = Database.ConnectDb();
         String sql = "insert into suppliercompany (s_address,s_phoneNumber,s_email,s_carType,s_rCompanyID)values(?,?,?,?,? )";
         try {
             pst = conn.prepareStatement(sql);
             pst.setString(1, txtAddress.getText());
             pst.setString(2, txtPhone.getText());
             pst.setString(3, txtEmail.getText());
             pst.setString(4, txtCarType.getText());
             pst.setInt(5, Integer.valueOf(comboRentalCompanyId.getValue().toString()));
             pst.execute();
             JOptionPane.showMessageDialog(null, "Supplier Company Add success");
             updateTable();
             searchCompany();
             clear();
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
         }
    	
    }

   

    @FXML
    void deleteCompany(ActionEvent event) {
     
    	conn = Database.ConnectDb();
        String sql = "delete from suppliercompany where s_id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtCompanyId.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Supplier Company Deleted");
            updateTable();
            searchCompany();
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    void editCompany(ActionEvent event) {
    	 try {
             conn = Database.ConnectDb();
                                                                                                   
             String sql = "update suppliercompany set s_address= ?,s_phoneNumber= ?,s_email= ?,s_carType= ?,s_rCompanyID=? where s_id=?";
             pst = conn.prepareStatement(sql);
             pst.setString(1, String.valueOf(txtAddress.getText()));
             pst.setString(2, String.valueOf(txtPhone.getText()));
             pst.setString(3, String.valueOf(txtEmail.getText()));
             pst.setString(4, String.valueOf(txtCarType.getText()));
             pst.setInt(5, Integer.valueOf(comboRentalCompanyId.getValue().toString()));
             pst.setInt(6, Integer.valueOf(txtCompanyId.getText()));
             pst.execute();
             JOptionPane.showMessageDialog(null, "Supplier Company Updated");
             updateTable();
             searchCompany();
             clear();
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
         }
    }

    @FXML
    public void getSelected(MouseEvent event) {
 
    	 index = tableSupplierCompanies.getSelectionModel().getSelectedIndex();
         if (index <= -1) {
        	 	
             return;
         }
         txtCompanyId.setText(colCompanyId.getCellData(index).toString());
         txtAddress.setText(colAddress.getCellData(index).toString());
         txtEmail.setText(colEmail.getCellData(index).toString());
         txtPhone.setText(colPhone.getCellData(index).toString());
         txtCarType.setText(colCarType.getCellData(index).toString());
         comboRentalCompanyId.setValue(colRentalCompanyId.getCellData(index).toString());
    }

  
    
    public void updateTable() {

        colCompanyId.setCellValueFactory(new PropertyValueFactory<SupplierCompany, Integer>("companyId"));
        colPhone.setCellValueFactory(new PropertyValueFactory<SupplierCompany, String>("companyPhone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<SupplierCompany, String>("companyEmail"));
        colAddress.setCellValueFactory(new PropertyValueFactory<SupplierCompany, String>("companyAddress"));
        colCarType.setCellValueFactory(new PropertyValueFactory<SupplierCompany, String>("carType"));
        colRentalCompanyId.setCellValueFactory(new PropertyValueFactory<SupplierCompany, Integer>("rentalCompanyId"));
        listM = Database.getSupplierCompanies();
        tableSupplierCompanies.setItems(listM);
    }
    
    public void clear(){
        txtCompanyId.setText("");
        txtCarType.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
        comboRentalCompanyId.setValue("Rental Company");

    }
    
    
    
    public void searchCompany() {
        colCompanyId.setCellValueFactory(new PropertyValueFactory<SupplierCompany, Integer>("companyId"));
        colPhone.setCellValueFactory(new PropertyValueFactory<SupplierCompany, String>("companyPhone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<SupplierCompany, String>("companyEmail"));
        colAddress.setCellValueFactory(new PropertyValueFactory<SupplierCompany, String>("companyAddress"));
        colCarType.setCellValueFactory(new PropertyValueFactory<SupplierCompany, String>("carType"));
        colRentalCompanyId.setCellValueFactory(new PropertyValueFactory<SupplierCompany, Integer>("rentalCompanyId"));

        dataList = Database.getSupplierCompanies();
        
       
        tableSupplierCompanies.setItems(dataList);
        FilteredList<SupplierCompany> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(supplierCompany -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(supplierCompany.getCarType()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                } else if (String.valueOf(supplierCompany.getCompanyPhone()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                } else if (supplierCompany.getCompanyAddress().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                } else if (supplierCompany.getCompanyEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                } else if (String.valueOf(supplierCompany.getCompanyId()).indexOf(lowerCaseFilter) != -1)
                    return true;

                else
                    return false; // Does not match.
            });
        });
        SortedList<SupplierCompany> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableSupplierCompanies.comparatorProperty());
       tableSupplierCompanies.setItems(sortedData);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> rentalCompanyIdList = Database.getRentalCompanyList();
        comboRentalCompanyId.setItems(rentalCompanyIdList);
        updateTable();
        searchCompany();
    }
    

}
