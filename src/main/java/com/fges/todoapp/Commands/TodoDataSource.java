package com.fges.todoapp.Commands;

import java.util.List;

public interface TodoDataSource {
    List<Todo> getAllTodos();
    List<Todo> getDoneTodos();
}