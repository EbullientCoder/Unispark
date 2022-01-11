package com.example.unispark.controller.applicationcontroller.communications;

import com.example.unispark.database.dao.CommunicationsDAO;
import com.example.unispark.model.communications.ProfessorCommunicationModel;

public class AddProfCommunication {
    //Add professor Communication
    public void addProfCommunication(ProfessorCommunicationModel communication){
        CommunicationsDAO.addProfessorCommunication(communication);
    }
}
