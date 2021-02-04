/*
 * Jonathan Widdowson
 * Neetfreek, 2021
 */

/*
 * Service for actions related to Invoice domain entities
 */


package com.neetfreek.myfancypdfinvoices.service;

import com.neetfreek.myfancypdfinvoices.model.Invoice;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InvoiceService {

    List<Invoice> invoices = new CopyOnWriteArrayList<>();

    public List<Invoice> findAllInvoices() {
        return invoices;
    }

    public Invoice create(String userId, Integer amount) {
        //TODO: Implement create real PDF, store on server, use link in constructor
        Invoice invoice = new Invoice(userId, "http://africau.edu/images/default/sample.pdf", amount);
        invoices.add(invoice);

        return invoice;
    }
}
