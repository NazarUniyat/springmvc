package ua.com.owu.utils;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.com.owu.models.Account;
import ua.com.owu.models.User;

@Component
public class AccountValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(User.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Account account = (Account) o;
        if (account.getUsername().length() < 3) {
            errors.rejectValue("username","message.length.error");
        }else if (account.getPassword().length() < 6){
            errors.rejectValue("password","message.length.error");
        }
    }
}
