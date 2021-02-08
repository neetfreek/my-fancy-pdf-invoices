/*
 * Jonathan Widdowson
 * Neetfreek, 2021
 */

/*
 * Explicit JavaConfig for Spring's ApplicationContext
 *      @Scope for @Beans specified as singleton scope (SCOPE_SINGLETON) for clarity
 */

package com.neetfreek.myfancypdfinvoices.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neetfreek.myfancypdfinvoices.service.InvoiceService;
import com.neetfreek.myfancypdfinvoices.service.UserService;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MyFancyPdfInvoicesApplicationConfiguration {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public UserService userService() {
        return new UserService();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public InvoiceService invoiceService(UserService userService) {
        return new InvoiceService(userService);
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
