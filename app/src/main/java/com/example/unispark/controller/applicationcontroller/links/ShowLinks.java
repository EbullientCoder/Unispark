package com.example.unispark.controller.applicationcontroller.links;

import com.example.unispark.database.dao.StudentLinksDAO;
import com.example.unispark.model.LinkModel;
import com.example.unispark.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class ShowLinks {
    //Students Links
    public List<LinkModel> showLinks(StudentModel student){
        List<LinkModel> linksItem;
        linksItem = StudentLinksDAO.getStudentLinks(student.getId());

        if(linksItem != null) return linksItem;
        else return new ArrayList<>();
    }
}
