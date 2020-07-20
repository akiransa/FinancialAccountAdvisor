package com.bankofapis.core.model.accounts;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Details {
	
	
	@JsonProperty("MonthAndYear")
    private String MonthAndYear;

    @JsonProperty("SumOfCredits")
    private double sumCredits;
    
    @JsonProperty("SumOfDebits")
    private double sumDebits;

	public String getMonthYear() {
		return MonthAndYear;
	}

	public void setMonthYear(String monthYear) {
		MonthAndYear = monthYear;
	}

	public double getSumCredits() {
		return sumCredits;
	}

	public void setSumCredits(double d) {
		this.sumCredits = d;
	}

	public double getSumDebits() {
		return sumDebits;
	}

	public void setSumDebits(double d) {
		this.sumDebits = d;
	}
    
}
