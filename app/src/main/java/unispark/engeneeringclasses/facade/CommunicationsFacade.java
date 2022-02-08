package unispark.engeneeringclasses.facade;

import unispark.engeneeringclasses.dao.CommunicationsDAO;
import unispark.engeneeringclasses.model.communications.ProfessorCommunicationModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommunicationsFacade {

    private static CommunicationsFacade instance=null;
    private CommunicationsFacade()
    {

    }
    public static CommunicationsFacade getInstance()
    {
        if(instance==null)
        {
            instance=new CommunicationsFacade();
        }
        return instance;
    }

    public List<ProfessorCommunicationModel> getAllCoursesCommunications(List<String> coursesShortNames, List<String> coursesFullNames) throws SQLException {
        List<ProfessorCommunicationModel> communicationList = new ArrayList<>();
        List<ProfessorCommunicationModel> tempList;
        for (int i = 0; i < coursesShortNames.size(); i++)
        {
            tempList = CommunicationsDAO.getCourseCommunications(coursesShortNames.get(i), coursesFullNames.get(i));
            communicationList.addAll(tempList);
        }
        return communicationList;
    }
}
