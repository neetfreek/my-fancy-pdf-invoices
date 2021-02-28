/*
 * Jonathan Widdowson
 * Neetfreek, 2021
 */

/*
 * @RestController (i.e. @Controller and @ResponseBody) on class applies to all methods, designating them as returning
 *      JSON, XML, and plain text in response body to client
 *      - Internally handles request URI parsing JSON conversion
 *      - The implicit inclusion of @ResponseBody means no HTML templating framework is used for responses
 * @GetMapping("/[endpoint]") shorthand for @RequestMapping(value = "/[endpoint]", method = RequestMethod.GET)
 */

package com.neetfreek.myfancypdfinvoices.web;

import com.neetfreek.myfancypdfinvoices.dto.InvoiceDto;
import com.neetfreek.myfancypdfinvoices.model.Invoice;
import com.neetfreek.myfancypdfinvoices.service.InvoiceService;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InvoicesController {

    private final InvoiceService invoiceService;

    public InvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/")
    public String index() {
        return "<html lang=\"en-GB\">\n" +
                "<body>\n" +
                "<h1>Hello World v2</h1>\n" +
                "<p>This is my first embedded Tomcat HTML page!</p>\n" +
                "<p>Now improved with @controllers instead of httpServlets :)</p>\n" +
                "</body>\n" +
                "</html>";
    }

    @GetMapping("/invoices")
    public List<Invoice> invoices() {
        return invoiceService.findAll();
    }

    @PostMapping("/invoices")
    public Invoice creatInvoice(@RequestBody @Valid InvoiceDto invoiceDto) {
        return invoiceService.create(invoiceDto.getUserId(), invoiceDto.getAmount());
    }
}
