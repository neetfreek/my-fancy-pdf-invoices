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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class InvoiceService {

    private final List<Invoice> invoices = new CopyOnWriteArrayList<>();

    private final String cdnUrl;
    private final UserService userService;

    @Autowired
    public InvoiceService(@Value("${cdn.url}") String cdnUrl, UserService userService) {
        this.cdnUrl = cdnUrl;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        System.out.println("Dummy download of PDF template from server...");
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("Dummy delete downloaded PDF template...");
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
        Invoice invoice = new Invoice(userId, cdnUrl + "/images/default/sample.pdf", amount);
        invoices.add(invoice);
        return invoice;
    }
}
