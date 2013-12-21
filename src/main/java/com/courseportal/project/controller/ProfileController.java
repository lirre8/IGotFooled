package com.courseportal.project.controller;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.courseportal.project.account.Account;
import com.courseportal.project.model.ActiveAccount;

@Controller
@Transactional
public class ProfileController {
    
    @Inject
    private Facebook facebook;
    
    @Inject
    private ConnectionRepository connectionRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public String userProfile(@PathVariable int userId, @ActiveAccount Account currentUser, Model model) {

        Account user = entityManager.find(Account.class, userId);

        if (userId == currentUser.getId()) {
            model.addAttribute("isProfileOwner", true);
        }
        
        model.addAttribute("user", user);
        
        /*
         * saved until know how to get courses
        Connection<LinkedIn> connection = connectionRepository.findPrimaryConnection(LinkedIn.class);
        if (connection != null) {
            LinkedIn linkedin = connection.getApi();
            List<Education> educations = linkedin.profileOperations().getUserProfileFull().getEducations();
            
            for (Education education : educations) {
                University university = entityManager.find(University.class, education.getSchoolName());
                if (university == null) {
                    entityManager.persist(new University(education.getSchoolName()));
                }
            }
        }
        */

        return "profile";
    }
}