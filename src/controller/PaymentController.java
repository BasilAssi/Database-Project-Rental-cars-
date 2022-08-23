package controller;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Payment;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {
    @FXML
    private TableView<Payment> tablePayments;

    @FXML
    private TableColumn<Payment, Integer> colPaymentId;

    @FXML
    private TableColumn<Payment, Integer> colCarId;

    @FXML
    private TableColumn<Payment, Double> colAmount;

    @FXML
    private TableColumn<Payment, String> colPaymentType;

    @FXML
    private TableColumn<Payment, Date> colPaymentDate;

    @FXML
    private ComboBox comboCarId;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtPaymentType;

    @FXML
    private DatePicker txtPaymentDate;

    @FXML
    private TextField txtPaymentId;

    @FXML
    private TextField filterField;


    ObservableList<Payment> listM;
    ObservableList<Payment> dataList;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    public void addPayment(ActionEvent actionEvent) {
        conn = Database.ConnectDb();
        String sql = "insert into payments (RC_CarId,amount,payment_type,payment_date)values(?,?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.valueOf(comboCarId.getValue().toString()));
            pst.setString(2, txtAmount.getText());
            pst.setString(3, txtPaymentType.getText());
            pst.setString(4, String.valueOf(txtPaymentDate.getValue()));
            pst.execute();

            JOptionPane.showMessageDialog(null, "Payment Add succes");
            updateTable();
            searchPayment();
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void editPayment(ActionEvent actionEvent) {
        try {
            conn = Database.ConnectDb();

            String sql = "update payments set RC_CarId= ?,amount= ?,payment_type= ?,payment_date= ? where payment_id=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.valueOf(comboCarId.getValue().toString()));
            pst.setDouble(2, Double.valueOf(txtAmount.getText()));
            pst.setString(3, txtPaymentType.getText());
            pst.setString(4, String.valueOf(txtPaymentDate.getValue()));
            pst.setInt(5,Integer.valueOf(txtPaymentId.getText()));
            pst.execute();
            JOptionPane.showMessageDialog(null, "Payment Updated");
            updateTable();
            searchPayment();
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void deletePayment(ActionEvent actionEvent) {
        conn = Database.ConnectDb();
        String sql = "delete from payments where payment_id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtPaymentId.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Payment Deleted");
            updateTable();
            searchPayment();
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void searchPayment() {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<Payment, Integer>("paymentId"));
        colCarId.setCellValueFactory(new PropertyValueFactory<Payment, Integer>("carId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<Payment, Double>("amount"));
        colPaymentType.setCellValueFactory(new PropertyValueFactory<Payment, String>("paymentType"));
        colPaymentDate.setCellValueFactory(new PropertyValueFactory<Payment, Date>("paymentDate"));

        dataList = Database.getPayments();
        tablePayments.setItems(dataList);
        FilteredList<Payment> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(payment -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
  
                if (String.valueOf(payment.getCarId()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
                } else if (String.valueOf(payment.getPaymentId()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (String.valueOf(payment.getAmount()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (payment.getPaymentType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (String.valueOf(payment.getPaymentDate()).indexOf(lowerCaseFilter) != -1)
                    return true;// Filter matches email

                else
                    return false; // Does not match.
            });
        });
        SortedList<Payment> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tablePayments.comparatorProperty());
        tablePayments.setItems(sortedData);
    }

    public void getSelected(MouseEvent mouseEvent) {
        index = tablePayments.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        txtPaymentId.setText(colPaymentId.getCellData(index).toString());
        comboCarId.setValue(colCarId.getCellData(index).toString());
        txtAmount.setText(colAmount.getCellData(index).toString());
        txtPaymentType.setText(colPaymentType.getCellData(index).toString());
        txtPaymentDate.setValue(LocalDate.parse(colPaymentDate.getCellData(index).toString()));


    }

    public void updateTable() {

        colPaymentId.setCellValueFactory(new PropertyValueFactory<Payment, Integer>("paymentId"));
        colCarId.setCellValueFactory(new PropertyValueFactory<Payment, Integer>("carId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<Payment, Double>("amount"));
        colPaymentType.setCellValueFactory(new PropertyValueFactory<Payment, String>("paymentType"));
        colPaymentDate.setCellValueFactory(new PropertyValueFactory<Payment, Date>("paymentDate"));
        listM = Database.getPayments();
        tablePayments.setItems(listM);
    }

    public void clear(){
        txtPaymentId.setText("");
        comboCarId.setValue("Car Id");
        txtAmount.setText("");
        txtPaymentType.setText("");
        txtPaymentDate.setValue(null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	ObservableList<String> carIdList=Database.getCarList();
    	comboCarId.setItems(carIdList);
        updateTable();
        searchPayment();
    }
}
