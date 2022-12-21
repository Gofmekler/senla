package atm.service.command.impl;

import atm.AtmStart;
import atm.exception.CustomAtmException;
import atm.service.AtmMessage;
import atm.service.MessageDisplay;
import atm.service.command.Command;

/**
 * @author Maiseichyk
 * The Exit command class implements the function of exiting from application (ATM).
 * @project ATM SENLA
 */
public class ExitCommand implements Command {
    @Override
    public void execute() throws CustomAtmException {
        boolean status = true;
        MessageDisplay.writeMessage(AtmMessage.QUESTION_EXIT_MESSAGE);
        do {
            String answer = MessageDisplay.readString();
            if (answer.equals(AtmMessage.CONTINUE_CHOICE)) {
                status = false;
                AtmStart.status = true;
            } else if (answer.equals(AtmMessage.EXIT_CHOICE)) {
                AtmStart.status = false;
                status = false;
            } else {
                MessageDisplay.writeMessage(AtmMessage.WRONG_MESSAGE);
            }
        } while (status);
    }
}
