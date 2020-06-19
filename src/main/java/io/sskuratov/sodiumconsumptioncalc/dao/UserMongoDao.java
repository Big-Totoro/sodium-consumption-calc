package io.sskuratov.sodiumconsumptioncalc.dao;


import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UserMongoDao implements UserDao {

    private final MongoCollection<Document> collection;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public UserMongoDao() {
        this.collection = MongoDbHolder.getInstance().getCollection("Users");
    }

    @Override
    public Optional<User> findUserByUserId(Integer userId) {
        Lock readLock = readWriteLock.readLock();

        try {
            FindIterable<Document> resultSet = collection.find(new Document().append("_id", userId));
            Document user = resultSet.first();
            if (user != null) {
                return Optional.of(toUser(user));
            }
            return Optional.empty();
        } finally {
            readLock.unlock();
        }
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
    public void save(User user) {
        Lock lock = readWriteLock.writeLock();
        try {
            collection.insertOne(toDocument(user));
        } finally {
            lock.unlock();
        }
    }

    public Document toDocument(User user) {
        Document documentTweet = new Document("_id", user.getUserId())
                .append("username", user.getUsername())
                .append("firstname", user.getFirstName())
                .append("lastname", user.getLastName())
                .append("userId", user.getUpdateId());
        return documentTweet;
    }
}
