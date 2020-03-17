package ru.ifmo.mit.repl.command;

import java.io.*;
import java.util.List;
import java.util.Arrays;
import java.nio.file.Files;

/*
    Класс, представляющий команду ls
 */
public class CommandLS extends Command {
    public CommandLS(List<String> arguments) {
        super(arguments);
    }

    @Override
    public void execute(InputStream input, OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(output);
        List<String> dirs = getArguments();
        if (dirs.size() == 0) {
            dirs = Arrays.asList(System.getProperty("user.dir"));
        }

        boolean isSingleDir = dirs.size() == 1;

        for (String directory : dirs) {
            if (!isSingleDir) {
                writer.println(directory + ":");

                Files.list(new File(directory).toPath())
                    .forEach(path -> {
                            writer.println("  " + new File(path.toString()).getName());
                        });
            } else {

                Files.list(new File(directory).toPath())
                    .forEach(path -> {
                            writer.println(new File(path.toString()).getName());
                        });
            }
        }

        writer.flush();
    }
}
