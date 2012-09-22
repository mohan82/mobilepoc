package mobilepoc.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: mohan
 * Date: 22/09/12
 * Time: 9:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class StockValidator implements Validator {
    @Override
    public boolean supports(Class aClass) {
        return StockParam.class.isAssignableFrom(aClass);
    }

    private static final Pattern pattern = Pattern.compile("^[A-Za-z]+$");

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "symbol",
                "Symbol cannot be Empty:", "Field name is required.");
        StockParam stockParam = (StockParam) o;
        if (stockParam.getSymbol().isEmpty()) {
            errors.rejectValue("symbol", "Symbol cannot be Empty");

        }
        if (!isAlphabetic(stockParam.getSymbol())) {
            errors.rejectValue("symbol", "Symbol can contain only alphabets");
        }
    }

    private static boolean isAlphabetic(String data) {
        Matcher matcher = pattern.matcher(data);
        if (matcher.matches())
            return true;
        else
            return false;

    }
}
