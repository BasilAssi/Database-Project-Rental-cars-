package model;

import java.util.Date;

public class Customer {
	
	
	private int  customerId;
	private String customerName;
	private String customerPhone;
	private Date dateOfBirth;
	private String customerAddress;
	private String customerBankAccount;

	
	public Customer(int customerId, String customerName, String customerPhone, Date dateOfBirth, String customerAddress,
			String customerBankAccount) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.dateOfBirth = dateOfBirth;
		this.customerAddress = customerAddress;
		this.customerBankAccount = customerBankAccount;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerBankAccount() {
		return customerBankAccount;
	}

	public void setCustomerBankAccount(String customerBankAccount) {
		this.customerBankAccount = customerBankAccount;
	}
	
	

}
