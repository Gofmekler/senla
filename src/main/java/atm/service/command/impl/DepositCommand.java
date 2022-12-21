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
 * The Deposit command class implements the function of adding funds to user's card.
 * @project ATM SENLA
 */
public class DepositCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String CREDIT_REGEXP = "\\d+";

    @Override
    public void execute() throws CustomAtmException {
        boolean exitStatus = false;
        do {
            MessageDisplay.writeMessage(AtmMessage.DEPOSIT_MESSAGE);
            MessageDisplay.writeMessage(AtmMessage.ATM_LIMIT_MESSAGE + AtmStart.atm.getRemainingBalance());
            String answer = MessageDisplay.readString();
            if (!answer.matches(CREDIT_REGEXP)) {
                MessageDisplay.writeMessage(AtmMessage.DEPOSIT_ERROR_MESSAGE);
                break;
            }
            if (depositAndSaveChanges(answer)) {
                MessageDisplay.writeMessage(AtmMessage.SUCCESS_DEPOSIT_MESSAGE);
                exitStatus = true;
            }
        } while (!exitStatus);
    }

    private boolean depositAndSaveChanges(String answer) {
        boolean status = false;
        BigDecimal amount = BigDecimal.valueOf(Long.parseLong(answer));
        FileReader reader = new FileReaderImpl();
        BigDecimal atmBalance = AtmStart.atm.getRemainingBalance();
        BigDecimal cardBalance = AtmStart.card.getCardBalance();
        if (checkAllowableAmount(amount) && checkATMBalance(amount)) {
            AtmStart.card.setCardBalance(cardBalance.add(amount));
            AtmStart.atm.setRemainingBalance(atmBalance.subtract(amount));
            status = true;
        } else {
            MessageDisplay.writeMessage(AtmMessage.SUM_EXCEPTION_MESSAGE);
        }
        try {
            reader.saveChanges(AtmStart.card);
        } catch (CustomAtmException e) {
            LOGGER.error("Exception in deposit command while saving changes: " + e);
        }
        return status;
    }

    private boolean checkATMBalance(BigDecimal amountToDeposit) {
        return AtmStart.atm.getRemainingBalance().compareTo(amountToDeposit) >= 0;
    }

    private boolean checkAllowableAmount(BigDecimal amountToDeposit) {
        return amountToDeposit.compareTo(new BigDecimal(1000000)) <= 0;
    }
}
