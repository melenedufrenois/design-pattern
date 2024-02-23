package com.fges.todoapp.Commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fges.todoapp.Options.OptionsParser;

public class MigrateCommand extends Command {

    public MigrateCommand(String cmd, OptionsParser opt, String fileContent, Path filePath) {
        super(cmd, opt, fileContent, filePath);
    }

    @Override
    public void exec() throws IOException {
        String sourceFileName = opt.getFileName();
        String destinationFileName = opt.getDestFileName();

        // Load the content of the source file
        String sourceContent = Files.readString(filePath.resolve(sourceFileName));

        if (sourceFileName.endsWith(".json") && destinationFileName.endsWith(".json")) {
            // Migrate from JSON to JSON
            migrateJsonToJson(sourceContent);
        } else if (sourceFileName.endsWith(".json") && destinationFileName.endsWith(".csv")) {
            // Migrate from JSON to CSV
            migrateJsonToCsv(sourceContent);
        } else if (sourceFileName.endsWith(".csv") && destinationFileName.endsWith(".json")) {
            // Migrate from CSV to JSON
            migrateCsvToJson(sourceContent);
        } else if (sourceFileName.endsWith(".csv") && destinationFileName.endsWith(".csv")) {
            // Migrate from CSV to CSV
            migrateCsvToCsv(sourceContent);
        } else {
            System.err.println("Unsupported migration: " + sourceFileName + " --> " + destinationFileName);
        }
    }

    private void migrateJsonToJson(String sourceContent) throws IOException {
        // For JSON to JSON migration, simply rewrite the content as it is
        Files.writeString(filePath.resolve(opt.getDestFileName()), sourceContent);
    }

    private void migrateJsonToCsv(String sourceContent) throws IOException {
        // JSON to CSV migration: Convert JSON array elements to CSV rows
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode jsonArray = (ArrayNode) mapper.readTree(sourceContent);
        StringBuilder csvContent = new StringBuilder();
        for (int i = 0; i < jsonArray.size(); i++) {
            csvContent.append(jsonArray.get(i).asText());
            csvContent.append("\n");
        }
        Files.writeString(filePath.resolve(opt.getDestFileName()), csvContent.toString());
    }

    private void migrateCsvToJson(String sourceContent) throws IOException {
        // CSV to JSON migration: Split CSV rows and create a JSON array
        String[] csvLines = sourceContent.split("\n");
        ArrayNode jsonArray = JsonNodeFactory.instance.arrayNode();
        for (String line : csvLines) {
            jsonArray.add(line);
        }
        Files.writeString(filePath.resolve(opt.getDestFileName()), jsonArray.toString());
    }

    private void migrateCsvToCsv(String sourceContent) throws IOException {
        // For CSV to CSV migration, simply rewrite the content as it is
        Files.writeString(filePath.resolve(opt.getDestFileName()), sourceContent);
    }

    @Override
    public String support() {
        return "migrate";
    }
}
