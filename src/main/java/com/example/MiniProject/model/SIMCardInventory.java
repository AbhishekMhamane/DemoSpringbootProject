package com.example.MiniProject.model;

import java.sql.Date;
import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "SIM_CARD_INVENTORY")
@EnableAutoConfiguration
public class SIMCardInventory {

	/*
	 * @Id @GeneratedValue(generator="system-uuid")
	 * 
	 * @GenericGenerator(name="system-uuid", strategy = "uuid")
	 */
	@Id
	String simCardNumber;

	Long telephoneNumber;

	String status;

	String simType;

	Integer customerId;

	Integer planId;

	Date planValidityDate;

	public SIMCardInventory() {
	}

	public SIMCardInventory(String simCardNumber, Long telephoneNumber, String status, String simType,
			Integer customerId, Integer planId, Date planValidityDate) {
		this.simCardNumber = simCardNumber;
		this.telephoneNumber = telephoneNumber;
		this.status = status;
		this.simType = simType;
		this.customerId = customerId;
		this.planId = planId;
		this.planValidityDate = planValidityDate;
	}

	public SIMCardInventory(Long telephoneNumber, String status, String simType, Integer customerId, Integer planId,
			Date planValidityDate) {
		this.telephoneNumber = telephoneNumber;
		this.status = status;
		this.simType = simType;
		this.customerId = customerId;
		this.planId = planId;
		this.planValidityDate = planValidityDate;

	}

	@Override
	public String toString() {
		return "SIMCardInventory [simCardNumber=" + simCardNumber + ", telephoneNumber=" + telephoneNumber + ", status="
				+ status + ", simType=" + simType + ", customerId=" + customerId + ", planId=" + planId
				+ ", planValidityDate=" + planValidityDate + "]";
	}

	public String getSimCardNumber() {
		return simCardNumber;
	}

	public void setSimCardNumber(String simCardNumber) {
		this.simCardNumber = simCardNumber;
	}

	public Long getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(Long i) {
		this.telephoneNumber = i;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSimType() {
		return simType;
	}

	public void setSimType(String simType) {
		this.simType = simType;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public Date getPlanValidityDate() {
		return planValidityDate;
	}

	public void setPlanValidityDate(Date date1) {
		this.planValidityDate = date1;
	}

}
