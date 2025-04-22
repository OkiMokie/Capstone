package com.example.elevateretailapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class CartNotificationWorker extends Worker {

    public CartNotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        if (CartManager.getCartItems().isEmpty()) {
            NotificationsHandler.sendCartReminderNotification(getApplicationContext());
        }
        return Result.success();
    }
}
