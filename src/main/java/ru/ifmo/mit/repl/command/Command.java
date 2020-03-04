package main.java.ru.ifmo.mit.repl.command;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/*
    Абстрактный класс, представляющий общий интерфейс для команд шелла.
 */
public abstract class Command {
    private final List<String> arguments;

    public Command(List<String> arguments) {
        this.arguments = arguments;
    }


    /*
        Абстрактный метод для запуска команды. Переопределяется в наследниках
     */
    public abstract void execute(InputStream input, OutputStream output) throws IOException;

    public List<String> getArguments() {
        return arguments;
    }

    @Override
    public String toString() {

        return this.getClass() + "{" +
                "arguments=" + arguments +
                '}';
    }
}
