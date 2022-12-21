package atm.service.command;

import atm.exception.CustomAtmException;
import atm.service.AtmMessage;
import atm.service.command.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Maiseichyk
 * The CommandManager enum class represents available commands and helps to choose it.
 * @project ATM SENLA
 */
public enum CommandManager {
    LOGIN(new LoginCommand()),
    EXIT(new ExitCommand()),
    INFO(new InfoCommand()),
    WITHDRAW(new WithdrawCommand()),
    DEPOSIT(new DepositCommand());

    private final Command command;
    private final static Logger LOGGER = LogManager.getLogger();

    CommandManager(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public static void define(String commandType) throws CustomAtmException {
        try {
            if (commandType == null || commandType.isEmpty()) {
                CommandManager.LOGIN.getCommand().execute();
            } else {
                CommandManager.valueOf(commandType.toUpperCase()).getCommand().execute();
            }
        } catch (CustomAtmException | IllegalArgumentException e) {
            LOGGER.error("Cannot define command: " + e);
        }
    }

    public static CommandManager retrieveCommand(int i) throws CustomAtmException {
        return switch (i) {
            case 1 -> CommandManager.INFO;
            case 2 -> CommandManager.DEPOSIT;
            case 3 -> CommandManager.WITHDRAW;
            case 4 -> CommandManager.EXIT;
            default -> throw new CustomAtmException(AtmMessage.MESSAGE);
        };
    }
}

