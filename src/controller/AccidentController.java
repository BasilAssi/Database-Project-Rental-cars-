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
import model.Accident;
import model.Customer;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class AccidentController implements Initializable {


    public TextField txtCost;
    public DatePicker txtAccidentDate;
    public TextField txtAccidentId;
    public TextField txtLocation;
    public TextField txtBlowType;
    public ComboBox comboCarId;
    public TableView<Accident> tableAccidents;
    public TableColumn<Accident, Integer> colAccidentId;
    public TableColumn<Accident, Integer> colCarId;
    public TableColumn<Accident, Double> colCost;
    public TableColumn<Accident, Date> colAccidentDate;
    public TableColumn<Accident, String> colBlowType;
    public TableColumn<Accident, String> colLocation;
    public TextField filterField;
    ObservableList<Accident> listM;
    ObservableList<Accident> dataList;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    @FXML
    void addAccident(ActionEvent event) {
        conn = Database.ConnectDb();
        String sql = "insert into accident (RC_CarId,cost,accident_date,blow_type,location)values(?,?,?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.valueOf(comboCarId.getValue().toString()));
            pst.setDouble(2, Double.valueOf(txtCost.getText()));
            pst.setString(3, String.valueOf(txtAccidentDate.getValue()));
            pst.setString(4, txtBlowType.getText());
            pst.setString(5, txtLocation.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Accident Add succes");
            updateTable();
            searchAccident();
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }


    @FXML
    void deleteAccident(ActionEvent event) {

        conn = Database.ConnectDb();
        String sql = "delete from accident where accident_id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtAccidentId.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Accident Deleted");
            updateTable();
            searchAccident();
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    void editAccident(ActionEvent event) {
        try {
            conn = Database.ConnectDb();

            String sql = "update accident set RC_CarId= ?,cost= ?,accident_date= ?,blow_type= ?,location= ? where accident_id=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.valueOf(comboCarId.getValue().toString()));
            pst.setDouble(2, Double.valueOf(txtCost.getText()));
            pst.setString(3, String.valueOf(txtAccidentDate.getValue()));
            pst.setString(4, String.valueOf(txtBlowType.getText()));
            pst.setString(5, String.valueOf(txtLocation.getText()));
            pst.setInt(6, Integer.valueOf(txtAccidentId.getText()));
            pst.execute();
            JOptionPane.showMessageDialog(null, "Accident Updated");
            updateTable();
            searchAccident();
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    public void getSelected(MouseEvent event) {

        index = tableAccidents.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        txtAccidentId.setText(colAccidentId.getCellData(index).toString());
        comboCarId.setValue(colCarId.getCellData(index).toString());
        txtCost.setText(colCost.getCellData(index).toString());
        txtAccidentDate.setValue(LocalDate.parse(colAccidentDate.getCellData(index).toString()));
        txtBlowType.setText(colBlowType.getCellData(index).toString());
        txtLocation.setText(colLocation.getCellData(index).toString());
    }


    public void updateTable() {

        colAccidentId.setCellValueFactory(new PropertyValueFactory<Accident, Integer>("accidentId"));
        colCarId.setCellValueFactory(new PropertyValueFactory<Accident, Integer>("carId"));
        colCost.setCellValueFactory(new PropertyValueFactory<Accident, Double>("cost"));
        colBlowType.setCellValueFactory(new PropertyValueFactory<Accident, String>("blowType"));
        colLocation.setCellValueFactory(new PropertyValueFactory<Accident, String>("location"));
        colAccidentDate.setCellValueFactory(new PropertyValueFactory<Accident, Date>("accidentDate"));
        listM = Database.getAccidents();
        tableAccidents.setItems(listM);
    }

    public void clear() {
        txtAccidentId.setText("");
        comboCarId.setValue("Car Id");
        txtCost.setText("");
        txtAccidentDate.setValue(null);
        txtBlowType.setText("");
        txtLocation.setText("");
    }


    public void searchAccident() {
        colAccidentId.setCellValueFactory(new PropertyValueFactory<Accident, Integer>("accidentId"));
        colCarId.setCellValueFactory(new PropertyValueFactory<Accident, Integer>("carId"));
        colCost.setCellValueFactory(new PropertyValueFactory<Accident, Double>("cost"));
        colBlowType.setCellValueFactory(new PropertyValueFactory<Accident, String>("blowType"));
        colLocation.setCellValueFactory(new PropertyValueFactory<Accident, String>("location"));
        colAccidentDate.setCellValueFactory(new PropertyValueFactory<Accident, Date>("accidentDate"));

        dataList = Database.getAccidents();


        tableAccidents.setItems(dataList);
        FilteredList<Accident> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(accident -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(accident.getAccidentId()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(accident.getCarId()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (accident.getBlowType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (accident.getLocation().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(accident.getAccidentDate()).indexOf(lowerCaseFilter) != -1)
                    return true;

                else
                    return false; // Does not match.
            });
        });
        SortedList<Accident> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableAccidents.comparatorProperty());
        tableAccidents.setItems(sortedData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> carIdList = Database.getCarList();
        comboCarId.setItems(carIdList);
        updateTable();
        searchAccident();
    }


}
