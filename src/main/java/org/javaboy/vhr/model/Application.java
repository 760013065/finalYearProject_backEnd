package org.javaboy.vhr.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Application implements Serializable {
    private Integer id;

    private String empName;

    private String empNum;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date appTime;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date endTime;

    private String provider;

    private String cost;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date processTime;

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

    public Date getAppTime() {
        return appTime;
    }

    public void setAppTime(Date appTime) {
        this.appTime = appTime;
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

    public Date getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Date processTime) {
        this.processTime = processTime;
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
        return "Application{" +
                "id=" + id +
                ", empName='" + empName + '\'' +
                ", empNum='" + empNum + '\'' +
                ", appTime=" + appTime +
                ", content='" + content + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", provider='" + provider + '\'' +
                ", cost='" + cost + '\'' +
                ", processTime=" + processTime +
                ", status='" + status + '\'' +
                ", postscript='" + postscript + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Application)) return false;
        Application that = (Application) o;
        return getId().equals(that.getId()) && Objects.equals(getEmpName(), that.getEmpName()) && Objects.equals(getEmpNum(), that.getEmpNum()) && Objects.equals(getAppTime(), that.getAppTime()) && Objects.equals(getContent(), that.getContent()) && Objects.equals(getStartTime(), that.getStartTime()) && Objects.equals(getEndTime(), that.getEndTime()) && Objects.equals(getProvider(), that.getProvider()) && Objects.equals(getCost(), that.getCost()) && Objects.equals(getProcessTime(), that.getProcessTime()) && Objects.equals(getStatus(), that.getStatus()) && Objects.equals(getPostscript(), that.getPostscript());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmpName(), getEmpNum(), getAppTime(), getContent(), getStartTime(), getEndTime(), getProvider(), getCost(), getProcessTime(), getStatus(), getPostscript());
    }
}
