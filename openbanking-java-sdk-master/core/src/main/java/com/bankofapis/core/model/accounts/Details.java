package com.bankofapis.core.model.accounts;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Details {
	
	@JsonProperty("AccountNo")
    private String accountNo;
	
	@JsonProperty("MonthAndYear")
    private String monthAndYear;

    @JsonProperty("TotalCreditsCurMonth")
    private double sumCredits;
    
    @JsonProperty("TotalDebitsCurMonth")
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

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getMonthAndYear() {
		return monthAndYear;
	}

	public void setMonthAndYear(String monthAndYear) {
		this.monthAndYear = monthAndYear;
	}
    
}
