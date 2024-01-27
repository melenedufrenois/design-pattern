package com.fges.todoapp;

import org.apache.commons.cli.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class OptionsParser {
    private CommandLine cmd;

    public OptionsParser(String[] args) throws ParseException {
        Options cliOptions = new Options();
        CommandLineParser parser = new DefaultParser();

        cliOptions.addRequiredOption("s", "source", true, "File containing the todos");

        this.cmd = parser.parse(cliOptions, args);
    }

    public List<String> getPositionArgs() {
        List<String> positionalArgs = new ArrayList<>(cmd.getArgList());
        return Collections.unmodifiableList(positionalArgs);
    }

    public String getFileName() {
        return cmd.getOptionValue("s");
    }
}
