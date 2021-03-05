/*
 * Jonathan Widdowson
 * Neetfreek, 2021
 */

/*
 * @Controller on class applies to all methods, designating them as returning HTML to client
 * @GetMapping("/[endpoint]") shorthand for @RequestMapping(value = "/[endpoint]", method = RequestMethod.GET)
 */


package com.neetfreek.myfancypdfinvoices.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebsiteController {

    @GetMapping("/")
    public String homepage() {
        return "index.html";
    }
}
