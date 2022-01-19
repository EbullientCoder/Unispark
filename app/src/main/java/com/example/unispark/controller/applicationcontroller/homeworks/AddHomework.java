package com.example.unispark.controller.applicationcontroller.homeworks;

import com.example.unispark.bean.BeanHomework;
import com.example.unispark.database.dao.HomeworkDAO;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.model.HomeworkModel;

import java.sql.SQLException;

public class AddHomework {
    //Add Homework
    public void addHomework(BeanHomework homework) throws GenericException {
        //Adding it into the DB
        HomeworkModel homeworkModel = new HomeworkModel(homework.getShortName(),
                homework.getFullName(),
                homework.getTitle(),
                homework.getExpiration(),
                homework.getInstructions(),
                homework.getPoints(),
                homework.getTrackProfessor());

        try {
            HomeworkDAO.addHomework(homeworkModel);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new GenericException("Try again");
        }
    }
}
