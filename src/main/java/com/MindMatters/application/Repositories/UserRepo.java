package com.MindMatters.application.Repositories;

import com.MindMatters.application.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

}
