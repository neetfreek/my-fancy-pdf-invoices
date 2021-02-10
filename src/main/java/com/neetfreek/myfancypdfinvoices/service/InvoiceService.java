/*
 * Jonathan Widdowson
 * Neetfreek, 2021
 */

/*
 * Service for actions related to Invoice domain entities
 *    @Component scanned by MyFancyPdfInvoicesApplicationConfiguration to create singleton @Bean
 *    Explicit @Autowired on constructor to specify required constructor for Spring
 *      - Included just for clarity; Spring would use the only constructor to pass @Bean dep automatically
 */

package com.neetfreek.myfancypdfinvoices.service;

import com.neetfreek.myfancypdfinvoices.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class InvoiceService {

    @Autowired
    public InvoiceService(UserService userService) {
        this.userService = userService;
    }

    private final List<Invoice> invoices = new CopyOnWriteArrayList<>();

    private final UserService userService;

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
