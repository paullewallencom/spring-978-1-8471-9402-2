package it.freshfruits.ui.validator;

import it.freshfruits.domain.vo.Address;
import it.freshfruits.domain.vo.ContactInformation;
import it.freshfruits.domain.vo.CustomerMap;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("customerValidator")
public class CustomerValidator implements Validator {

    @SuppressWarnings("unchecked")
    public boolean supports(Class clazz) {
        return clazz.isAssignableFrom(CustomerMap.class);
    }

    public void validate(Object command, Errors errs) {
        CustomerMap map = (CustomerMap) command;
        validateAddress(map.getAddress(), errs);
        validateContactInformation(map.getContactInformation(), errs);
        validateName(map, errs);

    }

    private void validateAddress(Address address, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", address.getCity(), "error.lenght");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", address.getState(), "error.lenght");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street", address.getStreet(), "error.lenght");
    }

    private void validateContactInformation(ContactInformation contact, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", contact.getEmail(), "error.lenght");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "faxNumber", contact.getFaxNumber(), "error.lenght");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobilePhoneNumber", contact.getMobilePhoneNumber(), "error.lenght");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", contact.getPhoneNumber(), "error.lenght");
    }

    private void validateName(CustomerMap map, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", map.getName(), "error.lenght");

    }
}
