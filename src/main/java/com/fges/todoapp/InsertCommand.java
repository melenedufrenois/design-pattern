package com.fges.todoapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class InsertCommand extends Command{

    public InsertCommand(String cmd, OptionsParser opt, String fileContent, Path filePath) throws IOException {
        super(cmd, opt, fileContent, filePath);
        exec();
    }

    public void exec() throws IOException {
        if (isCommand()){
            if (opt.getPositionArgs().size() < 2){
                System.out.println("Missing TODO name");
                return;
            }
            String todo = opt.getPositionArgs().get(1);
            if (!fileContent.endsWith("\n") && !fileContent.isEmpty()){
                fileContent += "\n";
            }
            fileContent += todo;
            Files.writeString(filePath, fileContent);
        }

    }

    @Override
    public String support() {
        return "insert";
    }
}
