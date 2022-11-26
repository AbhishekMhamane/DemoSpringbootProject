package com.example.MiniProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MiniProject.service.CustomerService;
import com.example.MiniProject.service.MobilePlansService;
import com.example.MiniProject.service.SIMCardInventoryService;
import com.example.MiniProject.model.Customer;
import com.example.MiniProject.model.MobilePlans;
import com.example.MiniProject.model.SIMCardInventory;

import java.text.ParseException;
import java.util.*;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/v1/customers")
public class CustomerController {

	private final CustomerService customerService;
	private final MobilePlansService mobilePlansService;
	private final SIMCardInventoryService simCardInventoryService;

	@Autowired
	public CustomerController(CustomerService customerService, MobilePlansService mobilePlansService,
			SIMCardInventoryService simCardInventoryService) {
		this.customerService = customerService;
		this.mobilePlansService = mobilePlansService;
		this.simCardInventoryService = simCardInventoryService;
	}

	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Object> getCustomer(@PathVariable("id") Integer customerId) {
		try {
			List<Object> temp = new ArrayList<Object>();

			Customer cust = customerService.getCustomer(customerId);

			SIMCardInventory simCardInventory = simCardInventoryService.getSIMCardInventory(customerId);

			MobilePlans mobilePlans = mobilePlansService.getMobilePlan(simCardInventory.getPlanId());

			temp.add(cust);
			temp.add(simCardInventory);
			temp.add(mobilePlans);

			return new ResponseEntity<>(temp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/lastdays/{lastDays}")
	public ResponseEntity<List<Customer>> getCustomersByLastDays(@PathVariable("lastDays") Integer lastDays)
			throws ParseException {
		return new ResponseEntity<>(customerService.getCustomersByLastDays(lastDays), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Object> addCustomer(@RequestBody Customer customer) {
		try {
			return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(path = "{id}")
	public ResponseEntity<Object> updateCustomer(@PathVariable("id") Integer customerId,
			@RequestBody Customer customer) {
		try {
			return new ResponseEntity<>(customerService.updateCustomer(customerId, customer), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(path = "{id}")
	public ResponseEntity<HttpStatus> removeAllCustomerDetails(@PathVariable("id") Integer customerId) {
		try {
			customerService.removeAllCustomerDetails(customerId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(path = "/one/{id}")
	public ResponseEntity<HttpStatus> removeCustomer(@PathVariable("id") Integer customerId) {
		try {
			customerService.removeCustomer(customerId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
