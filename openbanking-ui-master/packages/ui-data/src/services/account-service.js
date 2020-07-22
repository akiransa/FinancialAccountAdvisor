import createRequest from './request'
import { setData } from '../actions/app'
import { setAccountId } from '../actions/account'

//accounts api's
export function getAccountList(dispatch) {
    createRequest(dispatch, '/aisp/accounts', 'GET', null, {}, function (
        response
    ) {
        //callback placeholder where one or multiple actions can be dispatched
        dispatch(setData(response))
        if (response.Data.Account.length > 0) {
            dispatch(setAccountId(response.Data.Account[0].AccountId))
        }
    })
}

// get account by id
export function getAccountById(dispatch, accountId) {
    createRequest(
        dispatch,
        `/aisp/accounts/${accountId}`,
        'GET',
        null,
        {},
        function (response) {
            dispatch(setData(response))
        }
    )
}

export function getAccountBalances(dispatch, accountId) {
    createRequest(
        dispatch,
        `/aisp/accounts/${accountId}/balances`,
        'GET',
        null,
        {},
        function (response) {
            dispatch(setData(response))
        }
    )
}

export function getAccountTransactions(dispatch, accountId) {
    createRequest(
        dispatch,
        `/aisp/accounts/${accountId}/transactions`,
        'GET',
        null,
        {},
        function (response) {
            dispatch(setData(response))
        }
    )
}

export function getAccountDirectDebits(dispatch, accountId) {
    createRequest(
        dispatch,
        `/aisp/accounts/${accountId}/direct-debits`,
        'GET',
        null,
        {},
        function (response) {
            dispatch(setData(response))
        }
    )
}

export function getAccountStandingOrders(dispatch, accountId) {
    createRequest(
        dispatch,
        `/aisp/accounts/${accountId}/standing-orders`,
        'GET',
        null,
        {},
        function (response) {
            dispatch(setData(response))
        }
    )
}

export function getAccountProducts(dispatch, params) {
    createRequest(dispatch, '/aisp/products', 'GET', params, {}, function (
        response
    ) {
        dispatch(setData(response))
    })
}
// Extended

export function getAllAccountInfo(dispatch) {
    createRequest(dispatch, '/aisp/accountsInfo', 'GET', null, {}, function (
        response
    ) {
        //callback placeholder where one or multiple actions can be dispatched
        dispatch(setData(response))
    })
}

export function getAccountBalancesSum(dispatch, params) {
    createRequest(
        dispatch,
        '/aisp/accounts/balances',
        'GET',
        params,
        {},
        function (response) {
            dispatch(setData(response))
        }
    )
}

export function getDebitsCreditMonthlyConsolidated(dispatch, params) {
    createRequest(
        dispatch,
        '/aisp/accounts/transactions/monthly',
        'GET',
        params,
        {},
        function (response) {
            dispatch(setData(response))
        }
    )
}
/**
export function getSumMonthlyDebitCredit(dispatch, accountId) {
    createRequest(
        dispatch,
        `/aisp/accounts/${accountId}/transactions/monthly`,
        'GET',
        params,
        {},
        function (response) {
            dispatch(setData(response))
            
            // if (response.Data.Account.length > 0) {
            //     dispatch(setAccountId(response.Data.Account[0].AccountId))
            // }
        }
    )
}

export function getCategorySumSpent(dispatch, accountId) {
    createRequest(
        dispatch,
        `/aisp/accounts/${accountId}/transaction_category/total`,
        'GET',
        params,
        {},
        function (response) {
            dispatch(setData(response))
            // if (response.Data.Account.length > 0) {
            //     dispatch(setAccountId(response.Data.Account[0].AccountId))
            // }
        }
    )
}

export function getMonthlyTransactionsById(dispatch, accountId) {
    createRequest(
        dispatch,
        `/aisp/accounts/${accountId}/transaction_category/monthly`,
        'GET',
        params,
        {},
        function (response) {
            dispatch(setData(response))
            // if (response.Data.Account.length > 0) {
            //     dispatch(setAccountId(response.Data.Account[0].AccountId))
            // }
        }
    )
}

 */
