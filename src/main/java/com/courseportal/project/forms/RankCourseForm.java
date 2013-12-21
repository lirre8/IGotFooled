package com.courseportal.project.forms;



public class RankCourseForm {

    
    private int workload;
    private int structure;
    private int teachers;
    private int avarageScore;
    private String comment;
    private String date;
    private String userId;
    
    public int getWorkload() {
        return workload;
    }
    public void setWorkload(int workload) {
        this.workload = workload;
    }
    public int getStructure() {
        return structure;
    }
    public void setStructure(int structure) {
        this.structure = structure;
    }
    public int getTeachers() {
        return teachers;
    }
    public void setTeachers(int teachers) {
        this.teachers = teachers;
    }
    public int getAvarageScore() {
        return avarageScore;
    }
    public void setAvarageScore(int avarageScore) {
        this.avarageScore = avarageScore;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

}
