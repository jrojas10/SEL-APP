package com.example.hollo.sel.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Book {
    public String title;
    public String isbn;
    public String author;
    public String course;
    public String condition;
    public String ownerId;
    public String key;
    public long price;

    public Book(String title, String isbn, String author, String course, String condition, String ownerId, String key, long price) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.course = course;
        this.condition = condition;
        this.ownerId = ownerId;
        this.key = key;
        this.price = price;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("title", title);
        result.put("isbn", isbn);
        result.put("author", author);
        result.put("course", course);
        result.put("condition", condition);
        result.put("owner_id", ownerId);
        result.put("key", key);
        result.put("price", price);
        return result;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
