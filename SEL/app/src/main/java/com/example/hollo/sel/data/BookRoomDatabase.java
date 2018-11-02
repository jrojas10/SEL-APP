package com.example.hollo.sel.data;

import android.content.Context;

@Database(entities = {Book.class},version =1)
public abstract class BookRoomDatabase extends RoomDatabase {
 public abstract BookDao bookDao();

 private  static volatile BookRoomDatabase INSTANCE;

    static BookRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BookRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BookRoomDatabase.class, "Book")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
