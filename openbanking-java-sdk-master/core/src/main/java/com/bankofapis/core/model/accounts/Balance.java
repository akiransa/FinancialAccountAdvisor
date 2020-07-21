package com.bankofapis.core.model.accounts;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Balance {
	
	@JsonProperty("BalanceDetails")
    private List<BalanceDetails> balanceDetails= null;
	
	@JsonProperty("SumOfAllDebits")
    private double sumAllDebits;

    @JsonProperty("SumOfAllCredits")
    private double sumAllCredits;
    
    
    @JsonProperty("AvgBalPerMonAvailable")
    private double averageBalance;

	public List<BalanceDetails> getBalanceDetails() {
		return balanceDetails;
	}

	public void setBalanceDetails(List<BalanceDetails> balanceDetails) {
		this.balanceDetails = balanceDetails;
	}

	public double getSumAllDebits() {
		return sumAllDebits;
	}

	public void setSumAllDebits(double sumAllDebits) {
		this.sumAllDebits = sumAllDebits;
	}

	public double getSumAllCredits() {
		return sumAllCredits;
	}

	public void setSumAllCredits(double sumAllCredits) {
		this.sumAllCredits = sumAllCredits;
	}

	public double getAverageBalance() {
		return averageBalance;
	}

	public void setAverageBalance(double averageBalance) {
		this.averageBalance = averageBalance;
	}


	
}
