package com.example.unispark.controller.guicontroller.details;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class DetailsGuiController {

    private Context view;
    private String link;

    public DetailsGuiController(Context view, String link) {
        this.view = view;
        this.link = link;
    }

    public void goToLink(){
        Uri uri = Uri.parse(this.link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.view.startActivity(intent);
    }

    public void setLink(String link) {
        this.link = link;
    }

}
