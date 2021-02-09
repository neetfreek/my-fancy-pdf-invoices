/*
 * Jonathan Widdowson
 * Neetfreek, 2021
 */

/*
 * Service for actions related to User domain entities
 *    @Component scanned by MyFancyPdfInvoicesApplicationConfiguration to create singleton @Bean
 */

package com.neetfreek.myfancypdfinvoices.service;

import com.neetfreek.myfancypdfinvoices.model.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserService {

    public User findById(String id) {
        //TODO: Implement fetch real User from DB
        return new User(id, UUID.randomUUID().toString());
    }
}

