package com.courseportal.project.forms;

import org.hibernate.validator.constraints.NotEmpty;

public class AddScamForm {
    
    @NotEmpty
    private String title;

    private String description;

    private String category;
    
    private String country;
    
    private String city;
    
    private String lng;
    
    private String lat;
    
    private boolean trustcb;
    private boolean moneycb;
    private boolean propertycb; 
    private boolean violencecb;
    
    private String money;
    private String violence;
    private String Property;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public boolean getTrustcb() {
        return trustcb;
    }
    public void setTrustcb(boolean trustcb) {
        this.trustcb = trustcb;
    }
    public boolean getMoneycb() {
        return moneycb;
    }
    public void setMoneycb(boolean moneycb) {
        this.moneycb = moneycb;
    }
    public boolean getPropertycb() {
        return propertycb;
    }
    public void setPropertycb(boolean propertycb) {
        this.propertycb = propertycb;
    }
    public boolean getViolencecb() {
        return violencecb;
    }
    public void setViolencecb(boolean violencecb) {
        this.violencecb = violencecb;
    }
    public String getMoney() {
        return money;
    }
    public void setMoney(String money) {
        this.money = money; 
    }
    public String getViolence() {
        return violence;
    }
    public void setViolence(String violence) {
        this.violence = violence;
    }
    public String getProperty() {
        return Property;
    }
    public void setProperty(String property) {
        Property = property;
    }
    public String getLng() {
        return lng;
    }
    public void setLng(String lng) {
        this.lng = lng;
    }
    public String getLat() {
        return lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }
    


    
}
