package unispark.engeneeringclasses.applicationcontroller.links;

import unispark.engeneeringclasses.bean.BeanLink;
import unispark.engeneeringclasses.bean.student.BeanLoggedStudent;
import unispark.engeneeringclasses.dao.StudentLinksDAO;
import unispark.engeneeringclasses.exceptions.GenericException;
import unispark.engeneeringclasses.exceptions.LinkAlreadyExists;
import unispark.engeneeringclasses.model.LinkModel;

import java.sql.SQLException;

public class AddLink {
    //Add Link
    public void addLink(BeanLoggedStudent student, BeanLink link) throws LinkAlreadyExists, GenericException
    {
        LinkModel newLink = new LinkModel(link.getLinkName(), link.getLinkAddress());

        try {
            StudentLinksDAO.addStudentLink(newLink, student.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new GenericException("Try again");
        }
    }
}
