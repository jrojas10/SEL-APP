package com.example.hollo.sel.data;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

public class BookRepository {
    private BookDao mBookDao;
    private LiveData<List<Book>> mAllBooks;

    public BookRepository(Application application){
        BookRoomDatabase db = BookRoomDatabase.getDatabase(application.getApplicationContext());
        mBookDao = db.bookDao();
        mAllBooks = mBookDao.getAllBooks();
    }
    LiveData<List<Book>> getAllBooks() {return  mAllBooks;}

    public void insert (Book book){
        new insertAsyncTask(mBookDao).execute(book);
    }

    private static class insertAsyncTask extends AsyncTask<Book, Void, Void> {
        private BookDao mAsyncTaskDao;

        insertAsyncTask(BookDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Book... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
