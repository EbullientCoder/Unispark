package com.example.unispark.controller.guicontroller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class DetailsGuiController {

    public void goToLink(Context context, String link){
        Uri uri = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
