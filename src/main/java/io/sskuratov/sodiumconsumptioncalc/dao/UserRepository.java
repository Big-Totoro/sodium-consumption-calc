package io.sskuratov.sodiumconsumptioncalc.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    @Query("{ 'userId' : {$regex: ?0, $options: 'i'} }")
    Optional<User> findUserByUserId(Integer userId);
}
