/*
 * Jonathan Widdowson
 * Neetfreek, 2021
 */

/*
 * Handle serving requests to server
 */

package com.neetfreek;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyFancyPdfInvoicesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Write HTML greeting page to response for GET request for root endpoint
        if (request.getRequestURI().equalsIgnoreCase("/")) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print("<html lang=\"en-GB\">\n" +
                    "<body>\n" +
                    "<h1>Hello World</h1>\n" +
                    "<p>This is my first embedded Tomcat HTML page!</p>\n" +
                    "</body>\n" +
                    "</html>");
        }
        // Write empty json object to response for GET request for invoices endpoint
        else if (request.getRequestURI().equalsIgnoreCase("/invoices")) {
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().print("[]");
        }
    }
}

