package com.example.hollo.sel.data;

@Dao
public interface BookDao {

    @insert
    void insert(Book book);

    @Query("DELETE FROM Books WHERE BooID= :bID")
    public void deleteBook(int bID);



}
