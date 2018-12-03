package com.example.hollo.sel.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.hollo.sel.R;
import com.example.hollo.sel.adapters.BooksAdapter;
import com.example.hollo.sel.models.Book;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "Home";
    public static final int NEW_BOOK_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDIT_BOOK_ACTIVITY_REQUEST_CODE = 2;
    public static final int BUY_BOOK_ACTIVITY_REQUEST_CODE = 3;
    public static int credits =0;

    private Context mContext;
    private FirebaseAuth mAuth;
    private DatabaseReference mBookDatabase;
    private BooksAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //get credits from EmailPasswordActivity

        Bundle extras = getIntent().getExtras();
        if(extras != null){
                //inital amount is 100 credits
                 credits = extras.getInt("credits");
        }
        //pass number of credits to Payment activity

        Intent in = new Intent(HomeActivity.this,PaymentActivity.class);
        in.putExtra("homeCredits", credits);

        mBookDatabase = FirebaseDatabase.getInstance().getReference().child("books");
        mAuth = FirebaseAuth.getInstance();
        mContext = this;
        mRecyclerView = findViewById(R.id.recyclerview);
        mLinearLayoutManager = new LinearLayoutManager(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.fab);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BookActivity.class);
                startActivityForResult(intent, NEW_BOOK_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    private ArrayList<Book> parseBooks(Map<String, Object> booksMap) {
        ArrayList<Book> books = new ArrayList<>();
        if (booksMap != null) {
            for (Map.Entry<String, Object> entry : booksMap.entrySet()) {
                Map bookMap = (Map) entry.getValue();
                books.add(new Book((String) bookMap.get("title"),
                        (String) bookMap.get("isbn"),
                        (String) bookMap.get("author"),
                        (String) bookMap.get("course"),
                        (String) bookMap.get("condition"),
                        (String) bookMap.get("owner_id"),
                        (String) bookMap.get("key"),
                        (long) bookMap.get("price")));
            }
        }
        return books;
    }

    @Override
    public void onStart() {
        super.onStart();


        ValueEventListener bookListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Book> books = parseBooks((Map<String, Object>) dataSnapshot.getValue());
                mAdapter = new BooksAdapter(mContext, books, mAuth.getUid());
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.setLayoutManager(mLinearLayoutManager);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadBook:onCancelled", databaseError.toException());
                Toast.makeText(HomeActivity.this, "Failed to load book.",
                        Toast.LENGTH_SHORT).show();
            }
        };
        mBookDatabase.addValueEventListener(bookListener);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_BOOK_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String key = mBookDatabase.push().getKey();
            Book book = new Book(data.getStringExtra(BookActivity.EXTRA_REPLY_TITLE),
                    data.getStringExtra(BookActivity.EXTRA_REPLY_ISBN),
                    data.getStringExtra(BookActivity.EXTRA_REPLY_AUTHOR),
                    data.getStringExtra(BookActivity.EXTRA_REPLY_COURSE),
                    data.getStringExtra(BookActivity.EXTRA_REPLY_CONDITION),
                    mAuth.getUid(),
                    key,
                    Long.parseLong(data.getStringExtra(BookActivity.EXTRA_REPLY_PRICE)));
            Map<String, Object> postValues = book.toMap();
            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put(key, postValues);
            mBookDatabase.updateChildren(childUpdates);
            credits = credits + 10;
            Log.d(TAG, "Book Posted");
            Toast.makeText(
                    getApplicationContext(),
                    R.string.saved,
                    Toast.LENGTH_LONG).show();
        } else if (requestCode == EDIT_BOOK_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String key = data.getStringExtra(BookActivity.EXTRA_REPLY_KEY);
            Book book = new Book(data.getStringExtra(BookActivity.EXTRA_REPLY_TITLE),
                    data.getStringExtra(BookActivity.EXTRA_REPLY_ISBN),
                    data.getStringExtra(BookActivity.EXTRA_REPLY_AUTHOR),
                    data.getStringExtra(BookActivity.EXTRA_REPLY_COURSE),
                    data.getStringExtra(BookActivity.EXTRA_REPLY_CONDITION),
                    mAuth.getUid(),
                    key,
                    Long.parseLong(data.getStringExtra(BookActivity.EXTRA_REPLY_PRICE)));
            Map<String, Object> postValues = book.toMap();
            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put(key, postValues);
            mBookDatabase.updateChildren(childUpdates);
            Log.d(TAG, "Book Edited");
            Toast.makeText(
                    getApplicationContext(),
                    R.string.saved,
                    Toast.LENGTH_LONG).show();
        } else if (requestCode == BUY_BOOK_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String key = data.getStringExtra(BookActivity.EXTRA_REPLY_KEY);
            mBookDatabase.child(key).removeValue();
            Log.d(TAG, "Book Bought");
            credits = credits - 10;
            Toast.makeText(
                    getApplicationContext(),
                    R.string.bought,
                    Toast.LENGTH_LONG).show();
        } else if (requestCode == BUY_BOOK_ACTIVITY_REQUEST_CODE && resultCode == RESULT_CANCELED) {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.not_bought,
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
