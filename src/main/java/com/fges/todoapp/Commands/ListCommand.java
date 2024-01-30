package com.fges.todoapp.Commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fges.todoapp.Options.OptionsDone;
import com.fges.todoapp.Options.OptionsParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ListCommand extends Command {

    public ListCommand(String cmd, OptionsParser opt, String fileContent, OptionsDone optionsDone) {
        super(cmd, opt, fileContent, null);
    }

    public void exec(){
        if (opt.getFileName().endsWith(".json")) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = null;
            try {
                actualObj = mapper.readTree(fileContent);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            if (actualObj instanceof MissingNode) {
                actualObj = JsonNodeFactory.instance.arrayNode();
            }
            if (actualObj instanceof ArrayNode arrayNode) {
                arrayNode.forEach(node -> System.out.println("- " + node.toString()));
            }
            return;
        }
        if (opt.getFileName().endsWith(".csv")) {
            System.out.println(Arrays.stream(fileContent.split("\n"))
                    .map(todo -> "- " + todo)
                    .collect(Collectors.joining("\n"))
            );
        }
    }

    @Override
    public String support(){
        return "list";
    }
}
