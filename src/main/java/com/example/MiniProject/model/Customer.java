package com.example.MiniProject.model;

import javax.persistence.*;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Entity
@Table(name = "Customer")
@EnableAutoConfiguration
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CUSTOMER_ID", insertable = false)
	private Integer customerId;

	private String firstName;

	private String lastName;

	private String city;

	private String email;

	private String address;

	public Customer() {
	}

	public Customer(Integer customerId, String firstName, String lastName, String city, String email, String address) {
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.email = email;
		this.address = address;
	}

	public Customer(String firstName, String lastName, String city, String email, String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.email = email;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName + ", city="
				+ city + ", email=" + email + ", address=" + address + "]";
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
