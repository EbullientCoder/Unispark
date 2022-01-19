package com.example.unispark.controller.applicationcontroller.communications;

import com.example.unispark.bean.BeanProfessorCommunication;
import com.example.unispark.database.dao.CommunicationsDAO;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.model.communications.ProfessorCommunicationModel;

import java.sql.SQLException;

public class AddProfCommunication {
    //Add professor Communication
    public void addProfCommunication(BeanProfessorCommunication communication) throws GenericException {
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

}
