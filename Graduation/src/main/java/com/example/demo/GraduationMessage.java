package com.example.demo;
/**
 * Model class for Graduation Message.
 */
public class GraduationMessage {
    private String name;
    private int year;
    private String degree;
    private String message;

    public GraduationMessage() {}

    public GraduationMessage(String name, int year, String degree, String message) {
        this.name = name;
        this.year = year;
        this.degree = degree;
        this.message = message;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getDegree() { return degree; }
    public void setDegree(String degree) { this.degree = degree; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
