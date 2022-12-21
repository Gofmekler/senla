package atm.service.command.impl;

import atm.AtmStart;
import atm.service.AtmMessage;
import atm.service.MessageDisplay;
import atm.service.command.Command;

import java.math.BigDecimal;

/**
 * @author Maiseichyk
 * The Info command class implements the function of questing info about user's card.
 * @project ATM SENLA
 */
public class InfoCommand implements Command {
    @Override
    public void execute() {
        MessageDisplay.writeMessage(AtmMessage.MESSAGE_HEADER);
        BigDecimal cash = AtmStart.card.getCardBalance();
        MessageDisplay.writeMessage(cash + AtmMessage.CURRENCY);
    }
}
