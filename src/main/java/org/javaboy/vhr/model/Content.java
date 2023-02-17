package org.javaboy.vhr.model;

public class Content {
    String skill;
    String interest;
    String experience;

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Content{" +
                "skill='" + skill + '\'' +
                ", interest='" + interest + '\'' +
                ", experience='" + experience + '\'' +
                '}';
    }
}
