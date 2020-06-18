package io.sskuratov.sodiumconsumptioncalc.dao;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String id;

    @Indexed
    private Integer userId;
    private String firstName;
    private String lastName;
    private String username;
    private Integer updateId;

    public User(String id, Integer userId, String username, String firstName, String lastName, Integer updateId) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.updateId = updateId;
    }
}
