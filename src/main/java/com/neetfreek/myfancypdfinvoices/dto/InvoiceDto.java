/*
 * Jonathan Widdowson
 * Neetfreek, 2021
 */

/*
 * Domain transfer object modelling JSON objects sent in HTTP POST requests to create new invoices
 */

package com.neetfreek.myfancypdfinvoices.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class InvoiceDto {

    private Integer amount;

    @JsonProperty("user_id")
    @NotBlank
    private String userId;

    @NotNull
    @Min(1)
    @Max(1999)
    public Integer getAmount() { return amount;}

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
