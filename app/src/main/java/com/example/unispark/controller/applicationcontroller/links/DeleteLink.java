package com.example.unispark.controller.applicationcontroller.links;

import com.example.unispark.bean.BeanLink;
import com.example.unispark.database.dao.StudentLinksDAO;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.model.LinkModel;

import java.sql.SQLException;

public class DeleteLink {
    //Delete Link
    public void deleteLink(BeanLink link) throws GenericException
    {
        //Remove the Connection inside the DB
        LinkModel removeLink = new LinkModel(link.getLinkName(), link.getLinkAddress());
        try {
            StudentLinksDAO.removeLink(removeLink.getLinkAddress());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new GenericException("Try again");
        }
    }
}
