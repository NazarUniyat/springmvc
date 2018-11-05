package ua.com.owu.controllers;//package ua.com.owu.controller;
//
//import org.joda.time.DateTime;
//import org.joda.time.Interval;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.mail.MessagingException;
//
//public class FutureContoller {
//    @GetMapping("/times")
//    public String times() {
//        return "index";
//    }
//
//    @PostMapping("/times")
//    public String timesSave(
//            @RequestParam String date, @RequestParam String start, @RequestParam String end
//    ) {
//
//
//        DateTime startDay = DateTime.parse(date);
//        DateTime endDay = startDay.plusHours(24);
//        DateTime startTime = startDay.plusHours(Integer.parseInt(start));
//        DateTime endTime = startDay.plusHours(Integer.parseInt(end));
//        Interval interval = new Interval(startTime, endTime);
//        Interval interval1 = new Interval(startDay, endDay);
//
//        System.out.println(startDay);
//        System.out.println(endDay);
//        System.out.println(startTime);
//        System.out.println(endTime);
//        System.out.println(interval1.gap(interval));
//        return "redirect:/times";
//    }
//
//    @PostMapping("/sentMail")
//    public String sentMail(
//            @RequestParam String email,
//            @RequestParam String subject,
//            @RequestParam String message
//
//    ) {
//        try {
//            mailService.sendSimpleMessage(email, subject, message);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//
//        return "redirect:/";
//    }
//    @GetMapping("/create/manager_page")
//    public String managerRegistration() {
//
//
//        return "managerRegistration";
//    }

//@GetMapping("/userList")
//public String userList(Model model) {
//        List<Account> all = accountService.findByAccountType("user");
//        model.addAttribute("users", all);
//        return "userList";
//        }



//}
