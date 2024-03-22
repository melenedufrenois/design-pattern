package com.fges.todoapp.Commands;

import com.fges.todoapp.*;
import com.fges.todoapp.Options.OptionsParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class WebCommand extends Command {
    private OptionsParser optionsParser;
    private Path filePath;

    public WebCommand(String cmd, OptionsParser opt, String fileContent, Path filePath) throws Exception {
        super(cmd, opt, fileContent, filePath);
        this.optionsParser = opt;
        this.filePath = filePath;
    }

    @Override
    public String support() {
        return "web";
    }

    @Override
    public int neededArgs() {
        return 1;
    }

    @Override
    public void exec(String[] args) throws Exception {
        String url = args[0];
        String response = sendGetRequest(url);
        Todo todo = parseResponseToTodo(response);
        execute(todo);
    }

    private String sendGetRequest(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) URI.create(url).toURL().openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        connection.connect();
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        connection.disconnect();
        return response.toString();
    }

    private Todo parseResponseToTodo(String response) throws Exception {
        return new Todo(response);
    }

    private void execute(Todo todo) throws Exception {
        String fileContent = getFileContent(filePath);
        OptionsParser op = getOptionsParser();
        process(todo, fileContent, opt, filePath);
    }

    public String getFileContent(Path filePath) throws IOException {
        byte[] bytes = Files.readAllBytes(this.filePath);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    private void process(Todo todo, String fileContent, OptionsParser opt, Path filePath) throws IOException {
        System.out.println("Todo Name: " + todo.getName());
        System.out.println("Todo Description: " + todo.getDescription(filePath));
    }

    private OptionsParser getOptionsParser() {
        return optionsParser;
    }
}
