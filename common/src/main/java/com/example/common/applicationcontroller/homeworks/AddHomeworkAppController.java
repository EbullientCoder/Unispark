package com.example.common.applicationcontroller.homeworks;

import com.example.common.bean.BeanHomework;
import com.example.common.bean.professor.BeanLoggedProfessor;
import com.example.common.database.dao.HomeworkDAO;
import com.example.common.exceptions.GenericException;
import com.example.common.model.HomeworkModel;

import java.sql.SQLException;
import java.util.List;

public class AddHomeworkAppController {
    //Add Homework
    public void addHomework(BeanHomework homework, BeanLoggedProfessor professor) throws GenericException {
        //Adding it into the DB
        HomeworkModel homeworkModel = new HomeworkModel(homework.getShortName(),
                homework.getFullName(),
                homework.getTitle(),
                homework.getExpiration(),
                homework.getInstructions(),
                homework.getPoints(),
                professor.getId());

        try {
            HomeworkDAO.addHomework(homeworkModel);
            List<HomeworkModel> profHomeworks = professor.getHomeworks();
            profHomeworks.add(homeworkModel);
            professor.setHomeworks(profHomeworks);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new GenericException("EXCEPTION: Try again");
        }
    }
}
