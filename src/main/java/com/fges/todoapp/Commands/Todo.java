package com.fges.todoapp.Commands;

public class Todo {
    private String description;
    private boolean done;

    public Todo(String description, boolean done) {
        this.description = description;
        this.done = done;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }
}
