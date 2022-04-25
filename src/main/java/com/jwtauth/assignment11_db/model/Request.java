package com.jwtauth.assignment11_db.model;

import java.io.Serializable;

public class Request implements Serializable {
    private String username;
    private String password;

    public Request() {}

    public Request(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
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
}
