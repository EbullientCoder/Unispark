package com.example.unispark.controller.applicationcontroller.communications;

import com.example.unispark.bean.BeanProfCommunication;
import com.example.unispark.database.dao.CommunicationsDAO;
import com.example.unispark.exceptions.DatabaseOperationError;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.model.communications.ProfessorCommunicationModel;

public class AddProfCommunication {
    //Add professor Communication
    public void addProfCommunication(BeanProfCommunication communication) throws GenericException
    {
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
        } catch (DatabaseOperationError databaseOperationError) {
            databaseOperationError.printStackTrace();
            throw new GenericException("Cannot add communication, try again");
        }
    }
}
