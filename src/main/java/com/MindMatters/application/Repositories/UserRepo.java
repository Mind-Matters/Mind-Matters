package com.MindMatters.application.Repositories;

import com.MindMatters.application.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findById(long id);

    List<User> findByIsVerified(boolean isVerified);

    List<User> findByIsProviderAndIsVerified(boolean isProvider, boolean isVerified);

    List<User> findByIsProvider(boolean isProvider);


    void deleteByUsername(String username);
}
