/*
 * Jonathan Widdowson
 * Neetfreek, 2021
 */

/*
 * Domain Entity representing users
 */

package com.neetfreek.myfancypdfinvoices.model;

public class User {

    public User() {
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
