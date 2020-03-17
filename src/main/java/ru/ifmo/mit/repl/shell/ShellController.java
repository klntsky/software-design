package ru.ifmo.mit.repl.shell;


import ru.ifmo.mit.repl.io.IOController;
import ru.ifmo.mit.repl.command.CommandExecutable;
import ru.ifmo.mit.repl.parse.Lexer;
import ru.ifmo.mit.repl.parse.Parser;
import ru.ifmo.mit.repl.parse.token.Token;

import java.io.IOException;
import java.util.Optional;
import java.util.List;

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
        Optional<String> line = ioController.readLine();
        while (line.isPresent()) {
            List<Token> tokens = lexer.parse(line.get());
            try {
                CommandExecutable command = parser.parse(tokens);
                command.execute(ioController.getInputStream(), ioController.getOutputStream());
            } catch (Exception e) {
                ioController.println(e.getLocalizedMessage());
            }

            ioController.print(PROMPT);
            line = ioController.readLine();
        }
    }
}
