package com.example.unispark.controller.applicationcontroller.communications;

import com.example.unispark.database.dao.CommunicationsDAO;
import com.example.unispark.model.ProfessorModel;
import com.example.unispark.model.StudentModel;
import com.example.unispark.model.communications.UniversityCommunicationModel;

import java.util.ArrayList;
import java.util.List;

public class ShowUniCommunications{
    //Student
    public List<UniversityCommunicationModel> showStudentCommunications(StudentModel student){
        List<UniversityCommunicationModel> uniCommunicationsItem;
        uniCommunicationsItem = CommunicationsDAO.getUniversityCommunications(student.getFaculty());

        if(uniCommunicationsItem != null) return uniCommunicationsItem;
        else return new ArrayList<>();
    }

    //Professor
    public List<UniversityCommunicationModel> showProfessorCommunications(ProfessorModel professor){
        List<UniversityCommunicationModel> uniCommunicationsItem;
        uniCommunicationsItem = CommunicationsDAO.getUniversityCommunications(professor.getFaculty());

        if(uniCommunicationsItem != null) return uniCommunicationsItem;
        else return new ArrayList<>();
    }

    //University
    public List<UniversityCommunicationModel> showUniversityCommunications(){
        List<UniversityCommunicationModel> uniCommunicationsItem;
        uniCommunicationsItem = CommunicationsDAO.getUniversityCommunications("all");

        if(uniCommunicationsItem != null) return uniCommunicationsItem;
        else return new ArrayList<>();
    }
}
