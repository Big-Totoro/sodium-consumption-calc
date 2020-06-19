package io.sskuratov.sodiumconsumptioncalc.dao;

import lombok.Data;

@Data
public class User {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String username;
    private Integer updateId;

    public User() {
    }

    public User(Integer userId, String username, String firstName, String lastName, Integer updateId) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.updateId = updateId;
    }
}
