package model;

import java.util.Date;

public class Accident {
    private int accidentId;
    private int carId;
    private double cost;
    private Date accidentDate;
    private String blowType;
    private String location;

    public Accident(int accidentId, int carId, double cost, Date accidentDate, String blowType, String location) {
        this.accidentId = accidentId;
        this.carId = carId;
        this.cost = cost;
        this.accidentDate = accidentDate;
        this.blowType = blowType;
        this.location = location;
    }

    public int getAccidentId() {
        return accidentId;
    }

    public void setAccidentId(int accidentId) {
        this.accidentId = accidentId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getAccidentDate() {
        return accidentDate;
    }

    public void setAccidentDate(Date accidentDate) {
        this.accidentDate = accidentDate;
    }

    public String getBlowType() {
        return blowType;
    }

    public void setBlowType(String blowType) {
        this.blowType = blowType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
