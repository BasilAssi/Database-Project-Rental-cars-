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
import model.RentTransaction;
import model.RentalCarsCompany;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class RentTransactionController implements Initializable {


    public DatePicker txtTransactionDate;
    public TextField txtTransactionId;
    public ComboBox comboCarId;
    public ComboBox comboCustomerId;
    public DatePicker txtReturnDate;
    public TextField txtDaysNo;
    public TextField txtDelay;
    public TableView<RentTransaction> tableRentTransactions;
    public TableColumn<RentTransaction, Integer> colCarId;
    public TableColumn<RentTransaction, Integer> colCustomerId;
    public TableColumn<RentTransaction, Date> colTransactionDate;
    public TableColumn<RentTransaction, Date> colReturnDate;
    public TableColumn<RentTransaction, Integer> colDaysNo;
    public TableColumn<RentTransaction, Integer> colDelay;
    public TextField filterField;
    public TableColumn<RentTransaction, Integer> colTransactionId;

    ObservableList<RentTransaction> listM;
    ObservableList<RentTransaction> dataList;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    @FXML
    void addTransaction(ActionEvent event) {
        conn = Database.ConnectDb();
        String sql = "insert into rent_transaction (rc_CarId,c_id,rent_transaction_date,return_date,DaysNo,delay)values(?,?,?,?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.valueOf(comboCarId.getValue().toString()));
            pst.setInt(2, Integer.valueOf(comboCustomerId.getValue().toString()));
            pst.setString(3, txtTransactionDate.getValue().toString());
            pst.setString(4, txtReturnDate.getValue().toString());
            pst.setInt(5, Integer.valueOf(txtDaysNo.getText()));
            pst.setInt(6, Integer.valueOf(txtDelay.getText()));
            pst.execute();
            JOptionPane.showMessageDialog(null, "Transaction Add success");
            updateTable();
            searchTransaction();
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }


    @FXML
    void deleteTransaction(ActionEvent event) {

        conn = Database.ConnectDb();
        String sql = "delete from rent_transaction where rent_transaction_id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.valueOf(txtTransactionId.getText()));
            pst.execute();
            JOptionPane.showMessageDialog(null, "Transaction Deleted");
            updateTable();
            searchTransaction();
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    void editTransaction(ActionEvent event) {
        try {
            conn = Database.ConnectDb();

            String sql = "update rent_transaction set rc_CarId= ?,c_id= ?,rent_transaction_date= ?,return_date= ?,DaysNo=?, delay=? where rent_transaction_id=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.valueOf(comboCarId.getValue().toString()));
            pst.setInt(2, Integer.valueOf(comboCustomerId.getValue().toString()));
            pst.setString(3, String.valueOf(txtTransactionDate.getValue()));
            pst.setString(4, String.valueOf(txtReturnDate.getValue()));
            pst.setInt(5, Integer.valueOf(txtDaysNo.getText()));
            pst.setInt(6, Integer.valueOf(txtDelay.getText()));
            pst.setInt(7, Integer.valueOf(txtTransactionId.getText()));
            pst.execute();
            JOptionPane.showMessageDialog(null, "Rental Cars Company Updated");
            updateTable();
            searchTransaction();
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    public void getSelected(MouseEvent event) {

        index = tableRentTransactions.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        txtTransactionId.setText(colTransactionId.getCellData(index).toString());
        comboCarId.setValue(colCarId.getCellData(index).toString());
        comboCustomerId.setValue(colCustomerId.getCellData(index).toString());
        txtTransactionDate.setValue(LocalDate.parse(colTransactionDate.getCellData(index).toString()));
        txtReturnDate.setValue(LocalDate.parse(colReturnDate.getCellData(index).toString()));
        txtDaysNo.setText(colDaysNo.getCellData(index).toString());
        txtDelay.setText(colDelay.getCellData(index).toString());
    }


    public void updateTable() {

        colTransactionId.setCellValueFactory(new PropertyValueFactory<RentTransaction, Integer>("transactionId"));
        colCarId.setCellValueFactory(new PropertyValueFactory<RentTransaction,Integer>("carId"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<RentTransaction,Integer>("customerId"));
        colTransactionDate.setCellValueFactory(new PropertyValueFactory<RentTransaction, Date>("transactionDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<RentTransaction, Date>("returnDate"));
        colDaysNo.setCellValueFactory(new PropertyValueFactory<RentTransaction,Integer>("daysNo"));
        colDelay.setCellValueFactory(new PropertyValueFactory<RentTransaction,Integer>("delay"));
        listM = Database.getTransactions();
        tableRentTransactions.setItems(listM);
    }

    public void clear() {
        txtTransactionId.setText("");
        txtDelay.setText("");
        txtDaysNo.setText("");
        txtTransactionDate.setValue(null);
        txtReturnDate.setValue(null);
        comboCustomerId.setValue("Customer Id");
        comboCarId.setValue("Car Id");

    }


    public void searchTransaction() {
        colTransactionId.setCellValueFactory(new PropertyValueFactory<RentTransaction, Integer>("transactionId"));
        colCarId.setCellValueFactory(new PropertyValueFactory<RentTransaction,Integer>("carId"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<RentTransaction,Integer>("customerId"));
        colTransactionDate.setCellValueFactory(new PropertyValueFactory<RentTransaction, Date>("transactionDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<RentTransaction, Date>("returnDate"));
        colDaysNo.setCellValueFactory(new PropertyValueFactory<RentTransaction,Integer>("daysNo"));
        colDelay.setCellValueFactory(new PropertyValueFactory<RentTransaction,Integer>("delay"));

        dataList = Database.getTransactions();


        tableRentTransactions.setItems(dataList);
        FilteredList<RentTransaction> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(transaction -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(transaction.getCarId()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(transaction.getCustomerId()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (transaction.getTransactionDate().toString().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (transaction.getReturnDate().toString().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(transaction.getDelay()).indexOf(lowerCaseFilter) != -1)
                    return true;

                else
                    return false; // Does not match.
            });
        });
        SortedList<RentTransaction> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableRentTransactions.comparatorProperty());
        tableRentTransactions.setItems(sortedData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> carIdList = Database.getCarList();
        comboCarId.setItems(carIdList);
        ObservableList<String> customerIdList = Database.getCustomerList();
        comboCustomerId.setItems(customerIdList);
        updateTable();
        searchTransaction();
    }


}
