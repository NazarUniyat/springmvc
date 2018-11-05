package ua.com.owu.controllers;//package ua.com.owu.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import ua.com.owu.models.Account;
//import ua.com.owu.models.User;
//import ua.com.owu.service.mailService.MailService;
//import ua.com.owu.service.accountService.accountService;
//import ua.com.owu.utils.TokenUtils;
//import ua.com.owu.utils.AccountEditor;
//import ua.com.owu.utils.UserValidator;
//
//import javax.mail.MessagingException;
//import java.io.IOException;
//import java.util.List;
//
//@Controller
//public class UserController {
//
//    @Autowired
//    private AccountEditor accountEditor;
//
//    @Autowired
//    UserValidator userValidator;
//    @Autowired
//    private Environment environment;
//
//
//    @Autowired
//    private MailService mailService;
//
//
//    @Autowired
//    private accountService accountService;
//
//   @Autowired
//   TokenUtils tokenUtils;
//
//
////    @GetMapping("/login")
////    public String login() {
////        return "login";
////    }
//
//
//    @PostMapping("/save")
//    public String save(User user,
//                       BindingResult bindingResult,
//                       Model model
//    ) throws IOException {
//
//        String username = user.getUsername();
//        userValidator.validate(user, bindingResult);
//
//        user.setToken(tokenUtils.generateToken());
//
//        if (bindingResult.hasErrors()) {
//
//
//            String errorMessage = "";
//            List<ObjectError> allErrors = bindingResult.getAllErrors();
//            for (ObjectError allError : allErrors) {
//                String code = allError.getCode();
//                errorMessage += " " + environment.getProperty(code);
//            }
//
//            model.addAttribute("error", errorMessage);
//            return "index";
//        }
//
//
//        try {
//            mailService.sendConfirmMessage(user.getEmail(), user);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//
//        accountEditor.setValue(user);
//        accountService.save(user);
//
//
//        return "redirect:/";
//    }
//
//    @GetMapping("/userList")
//    public String userList(Model model) {
//        List<Account> all = accountService.findByAccountType("user");
//        model.addAttribute("users", all);
//        return "userList";
//    }
//
//    @GetMapping("/confirm/{token}")
//    public String accontConfirm(@PathVariable String token) {
//
//        Account byToken = accountService.findByToken(token);
//        if (byToken != null) {
//            byToken.setToken(null);
//            byToken.setEnabled(true);
//            accountService.save(byToken);
//            System.out.println(byToken.getUsername() + " is approveed!");
//            return "login";
//        } else {
//            System.out.println("there is  no tokens  like  that!");
//            return "index";
//        }
//    }
//}
