package atm.parser.impl;

import atm.entity.AvailableCard;
import atm.exception.CustomAtmException;
import atm.parser.FileParser;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Maiseichyk
 * The FileParserImpl parse objects from json and parse objects to string(json).
 * @project ATM SENLA
 */
public class FileParserImpl implements FileParser {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Gson gson = new Gson();

    @Override
    public AvailableCard parseFromFile(String content) throws CustomAtmException {
        AvailableCard cardList;
        try {
            cardList = gson.fromJson(content, AvailableCard.class);
        } catch (JsonParseException e) {
            LOGGER.error("Could not parse" + e);
            throw new CustomAtmException("Could not parse" + e);
        }
        return cardList;
    }

    @Override
    public String parseToFile(AvailableCard cardList) throws CustomAtmException {
        String jsonString;
        try {
            jsonString = gson.toJson(cardList, AvailableCard.class);
        } catch (JsonParseException e) {
            LOGGER.error("Could not parse" + e);
            throw new CustomAtmException("Could not parse" + e);
        }
        return jsonString;
    }
}
