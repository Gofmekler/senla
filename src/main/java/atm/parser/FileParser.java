package atm.parser;

import atm.entity.AvailableCard;
import atm.exception.CustomAtmException;

public interface FileParser {
    AvailableCard parseFromFile(String content) throws CustomAtmException;

    String parseToFile(AvailableCard cardList) throws CustomAtmException;
}
