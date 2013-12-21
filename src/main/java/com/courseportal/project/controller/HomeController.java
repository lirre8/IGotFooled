/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.courseportal.project.controller;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import com.courseportal.project.account.Account;
import com.courseportal.project.account.AccountRepository;
import com.courseportal.project.model.Course;
import com.courseportal.project.model.CourseBook;
import com.courseportal.project.model.University;
import com.courseportal.project.signin.SignInUtils;
import com.courseportal.project.utils.SendMailService;

@Controller
@Transactional
public class HomeController {

	//private final Provider<ConnectionRepository> connectionRepositoryProvider;

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private AccountRepository accountRepository;

	@RequestMapping("/")
	public String home(Model model) {
		return "home";
	}
	
	@RequestMapping("/testUser")
	public String createTestUser(Model model) {
	    createTestUser();
	    return "redirect:/";
	}

	private void createTestUser() {
	    University university = new University("Uppsala University");
	    entityManager.persist(university);

	    Course course = new Course();
	    course.setTeacherName("Bernt-Arne Jakobsson");
	    course.setCourseCode("IDA 230");
	    course.setUniversity(university);
	    course.setCourseName("Databases");
	    entityManager.persist(course);

        Account account = new Account("nilsson.nicklas@live.se", "$2a$10$yOhLrTHZXgOEE7h6/skj1eE/9L2jDbVHik7.N.6q7NQrNJZEOhWsu", "Nicklas", "Nilsson", university);
        accountRepository.createAccount(account);
        SignInUtils.signin(account);
	}
	
	/* Keeping for future
    private ConnectionRepository getConnectionRepository() {
        return connectionRepositoryProvider.get();
    }*/
}
