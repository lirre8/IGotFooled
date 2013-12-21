package com.courseportal.project.controller;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.courseportal.project.account.Account;
import com.courseportal.project.forms.SignupForm;
import com.courseportal.project.model.ActiveAccount;
import com.courseportal.project.model.University;
import com.courseportal.project.signin.SignInUtils;

@Controller
@Transactional
public class EditProfileController {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Inject
    private PasswordEncoder passwordEncoder;

    
    @RequestMapping(value = "/users/{userId}/edit", method = RequestMethod.POST)
    public String editProfile(@Valid SignupForm form, BindingResult formBinding, @ActiveAccount Account currentUser, @PathVariable int userId) {

        if (!form.getPassword().equals(form.getRepeatedPassword())) {
            formBinding.rejectValue("repeatedPassword", "error.account", "Passwords does not match");
        }
        
        @SuppressWarnings("unchecked")
        List<Account> usersWithSameUsername = entityManager.createQuery("SELECT a FROM Account a WHERE a.username = :username").setParameter("username", form.getUsername()).getResultList();

        for (Account user : usersWithSameUsername) {
            if ((user.getId() != currentUser.getId())) {
                formBinding.rejectValue("username", "user.duplicateUsername", "already in use");
            }
        }
        
        if (formBinding.hasErrors()) {
            return "editprofile";
        }
        
        University university = entityManager.find(University.class, form.getUniversity().getName());
        
        if (university == null) {
            university = form.getUniversity();
            entityManager.persist(university);
        }
        
        Account user = entityManager.find(Account.class, currentUser.getId());
        user.setUsername(form.getUsername());
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setUniversity(university);

        if (!user.getPassword().subSequence(0, 9).equals(form.getPassword().substring(0, 9))) {
            user.setPassword(passwordEncoder.encode(form.getPassword()));
        }

        entityManager.persist(user);
        
        SignInUtils.signin(user);

        return "redirect:/users/"+userId;
    }

    @RequestMapping(value = "/users/{userId}/edit", method = RequestMethod.GET)
    public ModelAndView editProfileForm(@PathVariable int userId, @ActiveAccount Account currentUser, WebRequest request, Model model) {

        if (userId != currentUser.getId()) {
            return new ModelAndView("redirect:/users/"+userId);
        }

        SignupForm form = new SignupForm();
        
        form.setUsername(currentUser.getUsername());
        form.setFirstName(currentUser.getFirstName());
        form.setLastName(currentUser.getLastName());
        form.setUniversity(currentUser.getUniversity());
        form.setPassword(currentUser.getPassword().substring(0, 9));
        form.setRepeatedPassword(currentUser.getPassword().substring(0, 9));

        return new ModelAndView("editprofile", "signupForm", form);
    }
}