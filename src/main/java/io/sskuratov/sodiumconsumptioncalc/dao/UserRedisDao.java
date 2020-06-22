package io.sskuratov.sodiumconsumptioncalc.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class UserRedisDao implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserRedisDao.class);

    private final JedisPool pool;

    private final String USER_ID = "user_id";
    private final String USERNAME = "username";
    private final String FIRST_NAME = "first_name";
    private final String LAST_NAME = "last_name";
    private final String UPDATE_ID = "update_id";

    public UserRedisDao(JedisPool pool) {
        Objects.requireNonNull(pool);

        this.pool = pool;
    }

    @Override
    public synchronized Optional<User> findUserByUserId(Integer userId) {
        try (Jedis jedis = pool.getResource()) {
            if (jedis.exists(userId.toString())) {
                return Optional.ofNullable(fromMap(userId, jedis));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public synchronized void save(User user) {
        try (Jedis jedis = pool.getResource()) {
            jedis.hmset(user.getUserId().toString(), toMap(user));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public User fromMap(Integer userId, Jedis jedis) {
        User user = new User();

        user.setUserId(userId);
        user.setUsername(jedis.hget(userId.toString(), USERNAME));
        user.setFirstName(jedis.hget(userId.toString(), FIRST_NAME));
        user.setLastName(jedis.hget(userId.toString(), LAST_NAME));
        user.setUserId(Integer.valueOf(jedis.hget(userId.toString(), UPDATE_ID)));

        return user;
    }

    public Map<String, String> toMap(User user) {
        Map<String, String> map = new HashMap<>();

        map.put(USER_ID, user.getUserId().toString());
        map.put(USERNAME, user.getUsername());
        map.put(FIRST_NAME, user.getFirstName());
        map.put(LAST_NAME, user.getLastName());
        map.put(UPDATE_ID, user.getUpdateId().toString());

        return map;
    }
}
