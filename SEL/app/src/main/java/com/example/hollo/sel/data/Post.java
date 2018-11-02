package com.example.hollo.sel.data;

import android.support.annotation.NonNull;

import java.util.Date;

@Entity (tableName = "Posts")
public class Post {
    @PrimaryKey
    @NonNull
    @ColumnInfo (name = "PostID")
    private int PostID;

    @ColumnInfo (name = "PostDate")
    private Date postDate;

    @ColumnInfo (name = "UserID")
    private int UserID;

    @ColumnInfo (name = "BookID")
    private int BookID;

    @ColumnInfo (name = "ExpirationDate")
    private Date expDate;

    @NonNull
    public int getPostID() {
        return PostID;
    }

    public void setPostID(@NonNull int postID) {
        PostID = postID;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int bookID) {
        BookID = bookID;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public Post (int PostID, Date postDate, int UserID, int BookID, Date expDate){
        this.PostID = PostID;
        this.postDate = postDate;
        this.UserID = UserID;
        this.BookID = BookID;
        this.expDate = expDate;


    }



}
