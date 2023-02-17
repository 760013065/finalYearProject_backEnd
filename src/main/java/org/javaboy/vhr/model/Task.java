package org.javaboy.vhr.model;

import java.io.Serializable;
import java.util.Objects;

public class Task implements Serializable {
    private int id;
    private int proId;
    private String empName;
    private String empNum;
    private String content;
    private String evaluation;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmpNum() {
        return empNum;
    }

    public void setEmpNum(String empNum) {
        this.empNum = empNum;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", proId=" + proId +
                ", empName='" + empName + '\'' +
                ", empNum='" + empNum + '\'' +
                ", content='" + content + '\'' +
                ", evaluation='" + evaluation + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
