package main.java.ru.ifmo.mit.repl.command;

import main.java.ru.ifmo.mit.repl.env.Context;

import java.util.List;

/*
    Ипмлементация фабрики для создания команд шелла.
 */
public class CommandFactoryImpl extends CommandFactory {
    private final Context context;

    public CommandFactoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public Command makeAssignCommand(List<String> arguments) {
        return new CommandAssign(arguments, context);
    }

    @Override
    public Command makeCatCommand(List<String> arguments) {
        return new CommandCat(arguments);
    }

    @Override
    public Command makeEchoCommand(List<String> arguments) {
        return new CommandEcho(arguments);
    }

    @Override
    public Command makeExternalCommand(List<String> arguments) {
        return new CommandExternal(arguments);
    }

    @Override
    public Command makePipeCommand(List<Command> commands) {
        return new CommandPipe(commands);
    }

    @Override
    public Command makePwdCommand(List<String> arguments) {
        return new CommandPWD(arguments);
    }

    @Override
    public Command makeWCCommand(List<String> arguments) {
        return new CommandWC(arguments);
    }

    @Override
    public Command makeExitCommand(List<String> arguments) { return new CommandExit(arguments); }
}
