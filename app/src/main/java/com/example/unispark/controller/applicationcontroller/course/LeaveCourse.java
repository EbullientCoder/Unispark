package com.example.unispark.controller.applicationcontroller.course;

import com.example.unispark.bean.BeanCourse;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.database.dao.CourseDAO;
import com.example.unispark.exceptions.CourseException;
import com.example.unispark.exceptions.DatabaseOperationError;
import com.example.unispark.exceptions.ExamBookedException;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.model.CourseModel;

import java.util.List;

public class LeaveCourse {
    //Remove Course Joined from DB
    public void leaveCourse(BeanLoggedStudent student, BeanCourse bCourse, int position) throws ExamBookedException, GenericException
    {
        List<CourseModel> courses = student.getCourses();

        try {
            CourseDAO.leaveCourse(student.getId(), bCourse.getShortName());
            courses.remove(position);
            student.setCourses(courses);

        } catch (CourseException e) {
            e.printStackTrace();
            throw new ExamBookedException("Cannot leave course, exam booked");

        } catch (DatabaseOperationError databaseOperationError) {
            databaseOperationError.printStackTrace();
            throw new GenericException("Cannot leave course, try again..");
        }


    }
}
