package com.example.hollo.sel.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.hollo.sel.R;

public class PaymentActivity extends AppCompatActivity {
    private static final String TAG = "Payment";
    public static final String EXTRA_REPLY_KEY = "key";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        final Bundle extras = getIntent().getExtras();
        final Button button = findViewById(R.id.button_buy);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // todo implement checkout flow
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    Intent replyIntent = new Intent();
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                if (extras != null) {
                                    replyIntent.putExtra(EXTRA_REPLY_KEY, extras.getString("key"));
                                }
                                setResult(RESULT_OK, replyIntent);
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                setResult(RESULT_CANCELED, replyIntent);
                                break;
                        }
                        finish();
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this);
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });
    }
}
