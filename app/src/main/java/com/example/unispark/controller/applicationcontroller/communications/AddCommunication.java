package com.example.unispark.controller.applicationcontroller.communications;

import com.example.unispark.bean.BeanProfessorCommunication;
import com.example.unispark.bean.BeanUniCommunication;
import com.example.unispark.database.dao.CommunicationsDAO;
import com.example.unispark.exceptions.CourseDoesNotExist;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.model.communications.ProfessorCommunicationModel;
import com.example.unispark.model.communications.UniversityCommunicationModel;

import java.sql.SQLException;

public class AddCommunication {
    //Add professor Communication
    public void addProfCommunication(BeanProfessorCommunication communication) throws GenericException, CourseDoesNotExist {
        ProfessorCommunicationModel communicationModel = new ProfessorCommunicationModel(
                communication.getProfilePhoto(),
                communication.getShortCourseName(),
                communication.getFullName(),
                communication.getProfessorName(),
                communication.getDate(),
                communication.getType(),
                communication.getCommunication()
        );

        try {
            CommunicationsDAO.addProfessorCommunication(communicationModel);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new GenericException("Try again");
        }

    }



    //Add Uni Communication
    public void addUniCommunication(BeanUniCommunication communication) throws GenericException{
        //Add Lesson into the DB
        UniversityCommunicationModel universityCommunicationModel;
        universityCommunicationModel = new UniversityCommunicationModel(
                communication.getBackground(),
                communication.getTitle(),
                communication.getDate(),
                communication.getCommunication(),
                communication.getFaculty());

        try {
            CommunicationsDAO.addUniversityCommunication(universityCommunicationModel);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new GenericException("Try again");
        }
    }

}
