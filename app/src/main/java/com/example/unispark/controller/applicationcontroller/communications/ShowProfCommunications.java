package com.example.unispark.controller.applicationcontroller.communications;

import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.facade.CommunicationsFacade;
import com.example.unispark.model.communications.BeanProfCommunication;

import java.util.ArrayList;
import java.util.List;

public class ShowProfCommunications{

    public List<com.example.unispark.bean.BeanProfCommunication> showProfessorCommunications(BeanLoggedStudent student){
       List<com.example.unispark.bean.BeanProfCommunication> beanProfCommunicationList = new ArrayList<>();

        List<BeanProfCommunication> profCommunicationsItem;
        List<String> courseShortnames = new ArrayList<>();
        List<String> courseFullNames = new ArrayList<>();

        //Getting Courses Short and Full names
        if(!student.getCourses().isEmpty()){
            for(int i = 0; i < student.getCourses().size(); i++) {
                courseShortnames.add(student.getCourses().get(i).getShortName());
                courseFullNames.add(student.getCourses().get(i).getFullName());
            }

            profCommunicationsItem = CommunicationsFacade.getInstance().getAllCoursesCommunications(courseShortnames, courseFullNames);

            for (int j = 0; j < profCommunicationsItem.size(); j++){
                beanProfCommunicationList.add(new com.example.unispark.bean.BeanProfCommunication(
                        profCommunicationsItem.get(j).getProfilePhoto(),
                        profCommunicationsItem.get(j).getShortCourseName(),
                        profCommunicationsItem.get(j).getFullName(),
                        profCommunicationsItem.get(j).getProfessorName(),
                        profCommunicationsItem.get(j).getDate(),
                        profCommunicationsItem.get(j).getType(),
                        profCommunicationsItem.get(j).getCommunication()
                ));
            }
        }

        return beanProfCommunicationList;

    }
}
