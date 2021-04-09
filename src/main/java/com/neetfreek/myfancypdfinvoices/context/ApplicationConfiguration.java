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

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackageClasses = ApplicationLauncher.class)
@PropertySource("classpath:/application.properties")
@PropertySource(value = "classpath:/application-${spring.profiles.active}.properties", ignoreResourceNotFound = true)
@EnableWebMvc
public class ApplicationConfiguration {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    // Creates, configures embedded DBs
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public DataSource dataSource() {
        JdbcDataSource jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL("jdbc:h2:~/programming/h2/my-fancy-invoices;INIT=RUNSCRIPT FROM " +
                "'classpath:schema.sql'");
        jdbcDataSource.setUser("sa");
        jdbcDataSource.setPassword("sa");
        return jdbcDataSource;
    }

    // Thread-safe class for multiple thread executions of SQL against DB
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    // Finds, renders Strings from @Controllers as HTML, XHTML files
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(templateEngine());
        thymeleafViewResolver.setOrder(1);
        thymeleafViewResolver.setViewNames(new String[]{"*.html", "*.xhtml"});
        return thymeleafViewResolver;
    }

    // Configures, creates Thymeleaf-specific template engine
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(resourceTemplateResolver());
        return springTemplateEngine;
    }

    // Finds Thymeleaf templates
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public SpringResourceTemplateResolver resourceTemplateResolver() {
        SpringResourceTemplateResolver springResourceTemplateResolver = new SpringResourceTemplateResolver();
        springResourceTemplateResolver.setPrefix("classpath:/templates/"); // src/main/resources during development
        springResourceTemplateResolver.setCacheable(false); // for development, not production
        return springResourceTemplateResolver;
    }
}
