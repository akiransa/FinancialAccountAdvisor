package com.bankofapis.core.model.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetSumOfAllCreditsDebits {

	@JsonProperty("SumOfAllDebits")
    private double sumAllDebits;

    @JsonProperty("SumOfAllCredits")
    private double sumAllCredits;
    
    @JsonProperty("AccountNos")
    private String accountNo;


	
	public void setSumAllCredits(Float sumAllCredits) {
		this.sumAllCredits = sumAllCredits;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
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


}
