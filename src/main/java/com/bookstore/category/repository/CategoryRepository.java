package com.bookstore.category.repository;

import com.bookstore.category.domain.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    public List<Category> findByCategory(Category category);
    public List<Category> findByCategoryIsNull();
}
