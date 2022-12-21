package atm.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Maiseichyk
 * The ATM class represents the ATM with application interface.
 * @project ATM SENLA
 */
public class Atm {
    private final String AtmNumber;
    private BigDecimal atmBalance;

    public Atm(String atmNumber, BigDecimal atmBalance) {
        AtmNumber = atmNumber;
        this.atmBalance = atmBalance;
    }

    public BigDecimal getRemainingBalance() {
        return atmBalance;
    }

    public void setRemainingBalance(BigDecimal atmBalance) {
        this.atmBalance = atmBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atm atm = (Atm) o;
        return AtmNumber.equals(atm.AtmNumber) && atmBalance.equals(atm.atmBalance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(AtmNumber, atmBalance);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Atm{");
        sb.append("AtmNumber='").append(AtmNumber).append('\'');
        sb.append(", atmBalance=").append(atmBalance);
        sb.append('}');
        return sb.toString();
    }
}
