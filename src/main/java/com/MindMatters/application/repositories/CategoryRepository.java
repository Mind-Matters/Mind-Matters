package com.MindMatters.application.repositories;

import com.MindMatters.application.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
