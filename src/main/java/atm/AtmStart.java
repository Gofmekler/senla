package atm;

import atm.entity.Atm;
import atm.entity.BankCard;
import atm.exception.CustomAtmException;
import atm.service.AtmMessage;
import atm.service.MessageDisplay;
import atm.service.command.CommandManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

/**
 * @author Maiseichyk
 * The AtmStart class start main application ATM.
 * @project ATM SENLA
 */
public class AtmStart {
    private static final Logger LOGGER = LogManager.getLogger();
    public static boolean status = true;
    public static BankCard card;
    public static Atm atm = new Atm("1", new BigDecimal("1000000.0"));

    public static void main(String[] args) {
        try {
            CommandManager command = CommandManager.LOGIN;
            CommandManager.define(command.toString());
            do {
                if (card == null) {
                    CommandManager.define(command.toString());
                }
                command = MessageDisplay.askCommand();
                CommandManager.define(command.toString());
                if (command != CommandManager.EXIT) {
                    CommandManager.define(CommandManager.EXIT.toString());
                }
            } while (status);
        } catch (CustomAtmException e) {
            status = false;
            LOGGER.error("Exception in App.execution(operation) method find ", e);
        }
        card = null;
        MessageDisplay.writeMessage(AtmMessage.GOODBYE_MESSAGE);
    }
}
