package com.example.models;

import com.fasterxml.jackson.annotation.JsonView;

public class FileModel {
    @JsonView
    private String name;
    @JsonView
    private String href;

    public FileModel(String name, String href) {
        this.name = name;
        this.href = href;
    }

    public FileModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
