package ua.com.owu.service.mailService;

import org.springframework.web.multipart.MultipartFile;
import ua.com.owu.models.Account;

import javax.mail.MessagingException;

public interface MailService {
    void sendSimpleMessage(String email, String subject, String text) throws MessagingException;
    void sendConfirmMessage(String email, Account account) throws MessagingException;
    void sendEmailConfirmMessage(String email, Account account) throws MessagingException;
    void sendMessageWithAttachment(String email, String subject, String text, MultipartFile file) throws MessagingException;
}
