package com.courseportal.project.controller;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.courseportal.project.account.Account;
import com.courseportal.project.account.AccountRepository;
import com.courseportal.project.forms.SignupForm;
import com.courseportal.project.message.Message;
import com.courseportal.project.message.MessageType;
import com.courseportal.project.signin.SignInUtils;

@Controller
public class SignupController {

    @PersistenceContext
    private EntityManager entityManager;
    
    private final AccountRepository accountRepository;
    
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SignupController(AccountRepository repository, PasswordEncoder passwordEncoder) {
        this.accountRepository = repository;
        this.passwordEncoder = passwordEncoder;
    }

	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public SignupForm signupForm(WebRequest request) {
		Connection<?> connection = ProviderSignInUtils.getConnection(request);
		if (connection != null) {
			request.setAttribute("message", new Message(MessageType.INFO, "Your " + StringUtils.capitalize(connection.getKey().getProviderId()) + " account is not associated with a Course Portal account. If you're new, please sign up."), WebRequest.SCOPE_REQUEST);
			SignupForm signupForm = new SignupForm();
			signupForm.setUsername(connection.fetchUserProfile().getEmail());
			signupForm.setFirstName(connection.fetchUserProfile().getFirstName());
			signupForm.setLastName(connection.fetchUserProfile().getLastName());
			return signupForm;
		} else {
			return new SignupForm();
		}
	}

	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signup(@Valid SignupForm form, BindingResult formBinding, WebRequest request) {
	    
	    if (!form.getPassword().equals(form.getRepeatedPassword())) {
	        formBinding.rejectValue("repeatedPassword", "error.account", "Passwords does not match");
	    }

	    Account user = null;
	    try {
	        user = (Account) entityManager.createQuery("SELECT a FROM Account a WHERE a.username = :username")
	                .setParameter("username", form.getUsername()).getSingleResult();
	    } catch (NoResultException nre) {}

	    if ((user != null)) {
	        formBinding.rejectValue("username", "user.duplicateUsername", "already in use");
	    }

	    if (formBinding.hasErrors()) {
			return null;
		}

        Account account = new Account(form.getUsername(), passwordEncoder.encode(form.getPassword()), form.getFirstName(), form.getLastName(), form.getUniversity());
        
        accountRepository.createAccount(account);
        
        SignInUtils.signin(account);

        ProviderSignInUtils.handlePostSignUp(account.getUsername(), request);
        
        return "redirect:/";
	}
}
