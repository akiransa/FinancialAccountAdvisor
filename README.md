# FinancialAccountAdvisor
FinancialAccountAdvisor as part of APIThon

Using the Existing APIs we were able to come up with the following endpoints:

ACCOUNT_LISTALL_ENDPOINT = "/accountsInfo";
-  This is for retrieving a subset of the Accounts resulting just the AccountIdentificationNumber and AccountNickName. This can be used to render display to user.

ACCOUNT_ID_BALANCESSUM_ENDPOINT = "/accounts/balances";

- Loop through all the accounts for which the user has provided Consent with and retrieves the Consolidated Balances of Credits and Debits.


ACCOUNT_ID_BALANCESSUM_MONTHLY_ENDPOINT ="/accounts/{accountId}/transactions/monthly";
- Loop through all the Transactions done by a user, for a individual account, categorised on  monthly basis 
-- Credits for the current Month
-- Debits for the current Month
-- Net Balance for the current Month
-- Balance in comparision to Previous Month
-- Account Balance for that Month


{
            "AccountNo": "da97d767-52ab-40cf-895c-5f1f6d663ec9",
            "MonthAndYear": "2018-10",
            "TotalCreditsCurMonth": 0.0,
            "TotalDebitsCurMonth": 322.13999938964844,
            "BalanceForThisMonth": -322.13999938964844,
            "BalanceForPrevMonth": 0.0,
            "AccBalCurrMonth": 19699.21
        }

ACCOUNT_ID_BALANCESSUM_MONTHLY_ENDPOINT_ALL ="/accounts/transactions/monthly";
- Loop through all the Accounts for which user has provided Consent with.
-- Again categorised by Month And Year, retrieves all the Debits and Credits , across all the accounts.

{
            "MonthAndYear": "2018-10",
            "AllDebits": 322.13999938964844,
            "AllCredits": 500.0,
            "AvailBalanceForCurMonth": 290760.26
        }


