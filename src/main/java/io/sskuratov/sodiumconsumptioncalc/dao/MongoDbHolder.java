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
            String dbName = Config.MONGO_DB_URL.substring(Config.MONGO_DB_URL.lastIndexOf("/") + 1);
            instance = mongoClient.getDatabase("sodium-consumption-calc");
        }
        return instance;
    }
}
