/*
 * Jonathan Widdowson
 * Neetfreek, 2021
 */

/*
 * @Controller on class applies to all methods, designating them as returning HTML to client
 * @GetMapping("/[endpoint]") shorthand for @RequestMapping(value = "/[endpoint]", method = RequestMethod.GET)
 */


package com.neetfreek.myfancypdfinvoices.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class WebsiteController {

    @GetMapping("/")
    public String homepage(Model model,
                           @RequestParam(required = false, defaultValue = ("${default.username}")) String username) {
        model.addAttribute("username", username);
        model.addAttribute("currentDate", new Date());
        return "index.html";
    }
}
