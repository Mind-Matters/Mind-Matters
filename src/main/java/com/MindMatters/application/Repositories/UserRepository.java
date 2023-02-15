package com.MindMatters.application.Repositories;

import com.MindMatters.application.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findById(long id);

    List<User> findByIsVerified(boolean isVerified);

    List<User> findByIsProviderAndIsVerified(boolean isProvider, boolean isVerified);

    List<User> findByIsProvider(boolean isProvider);

    List<User> findByProviderId(long providerId);

    void deleteByUsername(String username);

    List<User> findByIsProviderAndProviderId(boolean b, long b1);
}
