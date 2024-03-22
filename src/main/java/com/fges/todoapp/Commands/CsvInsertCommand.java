package com.fges.todoapp.Commands;

import com.fges.todoapp.Options.OptionsParser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CsvInsertCommand extends Command {

    public CsvInsertCommand(String cmd, OptionsParser opt, String fileContent, Path filePath) throws IOException {
        super(cmd, opt, fileContent, filePath);
    }

    public void exec() throws IOException {
        if (opt.getPositionArgs().size() < 2) {
            System.err.println("Missing TODO name");
            return;
        }
        String todo = opt.getPositionArgs().get(1);

        if (!fileContent.endsWith("\n") && !fileContent.isEmpty()) {
            fileContent += "\n";
        }
        fileContent += todo;

        Files.writeString(filePath, fileContent);
    }

    @Override
    public String support() {
        return "insert";
    }
}
