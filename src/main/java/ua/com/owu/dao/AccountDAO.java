package ua.com.owu.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.owu.models.Account;

import java.util.List;

@Repository
public interface AccountDAO extends JpaRepository<Account, Integer> {


    Account findByUsername(String username);

    List<Account> findByAccountType(String accountType);

    Account findByEmail(String email);

    Account findByToken(String token);

    Account findById(int id);


}
