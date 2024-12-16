package com.devDaph.todo.list.controller;

import com.devDaph.todo.list.model.TodoItem;
import com.devDaph.todo.list.model.Category; // Assuming you have Category entity
import com.devDaph.todo.list.repo.TodoRepo;
import com.devDaph.todo.list.repo.CategoryRepo; // Assuming you have CategoryRepo
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "tasks")
public class TodoController {

    @Autowired
    private TodoRepo todoRepo;

    @Autowired
    private CategoryRepo categoryRepo;  // Inject CategoryRepo to verify categories

    @GetMapping
    public List<TodoItem> findAll() {
        return todoRepo.findAll();
    }

    @PostMapping
    public TodoItem save(@Valid @RequestBody TodoItem todoItem) {
        // Validate category exists
        Category category = categoryRepo.findById(todoItem.getCategory().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not found"));

        todoItem.setCategory(category);
        return todoRepo.save(todoItem);
    }

    @PutMapping
    public TodoItem update(@Valid @RequestBody TodoItem todoItem) {
        // Check if TodoItem exists before updating
        if (!todoRepo.existsById(todoItem.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TodoItem not found");
        }

        // Validate category exists
        Category category = categoryRepo.findById(todoItem.getCategory().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not found"));

        todoItem.setCategory(category);
        return todoRepo.save(todoItem);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        if (!todoRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TodoItem not found");
        }
        todoRepo.deleteById(id);
    }
}
