package com.devDaph.todo.list.repo;

import com.devDaph.todo.list.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
