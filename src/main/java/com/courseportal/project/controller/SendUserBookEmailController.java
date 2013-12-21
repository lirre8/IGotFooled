package com.courseportal.project.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.courseportal.project.model.BookEmail;
import com.courseportal.project.utils.SendMailService;

@Controller
@Transactional
public class SendUserBookEmailController {
    
    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/courses/{courseId}/{name}/senduserbookemail", method = RequestMethod.POST)
    public String addUserBook(@PathVariable Long courseId,
                              @PathVariable String name,
                              @Valid @ModelAttribute BookEmail email,
                              BindingResult formBinding,
                              RedirectAttributes attr,
                              WebRequest request,
                              Model model) {

        if (formBinding.hasErrors()) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.emailMessage", formBinding);
            attr.addFlashAttribute("emailMessage", email);
            return "redirect:/courses/"+courseId+"/"+ name;
        }

        //Course course = entityManager.find(Course.class, courseId);
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-mail.xml");
        SendMailService mm = (SendMailService) context.getBean("sendMailService");
        mm.sendMail(email.getToEmail(), email.getFromEmail(), "Regarding your book on Course Portal - " + email.getBookTitle(), email.getMessage());

        return "redirect:/courses/"+courseId+"/"+ name;
    }
}