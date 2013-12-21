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
import com.courseportal.project.model.Course;
import com.courseportal.project.model.UserBook;

@Controller
@Transactional
public class RemoveUserBookController {
    
    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/courses/{courseId}/{name}/removeuserbook/{bookId}", method = RequestMethod.POST)
    public String removeComment(@PathVariable Long courseId,@PathVariable Long bookId, @ActiveAccount Account currentUser) {
        
        UserBook book = entityManager.find(UserBook.class, bookId);
        Course course = entityManager.find(Course.class, courseId);
        
        if (book.getUserId() == currentUser.getId()) {
            course.getUserBooks().remove(book);
            entityManager.remove(book);
            entityManager.persist(course);
        }

        return "redirect:/courses/"+courseId+"/"+ course.getCourseName();
    }
}