package org.javaboy.vhr.model;

import java.util.List;

public class AdvancedSearchBean {
    private List<Experience>  exp;
    private List<Skill> ski;
    private List<Interest> inte;
    private List<Education> edu;

    public List<Education> getEdu() {
        return edu;
    }

    public void setEdu(List<Education> edu) {
        this.edu = edu;
    }

    public List<Experience> getExp() {
        return exp;
    }

    public void setExp(List<Experience> exp) {
        this.exp = exp;
    }

    public List<Skill> getSki() {
        return ski;
    }

    public void setSki(List<Skill> ski) {
        this.ski = ski;
    }

    public List<Interest> getInte() {
        return inte;
    }

    public void setInte(List<Interest> inte) {
        this.inte = inte;
    }
}
