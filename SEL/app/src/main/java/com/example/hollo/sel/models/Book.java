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
    public int price;


    public Book(String title, String isbn, String author, String course, String condition, int price){
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.course = course;
        this.condition = condition;
        this.price = price;
    }
    @Exclude
    public Map<String,Object> toMap(){
        HashMap<String,Object>result = new HashMap<>();
        result.put("title",title);
        result.put("isbn",isbn);
        result.put("author",author);
        result.put("course",course);
        result.put("condition",condition);
        result.put("price",price);
        return result;
    }
}
