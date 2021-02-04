/*
 * Jonathan Widdowson
 * Neetfreek, 2021
 */

/*
 * Application class pattern for poor man's dependency management
 *      Keep singleton instances of services for global use
 */
package com.neetfreek.myfancypdfinvoices.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neetfreek.myfancypdfinvoices.service.InvoiceService;

public class Application {

    public static final InvoiceService invoiceService = new InvoiceService();
    public static final ObjectMapper objectMapper = new ObjectMapper();
}
