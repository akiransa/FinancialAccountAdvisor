package com.bankofapis.core.model.accounts;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SummaryDebitsCreditMonthly {
	
	@JsonProperty("Details")
	private List<Details> details = null;

	public List<Details> getDetails() {
		return details;
	}

	public void setDetails(List<Details> details) {
		this.details = details;
	}
}
