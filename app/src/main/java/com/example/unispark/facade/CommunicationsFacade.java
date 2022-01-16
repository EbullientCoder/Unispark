package com.example.unispark.facade;

import com.example.unispark.database.dao.CommunicationsDAO;
import com.example.unispark.model.communications.BeanProfCommunication;

import java.util.ArrayList;
import java.util.List;

public class CommunicationsFacade {

    private static CommunicationsFacade instance=null;
    private CommunicationsFacade()
    {

    }
    public static synchronized CommunicationsFacade getInstance()
    {
        if(instance==null)
        {
            instance=new CommunicationsFacade();
        }
        return instance;
    }

    public List<BeanProfCommunication> getAllCoursesCommunications(List<String> coursesShortNames, List<String> coursesFullNames)
    {
        List<BeanProfCommunication> communicationList = new ArrayList<>();
        List<BeanProfCommunication> tempList;
        for (int i = 0; i < coursesShortNames.size(); i++)
        {
            tempList = CommunicationsDAO.getCourseCommunications(coursesShortNames.get(i), coursesFullNames.get(i));
            communicationList.addAll(tempList);
        }
        return communicationList;
    }
}
