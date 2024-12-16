package com.devDaph.todo.list.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Use IDENTITY for auto-incremented values
    private int id;

    private String name;

    // Default constructor
    public Category() {}

    // Parameterized constructor
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter and Setter for id
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
