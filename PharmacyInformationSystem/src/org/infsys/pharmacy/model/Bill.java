package org.infsys.pharmacy.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

import org.infsys.pharmacy.ApplicationSingleton;

public class Bill implements Serializable  {
	
	private static final long serialVersionUID = -7713036093909132149L;
	private Map<String, Integer> medicinesWithAmounts;
	private Float totalPrice;
	private String pharmacistId;
	private LocalDateTime dateAndTime;
	
	public Bill() {
	}
	
	public Bill(Map<String, Integer> medicinesWithAmounts, Float totalPrice) {
		this.medicinesWithAmounts = medicinesWithAmounts;
		this.totalPrice = totalPrice;
		this.pharmacistId = ApplicationSingleton.getInstance().getLoggedInUser().getUsername();
		this.dateAndTime = LocalDateTime.now();
	}

	public Map<String, Integer> getMedicinesWithAmounts() {
		return medicinesWithAmounts;
	}

	public void setMedicinesWithAmounts(Map<String, Integer> medicinesWithAmounts) {
		this.medicinesWithAmounts = medicinesWithAmounts;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPharmacistId() {
		return pharmacistId;
	}

	public void setPharmacistId(String pharmacistId) {
		this.pharmacistId = pharmacistId;
	}

	public LocalDateTime getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(LocalDateTime dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
}