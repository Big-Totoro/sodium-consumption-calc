package io.sskuratov.sodiumconsumptioncalc.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Optional;

public class UserMongoDao implements UserDao {

    private final MongoCollection<Document> collection;

    public UserMongoDao() {
        this.collection = MongoDbHolder.getInstance().getCollection("Users");
    }

    @Override
    public synchronized Optional<User> findUserByUserId(Integer userId) {
        FindIterable<Document> resultSet = collection.find(new Document().append("_id", userId));
        Document user = resultSet.first();
        if (user != null) {
            return Optional.of(toUser(user));
        }
        return Optional.empty();
    }

    public User toUser(Document document) {
        User user = new User();

        user.setUserId(document.getInteger("_id"));
        user.setUsername(document.getString("username"));
        user.setFirstName(document.getString("firstname"));
        user.setLastName(document.getString("lastname"));
        user.setUpdateId(Optional.ofNullable(document.getInteger("updateId")).orElse(-1));

        return user;
    }

    @Override
    public synchronized void save(User user) {
        collection.insertOne(toDocument(user));
    }

    public Document toDocument(User user) {
        return new Document("_id", user.getUserId())
                .append("username", user.getUsername())
                .append("firstname", user.getFirstName())
                .append("lastname", user.getLastName())
                .append("updateId", user.getUpdateId());
    }
}
