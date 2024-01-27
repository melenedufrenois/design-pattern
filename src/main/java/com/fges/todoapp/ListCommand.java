package com.fges.todoapp;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ListCommand extends Command{

    public ListCommand(String cmd, OptionsParser opt, String fileContent, Path filePath) {
        super(cmd, opt, fileContent, null);
        exec();
    }

    public void exec(){
        if (isCommand()){
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
