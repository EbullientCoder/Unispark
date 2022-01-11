package com.example.unispark.controller.applicationcontroller.communications;

import com.example.unispark.database.dao.CommunicationsDAO;
import com.example.unispark.model.communications.UniversityCommunicationModel;

public class AddUniCommunication {
    //Add Uni Communication
    public void addCommunication(UniversityCommunicationModel communication){
        //Add Lesson into the DB
        CommunicationsDAO.addUniversityCommunication(communication);
    }
}
