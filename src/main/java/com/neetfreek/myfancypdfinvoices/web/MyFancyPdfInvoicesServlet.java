/*
 * Jonathan Widdowson
 * Neetfreek, 2021
 */

/*
 * Handle serving requests to server
 */

package com.neetfreek.myfancypdfinvoices.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neetfreek.myfancypdfinvoices.model.Invoice;
import com.neetfreek.myfancypdfinvoices.service.InvoiceService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MyFancyPdfInvoicesServlet extends HttpServlet {

    private final InvoiceService invoiceService = new InvoiceService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Write HTML greeting page to response for GET request for root endpoint
        if (request.getRequestURI().equalsIgnoreCase("/")) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print(
                    "<html lang=\"en-GB\">\n" +
                            "<body>\n" +
                            "<h1>Hello World</h1>\n" +
                            "<p>This is my first embedded Tomcat HTML page!</p>\n" +
                            "</body>\n" +
                            "</html>");
        } else if (request.getRequestURI().equalsIgnoreCase("/invoices")) {
            response.setContentType("application/json; charset=UTF-8");
            List<Invoice> invoices = invoiceService.findAllInvoices();
            response.getWriter().print(objectMapper.writeValueAsString(invoices));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Write JSON invoice to response for POST request using request parameters
        if (request.getRequestURI().equalsIgnoreCase("/invoices")) {

            String userId = request.getParameter("user_id");
            Integer amount = Integer.valueOf(request.getParameter("amount"));

            Invoice invoice = invoiceService.create(userId, amount);

            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().print(objectMapper.writeValueAsString(invoice));
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
