package com.fges.todoapp;

import com.fges.todoapp.Commands.Command;
import com.fges.todoapp.Commands.InsertCommand;
import com.fges.todoapp.Commands.ListCommand;
import com.fges.todoapp.Options.OptionsDone;
import com.fges.todoapp.Options.OptionsParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        System.exit(exec(args));
    }

    public static int exec(String[] args) throws IOException {
        OptionsParser optParser;
        try {
            optParser = new OptionsParser(args);
        } catch (Exception ex) {
            System.err.println("Fail to parse arguments: " + ex.getMessage());
            return 1;
        }


        List<String> positionalArgs = optParser.getPositionArgs();
        if (positionalArgs.isEmpty()) {
            System.err.println("Missing Command");
            return 1;
        }

        String command = positionalArgs.get(0);
        Path filePath = Paths.get(optParser.getFileName());
        String fileContent = "";

        if (Files.exists(filePath)) {
            fileContent = Files.readString(filePath);
        }

        Command cmd;
        switch (command) {
            case "insert":
                cmd = new InsertCommand(command, optParser, fileContent, filePath);
                break;
            case "list":
                cmd = new ListCommand(command, optParser, fileContent, null);
                break;
            default:
                System.err.println("Unknown command");
                return 1;
        }

        cmd.exec();

        System.err.println("Done.");
        return 0;
    }
}