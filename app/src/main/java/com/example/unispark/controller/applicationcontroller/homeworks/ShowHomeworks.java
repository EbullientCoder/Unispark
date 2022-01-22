package com.example.unispark.controller.applicationcontroller.homeworks;

import com.example.unispark.bean.BeanHomework;
import com.example.unispark.bean.professor.BeanLoggedProfessor;
import com.example.unispark.bean.student.BeanLoggedStudent;
import com.example.unispark.database.dao.HomeworkDAO;
import com.example.unispark.model.HomeworkModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowHomeworks{
    //Student
    public List<BeanHomework> getHomework(BeanLoggedStudent student) {


        List<HomeworkModel> homeworksItem = null;
        try {
            homeworksItem = HomeworkDAO.getStudentHomework(student.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return this.listBeanHomeworks(homeworksItem);

    }

    //Professor
    public List<BeanHomework> getHomework(BeanLoggedProfessor professor){

        List<HomeworkModel> homeworksItem;
        homeworksItem = professor.getHomeworks();

        return this.listBeanHomeworks(homeworksItem);

    }


    private List<BeanHomework> listBeanHomeworks(List<HomeworkModel> homeworksItem){
        List<BeanHomework> beanHomeworkList = new ArrayList<>();
        for (int i = 0; i < homeworksItem.size(); i++){
            BeanHomework beanHomework;
            beanHomework = new BeanHomework();
            beanHomework.setShortName(homeworksItem.get(i).getShortName());
            beanHomework.setFullName(homeworksItem.get(i).getFullName());
            beanHomework.setTitle(homeworksItem.get(i).getTitle());
            beanHomework.setExpiration(homeworksItem.get(i).getExpiration());
            beanHomework.setInstructions( homeworksItem.get(i).getInstructions());
            beanHomework.setPoints(homeworksItem.get(i).getPoints());
            beanHomework.setTrackProfessor(homeworksItem.get(i).getTrackProfessor());
            beanHomeworkList.add(beanHomework);
        }

        return beanHomeworkList;
    }
}
