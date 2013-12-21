package com.courseportal.project.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.courseportal.project.account.Account;
import com.courseportal.project.forms.AddCommentForm;
import com.courseportal.project.model.ActiveAccount;
import com.courseportal.project.model.Comment;
import com.courseportal.project.model.Course;

@Controller
@Transactional
public class AddCommentController {
    
    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/courses/{courseId}/{name}/comment", method = RequestMethod.POST)
    public String addComment(@PathVariable Long courseId, @PathVariable String name,@ActiveAccount Account currentUser, @Valid AddCommentForm form, BindingResult formBinding, Model model) {
        
        if (formBinding.hasErrors()) {
            return "course_found";
        }

        Course course = entityManager.find(Course.class, courseId);
        
        Comment comment = new Comment();
        comment.setAnonymous(form.isAnonymous());
        comment.setMessage(form.getMessage());
        comment.setDate(getDateTime());
        comment.setName(currentUser.getFirstName() + " " + currentUser.getLastName());
        comment.setUserId(currentUser.getId());
        comment.setUniversity(currentUser.getUniversity().getName());
        
        entityManager.persist(comment);
        course.addComment(comment);
        entityManager.persist(course);

        return "redirect:/courses/"+courseId+"/"+ course.getCourseName();
    }
    
    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }
    
}