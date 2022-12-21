package atm.validator.impl;

import atm.validator.Validator;

/**
 * @author Maiseichyk
 * The CardNumberValidator class validate the card number.
 * @project ATM SENLA
 */
public class CardNumberValidator implements Validator {

    private static final String CARD_NUMBER_REGEX = "\\d{4}-\\d{4}-\\d{4}-\\d{4}";

    @Override
    public boolean validate(String cardNumber) {
        return cardNumber.matches(CARD_NUMBER_REGEX);
    }
}
