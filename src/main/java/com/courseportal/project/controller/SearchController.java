package com.courseportal.project.controller;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.courseportal.project.account.Account;
import com.courseportal.project.forms.AddCommentForm;
import com.courseportal.project.forms.RankCourseForm;
import com.courseportal.project.model.ActiveAccount;
import com.courseportal.project.model.Comment;
import com.courseportal.project.model.Course;
import com.courseportal.project.model.CourseBook;
import com.courseportal.project.model.CourseRank;
import com.courseportal.project.model.BookEmail;
import com.courseportal.project.model.UserBook;

@Controller
@Transactional
public class SearchController {

    // private static final Logger logger =
    // LoggerFactory.getLogger(SearchController.class);

    @Inject
    private Facebook facebook;

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/courses/{courseId}/{name}", method = RequestMethod.GET)
    public String findCourse(@PathVariable Long courseId, @PathVariable String name, @ActiveAccount Account currentUser, Model model) {

        Course course = entityManager.find(Course.class, courseId);

        // Course found
        if (course != null) {
            if (!name.equals(course.getCourseName())) {
                return "redirect:" + course.getCourseName();
            }

            List<CourseBook> currentCourseBooks = course.getCourseBooks();
            Hibernate.initialize(currentCourseBooks);
            model.addAttribute("books", currentCourseBooks);

            List<UserBook> currentUserBooks = course.getUserBooks();
            Hibernate.initialize(currentUserBooks);
            model.addAttribute("userBooks", currentUserBooks);
            
            model.addAttribute("course", course);
            
            
            List<CourseRank> courseRanks = course.getCourseranks();
            Hibernate.initialize(courseRanks);
            model.addAttribute("ranksArray", courseRanks);
            
            model.addAttribute(new RankCourseForm());
            model.addAttribute(new AddCommentForm());
            model.addAttribute(new BookEmail());

            if(model.asMap().get("userBook") == null) {
                model.addAttribute(new UserBook());                
            } 

            model.addAttribute(new BookEmail());

            model.addAttribute("courseNotRanked", true);
            for (CourseRank rank : courseRanks) {
                if (rank.getUserId() == currentUser.getId()) {
                    model.addAttribute("courseNotRanked", false);
                }
            }
            
            
            List<Comment> currentComments = course.getComments();
            Hibernate.initialize(currentComments);
            model.addAttribute("comments", currentComments);
            
            return "course_found";
        } else {
            return "redirect:/addcourse";
        }
    }
}