package com.fges.todoapp.Commands;

import java.nio.file.Paths;

import static com.fges.todoapp.App.readDataSourceType;
import static com.fges.todoapp.App.readFilePathFromConfig;

public class TodoDataSourceFactory {
    public static TodoDataSource createTodoDataSource() {
        String configFilePath = "config.txt";
        String dataSourceType = readDataSourceType(configFilePath);

        switch (dataSourceType) {
            case "file":
                String filePath = readFilePathFromConfig(configFilePath);
                return new FileTodoDataSource(Paths.get(filePath));
            default:
                throw new IllegalArgumentException("Data Type :" + dataSourceType);
        }
    }
}
