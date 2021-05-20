package com.parrotsoft.mydirectorio.services.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.parrotsoft.mydirectorio.services.helper.DialogCallback;

public class Helpers {

    public static void dialog(Context packageContext, String title, String message, final DialogCallback callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(packageContext);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.onOk(packageContext);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void dialog(Context packageContext, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(packageContext);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

