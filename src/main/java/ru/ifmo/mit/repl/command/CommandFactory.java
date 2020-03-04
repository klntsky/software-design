package main.java.ru.ifmo.mit.repl.command;

import java.util.List;

/*
    Фабрика для создания команд шелла.
 */
public abstract class CommandFactory {
    public abstract Command makeAssignCommand(List<String> arguments);

    public abstract Command makeCatCommand(List<String> arguments);

    public abstract Command makeEchoCommand(List<String> arguments);

    public abstract Command makeExternalCommand(List<String> arguments);

    public abstract Command makePipeCommand(List<Command> commands);

    public abstract Command makePwdCommand(List<String> arguments);

    public abstract Command makeWCCommand(List<String> arguments);

    public abstract Command makeExitCommand(List<String> arguments);
    /*
        Функция-фабрика, позволяющая создавать инстансы команд, основываясь на их названиях.
     */
    public Command makeCommand(String commandName, List<String> args) {
        switch (commandName) {
            case "cat":
                return makeCatCommand(args);
            case "echo":
                return makeEchoCommand(args);
            case "pwd":
                return makePwdCommand(args);
            case "wc":
                return makeWCCommand(args);
            case "exit":
                return makeExitCommand(args);
            default:
                args.add(0, commandName);
                return makeExternalCommand(args);
        }
    }
}
