/*
 * Jonathan Widdowson
 * Neetfreek, 2021
 */

/*
 * Explicit JavaConfig for Spring's ApplicationContext
 *      @ComponentScan finds @Component classes in basePackage (com.neetfreek.myfancypdfinvoices):
 *          - UserService
 *          - InvoiceService
 */

package com.neetfreek.myfancypdfinvoices.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neetfreek.myfancypdfinvoices.ApplicationLauncher;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackageClasses = ApplicationLauncher.class)
public class MyFancyPdfInvoicesApplicationConfiguration {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
