package controller;


import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Customer;
import model.RentalCarsCompany;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class RentalCarsCompanyController implements Initializable{


    public TextField txtCompanyName;
    public TextField txtAddress;
    public TextField txtPhone;
    public TextField txtCompanyId;
    public TextField txtEmail;
    public TableView<RentalCarsCompany> tableRetalCarsCompanies;
    public TableColumn<RentalCarsCompany,Integer> colCompanyId;
    public TableColumn<RentalCarsCompany,String> colCompanyName;
    public TableColumn<RentalCarsCompany,String> colAddress;
    public TableColumn<RentalCarsCompany,String> colPhone;
    public TableColumn<RentalCarsCompany,String> colEmail;
    public TextField filterField;
    ObservableList<RentalCarsCompany> listM;
    ObservableList<RentalCarsCompany> dataList;
    
    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    
    
    @FXML
    void addCompany(ActionEvent event) {
    	 conn = Database.ConnectDb();
         String sql = "insert into rentalcarscompany (r_phone,r_email,r_name,r_address)values(?,?,?,? )";
         try {
             pst = conn.prepareStatement(sql);
             pst.setString(1, txtPhone.getText());
             pst.setString(2, txtEmail.getText());
             pst.setString(3, txtCompanyName.getText());
             pst.setString(4, txtAddress.getText());
             pst.execute();
             JOptionPane.showMessageDialog(null, "Rental Cars Company Add success");
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
        String sql = "delete from rentalcarscompany where r_CompanyId = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.valueOf(txtCompanyId.getText()));
            pst.execute();
            JOptionPane.showMessageDialog(null, "Rental Cars Company Deleted");
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
                                                                                                   
             String sql = "update rentalcarscompany set r_phone= ?,r_email= ?,r_name= ?,r_address= ? where r_CompanyId=?";
             pst = conn.prepareStatement(sql);
             pst.setString(1, String.valueOf(txtPhone.getText()));
             pst.setString(2, String.valueOf(txtEmail.getText()));
             pst.setString(3, String.valueOf(txtCompanyName.getText()));
             pst.setString(4, String.valueOf(txtAddress.getText()));
             pst.setInt(5, Integer.valueOf(txtCompanyId.getText()));
             pst.execute();
             JOptionPane.showMessageDialog(null, "Rental Cars Company Updated");
             updateTable();
             searchCompany();
             clear();
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
         }
    }

    @FXML
    public void getSelected(MouseEvent event) {
 
    	 index = tableRetalCarsCompanies.getSelectionModel().getSelectedIndex();
         if (index <= -1) {
        	 	
             return;
         }
         txtCompanyId.setText(colCompanyId.getCellData(index).toString());
         txtCompanyName.setText(colCompanyName.getCellData(index).toString());
         txtAddress.setText(colAddress.getCellData(index).toString());
         txtEmail.setText(colEmail.getCellData(index).toString());
         txtPhone.setText(colPhone.getCellData(index).toString());
    }

  
    
    public void updateTable() {

        colCompanyId.setCellValueFactory(new PropertyValueFactory<RentalCarsCompany, Integer>("companyId"));
        colCompanyName.setCellValueFactory(new PropertyValueFactory<RentalCarsCompany, String>("companyName"));
        colPhone.setCellValueFactory(new PropertyValueFactory<RentalCarsCompany, String>("companyPhone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<RentalCarsCompany, String>("companyEmail"));
        colAddress.setCellValueFactory(new PropertyValueFactory<RentalCarsCompany, String>("companyAddress"));
        listM = Database.getRentalCarsCompanies();
        tableRetalCarsCompanies.setItems(listM);
    }
    
    public void clear(){
        txtCompanyId.setText("");
        txtCompanyName.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtAddress.setText("");

    }
    
    
    
    public void searchCompany() {
        colCompanyId.setCellValueFactory(new PropertyValueFactory<RentalCarsCompany, Integer>("companyId"));
        colCompanyName.setCellValueFactory(new PropertyValueFactory<RentalCarsCompany, String>("companyName"));
        colPhone.setCellValueFactory(new PropertyValueFactory<RentalCarsCompany, String>("companyPhone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<RentalCarsCompany, String>("companyEmail"));
        colAddress.setCellValueFactory(new PropertyValueFactory<RentalCarsCompany, String>("companyAddress"));

        dataList = Database.getRentalCarsCompanies();
        
       
        tableRetalCarsCompanies.setItems(dataList);
        FilteredList<RentalCarsCompany> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(rentalCarsCompany -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(rentalCarsCompany.getCompanyName()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                } else if (String.valueOf(rentalCarsCompany.getCompanyPhone()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                } else if (rentalCarsCompany.getCompanyAddress().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                } else if (rentalCarsCompany.getCompanyEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                } else if (String.valueOf(rentalCarsCompany.getCompanyId()).indexOf(lowerCaseFilter) != -1)
                    return true;

                else
                    return false; // Does not match.
            });
        });
        SortedList<RentalCarsCompany> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableRetalCarsCompanies.comparatorProperty());
       tableRetalCarsCompanies.setItems(sortedData);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTable();
        searchCompany();
    }
    

}
