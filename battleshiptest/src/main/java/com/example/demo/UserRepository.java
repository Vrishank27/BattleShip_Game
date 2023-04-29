package com.example.demo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    @Query("{'username': ?0, 'password': ?1}")
    public User findByUsernameAndPassword(String username, String password);
}