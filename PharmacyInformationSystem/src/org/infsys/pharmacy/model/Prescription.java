package org.infsys.pharmacy.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

public class Prescription implements Serializable  {
	
	private static final long serialVersionUID = -3384104550969269280L;
	private int code;
	private String doctorId;
	private String patientId;
	private LocalDateTime dateAndTime;
	private Map<Medicine, Integer> medicinesWithAmounts;
	private float totalPrice;
	
	public Prescription() {
	}

	public Prescription(int code, String doctorId, String patientId) {
		this.code = code;
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.dateAndTime = LocalDateTime.now();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public LocalDateTime getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(LocalDateTime dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public Map<Medicine, Integer> getMedicinesWithAmounts() {
		return medicinesWithAmounts;
	}

	public void setMedicinesWithAmounts(Map<Medicine, Integer> medicinesWithAmounts) {
		this.medicinesWithAmounts = medicinesWithAmounts;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
}