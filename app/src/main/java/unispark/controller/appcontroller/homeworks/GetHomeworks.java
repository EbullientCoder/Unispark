package unispark.controller.appcontroller.homeworks;

import unispark.engeneeringclasses.bean.BeanHomework;
import unispark.engeneeringclasses.bean.professor.BeanLoggedProfessor;
import unispark.engeneeringclasses.bean.student.BeanLoggedStudent;
import unispark.engeneeringclasses.dao.HomeworkDAO;
import unispark.model.HomeworkModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetHomeworks {

    //Student: Needs a DB connection to get the Homeworks at every refresh of the HomePage
    public List<BeanHomework> getHomework(BeanLoggedStudent student) {
        List<HomeworkModel> homeworksItem = null;
        try {
            homeworksItem = HomeworkDAO.getStudentHomework(student.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return listBeanHomeworks(homeworksItem);
    }

    //Professor: Doesn't need a DB connection to get the Homeworks
    public List<BeanHomework> getHomework(BeanLoggedProfessor professor){
        List<HomeworkModel> homeworksItem;
        homeworksItem = professor.getHomeworks();

        return listBeanHomeworks(homeworksItem);
    }

    //Create a BeanHomeworkList from the ModelHomeworkList
    private List<BeanHomework> listBeanHomeworks(List<HomeworkModel> homeworksItem){
        List<BeanHomework> beanHomeworkList = new ArrayList<>();

        for (int i = 0; i < homeworksItem.size(); i++){
            BeanHomework beanHomework;
            beanHomework = new BeanHomework();
            beanHomework.setShortName(homeworksItem.get(i).getShortName());
            beanHomework.setFullName(homeworksItem.get(i).getFullName());
            beanHomework.setTitle(homeworksItem.get(i).getTitle());
            beanHomework.setExpiration(homeworksItem.get(i).getExpiration());
            beanHomework.setInstructions( homeworksItem.get(i).getInstructions());
            beanHomework.setPoints(homeworksItem.get(i).getPoints());
            beanHomeworkList.add(beanHomework);
        }
        Collections.reverse(beanHomeworkList);

        return beanHomeworkList;
    }
}
