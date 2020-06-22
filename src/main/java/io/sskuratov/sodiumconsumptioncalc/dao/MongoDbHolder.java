package io.sskuratov.sodiumconsumptioncalc.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import io.sskuratov.sodiumconsumptioncalc.Config;

public class MongoDbHolder {
    private static MongoDatabase instance = null;

    private MongoDbHolder() {
    }

    public static MongoDatabase getInstance() {
        if (instance == null) {
            MongoClient mongoClient = new MongoClient(new MongoClientURI(Config.MONGO_DB_URL));
            instance = mongoClient.getDatabase(Config.DATABASE_NAME);
        }
        return instance;
    }
}
