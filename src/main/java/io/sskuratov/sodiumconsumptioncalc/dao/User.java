package io.sskuratov.sodiumconsumptioncalc.dao;

import lombok.Data;

@Data
public class User {

    private Integer userId;
    private String username;
    private Integer updateId;

    public User() {
    }

    public User(Integer userId, String username, Integer updateId) {
        this.userId = userId;
        this.username = username;
        this.updateId = updateId;
    }
}
