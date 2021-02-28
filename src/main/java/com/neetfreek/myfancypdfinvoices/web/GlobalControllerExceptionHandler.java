/*
 * Jonathan Widdowson
 * Neetfreek, 2021
 */

/*
 * Exception handler for REST Controllers
 * @RestControllerAdvice:
 *      - Catch exceptions, react appropriately
 *      - Applies class to all @Controllers, @RestControllers
 *      - Writes JSON/XML, or anything else to @ResponseBody
 */


package com.neetfreek.myfancypdfinvoices.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    // Handle @RestController @RequestBody, @RequestParam validation errors
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        return String.format("Invalid request data: %s", e.getMessage());
    }

    // Handle Bean validation errors
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolation(ConstraintViolationException e) {
        return String.format("Invalid request data: %s", e.getMessage());
    }
}
