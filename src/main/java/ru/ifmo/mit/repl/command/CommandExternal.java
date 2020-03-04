package main.java.ru.ifmo.mit.repl.command;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/*
    Класс, представляющий команду вызова сторонней команды.
 */
public class CommandExternal extends Command {
    public CommandExternal(List<String> arguments) {
        super(arguments);
    }

    @Override
    public void execute(InputStream input, OutputStream output) throws IOException {
        Runtime.getRuntime().exec(getArguments().toArray(new String[0]));
    }
}
