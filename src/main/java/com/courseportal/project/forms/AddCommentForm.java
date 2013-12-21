package com.courseportal.project.forms;

import org.hibernate.validator.constraints.NotEmpty;

public class AddCommentForm {
    
    @NotEmpty
    private String message;
    
    private boolean anonymous;
    
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public boolean isAnonymous() {
        return anonymous;
    }
    
    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }
    
    
}
