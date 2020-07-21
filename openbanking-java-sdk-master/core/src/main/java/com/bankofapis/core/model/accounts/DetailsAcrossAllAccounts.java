package com.bankofapis.core.model.accounts;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DetailsAcrossAllAccounts {
	
	
	 @JsonProperty("Details")
	    private List<DetailsConsolidated> detailsConsolidated = null;

	public List<DetailsConsolidated> getDetailsConsolidated() {
		return detailsConsolidated;
	}

	public void setDetailsConsolidated(List<DetailsConsolidated> detailsConsolidated) {
		this.detailsConsolidated = detailsConsolidated;
	}
	
}
