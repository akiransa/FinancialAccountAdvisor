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
import java.util.Calendar;
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
	 * @return Sum of All Debits and Credits from all the accounts user has.
	 */
	public GetSumOfAllCreditsDebits getBalanceByIdSum(HttpRequestHeader httpRequestHeader) {
		float debitAmount = 0;
		float creditAmount = 0;
		OBReadDataResponse<OBReadAccountList> allAccountsList = getAccountResponse(httpRequestHeader);
		for (Iterator<OBReadAccountInformation> iterator = allAccountsList.getData().getAccount().iterator(); iterator
				.hasNext();) {

			String account = iterator.next().getAccountId();
			OBReadDataResponse<OBReadBalanceList> result = getBalanceById(account, httpRequestHeader);

			for (Iterator iterator1 = result.getData().getAccount().iterator(); iterator1.hasNext();) {
				OBReadBalance balance = (OBReadBalance) iterator1.next();

				if (balance.getCreditDebitIndicator().equals("Debit") && balance.getType().equals("InterimAvailable")) {
					debitAmount = debitAmount + Float.parseFloat(balance.getAmount().getAmount());

				}
				if (balance.getCreditDebitIndicator().equals("Credit")
						&& balance.getType().equals("InterimAvailable")) {
					creditAmount = creditAmount + Float.parseFloat(balance.getAmount().getAmount());
				}
			}

		}

		GetSumOfAllCreditsDebits returnObj = new GetSumOfAllCreditsDebits();
		returnObj.setSumAllCredits(creditAmount);
		returnObj.setSumAllDebits(debitAmount);

		return returnObj;

	}

//	public SummaryDebitsCreditMonthly getSumMonthlyDebitCredit(HttpRequestHeader httpRequestHeader)
//	{
//		SummaryDebitsCreditMonthly summaryDetails = new SummaryDebitsCreditMonthly();
//		OBReadDataResponse<OBReadAccountList> allAccountsList = getAccountResponse(httpRequestHeader);
//		for (Iterator<OBReadAccountInformation> iterator = allAccountsList.getData().getAccount().iterator(); iterator.hasNext();) {
//			System.err.println(iterator.next().getAccountId());
//			
//			summaryDetails=getSumMonthlyDebitCredit(iterator.next().getAccountId(),httpRequestHeader);
//			
//		}
//	}
	/**
	 * Custom Method to retrieve Monthly Credits and Debits also categorised with
	 * year- MonthAndYear as Key
	 * 
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
				OBReadTransaction obReadTransactionGetMonthlyDebit = (OBReadTransaction) iterator.next();
				Date date1 = new SimpleDateFormat("yyyy-MM-dd")
						.parse(obReadTransactionGetMonthlyDebit.getBookingDateTime().substring(0, 10));
				String monthYearUniqueKey = (date1.getYear() + 1900 + "-" + (date1.getMonth() + 1));

				collectUniqueMonthYear.add(monthYearUniqueKey);
			}

			int detailsIndex = 0;
			
			for (Iterator iterator = collectUniqueMonthYear.iterator(); iterator.hasNext();) {
				String monthOfYear = (String) iterator.next();
				Details details = new Details();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
				
				String previousBookingDateAsString = "1900-01-01T00:00:00.000Z";
						
						
				Date previousBookingTime = format.parse(previousBookingDateAsString);
				double accountBalance = 0.0;
				
				for (Iterator<OBReadTransaction> iterator2 = transList.getData().getTransactionList()
						.iterator(); iterator2.hasNext();) {
					OBReadTransaction obReadTransactionGetMonthlyDebit = (OBReadTransaction) iterator2.next();
					Date date1 = new SimpleDateFormat("yyyy-MM-dd")
							.parse(obReadTransactionGetMonthlyDebit.getBookingDateTime().substring(0, 10));

					String monthYearUniqueKey = (date1.getYear() + 1900 + "-" + (date1.getMonth() + 1));
					
					
					Date currentBookingTime = format.parse(obReadTransactionGetMonthlyDebit.getBookingDateTime());
					
					if (monthOfYear.equals(monthYearUniqueKey)) {
						if (obReadTransactionGetMonthlyDebit.getCreditDebitIndicator().equals("Debit")) {
							sumDebitAmount = sumDebitAmount
									+ Float.parseFloat(obReadTransactionGetMonthlyDebit.getAmount().getAmount());

						} else if (obReadTransactionGetMonthlyDebit.getCreditDebitIndicator().equals("Credit")) {
							sumCreditAmount = sumCreditAmount
									+ Float.parseFloat(obReadTransactionGetMonthlyDebit.getAmount().getAmount());

						}
						/// if booktime greater than current 
						if (previousBookingTime!=null) {
							System.err.println(previousBookingTime.compareTo(currentBookingTime));
							if(currentBookingTime.compareTo(previousBookingTime)>=0) { 
								accountBalance=Double.parseDouble(obReadTransactionGetMonthlyDebit.getBalance().getAmount().getAmount());
								System.err.println(accountBalance);
								System.out.println("reached here.. !atleast");
							}
						}
						else {
							previousBookingTime=currentBookingTime;
							System.err.println("reached here.. set booking time?");
						}

					}
					
					
				}

				details.setMonthAndYear(monthOfYear);
				details.setSumCredits(sumCreditAmount);
				details.setSumDebits(sumDebitAmount);
				details.setBalanceInThisMonth(sumCreditAmount - sumDebitAmount);
				if (detailsIndex != 0) {
					details.setBalanceInPrevMonth(detailsList.get(detailsIndex - 1).getBalanceInThisMonth());

				}
				details.setAccBalCurMonth(accountBalance);
				
				

//				double accountBalance = 0.0;
//				for (int i = 0; i < detailsList.size() + 1; i++) {
//					if (i != 0) {
//						accountBalance = detailsList.get(i - 1).getAccBalCurMonth() + sumCreditAmount - sumDebitAmount;
//					} else {
//						accountBalance = sumCreditAmount - sumDebitAmount;
//					}
//
//				}

				//details.setAccBalCurMonth(accountBalance);
				detailsList.add(details);

				detailsIndex = detailsIndex + 1;
			}

			summaryDetails.setDetails(detailsList);
			return summaryDetails;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return summaryDetails;
	}

	/**
	 * gets All Account Information
	 * 
	 * @param httpRequestHeader
	 * @return account name and Identification nos
	 */
	public AccountIdentificationDetails getAllAccountInfo(HttpRequestHeader httpRequestHeader) {
		// TODO Auto-generated method stub

		AccountIdentificationDetails accInfoDetails = new AccountIdentificationDetails();
		List<AccountInfo> acctInfor = new ArrayList<AccountInfo>();
		 

		OBReadDataResponse<OBReadAccountList> allAccountsList = getAccountResponse(httpRequestHeader);
		for (Iterator<OBReadAccountInformation> iterator = allAccountsList.getData().getAccount().iterator(); iterator
				.hasNext();) {
			List<OBReadAccount> acc= iterator.next().getAccount();
			
			for (int i = 0; i < acc.size(); i++) {
				AccountInfo actInfo= new AccountInfo();
				actInfo.setAccountIdentificationNumber(acc.get(i).getIdentification());
				actInfo.setAccountName((acc.get(i).getName()));
				acctInfor.add(actInfo);
			}
			

		}
		accInfoDetails.setAccountInfo(acctInfor);
		return accInfoDetails;
	}
}