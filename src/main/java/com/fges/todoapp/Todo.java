package com.fges.todoapp;

import java.io.IOException;
import java.nio.file.Path;

import com.fges.todoapp.Commands.WebCommand;

public class Todo {

    private String name;
    private boolean done;
    private final WebCommand webCommand;

    public Todo(WebCommand webCommand) {
        this.webCommand = webCommand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getDescription(Path filePath) throws IOException {
        String fileContent = webCommand.getFileContent(filePath);
        String[] lines = fileContent.split(System.lineSeparator());
        if (lines.length > 0) {
            return lines[0];
        }
        return "";
    }
}