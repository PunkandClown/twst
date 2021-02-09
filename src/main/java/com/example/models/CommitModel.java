package com.example.models;

public class CommitModel {
    private String name;
    private String message;
    private String author;
    private String time;
    private String link;

    public CommitModel(String name, String message, String author, String time,String link) {
        this.name = name;
        this.message = message;
        this.author = author;
        this.time = time;
        this.link = link;
    }

    public CommitModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
