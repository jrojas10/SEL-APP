package com.example.hollo.sel.data;

import android.content.Context;
@Database(entities = {Post.class},version = 1)
public abstract class PostRoomDatabase extends RoomDatabase {
    public abstract PostDao postDao();

    private  static volatile PostRoomDatabase INSTANCE;

    static PostRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PostRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PostRoomDatabase.class, "Post")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
