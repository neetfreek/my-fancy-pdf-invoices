/*
 * Jonathan Widdowson
 * Neetfreek, 2021
 */

/*
 * Service for actions related to Invoice domain entities
 *    @Component scanned by MyFancyPdfInvoicesApplicationConfiguration to create singleton @Bean
 */

package com.neetfreek.myfancypdfinvoices.service;

import com.neetfreek.myfancypdfinvoices.model.Invoice;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class InvoiceService {

    private final UserService userService;

    private List<Invoice> invoices = new CopyOnWriteArrayList<>();

    public InvoiceService(UserService userService) {
        this.userService = userService;
    }

    public List<Invoice> findAll() {
        return invoices;
    }

    public Invoice create(String userId, Integer amount) {
        // Validate user exists
        if (userService.findById(userId) == null) {
            throw new IllegalStateException();
        }

        //TODO: Implement create real PDF, store on server, use link in constructor
        Invoice invoice = new Invoice(userId, "http://africau.edu/images/default/sample.pdf", amount);
        invoices.add(invoice);

        return invoice;
    }
}
