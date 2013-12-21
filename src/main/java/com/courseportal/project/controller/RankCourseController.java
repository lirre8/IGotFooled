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
import com.courseportal.project.forms.RankCourseForm;
import com.courseportal.project.model.ActiveAccount;
import com.courseportal.project.model.Course;
import com.courseportal.project.model.CourseRank;

@Controller
@Transactional
public class RankCourseController {
    
    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/courses/{courseId}/{name}/rank", method = RequestMethod.POST)
    public String rankCourse(@PathVariable Long courseId, @ActiveAccount Account currentUser, @Valid RankCourseForm form, BindingResult formBinding, Model model) {
        
        if (formBinding.hasErrors()) {
            return "course_found";
        }

        Course course = entityManager.find(Course.class, courseId);
        
        CourseRank newRank = new CourseRank();
        newRank.setAvarageScore(form.getAvarageScore());
        newRank.setComment(form.getComment());
        newRank.setDate(getDateTime());
        newRank.setStructure(form.getStructure());
        newRank.setTeachers(form.getTeachers());
        newRank.setWorkload(form.getWorkload());
        newRank.setUserId(currentUser.getId());
        
        entityManager.persist(newRank);

        course.addRank(newRank);

        entityManager.persist(course);

        return "redirect:/courses/"+courseId+"/"+ course.getCourseName();
    }
    
    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }
}