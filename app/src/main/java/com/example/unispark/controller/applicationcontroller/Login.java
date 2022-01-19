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
            beanLoggedStudent = new BeanLoggedStudent(student.getFirstName(),
                    student.getLastName(),
                    student.getProfilePicture(),
                    student.getId(),
                    student.getFaculty(),
                    student.getAcademicYear(),
                    student.getCourses(),
                    student.getBookedExams(),
                    student.getVerbalizedExams(),
                    student.getFailedExams(),
                    student.getUniYear());
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
            beanLoggedProfessor = new BeanLoggedProfessor(
                    professor.getFirstName(),
                    professor.getLastName(),
                    professor.getProfilePicture(),
                    professor.getId(),
                    professor.getFaculty(),
                    professor.getWebsite(),
                    professor.getCourses(),
                    professor.getExams()
            );
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
            beanLoggedUniversity = new BeanLoggedUniversity(
                    university.getName(),
                    university.getProfilePicture(),
                    university.getStreetAddress(),
                    university.getFaculties()
            );

        } catch (LoginException e) {
            e.printStackTrace();
            throw new WrongUsernameOrPasswordException("Wrong username or password");
        }

        return beanLoggedUniversity;
    }
}
