package ua.com.owu.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.owu.dao.AccountDAO;

import java.util.UUID;

@Component
public class TokenUtils {
    private final AccountDAO accountDAO;

    @Autowired
    public TokenUtils(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public String generateToken(){
        return UUID.randomUUID().toString().replace("-", "");
    }



}
