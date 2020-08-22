package org.infsys.pharmacy.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.infsys.pharmacy.ApplicationSingleton;

public class Prescription implements Serializable  {
	
	private static final long serialVersionUID = -3384104550969269280L;
	private Integer code;
	private String doctorId;
	private String patientPersonalNumber;
	private LocalDateTime dateAndTime;
	private Map<String, Integer> medicinesWithAmounts;
	
	public Prescription() {
	}

	public Prescription(String patientId, Map<String, Integer> medicinesWithAmounts) {
		this.code = ApplicationSingleton.getInstance().getPrescriptions().size() + 1;
		
		//Take doctor's username as doctor ID
		this.doctorId = ApplicationSingleton.getInstance().getLoggedInUser().getUsername();
		
		this.patientPersonalNumber = patientId;
		this.dateAndTime = LocalDateTime.now();
		this.medicinesWithAmounts = medicinesWithAmounts;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getPatientPersonalNumber() {
		return patientPersonalNumber;
	}

	public void setPatientPersonalNumber(String patientPersonalNumber) {
		this.patientPersonalNumber = patientPersonalNumber;
	}

	public LocalDateTime getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(LocalDateTime dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
	
	public Map<String, Integer> getMedicinesWithAmounts() {
		return medicinesWithAmounts;
	}

	public void setMedicinesWithAmounts(Map<String, Integer> medicinesWithAmounts) {
		this.medicinesWithAmounts = medicinesWithAmounts;
	}

	public float getTotalPrice() {
		float totalPrice = 0.0f;
		for(String medicationCode : this.medicinesWithAmounts.keySet()) {
			Medication medication = ApplicationSingleton.getInstance().getMedications().get(medicationCode);
			totalPrice += medication.getPrice() * this.medicinesWithAmounts.get(medicationCode);
		}
		return BigDecimal.valueOf(totalPrice).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
	}
	
	public String medicationsWithAmountsToString() {
		StringBuilder medications = new StringBuilder();
		for(String medicationCode : this.medicinesWithAmounts.keySet()) {
			Medication medication = ApplicationSingleton.getInstance().getMedications().get(medicationCode);
			medications.append(medication.getName());
			medications.append("[" + this.medicinesWithAmounts.get(medicationCode));
			medications.append("]");
			medications.append("\n");
		}
		return medications.substring(0, medications.length() - 1);
	}
}