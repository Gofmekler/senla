package atm.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Maiseichyk
 * The Available card class represents Map with CardNumber as a key and BankCard object as value.
 * @project ATM SENLA
 */
public class AvailableCard {

    private final Map<String, BankCard> cardList = new HashMap<>();

    public Map<String, BankCard> getCardList() {
        return cardList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailableCard card = (AvailableCard) o;
        return Objects.equals(cardList, card.cardList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardList);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AvailableCard{");
        sb.append("cardList=").append(cardList);
        sb.append('}');
        return sb.toString();
    }
}
