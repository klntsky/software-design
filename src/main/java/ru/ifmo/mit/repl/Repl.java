package main.java.ru.ifmo.mit.repl;

import main.java.ru.ifmo.mit.repl.command.CommandFactory;
import main.java.ru.ifmo.mit.repl.command.CommandFactoryImpl;
import main.java.ru.ifmo.mit.repl.env.Context;
import main.java.ru.ifmo.mit.repl.env.ShellContext;
import main.java.ru.ifmo.mit.repl.io.IOController;
import main.java.ru.ifmo.mit.repl.parse.Lexer;
import main.java.ru.ifmo.mit.repl.parse.Parser;
import main.java.ru.ifmo.mit.repl.parse.ShellLexer;
import main.java.ru.ifmo.mit.repl.parse.ShellParser;
import main.java.ru.ifmo.mit.repl.shell.Controller;
import main.java.ru.ifmo.mit.repl.shell.ShellController;

public final class Repl {
    public static void main(String[] args) {
        Context context = new ShellContext();
        CommandFactory commandFactory = new CommandFactoryImpl(context);
        Parser parser = new ShellParser(commandFactory);
        Lexer lexer = new ShellLexer(context);
        var input = System.in;
        var printStream = System.out;
        IOController ioController = new IOController(input, printStream);
        Controller controller = new ShellController(ioController, parser, lexer);

        controller.start();
    }
}
