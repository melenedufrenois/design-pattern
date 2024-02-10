package com.fges.todoapp.Commands;

import com.fges.todoapp.Options.OptionsDone;
import com.fges.todoapp.Options.OptionsParser;

import java.io.IOException;
import java.nio.file.Path;

public abstract class Command {
    public String cmd;
    public OptionsParser opt;
    public String fileContent;
    public Path filePath;
    public OptionsDone optionsDone;

    public Command(String cmd, OptionsParser opt, String fileContent, Path filePath) {
        this.cmd = cmd;
        this.opt = opt;
        this.fileContent = fileContent;
        this.filePath = filePath;
    }

    public boolean isCommand() {
        return support().equals(cmd);
    }

    public abstract String support();

    public abstract void exec() throws IOException;
}