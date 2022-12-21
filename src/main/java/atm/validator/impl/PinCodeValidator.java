package atm.validator.impl;

import atm.validator.Validator;

import java.util.regex.Pattern;

/**
 * @author Maiseichyk
 * The CardNumberValidator class validate the card pin code.
 * @project ATM SENLA
 */
public class PinCodeValidator implements Validator {

    private static final String VALID_PASSWORD_REGEX = "\\d{4}";

    @Override
    public boolean validate(String password) {
        return Pattern.matches(VALID_PASSWORD_REGEX, password);
    }
}

