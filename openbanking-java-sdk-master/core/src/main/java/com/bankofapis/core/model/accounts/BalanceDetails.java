package com.bankofapis.core.model.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BalanceDetails {

	@JsonProperty("AccountNo")
    private String accountNo;
	
	@JsonProperty("DebitBalance")
    private double debitBalance;
	
	@JsonProperty("CreditBalance")
    private double creditBalance;
	
	

	public double getDebitBalance() {
		return debitBalance;
	}

	public void setDebitBalance(double debitBalance) {
		this.debitBalance = debitBalance;
	}

	public double getCreditBalance() {
		return creditBalance;
	}

	public void setCreditBalance(double creditBalance) {
		this.creditBalance = creditBalance;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}



	

	
	
}
