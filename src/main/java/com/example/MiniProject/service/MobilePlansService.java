package com.example.MiniProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MiniProject.model.MobilePlans;
import com.example.MiniProject.repository.MobilePlansRepository;
import java.util.*;

@Service
public class MobilePlansService {

	private final MobilePlansRepository mobilePlansRepository;

	@Autowired
	public MobilePlansService(MobilePlansRepository mobilePlansRepository) {
		this.mobilePlansRepository = mobilePlansRepository;
	}

	public List<MobilePlans> getAllMobilePlans() {
		return mobilePlansRepository.findAll();
	}

	public MobilePlans getMobilePlan(Integer planId) {
		Optional<MobilePlans> mobilePlan = mobilePlansRepository.findById(planId);

		if (!mobilePlan.isPresent()) {
			throw new IllegalStateException("Mobile plan not present");
		}
		return mobilePlan.get();
	}

	public boolean checkMobilePlan(Integer planId) {
		Optional<MobilePlans> mobilePlan = mobilePlansRepository.findById(planId);
		if (mobilePlan.isPresent()) {
			return true;
		}

		return false;
	}
}
