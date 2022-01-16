package com.example.unispark.controller.applicationcontroller.links;

import com.example.unispark.bean.BeanLink;
import com.example.unispark.database.dao.StudentLinksDAO;
import com.example.unispark.exceptions.DatabaseOperationError;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.model.LinkModel;

public class DeleteLink {
    //Delete Link
    public void deleteLink(BeanLink link) throws GenericException
    {
        //Remove the Connection inside the DB
        LinkModel removeLink = new LinkModel(link.getLinkName(), link.getLinkAddress());
        try {
            StudentLinksDAO.removeLink(removeLink.getLinkName());
        } catch (DatabaseOperationError databaseOperationError) {
            databaseOperationError.printStackTrace();
            throw new GenericException("Cannot delete link, try again");
        }
    }
}
