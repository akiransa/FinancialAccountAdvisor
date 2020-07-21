package com.bankofapis.core.model.accounts;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Details {
	
	
	@JsonProperty("MonthAndYear")
    private String monthAndYear;

    @JsonProperty("SumOfCredits")
    private double sumCredits;
    
    @JsonProperty("SumOfDebits")
    private double sumDebits;
    
    @JsonProperty("BalanceForThisMonth")
    private double balanceInThisMonth;
    
    @JsonProperty("BalanceForPrevMonth")
    private double balanceInPrevMonth;
    
    @JsonProperty("AccBalCurrMonth")
    private double accBalCurMonth;

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

	public double getBalanceInThisMonth() {
		return balanceInThisMonth;
	}

	public void setBalanceInThisMonth(double balanceInThisMonth) {
		this.balanceInThisMonth = balanceInThisMonth;
	}

	public void setMonthAndYear(String monthAndYear) {
		this.monthAndYear = monthAndYear;
	}

	public double getBalanceInPrevMonth() {
		return balanceInPrevMonth;
	}

	public void setBalanceInPrevMonth(double balanceInPrevMonth) {
		this.balanceInPrevMonth = balanceInPrevMonth;
	}

	public double getAccBalCurMonth() {
		return accBalCurMonth;
	}

	public void setAccBalCurMonth(double accBalCurMonth) {
		this.accBalCurMonth = accBalCurMonth;
	}
    
}
