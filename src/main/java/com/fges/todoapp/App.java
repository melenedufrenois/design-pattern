package com.fges.todoapp;

import com.fges.todoapp.Commands.*;
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
        System.exit(exec(args)).
    }

    public static int exec(String[] args) throws IOException {
        OptionsParser optParser;
        try {
            optParser = new OptionsParser(args);
        } catch (org.apache.commons.cli.ParseException e) {
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
            case "insert":
                cmd = new InsertCommand(command, optParser, fileContent, filePath);
                break;
            case "list":
                TodoDataSource dataSource = createTodoDataSource();
                ListCommand cmdlist = new ListCommand(command, optParser, fileContent, dataSource);
                cmd = cmdlist;
                break;
            default:
                System.err.println("Unknown command");
                return 1;
        }

        cmd.exec();

        System.err.println("Done.");
        return 0;
    }

    private static TodoDataSource createTodoDataSource() {
        String configFilePath = "config.txt";
        String dataSourceType = readDataSourceType(configFilePath);

        switch (dataSourceType) {
            case "file":
                String filePath = readFilePathFromConfig(configFilePath);
                return new FileTodoDataSource(Paths.get(filePath));
            default:
                throw new IllegalArgumentException("Invalid data source type specified in configuration: " + dataSourceType);
        }
    }

    private static String readDataSourceType(String configFilePath) {
        // Implement logic to read data source type from the configuration file
        // Example: Read the first line of the file
    }

    private static String readFilePathFromConfig(String configFilePath) {
        // Implement logic to read file path for file-based data source from the configuration file
    }

}