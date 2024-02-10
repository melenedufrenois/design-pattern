package com.fges.todoapp.Commands;

import com.fges.todoapp.Commands.TodoDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileTodoDataSource implements TodoDataSource {
    private Path filePath;

    public FileTodoDataSource(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Todo> getAllTodos() {
        List<Todo> allTodos = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                String[] parts = line.split(","); // Assuming todo data is comma-separated
                String description = parts[0];
                boolean done = Boolean.parseBoolean(parts[1]);
                allTodos.add(new Todo(description, done));
            }
        } catch (IOException e) {
            // Handle file read error
            e.printStackTrace();
        }
        return allTodos;
    }

    @Override
    public List<Todo> getDoneTodos() {
        List<Todo> doneTodos = new ArrayList<>();
        List<Todo> allTodos = getAllTodos();
        for (Todo todo : allTodos) {
            if (todo.isDone()) {
                doneTodos.add(todo);
            }
        }
        return doneTodos;
    }
}
