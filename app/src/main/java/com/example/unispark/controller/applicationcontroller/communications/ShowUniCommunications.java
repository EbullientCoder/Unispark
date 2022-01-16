package com.example.unispark.controller.applicationcontroller.communications;

import com.example.unispark.bean.login.BeanLoggedProfessor;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.database.dao.CommunicationsDAO;
import com.example.unispark.model.communications.UniversityCommunicationModel;


import java.util.ArrayList;
import java.util.List;

public class ShowUniCommunications{
    //Student
    public List<com.example.unispark.bean.BeanUniCommunication> showStudentCommunications(BeanLoggedStudent student){
        List<com.example.unispark.bean.BeanUniCommunication> beanUniCommunicationList = new ArrayList<>();

        List<UniversityCommunicationModel> uniCommunicationsItem;
        uniCommunicationsItem = CommunicationsDAO.getUniversityCommunications(student.getFaculty());
        for (int i = 0; i < uniCommunicationsItem.size(); i ++){
            beanUniCommunicationList.add(new com.example.unispark.bean.BeanUniCommunication(
                    uniCommunicationsItem.get(i).getBackground(),
                    uniCommunicationsItem.get(i).getTitle(),
                    uniCommunicationsItem.get(i).getDate(),
                    uniCommunicationsItem.get(i).getCommunication(),
                    uniCommunicationsItem.get(i).getFaculty()
                    ));
        }

        return beanUniCommunicationList;

    }

    //Professor
    public List<com.example.unispark.bean.BeanUniCommunication> showProfessorCommunications(BeanLoggedProfessor professor){
        List<com.example.unispark.bean.BeanUniCommunication> beanUniCommunicationList = new ArrayList<>();

        List<UniversityCommunicationModel> uniCommunicationsItem;
        uniCommunicationsItem = CommunicationsDAO.getUniversityCommunications(professor.getFaculty());

        for (int i = 0; i < uniCommunicationsItem.size(); i ++){
            beanUniCommunicationList.add(new com.example.unispark.bean.BeanUniCommunication(
                    uniCommunicationsItem.get(i).getBackground(),
                    uniCommunicationsItem.get(i).getTitle(),
                    uniCommunicationsItem.get(i).getDate(),
                    uniCommunicationsItem.get(i).getCommunication(),
                    uniCommunicationsItem.get(i).getFaculty()
            ));
        }

        return beanUniCommunicationList;

    }

    //University
    public List<com.example.unispark.bean.BeanUniCommunication> showUniversityCommunications(){
        List<com.example.unispark.bean.BeanUniCommunication> beanUniCommunicationList = new ArrayList<>();

        List<UniversityCommunicationModel> uniCommunicationsItem;
        uniCommunicationsItem = CommunicationsDAO.getUniversityCommunications("all");

        for (int i = 0; i < uniCommunicationsItem.size(); i ++){
            beanUniCommunicationList.add(new com.example.unispark.bean.BeanUniCommunication(
                    uniCommunicationsItem.get(i).getBackground(),
                    uniCommunicationsItem.get(i).getTitle(),
                    uniCommunicationsItem.get(i).getDate(),
                    uniCommunicationsItem.get(i).getCommunication(),
                    uniCommunicationsItem.get(i).getFaculty()
            ));
        }

        return beanUniCommunicationList;

    }
}
