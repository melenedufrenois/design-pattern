package com.fges.todoapp.Options;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import java.text.ParseException;

public class OptionsDone {
    private boolean done;

    public OptionsDone(String[] args) throws ParseException, org.apache.commons.cli.ParseException {
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption("d", "done", false, "Mark the todo as done");

        CommandLine cmd = parser.parse(options, args);

        this.done = cmd.hasOption("d");
    }

    public String getName() {
        return ("isDone");
    }

    public String getDescription() {
        return ("Options Done");
    }

    public boolean isRequired() {
        return false;
    }
}