package main.java.ru.ifmo.mit.repl.parse;

import main.java.ru.ifmo.mit.repl.command.Command;
import main.java.ru.ifmo.mit.repl.command.CommandFactory;
import main.java.ru.ifmo.mit.repl.parse.token.Token;

import java.util.ArrayList;
import java.util.List;

public final class ShellParser implements Parser {
    final private CommandFactory commandFactory;

    private int currentToken = 0;

    public ShellParser(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    private List<String> parseArguments(List<Token> tokens) {
        List<String> arguments = new ArrayList<>();
        List<String> currentArgument = new ArrayList<>();
        boolean inQuotes = false;
        while (currentToken < tokens.size() && tokens.get(currentToken).getTokenType() != Token.Type.PIPE) {

            if (tokens.get(currentToken).getTokenType() == Token.Type.SINGLE_QUOTE || tokens.get(currentToken).getTokenType() == Token.Type.DOUBLE_QUOTE) {
                inQuotes = !inQuotes;
                if (!inQuotes) {
                    arguments.add(String.join(" ", currentArgument));
                    currentArgument.clear();
                }
            } else {
                if (inQuotes) {
                    currentArgument.add(tokens.get(currentToken).getString());
                } else {
                    arguments.add(tokens.get(currentToken).getString());
                }
            }
            currentToken++;
        }
        if (!currentArgument.isEmpty()) {
            arguments.add(String.join(" ", currentArgument));
        }
        return arguments;
    }

    private Command parseCommand(List<Token> tokens) {
        Token commandName = tokens.get(currentToken++);
        if (currentToken == tokens.size()) {
            return commandFactory.makeCommand(commandName.getString(), List.of());
        } else if (currentToken < tokens.size() && tokens.get(currentToken).getTokenType() == Token.Type.ASSIGNMENT) {
            currentToken++;
            List<String> args = parseArguments(tokens);
            args.add(0, commandName.getString());
            return commandFactory.makeAssignCommand(args);
        }

        List<String> args = parseArguments(tokens);
        return commandFactory.makeCommand(commandName.getString(), args);
    }

    @Override
    public Command parse(List<Token> tokens) {
        currentToken = 0;
        List<Command> commands = new ArrayList<>();

        while (currentToken < tokens.size()) {
            var command = parseCommand(tokens);

            if (currentToken < tokens.size() && tokens.get(currentToken).getTokenType() == Token.Type.PIPE)
                currentToken++;

            commands.add(command);
        }

        return commandFactory.makePipeCommand(commands);
    }
}
