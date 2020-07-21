package com.bankofapis.core.model.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DetailsConsolidated {
	@JsonProperty("MonthAndYear")
	private String monthAndYear = null;
	
	
	
	@JsonProperty("AllDebits")
	private double allDebits = 0.0;
	
	@JsonProperty("AllCredits")
	private double allCredits = 0.0;
	
	@JsonProperty("AvailBalanceForCurMonth")
	private double availBalance = 0.0;

	

	public double getAllDebits() {
		return allDebits;
	}

	public void setAllDebits(double allDebits) {
		this.allDebits = allDebits;
	}

	public double getAllCredits() {
		return allCredits;
	}

	public void setAllCredits(double allCredits) {
		this.allCredits = allCredits;
	}

	public String getMonthAndYear() {
		return monthAndYear;
	}

	public void setMonthAndYear(String monthAndYear) {
		this.monthAndYear = monthAndYear;
	}

	public double getAvailBalance() {
		return availBalance;
	}

	public void setAvailBalance(double availBalance) {
		this.availBalance = availBalance;
	}
}
