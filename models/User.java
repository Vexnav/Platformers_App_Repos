package com.peerlink.peerlinkapp.models;

public class User {

    private int id;
    private String username;
    private String email;
    private String password;
    private String role;
    private String department;
    private String credentials;
    private String course;
    private String levelOfStudy;
    private boolean isApproved;


    public User(int id, String username, String email, String password, String role,
                String department, String credentials, String course,
                String levelOfStudy, boolean isApproved) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.department = department;
        this.credentials = credentials;
        this.course = course;
        this.levelOfStudy = levelOfStudy;
        this.isApproved = isApproved;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getDepartment() {
        return department;
    }

    public String getCredentials() {
        return credentials;
    }

    public String getCourse() {
        return course;
    }

    public String getLevelOfStudy() {
        return levelOfStudy;
    }

    public boolean isApproved() {
        return isApproved;
    }

}
