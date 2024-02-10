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
import java.util.List;
import java.util.stream.Collectors;

public class ListCommand extends Command {

    public TodoDataSource dataSource;

    public ListCommand(String cmd, OptionsParser opt, String fileContent, TodoDataSource dataSource) {
        super(cmd, opt, fileContent, null);
        this.dataSource = dataSource;
    }

    public void exec(){
        List<Todo> allTodos = dataSource.getAllTodos();
        List<Todo> doneTodos = dataSource.getDoneTodos();

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
        if (optionsDone != null && optionsDone.isDone()) {
            System.out.println("Done: " + doneTodos);
        } else {
            System.out.println("Todos: " + allTodos);
        }

        private List<Todo> getAllTodos() {
            List<Todo> allTodos = new ArrayList<>();

            allTodos.add(new Todo("Task 1", false));
            allTodos.add(new Todo("Task 2", true));
            allTodos.add(new Todo("Task 3", false));

            return allTodos;
        }

        private List<Todo> getDoneTodos() {
            List<Todo> doneTodos = new ArrayList<>();
            List<Todo> allTodos = getAllTodos();

            for (Todo todo : allTodos) {
                if (todo.isDone()) {
                    doneTodos.add(todo);
                }
            }

            return doneTodos;
        }
    }

    @Override
    public String support(){
        return "list";
    }
}
