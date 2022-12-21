package atm.service.command;

import atm.exception.CustomAtmException;
/**
 * @author Maiseichyk
 * The Command functional interface helps to execute chosen command from application.
 * @project ATM SENLA
 */
@FunctionalInterface
public interface Command {
    void execute() throws CustomAtmException;
}
