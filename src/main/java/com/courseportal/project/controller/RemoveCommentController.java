package com.courseportal.project.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.courseportal.project.account.Account;
import com.courseportal.project.model.ActiveAccount;
import com.courseportal.project.model.Comment;
import com.courseportal.project.model.Course;

@Controller
@Transactional
public class RemoveCommentController {
    
    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/courses/{courseId}/{name}/removecomment/{commentId}", method = RequestMethod.POST)
    public String removeComment(@PathVariable Long courseId,@PathVariable Long commentId, @ActiveAccount Account currentUser) {
        
        Comment comment = entityManager.find(Comment.class, commentId);
        Course course = entityManager.find(Course.class, courseId);
        
        if (comment.getUserId() == currentUser.getId()) {
            course.getComments().remove(comment);
            entityManager.remove(comment);
            entityManager.persist(course);
        }

        return "redirect:/courses/"+courseId+"/"+ course.getCourseName();
    }
}