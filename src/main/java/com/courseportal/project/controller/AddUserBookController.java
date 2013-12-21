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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.courseportal.project.forms.AddCommentForm;
import com.courseportal.project.model.Course;
import com.courseportal.project.model.CourseBook;
import com.courseportal.project.model.UserBook;

@Controller
@Transactional
public class AddUserBookController {
    
    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/courses/{courseId}/{name}/adduserbook", method = RequestMethod.POST)
    public String addUserBook(@PathVariable Long courseId,
                              @PathVariable String name,
                              @Valid @ModelAttribute UserBook userBook,
                              BindingResult formBinding,
                              RedirectAttributes attr,
                              @ModelAttribute("addCommentForm") AddCommentForm addCommentForm,
                              WebRequest request,
                              Model model) {

        if (formBinding.hasErrors()) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.userBook", formBinding);
            attr.addFlashAttribute("userBook", userBook);
            return "redirect:/courses/"+courseId+"/"+ name;
        }

        Course course = entityManager.find(Course.class, courseId);

        CourseBook courseBook = entityManager.find(CourseBook.class, userBook.getIsbn());
        
        userBook.setTitle(courseBook.getTitle());
        userBook.setAdded(getDateTime());
        
        entityManager.persist(userBook);
        course.addUserBook(userBook);
        entityManager.persist(course);

        return "redirect:/courses/"+courseId+"/"+ course.getCourseName();
    }
    
    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
}