package com.bankofapis.core.model.accounts;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SummaryDebitsCreditMonthlyConsolidated {

	 
	 
	@JsonProperty("SummaryDebitsCreditMonthly")
	private List<SummaryDebitsCreditMonthly> summaryDebitsCreditMonthly = null;

	public List<SummaryDebitsCreditMonthly> getSummaryDebitsCreditMonthly() {
		return summaryDebitsCreditMonthly;
	}

	public void setSummaryDebitsCreditMonthly(List<SummaryDebitsCreditMonthly> summaryDebitsCreditMonthly) {
		this.summaryDebitsCreditMonthly = summaryDebitsCreditMonthly;
	}

	

	
}
