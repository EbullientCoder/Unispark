package unispark.engeneeringclasses.applicationcontroller.communications;

import unispark.engeneeringclasses.bean.communications.BeanProfessorCommunication;
import unispark.engeneeringclasses.bean.communications.BeanUniCommunication;
import unispark.engeneeringclasses.dao.CommunicationsDAO;
import unispark.engeneeringclasses.exceptions.CourseDoesNotExist;
import unispark.engeneeringclasses.exceptions.GenericException;
import unispark.engeneeringclasses.model.communications.ProfessorCommunicationModel;
import unispark.engeneeringclasses.model.communications.UniversityCommunicationModel;

import java.sql.SQLException;

public class AddCommunication {
    //Add professor Communication
    public void addProfCommunication(BeanProfessorCommunication communication) throws GenericException, CourseDoesNotExist {
        ProfessorCommunicationModel communicationModel = new ProfessorCommunicationModel(
                communication.getProfilePhoto(),
                communication.getShortCourseName(),
                communication.getFullName(),
                communication.getProfessorName(),
                communication.getDate(),
                communication.getType(),
                communication.getCommunication()
        );

        try {
            CommunicationsDAO.addProfessorCommunication(communicationModel);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new GenericException("Try again");
        }

    }



    //Add Uni Communication
    public void addUniCommunication(BeanUniCommunication communication) throws GenericException{
        //Add Lesson into the DB
        UniversityCommunicationModel universityCommunicationModel;
        universityCommunicationModel = new UniversityCommunicationModel(
                communication.getBackground(),
                communication.getTitle(),
                communication.getDate(),
                communication.getCommunication(),
                communication.getFaculty());

        try {
            CommunicationsDAO.addUniversityCommunication(universityCommunicationModel);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new GenericException("Try again");
        }
    }

}
