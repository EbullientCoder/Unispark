package com.example.unispark.controller.applicationcontroller.professor;

import com.example.unispark.bean.login.BeanLoggedProfessor;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.database.dao.ProfessorDAO;
import com.example.unispark.model.ProfessorModel;

import java.util.ArrayList;
import java.util.List;

public class ShowFacultyProfessors {
    //Faculty professors
    public List<BeanLoggedProfessor> setFacultyProfessors(BeanLoggedStudent student){
        List<BeanLoggedProfessor> beanLoggedProfessorList = new ArrayList<>();

        List<ProfessorModel> professorsItem;
        professorsItem = ProfessorDAO.getFacultyProfessors(student.getFaculty());

        for (int i = 0; i < professorsItem.size(); i++){
            beanLoggedProfessorList.add(new BeanLoggedProfessor(
                    professorsItem.get(i).getFirstName(),
                    professorsItem.get(i).getLastName(),
                    professorsItem.get(i).getProfilePicture(),
                    professorsItem.get(i).getId(),
                    professorsItem.get(i).getFaculty(),
                    professorsItem.get(i).getWebsite(),
                    professorsItem.get(i).getCourses(),
                    professorsItem.get(i).getExams()
            ));
        }

        return beanLoggedProfessorList;

    }
}
