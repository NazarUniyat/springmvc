package ua.com.owu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.owu.models.Manager;
import ua.com.owu.models.Place;
import ua.com.owu.models.Role;
import ua.com.owu.service.accountService.AccountService;
import ua.com.owu.service.mailService.MailService;
import ua.com.owu.service.placeService.PlaceService;
import ua.com.owu.utils.AccountEditor;
import ua.com.owu.utils.AccountValidator;
import ua.com.owu.utils.TokenUtils;

import javax.mail.MessagingException;

@Controller
public class ManagerController {
    private final
    AccountService accountService;

    private final
    AccountEditor accountEditor;
    private final AccountValidator accountValidator;

    private final TokenUtils tokenUtils;

    private final PlaceService placeService;

    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    private final Environment environment;

    @Autowired
    public ManagerController(AccountService accountService, AccountEditor accountEditor, AccountValidator accountValidator, TokenUtils tokenUtils, PlaceService placeService, MailService mailService, PasswordEncoder passwordEncoder, Environment environment) {
        this.accountService = accountService;
        this.accountEditor = accountEditor;
        this.accountValidator = accountValidator;
        this.tokenUtils = tokenUtils;
        this.placeService = placeService;
        this.mailService = mailService;
        this.passwordEncoder = passwordEncoder;
        this.environment = environment;
    }


    @PostMapping("/save/manager")
    public String manager(Manager manager) {
        accountEditor.setValue(manager);
        manager.setRole(Role.ROLE_MANAGER);
        manager.setAccountNonLocked(false);
        accountService.save(manager);
        return "redirect:/";
    }


    @GetMapping("/create/manager_page")
    public String managerRegistration() {

        return "managerRegistration";
    }


    @GetMapping("/manager-account")
    public String manPage(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                !(SecurityContextHolder.getContext().getAuthentication()
                        instanceof AnonymousAuthenticationToken)) {
            Manager manager = (Manager) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Place place = placeService.findByManagerId(manager.getId());
            System.out.println(manager);
            System.out.println(place);
            model.addAttribute("manager", manager);
            model.addAttribute("place", place);
            return "managerPage/managerPage";
        } else
            return "redirect:/";

    }

    @GetMapping("/manager-account/delete")
    public String deleteAccount() {
        Manager manager = (Manager) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        accountService.deleteById(manager.getId());
        return "redirect:/";
    }

    @GetMapping("/manager-account/place/delete")
    public String deletePlace() {
        Manager manager = (Manager) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(manager);
        manager.setPlace(null);
        accountService.save(manager);
        return "redirect:/manager-account";
    }

    @PostMapping("/manager-account/update/names")
    public String updateManagerName(Manager managerNew, Model model) {
        Manager manager = (Manager) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(managerNew);
        accountService.updateNames(manager.getId(), managerNew);
        model.addAttribute("namesChanged", "Your name have been changed");
        return "redirect:/manager-account";
    }

    @PostMapping("/manager-account/update/email")
    public String updateManagerEmail(Manager managerNew, Model model) {
        Manager manager = (Manager) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        manager.setToken(tokenUtils.generateToken());
        accountService.save(manager);
        try {
            mailService.sendEmailConfirmMessage(managerNew.getEmail(), manager);
            model.addAttribute("mailSent", "Confirmation mail have been sent. Please check your post. Your email wouldn`t be changed untill you confirm it");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "redirect:/manager-account";
    }

    @PostMapping("/manager-account/update/password")
    public String updateManagerPassword(@RequestParam String password, @RequestParam String oldPassword,  Model model) {
        Manager manager = (Manager) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (passwordEncoder.matches(oldPassword, manager.getPassword())) {
                manager.setPassword(password);
                accountEditor.setValue(manager);
                accountService.save(manager);
                model.addAttribute("success", "Your password has been changed");
            }
         else {
            model.addAttribute("oldPasswordError", "Wrong password!");

        }


        return "redirect:/manager-account";
    }

}
