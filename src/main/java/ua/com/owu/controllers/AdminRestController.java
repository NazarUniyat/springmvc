package ua.com.owu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.dao.AccountDAO;
import ua.com.owu.dao.SearchDAO;
import ua.com.owu.models.Account;

import java.util.List;

@CrossOrigin
@RestController
public class AdminRestController {


    @Autowired
    AccountDAO accountDAO;

    @Autowired
    SearchDAO searchDAO;

    @GetMapping("/api/ad")
    List<Account> accountList() {
        List<Account> all = accountDAO.findAll();
        return all;
    }

    @GetMapping("/api/ad/{id}")
    Account getById(
            @PathVariable int id
    ) {
        return accountDAO.findById(id);
    }

    @PostMapping("/api/ad")
    public void saveAccount(
            @RequestBody Account account
    ) {
        accountDAO.save(account);
    }


//    @RequestMapping(value = "/api/adminsearch/data", method = RequestMethod.GET)
//    @ResponseBody
//    public List<Account> getBarBySimplePathWithRequestParam(
//            @RequestParam(value = "username", defaultValue = "test") String username,
//            @RequestParam(value = "email", defaultValue = "test") String email,
//            @RequestParam(value = "role", defaultValue = "test") String role
//
//    ) {
//
//        return searchDAO.getUsers(username, email, role);
//    }


    @DeleteMapping("/api/ad/{id}")
    public void deleteAcc(@PathVariable int id) {
        accountDAO.delete(id);
    }

}
