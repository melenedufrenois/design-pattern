package com.fges.todoapp.Commands;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fges.todoapp.Options.OptionsParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class InsertCommand extends Command {

    public InsertCommand(String cmd, OptionsParser opt, String fileContent, Path filePath) throws IOException {
        super(cmd, opt, fileContent, filePath);
    }

    public void exec() throws IOException {
        if (opt.getPositionArgs().size() < 2) {
            System.err.println("Missing TODO name");
            return;
        }
        String todo = opt.getPositionArgs().get(1);

        if (opt.getFileName().endsWith(".json")) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(fileContent);
            if (actualObj instanceof MissingNode) {
                actualObj = JsonNodeFactory.instance.arrayNode();
            }
            if (actualObj instanceof ArrayNode arrayNode) {
                arrayNode.add(todo);
            }
            Files.writeString(filePath, actualObj.toString());
        }
        if (opt.getFileName().endsWith(".csv")) {
            if (!fileContent.endsWith("\n") && !fileContent.isEmpty()) {
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
