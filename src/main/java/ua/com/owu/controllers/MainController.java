package ua.com.owu.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.com.owu.models.Account;
import ua.com.owu.models.Admin;
import ua.com.owu.models.Role;
import ua.com.owu.models.User;
import ua.com.owu.service.accountService.AccountService;
import ua.com.owu.service.mailService.MailService;
import ua.com.owu.utils.AccountEditor;
import ua.com.owu.utils.AccountValidator;
import ua.com.owu.utils.TokenUtils;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Environment environment;

    private final MailService mailService;

    private final
    AccountService accountService;

    private final
    AccountEditor accountEditor;

    private final
    AccountValidator accountValidator;

    private final
    TokenUtils tokenUtils;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MainController(Environment environment, MailService mailService, AccountService accountService, AccountEditor accountEditor, AccountValidator accountValidator, TokenUtils tokenUtils, PasswordEncoder passwordEncoder) {
        this.environment = environment;
        this.mailService = mailService;
        this.accountService = accountService;
        this.accountEditor = accountEditor;
        this.accountValidator = accountValidator;
        this.tokenUtils = tokenUtils;
        this.passwordEncoder = passwordEncoder;
    }
//    @PostMapping("/login")
//    public String login (Authentication authentication){
//        boolean isUser = false;
//        boolean isManager = false;
//        Collection<? extends GrantedAuthority> authorities
//                = authentication.getAuthorities();
//        for (GrantedAuthority grantedAuthority : authorities) {
//            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
//                isUser = true;
//                break;
//            } else if (grantedAuthority.getAuthority().equals("ROLE_MANAGER")) {
//                isManager = true;
//                break;
//            }
//        }
//
//        if (isUser) {
//            return "index";
//        } else if (isManager) {
//            return "managerPage";
//        } else {
//            throw new IllegalStateException();
//        }
//    }
//    @GetMapping("/adminPage")
//    public String adminPage (){
//    return "adminT";
//    }

    @GetMapping("/managerPage")
    public String managerPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        Account byUsername = accountService.findByUsername(auth.getName());
        Role role = byUsername.getRole();
        String accountType = byUsername.getAccountType();
        model.addAttribute("username", name);
        model.addAttribute("role", role);
        model.addAttribute("accType", accountType);
        System.out.println(name);
        return "managerPage";
    }

    @GetMapping("/")
    public String home(Model model) {
//        String path = System.getProperty("user.home")
//                + File.separator
//                + "IdeaProjects"
//                + File.separator
//                + "springmvc1"
//                + File.separator
//                + "pic"
//                + File.separator;
//
//        File file = new File(path);
//        File[] files= file.listFiles();
//        List<String> strings = new ArrayList<>();
//        for (File file1 : files) {
//            strings.add("/pic/"+file1.getName());
//        }
//
//        model.addAttribute("images",strings);
        return "index";
    }

    @GetMapping("/index")
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        Account byUsername = accountService.findByUsername(auth.getName());
        Role role = byUsername.getRole();
        String accountType = byUsername.getAccountType();
        model.addAttribute("username", name);
        model.addAttribute("role", role);
        model.addAttribute("accType", accountType);
        System.out.println(name);
        return "index";
    }

    //temporary method to  create  admin
    @GetMapping("/createAdmin")
    public String createAdmin() {
        Admin admin = new Admin(Role.ROLE_ADMIN, "admin", "admin", "as@as");
        accountEditor.setValue(admin);
        accountService.save(admin);
        return "index";
    }


    @PostMapping("/upload")
    String upload(@RequestParam MultipartFile file) {

        String path = System.getProperty("user.home")
                + File.separator
                + "IdeaProjects"
                + File.separator
                + "springmvc1"
                + File.separator
                + "pic"
                + File.separator;

        File file1 = new File(path + file.getOriginalFilename());
        try {
            logger.info("[Upload file] Transfering File name: " + file.getOriginalFilename());
            file.transferTo(file1);
        } catch (IOException e) {
            //todo replace with proper logging
            logger.error("[Upload file] file.transferTo() failed. File name: " + file.getOriginalFilename(), e);
        }

        return "redirect:/";
    }


    //USERcONTROLLER

    @PostMapping("/save")
    public String save(User user,
                       BindingResult bindingResult,
                       Model model
    ){
        accountValidator.validate(user, bindingResult);
        user.setToken(tokenUtils.generateToken());
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError allError : allErrors) {
                String code = allError.getCode();
                errorMessage.append(" ").append(environment.getProperty(code));
            }
            model.addAttribute("error", errorMessage.toString());
        }
        try {
            mailService.sendConfirmMessage(user.getEmail(), user);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        accountEditor.setValue(user);
        accountService.save(user);
        return "redirect:/";
    }


    @GetMapping("/confirm/{token}")
    public String accountConfirm(@PathVariable String token) {
        Account byToken = accountService.findByToken(token);
        if (byToken != null) {
            byToken.setToken(null);
            byToken.setEnabled(true);
            accountService.save(byToken);
            System.out.println(byToken.getUsername() + " is approved!");
            return "login";
        } else {
            System.out.println("there is  no tokens  like  that!");
            return "index";
        }
    }
 @GetMapping("/confirm/{email}/{token}")
    public String emailConfirm(@PathVariable String token, @PathVariable String email) {
        Account byToken = accountService.findByToken(token);
         System.out.println(byToken);
        if (byToken != null) {
            byToken.setToken(null);
            accountService.updateEmail(byToken.getId(), email);
            return "redirect:/";
        } else {
            System.out.println("there is  no tokens  like  that!");
            return "index";
        }
    }


    @PostMapping("/save/admin")
    public String saveAdmin(Admin admin) {
        accountEditor.setValue(admin);
        admin.setRole(Role.ROLE_ADMIN);
        admin.setAccountNonLocked(true);
        accountService.save(admin);
        return "redirect:/";
    }


}

