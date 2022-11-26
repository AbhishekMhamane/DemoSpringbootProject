package com.example.MiniProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MiniProject.model.Customer;
import com.example.MiniProject.model.SIMCardInventory;
import com.example.MiniProject.repository.CustomerRepository;
import com.example.MiniProject.repository.SIMCardInventoryRepository;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;
	private final SIMCardInventoryService simCardInventoryService;

	@Autowired
	public CustomerService(CustomerRepository customerRepository, SIMCardInventoryService simCardInventoryService) {
		this.customerRepository = customerRepository;
		this.simCardInventoryService = simCardInventoryService;
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Customer getCustomer(Integer customerId) {

		Optional<Customer> customerRecord = customerRepository.findById(customerId);

		if (customerRecord.isPresent()) {
			return customerRecord.get();
		} else {
			throw new IllegalStateException("Customer Not found");
		}

	}

	public Customer addCustomer(Customer customer) {

		Optional<Customer> customerByEmail = customerRepository.findCustomerByEmail(customer.getEmail());

		if (customerByEmail.isPresent()) {
			throw new IllegalStateException("Customer already present");
		} else {
			return customerRepository.save(customer);
		}

	}

	public void removeAllCustomerDetails(Integer customerId) {

		boolean isCustomerPresent = customerRepository.existsById(customerId);

		if (isCustomerPresent) {

			if (simCardInventoryService.updateStatusSIMCardInventory(customerId)) {
				customerRepository.deleteById(customerId);
			} else {
				throw new IllegalStateException("Illegal operation");
			}
		} else {
			throw new IllegalStateException("Customer not present");
		}

	}

	public void removeCustomer(Integer customerId) {
		boolean isCustomerPresent = customerRepository.existsById(customerId);

		if (isCustomerPresent) {

			customerRepository.deleteById(customerId);

		} else {
			throw new IllegalStateException("Customer not present");
		}
	}

	public Customer updateCustomer(Integer customerId, Customer customer) {

		Optional<Customer> customerOptional = customerRepository.findById(customerId);

		if (customerOptional.isPresent()) {

			Customer customerRecord = customerOptional.get();

			if (customer.getFirstName() != null) {
				if ((!customer.getFirstName().equals(" "))
						&& (!customer.getFirstName().equals(customerRecord.getFirstName()))) {
					customerRecord.setFirstName(customer.getFirstName());
				}
			}

			if (customer.getLastName() != null) {
				if ((!customer.getLastName().equals(" "))
						&& (!customer.getLastName().equals(customerRecord.getLastName()))) {
					customerRecord.setLastName(customer.getLastName());
				}
			}

			if (customer.getCity() != null) {
				if ((!customer.getCity().equals(" ")) && (!customer.getCity().equals(customerRecord.getCity()))) {
					customerRecord.setCity(customer.getCity());
				}
			}

			if (customer.getEmail() != null) {
				if ((!customer.getEmail().equals(" ")) && (!customer.getEmail().equals(customerRecord.getEmail()))) {
					customerRecord.setEmail(customer.getEmail());
				}
			}

			if (customer.getAddress() != null) {
				if ((!customer.getAddress().equals(" "))
						&& (!customer.getAddress().equals(customerRecord.getAddress()))) {
					customerRecord.setAddress(customer.getAddress());
				}
			}

			return customerRepository.save(customerRecord);

		} else {
			throw new IllegalStateException("Customer not found");
		}

	}

	public List<Customer> getCustomersByLastDays(Integer lastDays) {

		LocalDate currentdDate1 = LocalDate.now();
		LocalDate nextDate = currentdDate1.plusDays(lastDays);
		Date date = Date.valueOf(nextDate);

		return customerRepository.findByLastDays(nextDate.toString());
	}

}
