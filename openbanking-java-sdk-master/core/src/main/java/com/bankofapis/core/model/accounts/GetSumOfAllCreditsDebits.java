package com.bankofapis.core.model.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetSumOfAllCreditsDebits {

	@JsonProperty("SumOfAllDebits")
    private Float sumAllDebits;

    @JsonProperty("SumOfAllCredits")
    private Float sumAllCredits;

	public Float getSumAllDebits() {
		return sumAllDebits;
	}

	public void setSumAllDebits(Float sumAllDebits) {
		this.sumAllDebits = sumAllDebits;
	}

	public Float getSumAllCredits() {
		return sumAllCredits;
	}

	public void setSumAllCredits(Float sumAllCredits) {
		this.sumAllCredits = sumAllCredits;
	}
}
