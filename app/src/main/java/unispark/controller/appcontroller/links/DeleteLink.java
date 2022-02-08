package unispark.controller.appcontroller.links;

import unispark.engeneeringclasses.bean.BeanLink;
import unispark.engeneeringclasses.dao.StudentLinksDAO;
import unispark.engeneeringclasses.exceptions.GenericException;
import unispark.model.LinkModel;

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
