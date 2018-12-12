package com.example.hollo.sel.jobServices;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.hollo.sel.R;
import com.example.hollo.sel.activities.EmailPasswordActivity;
import com.example.hollo.sel.models.Book;
import com.firebase.jobdispatcher.JobService;
import com.firebase.jobdispatcher.JobParameters;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateNotificationJobService extends JobService {
    private static final int UPDATE_NOTIFICATION_ID = 1138;
    private static final int UPDATE_PENDING_INTENT_ID = 3417;
    private static final String UPDATE_NOTIFICATION_CHANNEL_ID = "update_notification_channel";
    private static final String TAG = "UpdateNotification";

    NotificationCompat.Builder notificationBuilder;

    public UpdateNotificationJobService() {
    }

    @Override
    public boolean onStartJob(JobParameters job) {
        Log.w(TAG, "Executing job id: " + job.getTag());
        DatabaseReference mBookDatabase = FirebaseDatabase.getInstance().getReference().child("books");
        Log.w(TAG, mBookDatabase.getKey());
        mBookDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                updateUser(UpdateNotificationJobService.this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Listener Cancelled");
            }
        });
        return false;
    }

    public void updateUser(Context context) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    UPDATE_NOTIFICATION_CHANNEL_ID,
                    context.getString(R.string.main_notification_channel_name),
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
        }
        notificationBuilder = new NotificationCompat.Builder(context, UPDATE_NOTIFICATION_CHANNEL_ID)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_refresh_black)
                .setContentTitle(context.getString(R.string.main_notification_title))
                .setContentText(context.getString(R.string.main_notification_body))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(
                        context.getString(R.string.main_notification_body)))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        notificationManager.notify(UPDATE_NOTIFICATION_ID, notificationBuilder.build());
    }

    private static PendingIntent contentIntent(Context context) {
        Intent startActivityIntent = new Intent(context, EmailPasswordActivity.class);
        return PendingIntent.getActivity(
                context,
                UPDATE_PENDING_INTENT_ID,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        Log.w(TAG, "Finished job: " + job.getTag());
        return false;
    }
}