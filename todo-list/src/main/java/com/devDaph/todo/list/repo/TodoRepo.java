package com.devDaph.todo.list.repo;

import com.devDaph.todo.list.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepo extends JpaRepository<TodoItem, Long> {
}
