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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class InvoiceService {


    private final String cdnUrl;
    private final JdbcTemplate jdbcTemplate;
    private final UserService userService;

    @Autowired
    public InvoiceService(@Value("${cdn.url}") String cdnUrl, JdbcTemplate jdbcTemplate, UserService userService) {
        this.cdnUrl = cdnUrl;
        this.userService = userService;
        this.jdbcTemplate = jdbcTemplate;
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
        return jdbcTemplate.query("SELECT id, user_id , pdf_url, amount FROM invoices", (resultSet, rowNum) -> {
            Invoice invoice = new Invoice();
            invoice.setId(resultSet.getObject("id").toString());
            invoice.setUserId(resultSet.getObject("user_id").toString());
            invoice.setPdfUrl(resultSet.getObject("pdf_url").toString());
            invoice.setAmount(resultSet.getInt("amount"));
            return invoice;
        });
    }

    public Invoice create(String userId, Integer amount) {
        String generatedPdfUrl = cdnUrl + "/images/default/sample.pdf";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO invoices (user_id, pdf_url, amount) VALUES (?, ?, ?)",
                            PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, generatedPdfUrl);
            preparedStatement.setInt(3, amount);
            return preparedStatement;
        }, keyHolder);

        String uuid = !Objects.requireNonNull(keyHolder.getKeys()).isEmpty()
                ? (keyHolder.getKeys().values().iterator().next()).toString()
                : null;

        Invoice invoice = new Invoice();
        invoice.setUserId(userId);
        invoice.setId(uuid);
        invoice.setPdfUrl(generatedPdfUrl);
        invoice.setAmount(amount);
        return invoice;
    }
}
