package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class Database {
    Connection conn = null;

    public static Connection ConnectDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
             //JOptionPane.showMessageDialog(null, "Connection Established");
            return (Connection) DriverManager.getConnection("jdbc:mysql://localhost/projectcars", "root", "assi.com");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public static ObservableList<Payment> getPayments() {
        Connection conn = ConnectDb();
        ObservableList<Payment> list = FXCollections.observableArrayList();
        if (conn != null) {

            try {
                PreparedStatement ps = conn.prepareStatement("select * from payments");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    list.add(new Payment(rs.getInt("payment_id"), rs.getInt("RC_CarId"), rs.getDouble("amount"), rs.getString("payment_type"), rs.getDate("payment_date")));
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public static ObservableList<RentalCar> getRentalCars() {
        Connection conn = ConnectDb();
        ObservableList<RentalCar> list = FXCollections.observableArrayList();
        if (conn != null) {

            try {
                PreparedStatement ps = conn.prepareStatement("select * from rentalcars");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    list.add(new RentalCar(rs.getInt("rc_CarId"), rs.getString("rc_License"),
                            rs.getInt("I_id"), rs.getString("rc_Type"), rs.getDouble("rc_weight"),
                            rs.getString("rc_brand"), rs.getInt("rc_manufacturingYear"),
                            rs.getString("rc_model"), rs.getString("rc_EngieType"), rs.getDouble("rc_dailyFare"),
                            rs.getDate("rc_theLicenseExpirationDate"), rs.getDouble("rc_fualConsumption"),
                            rs.getDouble("travelledDistance"), rs.getInt("rc_rCompanyId")));
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public static ObservableList<Customer> getCustomers() {
        Connection conn = ConnectDb();
        ObservableList<Customer> list = FXCollections.observableArrayList();
        if (conn != null) {

            try {
                PreparedStatement ps = conn.prepareStatement("select * from customer");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    list.add(new Customer(rs.getInt("c_id"), rs.getString("c_name"),
                            rs.getString("c_phone"), rs.getDate("c_dateOfBirth"),
                            rs.getString("c_address"),
                            rs.getString("c_bankAccount")));
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public static ObservableList<InsurancePolicy> getInsurancePolicies() {
        Connection conn = ConnectDb();
        ObservableList<InsurancePolicy> list = FXCollections.observableArrayList();
        if (conn != null) {

            try {
                PreparedStatement ps = conn.prepareStatement("select * from insurancepolicy");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    list.add(new InsurancePolicy(rs.getInt("I_id"), rs.getString("car_I_plateNumber"),
                            rs.getString("I_model"), rs.getString("I_type"), rs.getDouble("fees"),
                            rs.getString("I_Renterphone"), rs.getString("phoneNumber"),
                            rs.getString("tenantsLicense"), rs.getDate("I_insuranceDate")));
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public static ObservableList<Employee> getEmployees() {
        Connection conn = ConnectDb();
        ObservableList<Employee> list = FXCollections.observableArrayList();
        if (conn != null) {

            try {
                PreparedStatement ps = conn.prepareStatement("select * from rentalemployee");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    list.add(new Employee(rs.getInt("re_id"), rs.getString("re_name"),
                            rs.getString("re_phone"), rs.getString("re_address"), rs.getDate("re_dateOfBirth"),
                            rs.getDouble("salary"), rs.getString("re_email"),
                            rs.getInt("re_rCompanyId")));
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public static ObservableList<Accident> getAccidents() {
        Connection conn = ConnectDb();
        ObservableList<Accident> list = FXCollections.observableArrayList();
        if (conn != null) {

            try {
                PreparedStatement ps = conn.prepareStatement("select * from accident");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    list.add(new Accident(rs.getInt("accident_id"), rs.getInt("RC_CarId"),
                            rs.getDouble("cost"), rs.getDate("accident_date"), rs.getString("blow_type"),
                            rs.getString("location")));
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public static ObservableList<String> getInsuranceIdList() {
        Connection conn = ConnectDb();
        ObservableList<String> list = FXCollections.observableArrayList();
        if (conn != null) {

            try {
                PreparedStatement ps = conn.prepareStatement("select * from insurancepolicy");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    list.add(String.valueOf(rs.getInt("I_id")));
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public static ObservableList<String> getCompanyList() {
        Connection conn = ConnectDb();
        ObservableList<String> list = FXCollections.observableArrayList();
        if (conn != null) {

            try {
                PreparedStatement ps = conn.prepareStatement("select * from rentalcarscompany");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    list.add(String.valueOf(rs.getInt("r_CompanyId")));
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public static ObservableList<String> getCarList() {
        Connection conn = ConnectDb();
        ObservableList<String> list = FXCollections.observableArrayList();
        if (conn != null) {

            try {
                PreparedStatement ps = conn.prepareStatement("select * from rentalcars");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    list.add(String.valueOf(rs.getInt("rc_CarId")));
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public static ObservableList<String> getCustomerList() {
        Connection conn = ConnectDb();
        ObservableList<String> list = FXCollections.observableArrayList();
        if (conn != null) {

            try {
                PreparedStatement ps = conn.prepareStatement("select * from customer");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    list.add(String.valueOf(rs.getInt("c_id")));
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public static ObservableList<RentalCarsCompany> getRentalCarsCompanies() {
        Connection conn = ConnectDb();
        ObservableList<RentalCarsCompany> list = FXCollections.observableArrayList();
        if (conn != null) {

            try {
                PreparedStatement ps = conn.prepareStatement("select * from rentalcarscompany");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    list.add(new RentalCarsCompany(rs.getInt("r_CompanyId"), rs.getString("r_name"), rs.getString("r_phone"),
                            rs.getString("r_email"), rs.getString("r_address")));
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public static ObservableList<SupplierCompany> getSupplierCompanies() {
        Connection conn = ConnectDb();
        ObservableList<SupplierCompany> list = FXCollections.observableArrayList();
        if (conn != null) {

            try {
                PreparedStatement ps = conn.prepareStatement("select * from suppliercompany");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    list.add(new SupplierCompany(rs.getInt("s_id"), rs.getString("s_phoneNumber"), rs.getString("s_email"),
                            rs.getString("s_address"), rs.getString("s_carType"), rs.getInt("s_rCompanyID")));
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public static ObservableList<String> getRentalCompanyList() {
        Connection conn = ConnectDb();
        ObservableList<String> list = FXCollections.observableArrayList();
        if (conn != null) {

            try {
                PreparedStatement ps = conn.prepareStatement("select * from rentalcarscompany");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    list.add(String.valueOf(rs.getInt("r_CompanyId")));
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public static ObservableList<RentTransaction> getTransactions() {
        Connection conn = ConnectDb();
        ObservableList<RentTransaction> list = FXCollections.observableArrayList();
        if (conn != null) {

            try {
                PreparedStatement ps = conn.prepareStatement("select * from rent_transaction");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    list.add(new RentTransaction(rs.getInt("rent_transaction_id"), rs.getInt("rc_CarId"), rs.getInt("c_id"),
                            rs.getDate("rent_transaction_date"), rs.getDate("return_date"), rs.getInt("DaysNo"), rs.getInt("delay")));
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public static ObservableList<RentalCar> getTopCars() {
        Connection conn = ConnectDb();
        ObservableList<RentalCar> list = FXCollections.observableArrayList();
        if (conn != null) {

            try {
                PreparedStatement ps = conn.prepareStatement("select rentalcars.* from (select rc_CarId,count(rc_CarId) as count from rent_transaction group by\n" +
                        "rc_CarId order by count desc limit 5) as t1,rentalcars where rentalcars.rc_CarId=t1.rc_CarId");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    list.add(new RentalCar(rs.getInt("rc_CarId"), rs.getString("rc_License"),
                            rs.getInt("I_id"), rs.getString("rc_Type"), rs.getDouble("rc_weight"),
                            rs.getString("rc_brand"), rs.getInt("rc_manufacturingYear"),
                            rs.getString("rc_model"), rs.getString("rc_EngieType"), rs.getDouble("rc_dailyFare"),
                            rs.getDate("rc_theLicenseExpirationDate"), rs.getDouble("rc_fualConsumption"),
                            rs.getDouble("travelledDistance"), rs.getInt("rc_rCompanyId")));
                }
            } catch (Exception e) {
            }
        }
        return list;
    }


    public static ObservableList<Customer> getTopCustomers() {
        Connection conn = ConnectDb();
        ObservableList<Customer> list = FXCollections.observableArrayList();
        if (conn != null) {

            try {
                PreparedStatement ps = conn.prepareStatement("select customer.* from (select c_id,count(c_id) as count from rent_transaction group by\n" +
                        "c_id order by count desc limit 10) as t1,customer where customer.c_id=t1.c_id");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    list.add(new Customer(rs.getInt("c_id"), rs.getString("c_name"),
                            rs.getString("c_phone"), rs.getDate("c_dateOfBirth"),
                            rs.getString("c_address"),
                            rs.getString("c_bankAccount")));
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public static Map<String, String> statistics(String year) {
        Connection conn = ConnectDb();
        Map<String, String> result = new HashMap<>();

        if (conn != null) {

            try {
                PreparedStatement ps = conn.prepareStatement("SELECT sum(amount) as sum_payment,min(amount) as min_payment,max(amount) as max_payment, avg(amount) as average," +
                        "stddev(amount) as stdev_payment,variance(amount) as variance_payment, year(payment_date) from payments  where year(payment_date)=? group by year(payment_date)");
                ps.setString(1, year);
                ResultSet rs = ps.executeQuery();

                if (rs.next()){
                    result.put("Min Payment",String.valueOf(rs.getDouble("min_payment")));
                    result.put("Max Payment",String.valueOf(rs.getDouble("max_payment")));
                    result.put("Average Payment",String.valueOf(rs.getDouble("average")));
                    result.put("Stddev Payment",String.valueOf(rs.getDouble("stdev_payment")));
                    result.put("Variance Payment",String.valueOf(rs.getDouble("variance_payment")));
                    result.put("Sum Payment",String.valueOf(rs.getDouble("sum_payment")));
                }
                else {
                    result.put("Min Payment","0");
                    result.put("Max Payment","0");
                    result.put("Average Payment","0");
                    result.put("Stddev Payment","0");
                    result.put("Variance Payment","0");
                    result.put("Sum Payment","0");
                }

                ps = conn.prepareStatement("SELECT amount, COUNT(amount) AS mode FROM payments where year(payment_date)=? GROUP BY amount ORDER BY mode DESC LIMIT 1");
                ps.setString(1, year);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result.put("Mode Payment", String.valueOf(rs.getDouble("mode")));
                }
                else{
                    result.put("Mode Payment","0");
                }

                ps = conn.prepareStatement("SELECT sum(salary) as total_salary FROM rentalemployee");
                rs = ps.executeQuery();
                if (rs.next()) {
                    result.put("Sum Salary", String.valueOf(rs.getDouble("total_salary")));
                }
                else{
                    result.put("Sum Salary", "0");
                }

                ps = conn.prepareStatement("SELECT sum(fees) as total_fees FROM insurancepolicy where year(I_insuranceDate)=?");
                ps.setString(1, year);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result.put("Sum Insurance", String.valueOf(rs.getDouble("total_fees")));
                }
                else{
                    result.put("Sum Insurance","0");
                }

                ps = conn.prepareStatement("SELECT sum(cost) as total_costs  FROM accident where year(accident_date)=?");
                ps.setString(1, year);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result.put("Sum Accident", String.valueOf(rs.getDouble("total_costs")));
                }
                else{
                    result.put("Sum Accident","0");
                }


            } catch (Exception e) {

            }
        }
        return result;
    }
    
    public static ObservableList<String> getYearList() {
        Connection conn = ConnectDb();
        ObservableList<String> list = FXCollections.observableArrayList();
        if (conn != null) {

            try {
                PreparedStatement ps = conn.prepareStatement("select year(rent_transaction_date) as year from rent_transaction");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    list.add(String.valueOf(rs.getInt("year")));
                }
            } catch (Exception e) {
            }
        }
        return list;
    }
}
