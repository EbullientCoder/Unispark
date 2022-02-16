package unispark.controller.appcontroller.links;

import unispark.engeneeringclasses.bean.BeanLink;
import unispark.engeneeringclasses.bean.courses.BeanCourse;
import unispark.engeneeringclasses.bean.professor.BeanProfessorDetails;
import unispark.engeneeringclasses.bean.student.BeanLoggedStudent;
import unispark.engeneeringclasses.dao.ProfessorDAO;
import unispark.engeneeringclasses.dao.StudentLinksDAO;
import unispark.engeneeringclasses.exceptions.GenericException;
import unispark.engeneeringclasses.exceptions.LinkAlreadyExists;
import unispark.model.CourseModel;
import unispark.model.LinkModel;
import unispark.model.ProfessorModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageLinks {
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

    //Faculty professors
    public List<BeanProfessorDetails> setFacultyProfessors(BeanLoggedStudent student) {
        List<BeanProfessorDetails> beanProfessorDetailsList = new ArrayList<>();
        List<ProfessorModel> professorsItem;
        try {
            professorsItem = ProfessorDAO.getFacultyProfessors(student.getFaculty());

            for (int i = 0; i < professorsItem.size(); i++){
                BeanProfessorDetails beanProfessorDetails;
                beanProfessorDetails = new BeanProfessorDetails();
                beanProfessorDetails.setFaculty(professorsItem.get(i).getFaculty());
                beanProfessorDetails.setCourses(this.beanCourses(professorsItem.get(i).getCourses()));
                beanProfessorDetails.setId(professorsItem.get(i).getId());
                beanProfessorDetails.setProfilePicture(professorsItem.get(i).getProfilePicture());
                beanProfessorDetails.setWebsite(professorsItem.get(i).getWebsite());
                beanProfessorDetails.setFirstName(professorsItem.get(i).getFirstName());
                beanProfessorDetails.setLastName(professorsItem.get(i).getLastName());

                beanProfessorDetailsList.add(beanProfessorDetails);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return beanProfessorDetailsList;

    }


    private BeanCourse beanCourse(CourseModel courseModel){
        BeanCourse course;
        course  = new BeanCourse();
        course.setShortName(courseModel.getShortName());
        course.setFullName(courseModel.getFullName());
        course.setCourseYear(courseModel.getCourseYear());
        course.setCfu(courseModel.getCfu());
        course.setFaculty(courseModel.getFaculty());
        course.setId(String.valueOf(courseModel.getId()));
        course.setLink(courseModel.getLink());
        course.setSession(courseModel.getSession());
        course.setUniYear(courseModel.getUniYear());

        return course;
    }


    private List<BeanCourse> beanCourses(List<CourseModel> listCourses){
        List<BeanCourse> courseList = new ArrayList<>();
        CourseModel course;
        for (int i = 0; i < listCourses.size(); i++){
            course = listCourses.get(i);
            courseList.add(this.beanCourse(course));
        }
        return courseList;
    }
}
