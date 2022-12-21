package atm.service.command.impl;

import atm.AtmStart;
import atm.entity.BankCard;
import atm.exception.CustomAtmException;
import atm.reader.FileReader;
import atm.reader.impl.FileReaderImpl;
import atm.service.AtmMessage;
import atm.service.MessageDisplay;
import atm.service.command.Command;
import atm.validator.impl.CardNumberValidator;
import atm.validator.impl.PinCodeValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @author Maiseichyk
 * The Login command class implements the function of authorization to application (ATM).
 * @project ATM SENLA
 */
public class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private final String DATE_FORMATTER = "yyyy-MM-dd HH:mm";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
    private final PinCodeValidator pinValidator = new PinCodeValidator();
    private final CardNumberValidator serialNumberValidator = new CardNumberValidator();
    private final FileReader reader = new FileReaderImpl();

    @Override
    public void execute() {
        BankCard tempCard = checkCardNumber();
        if (tempCard != null && checkPin(tempCard)) {
            AtmStart.card = tempCard;
        }
    }


    private boolean checkPin(BankCard card) {
        boolean flag = false;
        int count = 0;
        while (!flag && count <= 2) {
            MessageDisplay.writeMessage(AtmMessage.PIN_CODE_MESSAGE);
            try {
                String pinNumber = MessageDisplay.readString().trim();
                if (pinValidator.validate(pinNumber)) {
                    if (checkBlock(card)) {
                        if (card.getPinCode().equals(pinNumber)) {
                            flag = true;
                        } else {
                            count += 1;
                            MessageDisplay.writeMessage(AtmMessage.WRONG_PIN_CODE_MESSAGE);
                        }
                    } else {
                        MessageDisplay.writeMessage(AtmMessage.BLOCKED_CARD_MESSAGE);
                        AtmStart.status = false;
                        return false;
                    }
                } else {
                    count++;
                    MessageDisplay.writeMessage(AtmMessage.WRONG_PIN_CODE_MESSAGE);
                }
            } catch (CustomAtmException e) {
                LOGGER.error("Exception while pin checking " + e);
            }
        }
        if (count == 3) {
            cardBlock(card);
        }
        return flag;
    }

    private boolean checkBlock(BankCard card) throws CustomAtmException {
        if (!card.isActive()) {
            String blockTime = card.getBlockTime();
            String actual = LocalDateTime.now().format(formatter);
            LocalDateTime actualTime = LocalDateTime.parse(actual, formatter);
            LocalDateTime cardBlockTime = LocalDateTime.parse(blockTime, formatter);
            if (cardBlockTime.isAfter(actualTime) || cardBlockTime.isEqual(actualTime)) {
                MessageDisplay.writeMessage(AtmMessage.UNBLOCK_CARD_MESSAGE);
                card.setBlockTime(null);
                card.setActive(true);
                try {
                    reader.saveChanges(card);
                } catch (CustomAtmException e) {
                    LOGGER.error("Exception while block checking: " + e);
                }
            } else {
                MessageDisplay.writeMessage(AtmMessage.BLOCKED_CARD_MESSAGE);
            }
        }
        return card.isActive();
    }

    private void cardBlock(BankCard card) {
        card.setActive(false);
        card.setBlockTime(LocalDateTime.now().format(formatter));
        MessageDisplay.writeMessage(AtmMessage.BLOCK_CARD_MESSAGE);
        try {
            reader.saveChanges(card);
        } catch (CustomAtmException e) {
            LOGGER.error("Exception while blocking card: " + e);
        }
    }

    private BankCard checkCardNumber() {
        boolean flag = false;
        BankCard tempBankCard = null;
        try {
            while (!flag) {
                MessageDisplay.writeMessage(AtmMessage.CARD_NUMBER_MESSAGE);
                String creditNumber = MessageDisplay.readString().trim();
                Map<String, BankCard> cardList = reader.readFile().getCardList();
                if (serialNumberValidator.validate(creditNumber)) {
                    tempBankCard = cardList.get(creditNumber);
                    if (tempBankCard != null) {
                        flag = true;
                    } else {
                        MessageDisplay.writeMessage(AtmMessage.WRONG_CREDIT_CARD_MESSAGE);
                    }
                } else {
                    MessageDisplay.writeMessage(AtmMessage.WRONG_CREDIT_CARD_MESSAGE);
                }
            }
        } catch (CustomAtmException e) {
            LOGGER.error("Exception while card number check: " + e);
        }
        return tempBankCard;
    }
}
