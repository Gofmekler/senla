package atm.service;

import atm.exception.CustomAtmException;
import atm.service.command.CommandManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Maiseichyk
 * The MessageDisplay class helps to display messages to user.
 * @project ATM SENLA
 */
public class MessageDisplay {
    private static final BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
    private static final Logger LOGGER = LogManager.getLogger();

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws CustomAtmException {
        String line;
        try {
            line = bufferReader.readLine();
        } catch (IOException e) {
            LOGGER.error("Exception while reading string: ", e);
            throw new CustomAtmException(e);
        }
        return line;
    }

    public static CommandManager askCommand() {
        boolean status = false;
        while (!status) {
            try {
                writeMessage(AtmMessage.MESSAGE);
                int commandType = Integer.parseInt(readString());
                status = true;
                return CommandManager.retrieveCommand(commandType);
            } catch (NumberFormatException | CustomAtmException e) {
                LOGGER.error("Exception while retrieving command: ", e);
                MessageDisplay.writeMessage(AtmMessage.UNKNOWN_OPERATION_MESSAGE);
            }
        }
        return askCommand();
    }
}
