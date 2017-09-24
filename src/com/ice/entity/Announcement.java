package com.ice.entity;

/**
 * Created by asd on 9/20/2017.
 */
public class Announcement {
    int id;
    String text;
    int level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", level=" + level +
                '}';
    }
}
