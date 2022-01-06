package com.example.unispark.controller.applicationcontroller.menu;

import android.content.Context;
import android.widget.Toast;

public class RightButtonMenu {
    //Attributes
    private Context context;

    //Constructor
    public RightButtonMenu(Context context){
        this.context = context;
    }

    //Change into DAY COLOR
    public void dayColor(){
        Toast.makeText(context, "DAY COLOR", Toast.LENGTH_SHORT).show();
    }

    //Change into NIGHT COLOR
    public void nightColor(){
        Toast.makeText(context, "NIGHT COLOR", Toast.LENGTH_SHORT).show();
    }
}
