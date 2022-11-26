package com.example.MiniProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.example.MiniProject.repository.MobilePlansRepository;
import com.example.MiniProject.repository.SIMCardInventoryRepository;
import com.example.MiniProject.model.MobilePlans;
import com.example.MiniProject.model.SIMCardInventory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;
import java.util.*;

@Service
public class SIMCardInventoryService {

	private final SIMCardInventoryRepository simCardInventoryRepository;
	private final MobilePlansService mobilePlansService;

	String availableStatus = "available";
	String allocatedStatus = "allocated";
	String prepaidSimType = "prepaid";
	long expectedAvaialblePercentage = 50;

	@Autowired
	public SIMCardInventoryService(SIMCardInventoryRepository simCardInventoryRepository,
			MobilePlansService mobilePlansService) {
		this.simCardInventoryRepository = simCardInventoryRepository;
		this.mobilePlansService = mobilePlansService;
	}

	public List<SIMCardInventory> getAllSIMCardInventory() {

		return simCardInventoryRepository.findAll();
	}

	public SIMCardInventory getSIMCardInventory(Integer customerId) {
		SIMCardInventory simcardRecord = simCardInventoryRepository.findByCustomerId(customerId);

		return simcardRecord;
	}

	public void addMultipleSIMCardsToInventory() {

		int count = 0;

		while (count != 2) {
			long randomTelephoneNo = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;

			if (!simCardInventoryRepository.existsByTelephoneNumber(randomTelephoneNo)) {
				SIMCardInventory newSimCard = new SIMCardInventory(randomTelephoneNo, availableStatus, prepaidSimType,
						null, null, null);
				addSIMCardToInventory(newSimCard);
				count = count + 1;
			}
		}

	}

	public SIMCardInventory addSIMCardToInventory(SIMCardInventory simCardInvnentory) {
		try {

			SIMCardInventory newSimCard = new SIMCardInventory(simCardInventoryRepository.getNextSeriesSimCardNumber(),
					simCardInvnentory.getTelephoneNumber(), simCardInvnentory.getStatus(),
					simCardInvnentory.getSimType(), simCardInvnentory.getCustomerId(), simCardInvnentory.getPlanId(),
					simCardInvnentory.getPlanValidityDate());

			return simCardInventoryRepository.save(newSimCard);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalStateException("Can not create SimCard");
		}
	}

	public SIMCardInventory assignSIMCardFromInventory(Integer customerId, Integer planId, String simType)
			throws ParseException {

		long availableSimCards = getPercentageOfAvaialbleSimCards();

		System.out.println("Avaible sim cards in %: " + availableSimCards);

		if (availableSimCards <= expectedAvaialblePercentage) {
			System.out.println("Event triggered");
			addMultipleSIMCardsToInventory();
		}

		SIMCardInventory simcardRecord = simCardInventoryRepository.findFirstByStatus("available");

		simcardRecord.setSimCardNumber(simcardRecord.getSimCardNumber());
		simcardRecord.setTelephoneNumber(simcardRecord.getTelephoneNumber());
		simcardRecord.setSimType(simType);
		simcardRecord.setStatus(allocatedStatus);
		simcardRecord.setCustomerId(customerId);

		MobilePlans mobilePlan = mobilePlansService.getMobilePlan(planId);

		if (mobilePlan != null) {
			simcardRecord.setPlanId(mobilePlan.getPlanId());
			LocalDate currentdDate1 = LocalDate.now();
			LocalDate currentDatePlus1 = currentdDate1.plusDays(mobilePlan.getValidity());
			Date date = Date.valueOf(currentDatePlus1); // System.out.println(date);
			simcardRecord.setPlanValidityDate(date);
		} else {
			throw new IllegalStateException("Please select correct plan Id");
		}

		return simCardInventoryRepository.save(simcardRecord);

	}

	public Object updateSIMCardWithCustomerAndPlanId(Integer customerId, Integer planId) {

		SIMCardInventory simcardRecord = simCardInventoryRepository.findByCustomerId(customerId);

		if (simcardRecord != null) {
			simcardRecord.setSimCardNumber(simcardRecord.getSimCardNumber());
			simcardRecord.setTelephoneNumber(simcardRecord.getTelephoneNumber());
			simcardRecord.setSimType(simcardRecord.getSimType());
			simcardRecord.setStatus(simcardRecord.getStatus());
			simcardRecord.setCustomerId(simcardRecord.getCustomerId());

			MobilePlans mobilePlan = mobilePlansService.getMobilePlan(planId);

			if (mobilePlan != null) {
				simcardRecord.setPlanId(mobilePlan.getPlanId());
				LocalDate currentdDate1 = LocalDate.now();
				LocalDate currentDatePlus1 = currentdDate1.plusDays(mobilePlan.getValidity());
				Date date = Date.valueOf(currentDatePlus1);
				// System.out.println(date);
				simcardRecord.setPlanValidityDate(date);
			} else {
				throw new IllegalStateException("Please select correct plan Id");
			}

			return simCardInventoryRepository.save(simcardRecord);
		} else {
			throw new IllegalStateException("Customer not present");
		}

	}

	public boolean updateStatusSIMCardInventory(Integer customerId) {
		SIMCardInventory simcardRecord = simCardInventoryRepository.findByCustomerId(customerId);

		if (simcardRecord != null) {
			simcardRecord.setSimCardNumber(simcardRecord.getSimCardNumber());
			simcardRecord.setTelephoneNumber(simcardRecord.getTelephoneNumber());
			simcardRecord.setSimType(prepaidSimType);
			simcardRecord.setStatus(availableStatus);
			simcardRecord.setCustomerId(null);
			simcardRecord.setPlanId(null);
			simcardRecord.setPlanValidityDate(null);

			return true;
		} else {
			return false;
		}
	}

	public long getPercentageOfAvaialbleSimCards() {
		long availableSimCards = simCardInventoryRepository.countByStatus(availableStatus);
		long totalSimCards = simCardInventoryRepository.count();

		System.out.println(availableSimCards);
		System.out.println(totalSimCards);

		long percentage = (availableSimCards * 100) / totalSimCards;

		return percentage;
	}

	public long getCountOfStatus() {
		return simCardInventoryRepository.countByStatus(availableStatus);
	}

	public long getCountOfTotal() {
		return simCardInventoryRepository.count();
	}

}
