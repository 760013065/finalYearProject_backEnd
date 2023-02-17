package org.javaboy.vhr.model;

import java.io.Serializable;

public class Interest implements Serializable {
    private Integer id;

    private String interest;

    private String description;

    private String jobNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    @Override
    public String toString() {
        return "Interest{" +
                "id=" + id +
                ", interest='" + interest + '\'' +
                ", description='" + description + '\'' +
                ", jobNumber='" + jobNumber + '\'' +
                '}';
    }
}
