package model;

import java.util.Date;

public class Payment {
    private int paymentId;
    private int carId;
    private double amount;
    private String paymentType;
    private Date paymentDate;

    public Payment(int paymentId, int carId, double amount, String paymentType, Date paymentDate) {
        this.paymentId = paymentId;
        this.carId = carId;
        this.amount = amount;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
