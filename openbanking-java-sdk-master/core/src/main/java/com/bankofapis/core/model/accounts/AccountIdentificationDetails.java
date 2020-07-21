package com.bankofapis.core.model.accounts;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountIdentificationDetails {
	
	 @JsonProperty("AccountInfo")
	    private List<AccountInfo> accountInfo = null;

	public List<AccountInfo> getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(List<AccountInfo> accountInfo) {
		this.accountInfo = accountInfo;
	}

}
