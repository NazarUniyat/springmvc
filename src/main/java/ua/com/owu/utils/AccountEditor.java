package ua.com.owu.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ua.com.owu.models.Account;

import java.beans.PropertyEditorSupport;

@Component
public class AccountEditor extends PropertyEditorSupport {


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountEditor(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void setValue(Object value) {
        Account account = (Account) value;
        account.setPassword(passwordEncoder.encode(account.getPassword()));
    }
}
