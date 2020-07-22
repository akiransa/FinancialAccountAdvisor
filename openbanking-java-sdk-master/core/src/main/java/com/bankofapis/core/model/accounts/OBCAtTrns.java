/* New clas to categorize transacctions*/
package com.bankofapis.core.model.accounts;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OBCAtTrns {

    @JsonProperty("Amount")
    private OBReadAmount amount;
    private String Category;

    public OBReadAmount getAmount() {
        return amount;
    }

    public void setAmount(OBReadAmount amount) {
        this.amount = amount;
    }

    public String getCategory(){

        return Category;}

    public void setCategory(String transactionInformation) {

        String tmp_Txn = transactionInformation;
        if (tmp_Txn.contains("Standing order"))
        {
            this.Category = "Regular Payments";
        }
        else if (tmp_Txn.contains("Pizz"))
        {
            this.Category = "Dining";
        }
        else if (tmp_Txn.contains("Amazon"))
        {
            this.Category = "Amazon";
        }
        else
        {
            this.Category = "HouseHold Expense";
        }
     //   System.out.println("setCategory:"+Category+ " : "+tmp_Txn);
    }
}