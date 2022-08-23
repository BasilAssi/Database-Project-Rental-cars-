package model;

import java.util.Date;

public class RentTransaction {
    private int transactionId;
    private int carId;
    private int customerId;
    private Date transactionDate;
    private Date returnDate;
    private int daysNo;
    private int delay;

    public RentTransaction(int transactionId, int carId, int customerId, Date transactionDate, Date returnDate, int daysNo, int delay) {
        this.transactionId = transactionId;
        this.carId = carId;
        this.customerId = customerId;
        this.transactionDate = transactionDate;
        this.returnDate = returnDate;
        this.daysNo = daysNo;
        this.delay = delay;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getDaysNo() {
        return daysNo;
    }

    public void setDaysNo(int daysNo) {
        this.daysNo = daysNo;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
