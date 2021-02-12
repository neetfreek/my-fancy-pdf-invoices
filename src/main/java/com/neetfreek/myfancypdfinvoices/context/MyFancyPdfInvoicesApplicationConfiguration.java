/*
 * Jonathan Widdowson
 * Neetfreek, 2021
 */

/*
 * Explicit JavaConfig for Spring's ApplicationContext
 *      @ComponentScan finds @Component, @Component specialisation classes in basePackage
 *          - UserService
 *          - InvoiceService
 */

package com.neetfreek.myfancypdfinvoices.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neetfreek.myfancypdfinvoices.ApplicationLauncher;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackageClasses = ApplicationLauncher.class)
@PropertySource("classpath:/application.properties")
public class MyFancyPdfInvoicesApplicationConfiguration {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
