package atm.reader.impl;

import atm.entity.AvailableCard;
import atm.entity.BankCard;
import atm.exception.CustomAtmException;
import atm.parser.impl.FileParserImpl;
import atm.reader.FileReader;
import atm.service.AtmMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static atm.entity.BankCard.builder;

/**
 * @author Maiseichyk
 * The FileReaderImpl read data from file and save changes into data file.
 * @project ATM SENLA
 */
public class FileReaderImpl implements FileReader {
    private static final Logger LOGGER = LogManager.getLogger();
    private final FileParserImpl fileParser = new FileParserImpl();

    @Override
    public AvailableCard readFile() throws CustomAtmException {
        ArrayList<String> lines;
        Path pathFile = Paths.get(AtmMessage.PATH);
        try (Stream<String> lineStream = Files.lines(pathFile)) {
            lines = lineStream.collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            throw new CustomAtmException(AtmMessage.READING_FAIL, e);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : lines) {
            stringBuilder.append(s).append("\n");
        }
        return fileParser.parseFromFile(stringBuilder.toString());
    }

    @Override
    public boolean saveFile(String cardList) throws CustomAtmException {
        Path pathFile = Paths.get(AtmMessage.PATH);
        boolean flag;
        try {
            Files.writeString(pathFile, cardList);
            flag = true;
        } catch (IOException e) {
            LOGGER.error(AtmMessage.READING_FAIL);
            throw new CustomAtmException(AtmMessage.READING_FAIL, e);
        }
        return flag;
    }

    @Override
    public void saveChanges(BankCard tempCard) throws CustomAtmException {
        AvailableCard dataCard = readFile();
        BankCard card = builder()
                .setCardBalance(tempCard.getCardBalance())
                .setIsActive(tempCard.isActive())
                .setCardNumber(tempCard.getSerialNumber())
                .setPinCode(tempCard.getPinCode())
                .setBlockTime(tempCard.getBlockTime())
                .build();
        dataCard.getCardList().put(card.getSerialNumber(), card);
        String cardInfo = fileParser.parseToFile(dataCard);
        saveFile(cardInfo);
    }
}
