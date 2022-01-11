package com.example.unispark.controller.applicationcontroller.links;

import com.example.unispark.database.dao.StudentLinksDAO;
import com.example.unispark.model.LinkModel;

public class DeleteLink {
    //Delete Link
    public void deleteLink(LinkModel link){
        //Remove the Connection inside the DB
        StudentLinksDAO.removeLink(link.getLinkName());
    }
}
