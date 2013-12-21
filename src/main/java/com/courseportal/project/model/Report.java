package com.courseportal.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="reports")
public class Report {
            
            @Id
            @GeneratedValue(strategy = GenerationType.AUTO)
            private long id;
            
            @ManyToOne
            private Scam scam;
            
            @ManyToOne
            private PoliceStation policeStation;
            
            private String outcome;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public Scam getScam() {
                return scam;
            }

            public void setScam(Scam scam) {
                this.scam = scam;
            }

            public PoliceStation getPoliceStation() {
                return policeStation;
            }

            public void setPoliceStation(PoliceStation policeStation) {
                this.policeStation = policeStation;
            }

            public String getOutcome() {
                return outcome;
            }

            public void setOutcome(String outcome) {
                this.outcome = outcome;
            }
            
            

}
