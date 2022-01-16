package com.example.unispark.controller.applicationcontroller.course;

import com.example.unispark.bean.BeanCourse;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.database.dao.CourseDAO;
import com.example.unispark.exceptions.CourseAlreadyJoined;
import com.example.unispark.exceptions.CourseException;
import com.example.unispark.exceptions.DatabaseOperationError;
import com.example.unispark.exceptions.ExamBookedException;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.model.CourseModel;

import java.util.ArrayList;
import java.util.List;

public class JoinCourse {
    //Join Course
    public void joinCourse(BeanLoggedStudent student, BeanCourse bCourse, int position) throws CourseAlreadyJoined, GenericException {
        try {
            CourseDAO.joinCourse(student.getId(), bCourse.getFullName());
            //Add Course to the Student's Joined Courses
            List<CourseModel> joinedCourses = student.getCourses();
            joinedCourses.add(0, new CourseModel(bCourse.getId(),
                    bCourse.getShortName(),
                    bCourse.getFullName(),
                    bCourse.getCourseYear(),
                    bCourse.getCfu(),
                    bCourse.getSession(),
                    bCourse.getLink(),
                    bCourse.getFaculty(),
                    bCourse.getUniYear()));

            student.setCourses(joinedCourses);

        } catch (CourseException e) {
            e.printStackTrace();
            throw new CourseAlreadyJoined("Course already joined");

        } catch (DatabaseOperationError databaseOperationError) {
            databaseOperationError.printStackTrace();
            throw new GenericException("Cannot join course, try again");
        }

    }
}
