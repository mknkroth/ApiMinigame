package com.minigame.apiminigame.Service;

import com.minigame.apiminigame.Repository.UserRepository;
import com.minigame.apiminigame.Model.User;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class DatabaseInitializer {
    @PersistenceContext
    private EntityManager entityManager;

    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        // Check if the table exists
        List<?> result = entityManager.createNativeQuery("SHOW TABLES LIKE 'item'").getResultList();
        if (result.isEmpty()) {
            // The table does not exist, so we can create it
            // Note: This is just a demonstration. Normally, you would rely on Hibernate to manage schema.
            entityManager.createNativeQuery("CREATE TABLE item (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255))").executeUpdate();
        }
    }
}
