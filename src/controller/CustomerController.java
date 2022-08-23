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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Customer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

public class CustomerController  implements Initializable{

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtCustomerAddress;

    @FXML
    private TextField txtCustomerPhone;

    @FXML
    private DatePicker txtDateOfBirth;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerBankAccount;

    @FXML
    private TableView<Customer> tableCustomers;

    @FXML
    private TableColumn<Customer, Integer> colCustomerId;

    @FXML
    private TableColumn<Customer, String> colCustomerName;

    @FXML
    private TableColumn<Customer, String> colCustomerAddress;

    @FXML
    private TableColumn<Customer, String> colCustomerPhone;

    @FXML
    private TableColumn<Customer, String> colcustomerBankAccount;

    @FXML
    private TableColumn<Customer, Date> colDateOfBirth;

    @FXML
    private TextField filterField;
 
    ObservableList<Customer> listM;
    ObservableList<Customer> dataList;
    
    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    
    
    @FXML
    void addCustomer(ActionEvent event) {
    	 conn = Database.ConnectDb();
         String sql = "insert into Customer (c_name,c_phone,c_dateOfBirth,c_bankAccount)values(?,?,?,? )";
         try {
             pst = conn.prepareStatement(sql);
             pst.setString(1, txtCustomerName.getText());
             pst.setString(2, txtCustomerPhone.getText());
             pst.setString(3, String.valueOf(txtDateOfBirth.getValue()));
             pst.setString(4, txtCustomerBankAccount.getText());
             pst.execute();
             JOptionPane.showMessageDialog(null, "Customer Add succes");
             updateTable();
             searchCustomer();
             clear();
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
         }
    	
    }

   

    @FXML
    void deleteCustomer(ActionEvent event) {
     
    	conn = Database.ConnectDb();
        String sql = "delete from Customer where c_id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtCustomerId.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Customer Deleted");
            updateTable();
            searchCustomer();
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    void editCustomer(ActionEvent event) {
    	 try {
             conn = Database.ConnectDb();
                                                                                                   
             String sql = "update Customer set c_name= ?,c_phone= ?,c_address= ?,c_dateOfBirth= ?,c_bankAccount= ? where c_id=?";
             pst = conn.prepareStatement(sql);
             pst.setString(1, String.valueOf(txtCustomerName.getText()));
             pst.setString(2, String.valueOf(txtCustomerPhone.getText()));
             pst.setString(3, String.valueOf(txtCustomerAddress.getText()));
             pst.setString(4, String.valueOf(txtDateOfBirth.getValue()));
             pst.setString(5, String.valueOf(txtCustomerBankAccount.getText()));
             pst.setInt(6,Integer.valueOf(txtCustomerId.getText()));
             pst.execute();
             JOptionPane.showMessageDialog(null, "Customer Updated");
             updateTable();
             searchCustomer();
             clear();
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
         }
    }

    @FXML
    public void getSelected(MouseEvent event) {
 
    	 index = tableCustomers.getSelectionModel().getSelectedIndex();
         if (index <= -1) {
        	 	
             return;
         }
         txtCustomerId.setText(colCustomerId.getCellData(index).toString());
         txtCustomerName.setText(colCustomerName.getCellData(index).toString());
         txtCustomerPhone.setText(colCustomerPhone.getCellData(index).toString());
         txtCustomerAddress.setText(colCustomerAddress.getCellData(index).toString());
         txtCustomerBankAccount.setText(colcustomerBankAccount.getCellData(index).toString());
         txtDateOfBirth.setValue(LocalDate.parse(colDateOfBirth.getCellData(index).toString()));

    }

  
    
    public void updateTable() {

        colCustomerId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerAddress"));
        colCustomerPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerPhone"));
        colcustomerBankAccount.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerBankAccount"));
        colDateOfBirth.setCellValueFactory(new PropertyValueFactory<Customer, Date>("dateOfBirth"));
        listM = Database.getCustomers();
        tableCustomers.setItems(listM);
    }
    
    public void clear(){
        txtCustomerId.setText("");
        txtCustomerName.setText("");
        txtCustomerAddress.setText("");
        txtCustomerPhone.setText("");
        txtCustomerBankAccount.setText("");
        txtDateOfBirth.setValue(null);
    }
    
    
    
    public void searchCustomer() {
    	colCustomerId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerAddress"));
        colCustomerPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerPhone"));
        colcustomerBankAccount.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerBankAccount"));
        colDateOfBirth.setCellValueFactory(new PropertyValueFactory<Customer, Date>("dateOfBirth"));

        dataList = Database.getCustomers();
        
       
        tableCustomers.setItems(dataList);
        FilteredList<Customer> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(customer -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(customer.getCustomerName()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                } else if (String.valueOf(customer.getCustomerPhone()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                } else if (customer.getCustomerAddress().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                } else if (customer.getCustomerBankAccount().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                } else if (String.valueOf(customer.getDateOfBirth()).indexOf(lowerCaseFilter) != -1)
                    return true;

                else
                    return false; // Does not match.
            });
        });
        SortedList<Customer> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableCustomers.comparatorProperty());
       tableCustomers.setItems(sortedData);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTable();
        searchCustomer();
    }
    

}
