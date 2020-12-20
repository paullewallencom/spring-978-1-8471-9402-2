package it.freshfruits.ui.validator;

import java.math.BigDecimal;

import it.freshfruits.domain.vo.FruitMap;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("fruitValidator")
public class FruitValidator implements Validator {

    @SuppressWarnings("unchecked")
    public boolean supports(Class clazz) {
        return clazz.isAssignableFrom(FruitMap.class);
    }

    public void validate(Object command, Errors errors) {
        FruitMap map = (FruitMap) command;
        validateColor(map, errors);
        validateFlavour(map, errors);
        validateLocation(map, errors);
        validateName(map, errors);
        validatePrice(map, errors);
    }

    private void validateColor(FruitMap map, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "color", map.getColor(), "error.lenght");
    }

    private void validateFlavour(FruitMap map, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "flavour", map.getFlavour(), "error.lenght");
    }

    private void validateLocation(FruitMap map, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "location", map.getLocation(), "error.lenght");
    }

    private void validateName(FruitMap map, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", map.getName(), "error.lenght");
    }

    private void validatePrice(FruitMap map, Errors errors) {
        if (map.getPrice().toString().equals(BigDecimal.ZERO)) {
            errors.rejectValue("price", "error.lenght");
        }
    }
}
