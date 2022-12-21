package atm.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Maiseichyk
 * The Bank Card class represents the bank card.
 * @project ATM SENLA
 */
public class BankCard {
    private String pinCode;
    private String cardNumber;
    private boolean isActive;
    private BigDecimal cardBalance;
    private String blockTime;

    public BankCard() {
    }

    public static BankCardBuilder builder() {
        return new BankCard().new BankCardBuilder();
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getSerialNumber() {
        return cardNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public BigDecimal getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(BigDecimal cardBalance) {
        this.cardBalance = cardBalance;
    }

    public String getBlockTime() {
        return blockTime;
    }

    public void setBlockTime(String blockTime) {
        this.blockTime = blockTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankCard card = (BankCard) o;
        return isActive == card.isActive && pinCode.equals(card.pinCode) && Objects.equals(cardNumber, card.cardNumber) && cardBalance.equals(card.cardBalance) && Objects.equals(blockTime, card.blockTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pinCode, cardNumber, isActive, cardBalance, blockTime);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BankCard{");
        sb.append("pinCode='").append(pinCode).append('\'');
        sb.append(", cardNumber='").append(cardNumber).append('\'');
        sb.append(", isActive=").append(isActive);
        sb.append(", cardBalance=").append(cardBalance);
        sb.append(", blockDate=").append(blockTime);
        sb.append('}');
        return sb.toString();
    }

    public class BankCardBuilder {
        private BankCardBuilder() {

        }

        public BankCardBuilder setPinCode(String pinCode) {
            BankCard.this.pinCode = pinCode;
            return this;
        }

        public BankCardBuilder setCardNumber(String cardNumber) {
            BankCard.this.cardNumber = cardNumber;
            return this;
        }

        public BankCardBuilder setIsActive(boolean isActive) {
            BankCard.this.isActive = isActive;
            return this;
        }

        public BankCardBuilder setCardBalance(BigDecimal cardBalance) {
            BankCard.this.cardBalance = cardBalance;
            return this;
        }

        public BankCardBuilder setBlockTime(String blockTime) {
            BankCard.this.blockTime = blockTime;
            return this;
        }

        public BankCard build() {
            return BankCard.this;
        }
    }
}
