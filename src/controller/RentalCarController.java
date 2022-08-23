package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.RentalCar;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class RentalCarController implements Initializable {

    public TextField txtLicense;
    public TextField txtType;
    public TextField txtWeight;
    public TextField txtBrand;
    public TextField txtManufacturingYear;
    public TextField txtModel;
    public TextField txtEngineType;
    public TextField txtDailyFare;
    public DatePicker txtLicenseExpirationDate;
    public TextField txtFuelConsumption;
    public TextField txtTravelledDistance;
    public ComboBox comboCompanyId;
    public TextField txtCarId;
    public TableColumn<RentalCar, Integer> colCarId;
    public TableColumn<RentalCar, String> colLicense;
    public TableColumn<RentalCar, String> colType;
    public TableColumn<RentalCar, Double> colWeight;
    public TableColumn<RentalCar, String> colBrand;
    public TableColumn<RentalCar, Integer> colYear;
    public TableColumn<RentalCar, String> colModel;
    public TableColumn<RentalCar, String> colEngineType;
    public TableColumn<RentalCar, Double> colDailyFare;
    public TableColumn<RentalCar, Date> colLicendeExpirationDate;
    public TableColumn<RentalCar, Double> colFuelConsumption;
    public TableColumn<RentalCar, Double> colTravelledDistance;
    public TableColumn<RentalCar, Integer> colCompanyId;
    public TableColumn<RentalCar, Integer> colInsuranceId;
    public TextField filterField;
    public TableView<RentalCar> tableRentalCars;
    public ComboBox comboInsuranceId;


    ObservableList<RentalCar> listM;
    ObservableList<RentalCar> dataList;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    	ObservableList<String> companyIdList=Database.getCompanyList();
    	comboCompanyId.setItems(companyIdList);
        ObservableList<String> insuranceIdList = Database.getInsuranceIdList();
        comboInsuranceId.setItems(insuranceIdList);
        updateTable();

        searchRentalCar();
    }

    public void addRentalCar(ActionEvent actionEvent) {
        conn = Database.ConnectDb();
        String sql = "insert into rentalcars (rc_License,I_id,rc_Type,rc_weight,rc_brand,rc_manufacturingYear,rc_model," +
                "rc_EngieType,rc_dailyFare,rc_theLicenseExpirationDate,rc_fualConsumption,travelledDistance,rc_rCompanyID)" +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtLicense.getText());
            pst.setInt(2, Integer.valueOf(comboInsuranceId.getValue().toString()));
            pst.setString(3, txtType.getText());
            pst.setDouble(4, Double.valueOf(txtWeight.getText()));
            pst.setString(5, txtBrand.getText());
            pst.setInt(6, Integer.valueOf(txtManufacturingYear.getText()));
            pst.setString(7, txtModel.getText());
            pst.setString(8, txtEngineType.getText());
            pst.setDouble(9, Double.valueOf(txtDailyFare.getText()));
            pst.setString(10, String.valueOf(txtLicenseExpirationDate.getValue()));
            pst.setDouble(11, Double.valueOf(txtFuelConsumption.getText()));
            pst.setDouble(12, Double.valueOf(txtTravelledDistance.getText()));
            pst.setInt(13, Integer.valueOf(comboCompanyId.getValue().toString()));
            pst.execute();

            JOptionPane.showMessageDialog(null, "Rental Car Add succes");
            updateTable();
            searchRentalCar();
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void updateTable() {
        colCarId.setCellValueFactory(new PropertyValueFactory<RentalCar, Integer>("carId"));
        colLicense.setCellValueFactory(new PropertyValueFactory<RentalCar, String>("license"));
        colInsuranceId.setCellValueFactory(new PropertyValueFactory<RentalCar, Integer>("insuranceId"));
        colType.setCellValueFactory(new PropertyValueFactory<RentalCar, String>("type"));
        colWeight.setCellValueFactory(new PropertyValueFactory<RentalCar, Double>("weight"));
        colBrand.setCellValueFactory(new PropertyValueFactory<RentalCar, String>("brand"));
        colYear.setCellValueFactory(new PropertyValueFactory<RentalCar, Integer>("manufacturingYear"));
        colModel.setCellValueFactory(new PropertyValueFactory<RentalCar, String>("model"));
        colEngineType.setCellValueFactory(new PropertyValueFactory<RentalCar, String>("engineType"));
        colDailyFare.setCellValueFactory(new PropertyValueFactory<RentalCar, Double>("dailyFare"));
        colLicendeExpirationDate.setCellValueFactory(new PropertyValueFactory<RentalCar, Date>("licenceExpirationDate"));
        colFuelConsumption.setCellValueFactory(new PropertyValueFactory<RentalCar, Double>("fuelConsumption"));
        colTravelledDistance.setCellValueFactory(new PropertyValueFactory<RentalCar, Double>("travelledDistance"));
        colCompanyId.setCellValueFactory(new PropertyValueFactory<RentalCar, Integer>("companyId"));

        listM = Database.getRentalCars();
        tableRentalCars.setItems(listM);
    }

    public void editRentalCar(ActionEvent actionEvent) {
        try {
            conn = Database.ConnectDb();

            String sql = "update rentalcars set rc_License= ?,I_id= ?,rc_Type= ?,rc_weight= ?,rc_brand=?,rc_manufacturingYear=?," +
                    "rc_model=?,rc_EngieType=?,rc_dailyFare=?,rc_theLicenseExpirationDate=?,rc_fualConsumption=?,travelledDistance=?," +
                    "rc_rCompanyID=? where rc_CarId=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,txtLicense.getText());
            pst.setInt(2, Integer.valueOf(comboInsuranceId.getValue().toString()));
            pst.setString(3, txtType.getText());
            pst.setDouble(4, Double.valueOf(txtWeight.getText()));
            pst.setString(5,txtBrand.getText());
            pst.setInt(6,Integer.valueOf(txtManufacturingYear.getText()));
            pst.setString(7,txtModel.getText());
            pst.setString(8,txtEngineType.getText());
            pst.setDouble(9,Double.valueOf(txtDailyFare.getText()));
            pst.setString(10,String.valueOf(txtLicenseExpirationDate.getValue()));
            pst.setDouble(11,Double.valueOf(txtFuelConsumption.getText()));
            pst.setDouble(12,Double.valueOf(txtTravelledDistance.getText()));
            pst.setInt(13,Integer.valueOf(comboCompanyId.getValue().toString()));
            pst.setInt(14,Integer.valueOf(txtCarId.getText()));
            pst.execute();
            JOptionPane.showMessageDialog(null, "Car Updated");
            updateTable();
            searchRentalCar();
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void deleteRentalCar(ActionEvent actionEvent) {
        conn = Database.ConnectDb();
        String sql = "delete from rentalcars where rc_CarId = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtCarId.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Car Deleted");
            updateTable();
            searchRentalCar();
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void searchRentalCar() {
        colCarId.setCellValueFactory(new PropertyValueFactory<RentalCar, Integer>("carId"));
        colLicense.setCellValueFactory(new PropertyValueFactory<RentalCar, String>("license"));
        colInsuranceId.setCellValueFactory(new PropertyValueFactory<RentalCar, Integer>("insuranceId"));
        colType.setCellValueFactory(new PropertyValueFactory<RentalCar, String>("type"));
        colBrand.setCellValueFactory(new PropertyValueFactory<RentalCar, String>("brand"));
        colYear.setCellValueFactory(new PropertyValueFactory<RentalCar, Integer>("manufacturingYear"));
        colModel.setCellValueFactory(new PropertyValueFactory<RentalCar, String>("model"));
        colEngineType.setCellValueFactory(new PropertyValueFactory<RentalCar, String>("engineType"));
        colDailyFare.setCellValueFactory(new PropertyValueFactory<RentalCar, Double>("dailyFare"));

        dataList = Database.getRentalCars();
        tableRentalCars.setItems(dataList);
        FilteredList<RentalCar> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(rentalCar -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(rentalCar.getCarId()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches car Id
                } else if (rentalCar.getLicense().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (String.valueOf(rentalCar.getInsuranceId()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (rentalCar.getType().indexOf(lowerCaseFilter) != -1) {
                    return true;// Filter matches email
                } else if (rentalCar.getBrand().indexOf(lowerCaseFilter) != -1) {
                    return true;// Filter matches email
                } else if (String.valueOf(rentalCar.getManufacturingYear()).indexOf(lowerCaseFilter) != -1) {
                    return true;// Filter matches email
                } else if (rentalCar.getModel().indexOf(lowerCaseFilter) != -1) {
                    return true;// Filter matches email
                } else if (rentalCar.getEngineType().indexOf(lowerCaseFilter) != -1) {
                    return true;// Filter matches email
                } else if (String.valueOf(rentalCar.getDailyFare()).indexOf(lowerCaseFilter) != -1)
                    return true;// Filter matches email
                else
                    return false; // Does not match.
            });
        });
        SortedList<RentalCar> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableRentalCars.comparatorProperty());
        tableRentalCars.setItems(sortedData);
    }

    public void clear() {
        txtCarId.setText("");
        txtLicense.setText("");
        comboInsuranceId.setValue("Select Insurance Id");
        txtType.setText("");
        txtWeight.clear();
        txtBrand.clear();
        txtManufacturingYear.clear();
        txtModel.clear();
        txtEngineType.clear();
        txtDailyFare.clear();
        txtLicenseExpirationDate.setValue(null);
        txtFuelConsumption.clear();
        txtTravelledDistance.clear();
        comboCompanyId.setValue("Company Id");
    }

    public void getSelected(MouseEvent mouseEvent) {
        index = tableRentalCars.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        txtCarId.setText(colCarId.getCellData(index).toString());
        txtLicense.setText(colLicense.getCellData(index).toString());
        comboInsuranceId.setValue(colInsuranceId.getCellData(index).toString());
        txtType.setText(colType.getCellData(index).toString());
        txtWeight.setText(colWeight.getCellData(index).toString());
        txtBrand.setText(colBrand.getCellData(index).toString());
        txtManufacturingYear.setText(colYear.getCellData(index).toString());
        txtModel.setText(colModel.getCellData(index).toString());
        txtEngineType.setText(colEngineType.getCellData(index).toString());
        txtDailyFare.setText(colDailyFare.getCellData(index).toString());
        txtLicenseExpirationDate.setValue(LocalDate.parse(colLicendeExpirationDate.getCellData(index).toString()));
        txtFuelConsumption.setText(colFuelConsumption.getCellData(index).toString());
        txtTravelledDistance.setText(colTravelledDistance.getCellData(index).toString());
        comboCompanyId.setValue(colCompanyId.getCellData(index).toString());
    }
}
