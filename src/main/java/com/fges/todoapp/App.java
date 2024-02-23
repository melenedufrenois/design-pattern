package com.fges.todoapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.fges.todoapp.Commands.*;
import com.fges.todoapp.Options.OptionsParser;
import org.apache.commons.cli.ParseException;

public class App {
    public static void main(String[] args) throws Exception {
        System.exit(exec(args));
    }

    public static int exec(String[] args) throws IOException {
        OptionsParser optParser;
        try {
            optParser = new OptionsParser(args);
        } catch (ParseException e) {
            throw new RuntimeException(e);
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
            case "insert" -> cmd = new InsertCommand(command, optParser, fileContent, filePath);
            case "list" -> cmd = new ListCommand(command, optParser, fileContent);
            case "migrate"-> cmd = new MigrateCommand(command, optParser, fileContent, filePath);
            default -> {
                System.err.println("Unknown command");
                return 1;
            }
        }

        cmd.exec();
        try {
            fileContent.getCommand();
        }catch (Exception e){
            System.out.println("Impossible to execute the command");
            System.out.println(e);
        }finally{
            System.err.println("Done.");
        }
        return 0;
    }
}