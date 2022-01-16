package com.example.unispark.controller.applicationcontroller.homeworks;

import com.example.unispark.bean.BeanHomework;
import com.example.unispark.bean.login.BeanLoggedProfessor;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.database.dao.HomeworkDAO;
import com.example.unispark.model.HomeworkModel;
import com.example.unispark.model.ProfessorModel;

import java.util.ArrayList;
import java.util.List;

public class ShowHomeworks{
    //Student
    public List<BeanHomework> getHomework(BeanLoggedStudent student){
        List<BeanHomework> beanHomeworkList = new ArrayList<>();

        List<HomeworkModel> homeworksItem;
        homeworksItem = HomeworkDAO.getStudentHomework(student.getId());

        for (int i = 0; i < homeworksItem.size(); i++){
            beanHomeworkList.add(new BeanHomework(
                    homeworksItem.get(i).getShortName(),
                    homeworksItem.get(i).getFullName(),
                    homeworksItem.get(i).getTitle(),
                    homeworksItem.get(i).getExpiration(),
                    homeworksItem.get(i).getInstructions(),
                    homeworksItem.get(i).getPoints(),
                    homeworksItem.get(i).getTrackProfessor()));
        }

        return beanHomeworkList;

    }

    //Professor
    public List<BeanHomework> getHomework(BeanLoggedProfessor professor){
        List<BeanHomework> beanHomeworkList = new ArrayList<>();

        List<HomeworkModel> homeworksItem;
        homeworksItem = HomeworkDAO.getAssignedHomework(professor.getId());

        for (int i = 0; i < homeworksItem.size(); i++){
            beanHomeworkList.add(new BeanHomework(
                    homeworksItem.get(i).getShortName(),
                    homeworksItem.get(i).getFullName(),
                    homeworksItem.get(i).getTitle(),
                    homeworksItem.get(i).getExpiration(),
                    homeworksItem.get(i).getInstructions(),
                    homeworksItem.get(i).getPoints(),
                    homeworksItem.get(i).getTrackProfessor()));
        }

        return beanHomeworkList;

    }
}
