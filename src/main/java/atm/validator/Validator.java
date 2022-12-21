package atm.validator;

@FunctionalInterface
public interface Validator {
    boolean validate(String content);
}
