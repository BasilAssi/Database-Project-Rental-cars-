package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController{
   

    public void payments(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/payments.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Payments");
        stage.setScene(scene);
        stage.show();
    }

    public void rentalCars(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/rentalCars.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Rental Cars");
        stage.setScene(scene);
        stage.show();
    }

    public void customers(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/customers.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();
    }

    public void insurancePolicies(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/insurancePolicy.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Insurance Policies");
        stage.setScene(scene);
        stage.show();
    }

    public void employees(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/employees.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Employees");
        stage.setScene(scene);
        stage.show();
    }

    public void accidents(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/accident.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Accidents");
        stage.setScene(scene);
        stage.show();
    }

    public void rentalCarsCompanies(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/rentalCarsCompany.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Rental Cars Companies");
        stage.setScene(scene);
        stage.show();
    }

    public void supplierCompanies(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/supplierCompany.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Supplier Companies");
        stage.setScene(scene);
        stage.show();
    }

    public void rentTransactions(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/rentTransaction.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Rent Transactions");
        stage.setScene(scene);
        stage.show();
    }

    public void reports(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/reports.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Reports");
        stage.setScene(scene);
        stage.show();
    }

    public void stats(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/statistics.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Satistics");
        stage.setScene(scene);
        stage.show();
    }
}
