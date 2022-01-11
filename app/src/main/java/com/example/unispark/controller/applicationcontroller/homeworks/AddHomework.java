package com.example.unispark.controller.applicationcontroller.homeworks;

import com.example.unispark.database.dao.HomeworkDAO;
import com.example.unispark.model.HomeworkModel;

public class AddHomework {
    //Add Homework
    public void addHomework(HomeworkModel homework){
        //Adding it into the DB
        HomeworkDAO.addHomework(homework);
    }
}
