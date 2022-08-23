package model;

import java.util.Date;

public class InsurancePolicy {
    private int insuranceId;
    private String plateNumber;
    private String model;
    private String type;
    private double fees;
    private String renterPhone;
    private String phoneNumber;
    private String tenantsLicense;
    private Date insuranceDate;

    public InsurancePolicy(int insuranceId, String plateNumber, String model, String type, double fees, String renterPhone, String phoneNumber, String tenantsLicense, Date insuranceDate) {
        this.insuranceId = insuranceId;
        this.plateNumber = plateNumber;
        this.model = model;
        this.type = type;
        this.fees = fees;
        this.renterPhone = renterPhone;
        this.phoneNumber = phoneNumber;
        this.tenantsLicense = tenantsLicense;
        this.insuranceDate = insuranceDate;
    }

    public int getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(int insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public String getRenterPhone() {
        return renterPhone;
    }

    public void setRenterPhone(String renterPhone) {
        this.renterPhone = renterPhone;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTenantsLicense() {
        return tenantsLicense;
    }

    public void setTenantsLicense(String tenantsLicense) {
        this.tenantsLicense = tenantsLicense;
    }

    public Date getInsuranceDate() {
        return insuranceDate;
    }

    public void setInsuranceDate(Date insuranceDate) {
        this.insuranceDate = insuranceDate;
    }
}
