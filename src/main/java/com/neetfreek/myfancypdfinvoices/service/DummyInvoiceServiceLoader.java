/*
 * Jonathan Widdowson
 * Neetfreek, 2021
 */

package com.neetfreek.myfancypdfinvoices.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Profile("dev")
public class DummyInvoiceServiceLoader {

    private final InvoiceService invoiceService;

    public DummyInvoiceServiceLoader(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostConstruct
    public void setup() {
        System.out.println("Create dummy invoices for dev environment...");
        invoiceService.create("Bob", 2);
        invoiceService.create("Obo", 100);
    }
}
