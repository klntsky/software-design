package ru.ifmo.mit.repl.command;

import java.io.*;
import java.util.List;

/*
    Класс, представляющий команду вывода текущей директории.
 */
public class CommandPWD extends Command {
    public CommandPWD(List<String> arguments) {
        super(arguments);
    }

    @Override
    public void execute(InputStream input, OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(output);
        writer.println(System.getProperty("user.dir"));
        writer.flush();
    }
}
