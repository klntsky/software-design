package main.java.ru.ifmo.mit.repl.parse;

import main.java.ru.ifmo.mit.repl.command.CommandExecutable;
import main.java.ru.ifmo.mit.repl.parse.token.Token;

import java.util.List;

/*
    Интерфейс для разбора команд и аргуметов к ним
 */
public interface Parser {
    /*
        Создает команду на основе последовательности токенов, поданных на вход.
     */
    CommandExecutable parse(List<Token> tokens);
}
