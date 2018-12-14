package com.example.hollo.sel.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.example.hollo.sel.R;
import com.google.firebase.auth.FirebaseAuth;


public class UserPageActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView mWelcomeTextView;
    private TextView mCreditsTextView;
    private FirebaseAuth mAuth;
    private static int credits = 0;


    @SuppressLint("StringFormatMatches")
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        mCreditsTextView = findViewById(R.id.credits);
        mWelcomeTextView = findViewById(R.id.welcome);


        findViewById(R.id.buttonBooksForSale).setOnClickListener(this);
        findViewById(R.id.buttonBooksOwned).setOnClickListener(this);
        findViewById(R.id.buttonSignOut).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            credits = extras.getInt("credits");
            mCreditsTextView.setText(getString(R.string.credits_available, credits));
            Log.w("credit Balance",String.valueOf(credits));
        }

    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if(i == R.id.buttonBooksForSale){
            String uid = mAuth.getCurrentUser().getUid();
            Intent intent = new Intent(UserPageActivity.this, HomeActivity.class);
            intent.putExtra("uid", uid).putExtra("yourBooks",false ).putExtra("credits", credits);
            startActivity(intent);

        }
        else if(i == R.id.buttonBooksOwned){
            String uid = mAuth.getCurrentUser().getUid();
            Intent intent = new Intent(UserPageActivity.this, HomeActivity.class);
            intent.putExtra("uid", uid).putExtra("yourBooks",true ).putExtra("credits", credits);
            startActivity(intent);

        }
        else if(i == R.id.buttonSignOut){
            mAuth.signOut();
            Intent intent = new Intent(UserPageActivity.this, EmailPasswordActivity.class);
            startActivity(intent);
        }

    }

}
