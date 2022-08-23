package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
import java.util.Map;
import java.util.ResourceBundle;

public class StatsController implements Initializable {

    public ComboBox year;
    public Label statistics;

    ObservableStringValue labelText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> yearList =Database.getYearList();
        
    
       
        year.setItems(yearList);
        year.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                updateReport(newValue);
            }
        });
    }


    public void updateReport(String year) {
        Map<String, String> stats = Database.statistics(year);

        statistics.setText("Minimum Payment for year " + year + "\t\t\t\t\t" + stats.get("Min Payment") + "\n" +
                "Maximum Payment for year " + year + "\t\t\t\t\t" + stats.get("Max Payment") + "\n" +
                "Standard Deviation of Payments for year " + year + "\t\t" + stats.get("Stddev Payment") + "\n" +
                "Average Payment for year " + year + "\t\t\t\t\t" + stats.get("Average Payment") + "\n" +
                "Variance of Payments for year " + year + "\t\t\t\t" + stats.get("Variance Payment") + "\n" +
                "Mode Payments for year " + year + "\t\t\t\t\t" + stats.get("Mode Payment") + "\n" +
                "Total amount of payments for year " + year + "\t\t\t" + stats.get("Sum Payment") + "\n" +
                "Last Profit for year " + year + "\t\t\t\t\t\t\t" + (Double.valueOf(stats.get("Sum Payment")) - Double.valueOf(stats.get("Sum Salary")) - Double.valueOf(stats.get("Sum Insurance"))) + "\n" +
                "Accident costs for year " + year + " (Customers' costs)\t\t" + Double.valueOf(stats.get("Sum Accident"))/5 + "\n"+
                "Accident costs for year " + year + " (Insurance costs)\t\t" + 4*Double.valueOf(stats.get("Sum Accident"))/5 + "\n"+
                "Accident costs for year " + year + "\t\t\t\t\t\t" + stats.get("Sum Accident") + "\n");

    }
}
