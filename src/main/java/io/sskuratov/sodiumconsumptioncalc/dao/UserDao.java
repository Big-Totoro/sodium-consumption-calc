package io.sskuratov.sodiumconsumptioncalc.dao;

import java.util.Optional;

public interface UserDao {
    Optional<User> findUserByUserId(Integer userId);
    void save(User user);
}
