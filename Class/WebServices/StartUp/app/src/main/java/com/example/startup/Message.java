package com.example.startup;

import java.io.Serializable;

public class Message implements Serializable {
    private int id;
    private int userid;
    private String title;
    private String body;

    public Message(int id, int userid, String title, String body) {
        this.id = id;
        this.userid = userid;
        this.title = title;
        this.body = body;
    }

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", userid=" + userid +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
