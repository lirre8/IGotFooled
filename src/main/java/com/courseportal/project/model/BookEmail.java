package com.courseportal.project.model;

public class BookEmail {

    String fromEmail;
    String toEmail;
    String message;
    String bookTitle;


    public String getBookTitle() {
        return bookTitle;
    }
    public void setBookTitle(String title) {
        this.bookTitle = title;
    }
    public String getFromEmail() {
        return fromEmail;
    }
    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }
    public String getToEmail() {
        return toEmail;
    }
    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
