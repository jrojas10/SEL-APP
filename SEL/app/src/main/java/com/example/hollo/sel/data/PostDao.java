package com.example.hollo.sel.data;
@Dao
public interface PostDao {

    @insert
    void insert(Post post);

    @Query("DELETE FROM POSTS WHERE BookID = :bID")
    public void deletePost(int bID);


}
