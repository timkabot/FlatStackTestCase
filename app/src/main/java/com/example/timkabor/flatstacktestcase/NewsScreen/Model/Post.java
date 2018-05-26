package com.example.timkabor.flatstacktestcase.NewsScreen.Model;

public class Post {
    private String text;
    private String avatar;
    private int owner_id;
    private String group_name;
    private int post_id;
    private int date;
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }
}
