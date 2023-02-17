package org.javaboy.vhr.model;

import java.io.Serializable;
import java.util.Objects;

public class EmpLogin implements Serializable {
    private int id;
    private String username;
    private String password;
    private String salt;
    private String headerUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }

    @Override
    public String toString() {
        return "EmpLogin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", headerUrl='" + headerUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmpLogin)) return false;
        EmpLogin empLogin = (EmpLogin) o;
        return getId() == empLogin.getId() && Objects.equals(getUsername(), empLogin.getUsername()) && Objects.equals(getPassword(), empLogin.getPassword()) && Objects.equals(getSalt(), empLogin.getSalt()) && Objects.equals(getHeaderUrl(), empLogin.getHeaderUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getSalt(), getHeaderUrl());
    }
}
