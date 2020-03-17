package ru.ifmo.mit.repl.command;

import java.io.*;
import java.nio.*;
import java.util.List;

/*
    Класс, представляющий команду смены текущей директории.
 */
public class CommandCD extends Command {
    public CommandCD(List<String> arguments) {
        super(arguments);
    }

    @Override
    public void execute(InputStream input, OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(output);

        if (getArguments().size() != 1) {
            writer.println("Wrong arguments.");
        } else {
            String absolute = new File(
                getArguments().get(0)).getCanonicalPath();

            System.setProperty("user.dir", absolute);
        }

        writer.flush();
    }
}
