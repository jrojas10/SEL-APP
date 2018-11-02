package com.example.hollo.sel.data;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

public class PostRepository {
    private PostDao mPostDao;
    private LiveData<List<Post>> mAllPosts;

    public PostRepository(Application application){
        PostRoomDatabase db = PostRoomDatabase.getDatabase(application.getApplicationContext());
        mPostDaoDao = db.postDao();
        mAllPosts = mPostDao.getAllPosts();
    }
    LiveData<List<Post>> getAllBooks() {return  mAllPosts;}

    public void insert (Post post){
        new PostRepository.insertAsyncTask(mPostDao).execute(post);
    }

    private static class insertAsyncTask extends AsyncTask<Post, Void, Void> {
        private PostDao mAsyncTaskDao;

        insertAsyncTask(PostDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Post... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
