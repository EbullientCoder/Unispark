package com.example.unispark.controller.applicationcontroller.menu;

import android.content.Context;
import android.widget.Toast;

public class RightButtonMenu {

    //Change into DAY COLOR
    public void dayColor(Context context){
        Toast.makeText(context, "DAY COLOR", Toast.LENGTH_SHORT).show();
    }

    //Change into NIGHT COLOR
    public void nightColor(Context context){
        Toast.makeText(context, "NIGHT COLOR", Toast.LENGTH_SHORT).show();
    }
}
