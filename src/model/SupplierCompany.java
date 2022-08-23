package model;

public class SupplierCompany {
    private int companyId;
    private String companyPhone;
    private String companyEmail;
    private String companyAddress;
    private String carType;
    private int rentalCompanyId;

    public SupplierCompany(int companyId, String companyPhone, String companyEmail, String companyAddress, String carType, int rentalCompanyId) {
        this.companyId = companyId;
        this.companyPhone = companyPhone;
        this.companyEmail = companyEmail;
        this.companyAddress = companyAddress;
        this.carType = carType;
        this.rentalCompanyId = rentalCompanyId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public int getRentalCompanyId() {
        return rentalCompanyId;
    }

    public void setRentalCompanyId(int rentalCompanyId) {
        this.rentalCompanyId = rentalCompanyId;
    }
}
