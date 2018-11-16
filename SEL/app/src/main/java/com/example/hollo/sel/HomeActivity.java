package com.example.hollo.sel;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.hollo.sel.models.Book;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private TextView mtextView;
    private String val;
    public FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mtextView = findViewById(R.id.userID);

        mAuth = FirebaseAuth.getInstance();




    }
    @Override
    public void onStart() {
        super.onStart();

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            val = extras.getString("uid");
        }
        mtextView.setText(val);
        mtextView.setVisibility(View.VISIBLE);


        FirebaseUser user = mAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        Log.d(TAG, "userID:" + user.getUid());
        String title = "Intro to CS";
        String isbn = "1234567890";
        String author = "Author Name";
        String course = "CS 101";
        String condition = "NEW";
        int price = 100;

        //add book to database
        String key = mDatabase.child("books").push().getKey();
        Book b = new Book(title,isbn,author,course,condition,price);
        Map<String,Object> postValues = b.toMap();

        Map<String,Object> childUpdates = new HashMap<>();
        childUpdates.put("/books/" + key,postValues);

      //  mDatabase.updateChildren(childUpdates);

        Log.d(TAG,"Book posted");




    }
}
