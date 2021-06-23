package com.codegym.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Cmt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private int rate;
    private String feedback;
    private Date date;
    private int likes;

    public Cmt() {
    }

    public Cmt(Long id, String author, int rate, String feedback, Date date, int likes) {
        this.id = id;
        this.author = author;
        this.rate = rate;
        this.feedback = feedback;
        this.date = date;
        this.likes = likes;
    }

    public int getLike() {
        return likes;
    }

    public void setLike(int likes) {
        this.likes = likes;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
