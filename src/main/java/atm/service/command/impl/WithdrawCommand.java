package atm.service.command.impl;

import atm.AtmStart;
import atm.exception.CustomAtmException;
import atm.reader.FileReader;
import atm.reader.impl.FileReaderImpl;
import atm.service.AtmMessage;
import atm.service.MessageDisplay;
import atm.service.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

/**
 * @author Maiseichyk
 * The Withdraw command class implements the function of withdrawal funds from user's card.
 * @project ATM SENLA
 */
public class WithdrawCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String CREDIT_REGEXP = "\\d+";

    @Override
    public void execute() throws CustomAtmException {
        boolean exitStatus = false;
        do {
            MessageDisplay.writeMessage(AtmMessage.QUESTION_CASH_WITHDRAWAL_MESSAGE);
            MessageDisplay.writeMessage(AtmMessage.ATM_LIMIT_MESSAGE + AtmStart.atm.getRemainingBalance());
            String answer = MessageDisplay.readString();
            if (!answer.matches(CREDIT_REGEXP)) {
                break;
            }
            if (withdrawAndSaveChanges(answer)) {
                MessageDisplay.writeMessage(AtmMessage.TAKE_THE_MONEY_MESSAGE);
                exitStatus = true;
            }
        } while (!exitStatus);
    }

    private boolean withdrawAndSaveChanges(String answer) {
        boolean status = false;
        BigDecimal amount = BigDecimal.valueOf(Long.parseLong(answer));
        FileReader reader = new FileReaderImpl();
        BigDecimal atmLimit = AtmStart.atm.getRemainingBalance();
        BigDecimal cardLimit = AtmStart.card.getCardBalance();
        if (checkCardBalance(amount) && checkATMCreditLimit(amount)) {
            AtmStart.card.setCardBalance(cardLimit.subtract(amount));
            AtmStart.atm.setRemainingBalance(atmLimit.subtract(amount));
            status = true;
        } else {
            MessageDisplay.writeMessage(AtmMessage.SUM_EXCEPTION_MESSAGE);
        }
        try {
            reader.saveChanges(AtmStart.card);
        } catch (CustomAtmException e) {
            LOGGER.error("Exception while withdrawal funds from card: " + e);
        }
        return status;
    }

    private boolean checkCardBalance(BigDecimal amountToWithdraw) {
        return AtmStart.card.getCardBalance().compareTo(amountToWithdraw) >= 0;
    }

    private boolean checkATMCreditLimit(BigDecimal amountToWithdraw) {
        return AtmStart.atm.getRemainingBalance().compareTo(amountToWithdraw) >= 0;
    }
}
