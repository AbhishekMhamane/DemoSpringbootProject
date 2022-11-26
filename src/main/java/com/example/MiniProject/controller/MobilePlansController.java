package com.example.MiniProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MiniProject.model.MobilePlans;
import com.example.MiniProject.service.MobilePlansService;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/v1/mobileplans")
public class MobilePlansController {

	private final MobilePlansService mobilePlansService;

	@Autowired
	public MobilePlansController(MobilePlansService mobilePlansService) {
		this.mobilePlansService = mobilePlansService;
	}

	@GetMapping
	public ResponseEntity<List<MobilePlans>> getAllMobilePlans() {
		return new ResponseEntity<List<MobilePlans>>(mobilePlansService.getAllMobilePlans(), HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Object> getMobilePlan(@PathVariable("id") Integer planId) {
		try {
			return new ResponseEntity<>(mobilePlansService.getMobilePlan(planId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

		}
	}

}
