package com.example.unispark.controller.applicationcontroller.communications;

import com.example.unispark.bean.BeanProfessorCommunication;
import com.example.unispark.bean.BeanUniCommunication;
import com.example.unispark.bean.login.BeanLoggedProfessor;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.database.dao.CommunicationsDAO;
import com.example.unispark.facade.CommunicationsFacade;
import com.example.unispark.model.communications.ProfessorCommunicationModel;
import com.example.unispark.model.communications.UniversityCommunicationModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowCommunications {


    public List<BeanProfessorCommunication> showProfessorCommunications(BeanLoggedStudent student)  {
       List<BeanProfessorCommunication> beanProfCommunicationList = new ArrayList<>();

        List<ProfessorCommunicationModel> profCommunicationsItem;
        List<String> courseShortnames = new ArrayList<>();
        List<String> courseFullNames = new ArrayList<>();

        //Getting Courses Short and Full names
        if(!student.getCourses().isEmpty()){
            for(int i = 0; i < student.getCourses().size(); i++) {
                courseShortnames.add(student.getCourses().get(i).getShortName());
                courseFullNames.add(student.getCourses().get(i).getFullName());
            }

            try{
                profCommunicationsItem = CommunicationsFacade.getInstance().getAllCoursesCommunications(courseShortnames, courseFullNames);

                for (int j = 0; j < profCommunicationsItem.size(); j++){
                    beanProfCommunicationList.add(new BeanProfessorCommunication(
                            profCommunicationsItem.get(j).getProfilePhoto(),
                            profCommunicationsItem.get(j).getShortCourseName(),
                            profCommunicationsItem.get(j).getFullName(),
                            profCommunicationsItem.get(j).getProfessorName(),
                            profCommunicationsItem.get(j).getDate(),
                            profCommunicationsItem.get(j).getType(),
                            profCommunicationsItem.get(j).getCommunication()
                    ));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return beanProfCommunicationList;

    }


    //Student
    public List<BeanUniCommunication> showUniversityCommunications(BeanLoggedStudent student) {
        List<com.example.unispark.bean.BeanUniCommunication> beanUniCommunicationList = new ArrayList<>();

        List<UniversityCommunicationModel> uniCommunicationsItem;
        try {
            uniCommunicationsItem = CommunicationsDAO.getUniversityCommunications(student.getFaculty());
            for (int i = 0; i < uniCommunicationsItem.size(); i ++){
                beanUniCommunicationList.add(new BeanUniCommunication(
                        uniCommunicationsItem.get(i).getBackground(),
                        uniCommunicationsItem.get(i).getTitle(),
                        uniCommunicationsItem.get(i).getDate(),
                        uniCommunicationsItem.get(i).getCommunication(),
                        uniCommunicationsItem.get(i).getFaculty()
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return beanUniCommunicationList;

    }

    //Professor
    public List<BeanUniCommunication> showUniversityCommunications(BeanLoggedProfessor professor) {
        List<BeanUniCommunication> beanUniCommunicationList = new ArrayList<>();

        List<UniversityCommunicationModel> uniCommunicationsItem;
        try {
            uniCommunicationsItem = CommunicationsDAO.getUniversityCommunications(professor.getFaculty());

            for (int i = 0; i < uniCommunicationsItem.size(); i ++){
                beanUniCommunicationList.add(new BeanUniCommunication(
                        uniCommunicationsItem.get(i).getBackground(),
                        uniCommunicationsItem.get(i).getTitle(),
                        uniCommunicationsItem.get(i).getDate(),
                        uniCommunicationsItem.get(i).getCommunication(),
                        uniCommunicationsItem.get(i).getFaculty()
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return beanUniCommunicationList;

    }

    //University
    public List<BeanUniCommunication> showUniversityCommunications() {
        List<BeanUniCommunication> beanUniCommunicationList = new ArrayList<>();

        List<UniversityCommunicationModel> uniCommunicationsItem;
        try {
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return beanUniCommunicationList;

    }
}
