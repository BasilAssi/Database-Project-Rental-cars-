package model;

import java.util.Date;

public class RentalCar {
    private int carId;
    private int insuranceId;
    private String license;
    private String type;
    private double weight;
    private String brand;
    private int manufacturingYear;
    private String model;
    private String engineType;
    private double dailyFare;
    private Date licenceExpirationDate;
    private double fuelConsumption;
    private double travelledDistance;
    private int companyId;

    public RentalCar(int carId,String license, int insuranceId, String type, double weight, String brand, int manufacturingYear, String model, String engineType, double dailyFare, Date licenceExpirationDate, double fuelConsumption, double travelledDistance, int companyId) {
        this.carId = carId;
        this.insuranceId=insuranceId;
        this.license = license;
        this.type = type;
        this.weight = weight;
        this.brand = brand;
        this.manufacturingYear = manufacturingYear;
        this.model = model;
        this.engineType = engineType;
        this.dailyFare = dailyFare;
        this.licenceExpirationDate = licenceExpirationDate;
        this.fuelConsumption = fuelConsumption;
        this.travelledDistance = travelledDistance;
        this.companyId = companyId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(int insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getManufacturingYear() {
        return manufacturingYear;
    }

    public void setManufacturingYear(int manufacturingYear) {
        this.manufacturingYear = manufacturingYear;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public double getDailyFare() {
        return dailyFare;
    }

    public void setDailyFare(double dailyFare) {
        this.dailyFare = dailyFare;
    }

    public Date getLicenceExpirationDate() {
        return licenceExpirationDate;
    }

    public void setLicenceExpirationDate(Date licenceExpirationDate) {
        this.licenceExpirationDate = licenceExpirationDate;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public double getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(double travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
