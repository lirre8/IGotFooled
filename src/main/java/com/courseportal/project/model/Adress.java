package com.courseportal.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="adresses")
public class Adress {
        
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
        
        private String streetname;
        
        private int streetNumber;

        
        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getStreetname() {
            return streetname;
        }

        public void setStreetname(String streetname) {
            this.streetname = streetname;
        }

        public int getStreetNumber() {
            return streetNumber;
        }

        public void setStreetNumber(int streetNumber) {
            this.streetNumber = streetNumber;
        }
}
