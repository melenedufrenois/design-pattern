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

        String sourceContent = Files.readString(filePath.resolve(sourceFileName));

        if (sourceFileName.endsWith(".json") && destinationFileName.endsWith(".json")) {
            migrateJsonToJson(sourceContent);
        } else if (sourceFileName.endsWith(".json") && destinationFileName.endsWith(".csv")) {
            migrateJsonToCsv(sourceContent);
        } else if (sourceFileName.endsWith(".csv") && destinationFileName.endsWith(".json")) {
            migrateCsvToJson(sourceContent);
        } else if (sourceFileName.endsWith(".csv") && destinationFileName.endsWith(".csv")) {
            migrateCsvToCsv(sourceContent);
        } else {
            System.err.println("Unsupported migration: " + sourceFileName + " --> " + destinationFileName);
        }
    }

    private void migrateJsonToJson(String sourceContent) throws IOException {
        Files.writeString(filePath.resolve(opt.getDestFileName()), sourceContent);
    }

    private void migrateJsonToCsv(String sourceContent) throws IOException {
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
        String[] csvLines = sourceContent.split("\n");
        ArrayNode jsonArray = JsonNodeFactory.instance.arrayNode();
        for (String line : csvLines) {
            jsonArray.add(line);
        }
        Files.writeString(filePath.resolve(opt.getDestFileName()), jsonArray.toString());
    }

    private void migrateCsvToCsv(String sourceContent) throws IOException {
        Files.writeString(filePath.resolve(opt.getDestFileName()), sourceContent);
    }

    @Override
    public String support() {
        return "migrate";
    }
}