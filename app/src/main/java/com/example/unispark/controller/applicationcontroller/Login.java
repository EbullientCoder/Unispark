package com.example.unispark.controller.applicationcontroller;

import com.example.unispark.bean.login.BeanLoggedProfessor;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.bean.login.BeanLoggedUniversity;
import com.example.unispark.bean.login.BeanUser;
import com.example.unispark.database.dao.ProfessorDAO;
import com.example.unispark.database.dao.StudentDAO;
import com.example.unispark.database.dao.UniversityDAO;
import com.example.unispark.exceptions.WrongUsernameOrPasswordException;
import com.example.unispark.model.ProfessorModel;
import com.example.unispark.model.StudentModel;
import com.example.unispark.model.UniversityModel;

import java.sql.SQLException;

import javax.security.auth.login.LoginException;



public class Login {

    //Login Methods
    public BeanLoggedStudent studentLogin(BeanUser user) throws WrongUsernameOrPasswordException, SQLException {
        BeanLoggedStudent beanLoggedStudent = null;
        StudentModel student = null;
        try {
            student = StudentDAO.selectStudent(user.getEmail(), user.getPassword());
            beanLoggedStudent = new BeanLoggedStudent();


            beanLoggedStudent.setFirstName(student.getFirstName());
            beanLoggedStudent.setLastName(student.getLastName());
            beanLoggedStudent.setProfilePicture(student.getProfilePicture());
            beanLoggedStudent.setId(student.getId());
            beanLoggedStudent.setFaculty(student.getFaculty());
            beanLoggedStudent.setAcademicYear(student.getAcademicYear());
            beanLoggedStudent.setCourses(student.getCourses());
            beanLoggedStudent.setBookedExams(student.getBookedExams());
            beanLoggedStudent.setUniYear(student.getUniYear());

        } catch (LoginException e) {
            e.printStackTrace();
            throw new WrongUsernameOrPasswordException("Wrong username or password");
        }

        return beanLoggedStudent;
    }

    public BeanLoggedProfessor professorLogin(BeanUser user) throws WrongUsernameOrPasswordException, SQLException {

        BeanLoggedProfessor beanLoggedProfessor = null;
        ProfessorModel professor = null;

        try {
            professor = ProfessorDAO.selectProfessor(user.getEmail(), user.getPassword());
            beanLoggedProfessor = new BeanLoggedProfessor();


            beanLoggedProfessor.setFirstName(professor.getFirstName());
            beanLoggedProfessor.setLastName(professor.getLastName());
            beanLoggedProfessor.setProfilePicture(professor.getProfilePicture());
            beanLoggedProfessor.setId(professor.getId());
            beanLoggedProfessor.setFaculty(professor.getFaculty());
            beanLoggedProfessor.setCourses(professor.getCourses());
            beanLoggedProfessor.setWebsite(professor.getWebsite());
        } catch (LoginException e) {
            e.printStackTrace();
            throw new WrongUsernameOrPasswordException("Wrong username or password");
        }

        return beanLoggedProfessor;
    }

    public BeanLoggedUniversity universityLogin(BeanUser user) throws WrongUsernameOrPasswordException, SQLException {
        BeanLoggedUniversity beanLoggedUniversity = null;
        UniversityModel university = null;

        try {
            university = UniversityDAO.selectUniversity(user.getEmail(), user.getPassword());
            beanLoggedUniversity = new BeanLoggedUniversity();
            beanLoggedUniversity.setFaculties(university.getFaculties());
            beanLoggedUniversity.setName(university.getName());
            beanLoggedUniversity.setProfilePicture(university.getProfilePicture());
            beanLoggedUniversity.setStreetAddress(university.getStreetAddress());

        } catch (LoginException e) {
            e.printStackTrace();
            throw new WrongUsernameOrPasswordException("Wrong username or password");
        }

        return beanLoggedUniversity;
    }
}
