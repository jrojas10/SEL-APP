package com.example.hollo.sel.activities;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hollo.sel.R;

public class BookActivity extends AppCompatActivity {
    private static final String TAG = "Book";
    public static final String EXTRA_REPLY_TITLE = "title";
    public static final String EXTRA_REPLY_ISBN = "isbn";
    public static final String EXTRA_REPLY_AUTHOR = "author";
    public static final String EXTRA_REPLY_COURSE = "course";
    public static final String EXTRA_REPLY_CONDITION = "condition";
    public static final String EXTRA_REPLY_PRICE = "price";
    public static final String EXTRA_REPLY_KEY = "key";
    private EditText mEditTitle;
    private EditText mEditIsbn;
    private EditText mEditAuthor;
    private EditText mEditCourse;
    private EditText mEditCondition;
    private EditText mEditPrice;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        final Bundle extras = getIntent().getExtras();
        mEditTitle = findViewById(R.id.edit_title);
        mEditIsbn = findViewById(R.id.edit_isbn);
        mEditAuthor = findViewById(R.id.edit_author);
        mEditCourse = findViewById(R.id.edit_course);
        mEditCondition = findViewById(R.id.edit_condition);
        mEditPrice = findViewById(R.id.edit_price);
        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditTitle.getText()) ||
                        TextUtils.isEmpty(mEditIsbn.getText()) ||
                        TextUtils.isEmpty(mEditAuthor.getText()) ||
                        TextUtils.isEmpty(mEditCourse.getText()) ||
                        TextUtils.isEmpty(mEditCondition.getText()) ||
                        TextUtils.isEmpty(mEditPrice.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String title = mEditTitle.getText().toString();
                    String isbn = mEditIsbn.getText().toString();
                    String author = mEditAuthor.getText().toString();
                    String course = mEditCourse.getText().toString();
                    String condition = mEditCondition.getText().toString();
                    String price = mEditPrice.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY_TITLE, title);
                    replyIntent.putExtra(EXTRA_REPLY_ISBN, isbn);
                    replyIntent.putExtra(EXTRA_REPLY_AUTHOR, author);
                    replyIntent.putExtra(EXTRA_REPLY_COURSE, course);
                    replyIntent.putExtra(EXTRA_REPLY_CONDITION, condition);
                    replyIntent.putExtra(EXTRA_REPLY_PRICE, price);
                    if (extras != null) {
                        replyIntent.putExtra(EXTRA_REPLY_KEY, extras.getString("key"));
                    }
                    setResult(RESULT_OK, replyIntent);
                    Log.d(TAG, "Book: " + title + ", " + isbn + ", " + author + ", " + course + ", " + condition + ", " + price);
                }
                finish();
            }
        });
    }

}
