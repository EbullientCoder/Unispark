package com.example.common.applicationcontroller.links;

import com.example.common.bean.BeanLink;
import com.example.common.database.dao.StudentLinksDAO;
import com.example.common.exceptions.GenericException;
import com.example.common.model.LinkModel;

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
