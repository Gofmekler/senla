package atm.service;

/**
 * @author Maiseichyk
 * The AtmMessage final class keeps constants to display.
 * @project ATM SENLA
 */
public final class AtmMessage {
    public static final String PATH = "src/main/resources/data/data.txt";
    public static final String READING_FAIL = "Reading file is failed ";
    public static final String MESSAGE = """
            Enter operation type, please:\s
             1 - INFO.\s
             2 - DEPOSIT.
             3 - WITHDRAW.
             4 - EXIT.""";
    public static final String QUESTION_EXIT_MESSAGE = """
            Do you want to exit o continue?\s
            Enter:\s
             1 to continue.\s
             2 to exit.""";
    public static final String CONTINUE_CHOICE = "1";
    public static final String EXIT_CHOICE = "2";
    public static final String ATM_LIMIT_MESSAGE = "ATM current balance: ";
    public static final String DEPOSIT_MESSAGE = "Enter the amount you want to add to the account.";
    public static final String DEPOSIT_ERROR_MESSAGE = "The amount you entered is incorrect. Enter the amount to deposit again.";
    public static final String SUCCESS_DEPOSIT_MESSAGE = "The money has been successfully credited to your card account.";
    public static final String SUM_EXCEPTION_MESSAGE = "The amount you entered is incorrect. Enter the amount to withdraw again.";
    public static final String WRONG_MESSAGE = "Incorrect input. Try again.";
    public static String CURRENCY = " BY";
    public static String MESSAGE_HEADER = "Current balance: ";
    public static final String CARD_NUMBER_MESSAGE = "Enter card number: ";
    public static final String PIN_CODE_MESSAGE = "Enter your PIN-code: ";
    public static final String WRONG_CREDIT_CARD_MESSAGE = "This card is not a card of our bank and is not serviced. Try another card.";
    public static final String WRONG_PIN_CODE_MESSAGE = "The pin code you entered is incorrect.";
    public static final String BLOCKED_CARD_MESSAGE = "Your card is still blocked. Try again later or use another card.";
    public static final String BLOCK_CARD_MESSAGE = "Your card has been blocked for 24 hours.";
    public static final String UNBLOCK_CARD_MESSAGE = "Your card has been unblocked.";
    public static final String QUESTION_CASH_WITHDRAWAL_MESSAGE = "Type an amount or press any letter to exit";
    public static final String TAKE_THE_MONEY_MESSAGE = "Take the money.";
    public static final String UNKNOWN_OPERATION_MESSAGE = "Unknown operation, try again";
    public static final String GOODBYE_MESSAGE = "Goodbye.";

    private AtmMessage() {
    }
}
