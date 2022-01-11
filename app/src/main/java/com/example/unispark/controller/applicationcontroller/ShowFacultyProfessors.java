package com.example.unispark.controller.applicationcontroller;

import com.example.unispark.database.dao.ProfessorDAO;
import com.example.unispark.model.ProfessorModel;
import com.example.unispark.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class ShowFacultyProfessors {
    //Faculty professors
    public List<ProfessorModel> setFacultyProfessors(StudentModel student){
        List<ProfessorModel> professorsItem;
        professorsItem = ProfessorDAO.getFacultyProfessors(student.getFaculty());

        if(professorsItem != null) return professorsItem;
        else return new ArrayList<>();
    }
}
