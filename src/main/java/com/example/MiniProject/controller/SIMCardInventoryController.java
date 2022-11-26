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

import com.example.MiniProject.service.SIMCardInventoryService;
import com.example.MiniProject.model.Customer;
import com.example.MiniProject.model.SIMCardInventory;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/v1/simcardinventory")
public class SIMCardInventoryController {

	private final SIMCardInventoryService simCardInventoryService;

	@Autowired
	public SIMCardInventoryController(SIMCardInventoryService simCardInventoryService) {
		this.simCardInventoryService = simCardInventoryService;
	}

	@GetMapping
	public ResponseEntity<List<SIMCardInventory>> getAllSIMCardInventory() {

		return new ResponseEntity<>(simCardInventoryService.getAllSIMCardInventory(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Object> addSIMCardToInventory(@RequestBody SIMCardInventory simCardInventory) {
		try {
			return new ResponseEntity<>(simCardInventoryService.addSIMCardToInventory(simCardInventory), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{customerId}/{planId}/{simType}")
	public ResponseEntity<Object> assignSIMCardFromInventory(@PathVariable("customerId") Integer customerId,
			@PathVariable("planId") Integer planId, @PathVariable String simType) {
		try {
			return new ResponseEntity<>(simCardInventoryService.assignSIMCardFromInventory(customerId, planId, simType),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(path = "/{customerId}/{planId}")
	public ResponseEntity<Object> updateSIMCardWithCustomerAndPlanId(@PathVariable("customerId") Integer customerId,
			@PathVariable("planId") Integer planId) {
		try {
			return new ResponseEntity<>(simCardInventoryService.updateSIMCardWithCustomerAndPlanId(customerId, planId),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
