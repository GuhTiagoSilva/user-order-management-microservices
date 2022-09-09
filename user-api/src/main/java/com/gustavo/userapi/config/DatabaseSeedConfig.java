package com.gustavo.userapi.config;

import com.gustavo.userapi.entities.User;
import com.gustavo.userapi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
@AllArgsConstructor
public class DatabaseSeedConfig {

    private final UserRepository userRepository;

    @Bean
    @Transactional
    public void databaseSeeding() {

        User alex = new User(
                "e9ef7e9c-02fc-4ade-a247-147abd44dba6",
                "Alex",
                "Green",
                "alex@gmail.com",
                "12345678",
                "767.230.620-00",
                LocalDateTime.now(), null);

        User bob = new User(
                "5ef75b89-c390-4541-a694-e4a73f3c1f16",
                "Bob",
                "Brown",
                "bob@gmail.com",
                "12345678",
                "729.975.030-94",
                LocalDateTime.now(),
                null);

        User maria = new User(
                "df23740c-4e8b-41c1-a06a-8321764dbe06",
                "Maria",
                "Red",
                "maria@gmail.com",
                "12345678",
                "882.884.470-15",
                LocalDateTime.now(),
                null);

        List<User> users = new ArrayList<>();
        users.add(alex);
        users.add(bob);
        users.add(maria);

        userRepository.saveAll(users);
    }

}
