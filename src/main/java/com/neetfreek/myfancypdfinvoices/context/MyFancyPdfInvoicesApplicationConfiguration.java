/*
 * Jonathan Widdowson
 * Neetfreek, 2021
 */

/*
 * Explicit JavaConfig for Spring's ApplicationContext
 * @ComponentScan finds @Component, @Component specialisation classes in basePackage
 *      - UserService
 *      - InvoiceService
 * @PropertySource overwritten to application-dev.properties if profile changed with e.g.
 *      -Dspring.profiles.active=dev
 * @EnableWebMvc ensures Spring WebMVC initialized with sane default configuration (including, for instance, JSON
 *      conversion from e.g. Invoices' CopyOnWriteArrayList<> to JSON provided jackson dependency in classpath
 */

package com.neetfreek.myfancypdfinvoices.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neetfreek.myfancypdfinvoices.ApplicationLauncher;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackageClasses = ApplicationLauncher.class)
@PropertySource("classpath:/application.properties")
@PropertySource(value = "classpath:/application-${spring.profiles.active}.properties", ignoreResourceNotFound = true)
@EnableWebMvc
public class MyFancyPdfInvoicesApplicationConfiguration {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
