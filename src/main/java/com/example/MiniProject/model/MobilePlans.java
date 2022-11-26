package com.example.MiniProject.model;

import javax.persistence.*;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Entity
@Table(name = "Mobile_Plans")
@EnableAutoConfiguration
public class MobilePlans {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PLAN_ID", insertable = false)
	private Integer planId;

	private String planName;

	private Integer validity;

	private Integer amount;

	private String planInfo;

	public MobilePlans() {
	}

	public MobilePlans(Integer planId, String planName, Integer validity, Integer amount, String planInfo) {

		this.planId = planId;
		this.planName = planName;
		this.validity = validity;
		this.amount = amount;
		this.planInfo = planInfo;
	}

	public MobilePlans(String planName, Integer validity, Integer amount, String planInfo) {

		this.planName = planName;
		this.validity = validity;
		this.amount = amount;
		this.planInfo = planInfo;
	}

	@Override
	public String toString() {
		return "MobilePlans [planId=" + planId + ", planName=" + planName + ", validity=" + validity + ", amount="
				+ amount + ", planInfo=" + planInfo + "]";
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Integer getValidity() {
		return validity;
	}

	public void setValidity(Integer validity) {
		this.validity = validity;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getPlanInfo() {
		return planInfo;
	}

	public void setPlanInfo(String planInfo) {
		this.planInfo = planInfo;
	}

}
