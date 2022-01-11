package com.example.unispark.controller.applicationcontroller.links;

import com.example.unispark.database.dao.StudentLinksDAO;
import com.example.unispark.model.LinkModel;
import com.example.unispark.model.StudentModel;

public class AddLink {
    //Add Link
    public boolean addLink(StudentModel student, LinkModel link){
        boolean isAdded = StudentLinksDAO.addStudentLink(link, student.getId());

        return isAdded;
    }
}
