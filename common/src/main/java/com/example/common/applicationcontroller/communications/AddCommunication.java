package com.example.common.applicationcontroller.communications;

import com.example.common.bean.communications.BeanProfessorCommunication;
import com.example.common.bean.communications.BeanUniCommunication;
import com.example.common.database.dao.CommunicationsDAO;
import com.example.common.exceptions.CourseDoesNotExist;
import com.example.common.exceptions.GenericException;
import com.example.common.model.communications.ProfessorCommunicationModel;
import com.example.common.model.communications.UniversityCommunicationModel;

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
