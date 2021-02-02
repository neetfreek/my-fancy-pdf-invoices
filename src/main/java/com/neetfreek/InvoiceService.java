/*
 * Jonathan Widdowson
 * Neetfreek, 2021
 */

package com.neetfreek;

public class InvoiceService {

    public Invoice create(String userId, Integer amount) {
        //TODO: Implement create real PDF file, store on server, use link in constructor
        return new Invoice(userId, "http://africau.edu/images/default/sample.pdf", amount);
    }
}
