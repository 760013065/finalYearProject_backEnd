package org.javaboy.vhr.model;

import java.io.Serializable;

public class Skill implements Serializable {
    private Integer id;

    private String skill;

    private String description;

    private String jobNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
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
        return "Skill{" +
                "id=" + id +
                ", skill='" + skill + '\'' +
                ", description='" + description + '\'' +
                ", jobNumber='" + jobNumber + '\'' +
                '}';
    }
}
