package org.javaboy.vhr.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Objects;

public class Plan {
    private Integer id;

    private String empName;

    private String empNum;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date endTime;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date createTime;

    private String provider;

    private String cost;

    private String evaluation;

    private String status;

    private String postscript;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpNum() {
        return empNum;
    }

    public void setEmpNum(String empNum) {
        this.empNum = empNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
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

    public String getPostscript() {
        return postscript;
    }

    public void setPostscript(String postscript) {
        this.postscript = postscript;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", empName='" + empName + '\'' +
                ", empNum='" + empNum + '\'' +
                ", content='" + content + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                ", provider='" + provider + '\'' +
                ", cost='" + cost + '\'' +
                ", evaluation='" + evaluation + '\'' +
                ", status='" + status + '\'' +
                ", postscript='" + postscript + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plan)) return false;
        Plan plan = (Plan) o;
        return getId().equals(plan.getId()) && Objects.equals(getEmpName(), plan.getEmpName()) && Objects.equals(getEmpNum(), plan.getEmpNum()) && Objects.equals(getContent(), plan.getContent()) && Objects.equals(getStartTime(), plan.getStartTime()) && Objects.equals(getEndTime(), plan.getEndTime()) && Objects.equals(getCreateTime(), plan.getCreateTime()) && Objects.equals(getProvider(), plan.getProvider()) && Objects.equals(getCost(), plan.getCost()) && Objects.equals(getEvaluation(), plan.getEvaluation()) && Objects.equals(getStatus(), plan.getStatus()) && Objects.equals(getPostscript(), plan.getPostscript());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmpName(), getEmpNum(), getContent(), getStartTime(), getEndTime(), getCreateTime(), getProvider(), getCost(), getEvaluation(), getStatus(), getPostscript());
    }
}
