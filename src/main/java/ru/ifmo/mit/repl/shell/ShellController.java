package main.java.ru.ifmo.mit.repl.shell;


import main.java.ru.ifmo.mit.repl.io.IOController;
import main.java.ru.ifmo.mit.repl.parse.Lexer;
import main.java.ru.ifmo.mit.repl.parse.Parser;

import java.io.IOException;

public class ShellController implements Controller {
    private final static String PROMPT = "> ";

    private final Parser parser;
    private final Lexer lexer;
    private final IOController ioController;

    public ShellController(IOController ioController, Parser parser, Lexer lexer) {
        this.parser = parser;
        this.lexer = lexer;
        this.ioController = ioController;
    }

    @Override
    public void start() {
        ioController.print(PROMPT);
        var line = ioController.readLine();
        while (line.isPresent()) {
            var tokens = lexer.parse(line.get());
            var command = parser.parse(tokens);
            try {
                command.execute(ioController.getInputStream(), ioController.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            ioController.print(PROMPT);
            line = ioController.readLine();
        }
    }
}
