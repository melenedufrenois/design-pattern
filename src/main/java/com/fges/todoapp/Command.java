package com.fges.todoapp;

import java.io.IOException;
import java.nio.file.Path;

public abstract class Command {
    public String cmd;
    public OptionsParser opt;
    public String fileContent;
    public Path filePath;

    public Command(String cmd, OptionsParser opt, String fileContent, Path filePath) {
        this.cmd = cmd;
        this.opt = opt;
        this.fileContent = fileContent;
        this.filePath = filePath;
    }

    public boolean isCommand() {
        return "insert".equals(cmd) || "list".equals(cmd);
    }

    public abstract String support();

    public abstract void exec() throws IOException;
}
