package com.courseportal.project.account;

import java.security.Principal;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.courseportal.project.model.ActiveAccount;

public class CurrentAccountArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(ActiveAccount.class) != null
                && parameter.getParameterType().equals(Account.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
          NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        if (this.supportsParameter(parameter)) {
            Principal principal = webRequest.getUserPrincipal();
            return (Account) ((Authentication) principal).getPrincipal();
        } else {
            return WebArgumentResolver.UNRESOLVED;
        }
    }
  }