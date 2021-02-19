/*
 * Jonathan Widdowson
 * Neetfreek, 2021
 */

package com.neetfreek.myfancypdfinvoices.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyFirstSpringController {

    @GetMapping("/")
    @ResponseBody
    public String index(){
        return "<html lang=\"en-GB\">\n" +
                "<body>\n" +
                "<h1>Hello World v2</h1>\n" +
                "<p>This is my first embedded Tomcat HTML page!</p>\n" +
                "<p>Now improved with @controllers instead of httpServlets :)</p>\n" +
                "</body>\n" +
                "</html>";
    }
}
