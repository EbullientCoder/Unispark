package unispark.controller.appcontroller.homeworks;

import unispark.engeneeringclasses.bean.BeanHomework;
import unispark.engeneeringclasses.bean.professor.BeanLoggedProfessor;
import unispark.engeneeringclasses.dao.HomeworkDAO;
import unispark.engeneeringclasses.exceptions.GenericException;
import unispark.model.HomeworkModel;

import java.sql.SQLException;
import java.util.List;

public class AddHomework {
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
