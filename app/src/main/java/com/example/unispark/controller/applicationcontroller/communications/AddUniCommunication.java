package com.example.unispark.controller.applicationcontroller.communications;

import com.example.unispark.bean.BeanUniCommunication;
import com.example.unispark.database.dao.CommunicationsDAO;
import com.example.unispark.exceptions.DatabaseOperationError;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.model.communications.UniversityCommunicationModel;

import java.util.List;

public class AddUniCommunication {
    //Add Uni Communication
    public void addCommunication(BeanUniCommunication communication) throws GenericException
    {
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
        } catch (DatabaseOperationError databaseOperationError) {
            databaseOperationError.printStackTrace();
            throw new GenericException("Cannot add communication, try again");
        }
    }
}
