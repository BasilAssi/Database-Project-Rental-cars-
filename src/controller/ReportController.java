package controller;

import javafx.beans.value.ObservableStringValue;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.RentalCar;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;

public class ReportController implements Initializable {


    public TableColumn<RentalCar, Integer> colCarId;
    public TableColumn<RentalCar, String> colCarType;
    public TableColumn<RentalCar, Double> colWeight;
    public TableColumn<RentalCar, String> colBrand;
    public TableColumn<RentalCar, String> colModel;
    public TableColumn<RentalCar, String> colEngineType;
    public TableColumn<RentalCar, Double> colDailyFare;
    public TableView<RentalCar> TopCarsTable;
    public TableColumn<RentalCar, Integer> colYear;
    public TableView<Customer> tableCustomers;
    public TableColumn<Customer, Integer> colCustomerId;
    public TableColumn<Customer, String> colCustomerName;
    public TableColumn<Customer, String> colCustomerAddress;
    public TableColumn<Customer, String> colCustomerPhone;
    public TableColumn<Customer, String> colcustomerBankAccount;
    public TableColumn<Customer, Date> colDateOfBirth;

    ObservableList<RentalCar> listCars;
    ObservableList<Customer> listCustomers;
    ObservableStringValue labelText;
    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateCarsTable();
        updateCustomersTable();

    }


    private void updateCarsTable() {
        colCarId.setCellValueFactory(new PropertyValueFactory<RentalCar, Integer>("carId"));
        colCarType.setCellValueFactory(new PropertyValueFactory<RentalCar, String>("type"));
        colWeight.setCellValueFactory(new PropertyValueFactory<RentalCar, Double>("weight"));
        colBrand.setCellValueFactory(new PropertyValueFactory<RentalCar, String>("brand"));
        colYear.setCellValueFactory(new PropertyValueFactory<RentalCar, Integer>("manufacturingYear"));
        colModel.setCellValueFactory(new PropertyValueFactory<RentalCar, String>("model"));
        colEngineType.setCellValueFactory(new PropertyValueFactory<RentalCar, String>("engineType"));
        colDailyFare.setCellValueFactory(new PropertyValueFactory<RentalCar, Double>("dailyFare"));

        listCars = Database.getTopCars();
        TopCarsTable.setItems(listCars);
    }

    private void updateCustomersTable() {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerAddress"));
        colCustomerPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerPhone"));
        colcustomerBankAccount.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerBankAccount"));
        colDateOfBirth.setCellValueFactory(new PropertyValueFactory<Customer, Date>("dateOfBirth"));
        listCustomers = Database.getTopCustomers();
        tableCustomers.setItems(listCustomers);
    }


}
