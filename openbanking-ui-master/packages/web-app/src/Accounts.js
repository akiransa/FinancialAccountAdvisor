import React from 'react'
import { useDispatch, useSelector } from 'react-redux'
import {
    getAccountList,
    getAccountById,
    getAccountBalances,
    getAccountTransactions,
    getAccountDirectDebits,
    getAccountProducts,
    getAccountStandingOrders,
    getAccountBalancesSum,
    getDebitsCreditMonthlyConsolidated,
    getCategorySumSpent,
    getMonthlyTransactionsById,
    getAllAccountInfo,
    getSumMonthlyDebitCredit,
} from '@openbanking/ui-data/lib/services/account-service'
import InfoDisplay from '@openbanking/ui-common/lib/InfoDisplay'
import './Accounts.css'

//accounts api list
const Accounts = () => {
    const data = useSelector((state) => state.app.data)
    const accountId = useSelector((state) => state.account.accountId)

    const dispatch = useDispatch()

    return (
        <div className="mainContainer">
            <h2 className="pageTitle">AISP Information</h2>
            <div className="apiContainer">
                <div className="btnContainer">
                    <button
                        className="buttonLinks"
                        onClick={() => getAccountList(dispatch)}
                    >
                        Get Account List
                    </button>
                    <button
                        className="buttonLinks"
                        onClick={() => getAccountById(dispatch, accountId)}
                    >
                        Get Account By Id
                    </button>
                    <button
                        className="buttonLinks"
                        onClick={() => getAccountBalances(dispatch, accountId)}
                    >
                        Get Account Balances
                    </button>
                    <button
                        className="buttonLinks"
                        onClick={() =>
                            getAccountDirectDebits(dispatch, accountId)
                        }
                    >
                        Get Account Direct Debits
                    </button>
                    <button
                        className="buttonLinks"
                        onClick={() =>
                            getAccountTransactions(dispatch, accountId)
                        }
                    >
                        Get Account Transactions
                    </button>
                    <button
                        className="buttonLinks"
                        onClick={() => getAccountProducts(dispatch, accountId)}
                    >
                        Get Account Products
                    </button>
                    <button
                        className="buttonLinks"
                        onClick={() => (dispatch, accountId)}
                    >
                        Get Account Standing Orders
                    </button>
                    {/* Added functionalities */}
                    <button
                        className="buttonLinks"
                        onClick={() => getAllAccountInfo(dispatch)}
                    >
                        Get Consolidated Account Info
                    </button>

                    <button
                        className="buttonLinks"
                        onClick={() => getAccountBalancesSum(dispatch)}
                    >
                        Get Balance Consolidated across All Accounts
                    </button>

                    <button
                        className="buttonLinks"
                        onClick={() =>
                            getDebitsCreditMonthlyConsolidated(dispatch)
                        }
                    >
                        Get Transactions Consolidated across All Accounts -
                        Monthly
                    </button>

                    {/* <button
                        className="buttonLinks"
                        onClick={() =>
                            getSumMonthlyDebitCredit(dispatch, accountId)
                        }
                    >
                        Get Monthly Debits and Credits - Monthly
                    </button>
                    

                    <button
                        className="buttonLinks"
                        onClick={() => getCategorySumSpent(dispatch, accountId)}
                    >
                        Get Transaction Category
                    </button>



                    <button
                        className="buttonLinks"
                        onClick={() =>
                            getMonthlyTransactionsById(dispatch, accountId)
                        }
                    >
                        Get Transaction Category - Monthly
                    </button> */}
                </div>
                <div className="displayInfo">
                    <InfoDisplay data={data} />
                </div>
            </div>
        </div>
    )
}

export default Accounts
