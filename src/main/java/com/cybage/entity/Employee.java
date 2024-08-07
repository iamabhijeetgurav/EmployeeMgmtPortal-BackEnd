package com.cybage.entity;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employee {

	@Id
	private Long employeeId;

	private String email;
	private String mobileNo;
	private String firstName;
	private String lastName;
	private String password;
	private String designation;
	private String managerEmailId;
	private String city;

	@ElementCollection
	private List<String> jobPreferenceLocations;

	public Employee() {

	}

	public Employee(Long employeeId, String email, String mobileNo, String firstName, String lastName, String password,
			String designation, String managerEmailId, String city, List<String> jobPreferenceLocations) {
		super();
		this.employeeId = employeeId;
		this.email = email;
		this.mobileNo = mobileNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.designation = designation;
		this.managerEmailId = managerEmailId;
		this.city = city;
		this.jobPreferenceLocations = jobPreferenceLocations;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getManagerEmailId() {
		return managerEmailId;
	}

	public void setManagerEmailId(String managerEmailId) {
		this.managerEmailId = managerEmailId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<String> getJobPreferenceLocations() {
		return jobPreferenceLocations;
	}

	public void setJobPreferenceLocations(List<String> jobPreferenceLocations) {
		this.jobPreferenceLocations = jobPreferenceLocations;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", email=" + email + ", mobileNo=" + mobileNo + ", firstName="
				+ firstName + ", lastName=" + lastName + ", password=" + password + ", designation=" + designation
				+ ", managerEmailId=" + managerEmailId + ", city=" + city + ", jobPreferenceLocations="
				+ jobPreferenceLocations + "]";
	}

}
