package com.example.unispark.controller.applicationcontroller.links;

import com.example.unispark.bean.BeanLink;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.database.dao.StudentLinksDAO;
import com.example.unispark.model.LinkModel;

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
            beanLinkList.add(new BeanLink(linksItem.get(i).getLinkName(), linksItem.get(i).getLinkAddress()));
        }

        return beanLinkList;

    }
}
