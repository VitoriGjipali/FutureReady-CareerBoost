package org.futureready.futurereadycareerboost.entity;

import jakarta.persistence.*;

@Entity
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String industry;

    public Object getCompanyName() {
        return null;
    }

    public Object getId() {
        return null;
    }

    public void setCompanyName(String companyName) {
    }
    public void setEmail(String email) {
    }
    public void setPassword(String password) {
    }
    public void setIndustry(String industry) {
    }

    public String getIndustry() {
        return null;
    }






//
//    // Lidhje me entitetin Job (do vijë më vonë)
//    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
//    private List<Job> jobPosted;
//
//    // Lidhje me JobApplication (do vijë më vonë)
//    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
//    private List<JobApplication> applicationsSentToStudents;
}

