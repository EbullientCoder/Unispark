package unispark.engeneeringclasses.applicationcontroller.links;

import unispark.engeneeringclasses.bean.BeanLink;
import unispark.engeneeringclasses.bean.student.BeanLoggedStudent;
import unispark.engeneeringclasses.dao.StudentLinksDAO;
import unispark.engeneeringclasses.model.LinkModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowLinks {
    //Students Links
    public List<BeanLink> showLinks(BeanLoggedStudent student){
        List<BeanLink> beanLinkList = new ArrayList<>();

        List<LinkModel> linksItem = null;
        try {
            linksItem = StudentLinksDAO.getStudentLinks(student.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (int i = 0; i < linksItem.size(); i++){
            BeanLink beanLink;
            beanLink = new BeanLink();
            beanLink.setLinkAddress(linksItem.get(i).getLinkAddress());
            beanLink.setLinkName(linksItem.get(i).getLinkName());
            beanLinkList.add(beanLink);
        }

        return beanLinkList;

    }
}
