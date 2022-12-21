package atm.reader;

import atm.entity.AvailableCard;
import atm.entity.BankCard;
import atm.exception.CustomAtmException;

public interface FileReader {

    AvailableCard readFile() throws CustomAtmException;

    boolean saveFile(String cardList) throws CustomAtmException;

    void saveChanges(BankCard card) throws CustomAtmException;
}
