package com.bankofapis.remote.service;

import com.bankofapis.core.model.accounts.*;
import com.bankofapis.core.model.common.HttpRequestHeader;
import com.bankofapis.remote.util.BaseApiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import static com.bankofapis.remote.common.Endpoints.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class AispRemote {

	private static final Logger logger = LoggerFactory.getLogger(AispRemote.class);

	private final RestTemplate securedRestTemplate;
	private final BaseApiUtils apiUtils;

	public AispRemote(RestTemplate securedRestTemplate, BaseApiUtils apiUtils) {
		this.securedRestTemplate = securedRestTemplate;
		this.apiUtils = apiUtils;
	}

	public OBReadDomesticConsentResponse createAispConsent(OBReadDomesticConsent obReadDataDomesticConsent,
			HttpRequestHeader httpRequestHeader) {

		HttpEntity<OBReadDomesticConsent> accountConsentRequest = apiUtils.createRequest(obReadDataDomesticConsent,
				httpRequestHeader);

		return securedRestTemplate.postForEntity(apiUtils.getUri(ACCOUNT_ACCESS_CONSENT_ENDPOINT),
				accountConsentRequest, OBReadDomesticConsentResponse.class).getBody();

	}

	public String createAuthorizeUri(String consentId) {
		return apiUtils.createAuthorizeUrl(consentId);
	}

	public OBReadDataResponse<OBReadAccountList> getAccountResponse(HttpRequestHeader httpRequestHeader) {

		return securedRestTemplate.exchange(apiUtils.getUri(ACCOUNT_LIST_ENDPOINT), HttpMethod.GET,
				apiUtils.createRequest(null, httpRequestHeader),
				new ParameterizedTypeReference<OBReadDataResponse<OBReadAccountList>>() {
				}).getBody();

	}

	public OBReadDataResponse<OBReadAccountList> getAccountById(String accountId, HttpRequestHeader httpRequestHeader) {

		return securedRestTemplate.exchange(apiUtils.getUri(ACCOUNT_ID_ENDPOINT), HttpMethod.GET,
				apiUtils.createRequest(null, httpRequestHeader),
				new ParameterizedTypeReference<OBReadDataResponse<OBReadAccountList>>() {
				}, accountId).getBody();

	}

	public OBReadDataResponse<OBReadBalanceList> getBalanceById(String accountId, HttpRequestHeader httpRequestHeader) {

		return securedRestTemplate.exchange(apiUtils.getUri(ACCOUNT_ID_BALANCES_ENDPOINT), HttpMethod.GET,
				apiUtils.createRequest(null, httpRequestHeader),
				new ParameterizedTypeReference<OBReadDataResponse<OBReadBalanceList>>() {
				}, accountId).getBody();
	}

	public OBReadDataResponse<OBReadTransactionList> getTransactionsById(String accountId,
			HttpRequestHeader httpRequestHeader) {

		return securedRestTemplate.exchange(apiUtils.getUri(ACCOUNT_ID_TRANSACTIONS_ENDPOINT), HttpMethod.GET,
				apiUtils.createRequest(null, httpRequestHeader),
				new ParameterizedTypeReference<OBReadDataResponse<OBReadTransactionList>>() {
				}, accountId).getBody();
	}

	public OBReadDataResponse<OBReadDirectDebitList> getDirectDebitsById(String accountId,
			HttpRequestHeader httpRequestHeader) {

		return securedRestTemplate.exchange(apiUtils.getUri(ACCOUNT_ID_DIRECT_DEBITS_ENDPOINT), HttpMethod.GET,
				apiUtils.createRequest(null, httpRequestHeader),
				new ParameterizedTypeReference<OBReadDataResponse<OBReadDirectDebitList>>() {
				}, accountId).getBody();

	}

	public OBReadDataResponse<OBReadStandingOrderList> getStandingOrdersById(String accountId,
			HttpRequestHeader httpRequestHeader) {

		return securedRestTemplate.exchange(apiUtils.getUri(ACCOUNT_ID_STANDING_ORDERS_ENDPOINT), HttpMethod.GET,
				apiUtils.createRequest(null, httpRequestHeader),
				new ParameterizedTypeReference<OBReadDataResponse<OBReadStandingOrderList>>() {
				}, accountId).getBody();

	}

	public OBReadDataResponse<OBReadProductList> getProductById(String accountId, HttpRequestHeader httpRequestHeader) {

		return securedRestTemplate.exchange(apiUtils.getUri(ACCOUNT_ID_PRODUCT_ENDPOINT), HttpMethod.GET,
				apiUtils.createRequest(null, httpRequestHeader),
				new ParameterizedTypeReference<OBReadDataResponse<OBReadProductList>>() {
				}, accountId).getBody();

	}

	public OBReadDataResponse<OBReadBeneficiaryList> getBeneficiariesById(String accountId,
			HttpRequestHeader httpRequestHeader) {

		return securedRestTemplate.exchange(apiUtils.getUri(ACCOUNT_ID_BENEFICIARIES_ENDPOINT), HttpMethod.GET,
				apiUtils.createRequest(null, httpRequestHeader),
				new ParameterizedTypeReference<OBReadDataResponse<OBReadBeneficiaryList>>() {
				}, accountId).getBody();

	}

	/**
	 * Extended from here on
	 */

	/**
	 * Custom Method Written to retrieve Sum of All Debits and Sum of All Credits
	 * 
	 * @param accountId
	 * @param httpRequestHeader
	 * @return Sum of All Debits and Credits from the accountId given
	 */
	public GetSumOfAllCreditsDebits getBalanceByIdSum(String accountId, HttpRequestHeader httpRequestHeader) {
		float debitAmount = 0;
		float creditAmount = 0;
		OBReadDataResponse<OBReadBalanceList> result = getBalanceById(accountId, httpRequestHeader);

		for (Iterator iterator = result.getData().getAccount().iterator(); iterator.hasNext();) {
			OBReadBalance balance = (OBReadBalance) iterator.next();

			if (balance.getCreditDebitIndicator().equals("Debit")) {
				System.out.println(balance.getAmount().getAmount());
				debitAmount = debitAmount + Float.parseFloat(balance.getAmount().getAmount());
			}
			if (balance.getCreditDebitIndicator().equals("Credit")) {
				System.out.println(balance.getAmount().getAmount());
				creditAmount = creditAmount + Float.parseFloat(balance.getAmount().getAmount());
			}
		}
		GetSumOfAllCreditsDebits returnObj = new GetSumOfAllCreditsDebits();
		returnObj.setSumAllCredits(debitAmount);
		returnObj.setSumAllDebits(creditAmount);

		return returnObj;

	}

	/**
	 * Custom Method to retrieve Monthly Credits and Debits also categorised with year- MonthAndYear as Key
	 * @return
	 */
	public SummaryDebitsCreditMonthly getSumMonthlyDebitCredit(String accountId, HttpRequestHeader httpRequestHeader) {
		
		Set<String> collectUniqueMonthYear = new TreeSet<String>();
		SummaryDebitsCreditMonthly summaryDetails = new SummaryDebitsCreditMonthly();
		List<Details> detailsList = new ArrayList<Details>();
		
		
		try {
			double sumCreditAmount = 0;
			double sumDebitAmount = 0;
			
			OBReadDataResponse<OBReadTransactionList> transList = getTransactionsById(accountId, httpRequestHeader);
		
			/**
			 * Get All Credits Sums Monthly
			 */
			for (Iterator<OBReadTransaction> iterator = transList.getData().getTransactionList().iterator(); iterator
					.hasNext();) {
				OBReadTransaction obReadTransactionGetMonthlyDebit = (OBReadTransaction) iterator
						.next();
				Date date1= new SimpleDateFormat("yyyy-MM-dd")
						.parse(obReadTransactionGetMonthlyDebit.getBookingDateTime().substring(0, 10));
				String monthYearUniqueKey = (date1.getMonth() + 1) + "-" + (date1.getYear() + 1900);
				
				collectUniqueMonthYear.add(monthYearUniqueKey);
			}
			
			for (Iterator iterator = collectUniqueMonthYear.iterator(); iterator.hasNext();) {
				String monthOfYear = (String) iterator.next();
				System.out.println(monthOfYear);
				Details details = new Details();
				
				for (Iterator<OBReadTransaction> iterator2 = transList.getData().getTransactionList().iterator(); iterator2
						.hasNext();) {
					OBReadTransaction obReadTransactionGetMonthlyDebit = (OBReadTransaction) iterator2
							.next();
					Date date1= new SimpleDateFormat("yyyy-MM-dd")
							.parse(obReadTransactionGetMonthlyDebit.getBookingDateTime().substring(0, 10));
					String monthYearUniqueKey = (date1.getMonth() + 1) + "-" + (date1.getYear() + 1900);
					
					if (monthOfYear.equals(monthYearUniqueKey)) {
						if (obReadTransactionGetMonthlyDebit.getCreditDebitIndicator().equals("Debit")) {
							sumDebitAmount= sumDebitAmount
									+ Float.parseFloat(obReadTransactionGetMonthlyDebit.getAmount().getAmount());
							
							}
					else if (obReadTransactionGetMonthlyDebit.getCreditDebitIndicator().equals("Credit")) {
						sumCreditAmount= sumCreditAmount
								+ Float.parseFloat(obReadTransactionGetMonthlyDebit.getAmount().getAmount());
						
					}
						
					}
				}
				details.setMonthYear(monthOfYear);
				details.setSumCredits(sumCreditAmount);
				details.setSumDebits(sumDebitAmount);
				detailsList.add(details);
				
			}
			summaryDetails.setDetails(detailsList);
			return summaryDetails;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return summaryDetails;}
}