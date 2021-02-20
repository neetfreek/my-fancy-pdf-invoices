/*
 * Jonathan Widdowson
 * Neetfreek, 2021
 */

/*
 * Domain transfer object modelling JSON objects send in HTTP POST requests to create new invoices
 */

package com.neetfreek.myfancypdfinvoices.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InvoiceDto {

    private Integer amount;
    @JsonProperty("user_id")
    private String userId;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
