package com.bankofapis.core.model.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountInfo {

	@JsonProperty("AccountIdNum")
    private String accountIdentificationNumber;

    @JsonProperty("AccountName")
    private String accountName;

	public String getAccountIdentificationNumber() {
		return accountIdentificationNumber;
	}

	public void setAccountIdentificationNumber(String accountIdentificationNumber) {
		this.accountIdentificationNumber = accountIdentificationNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
}
