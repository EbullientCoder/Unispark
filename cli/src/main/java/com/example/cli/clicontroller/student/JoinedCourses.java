package com.example.cli.clicontroller.student;


import com.example.common.applicationcontroller.course.ManageCourses;
import com.example.common.bean.courses.BeanCourse;
import com.example.common.bean.student.BeanLoggedStudent;

import java.io.PrintStream;
import java.util.List;

public class JoinedCourses {

    public void joinedCourses(BeanLoggedStudent student){
        PrintStream write = System.out;

        //Joined Courses List
        List<BeanCourse> joinedCourses;

        //Application Controller
        ManageCourses joinedCoursesAppController = new ManageCourses();
        joinedCourses = joinedCoursesAppController.getCourses(student);

        //Show Joined Courses
        StringBuilder bld = new StringBuilder();
        bld.append("-------------------- Joined Courses --------------------\n\n");

        for(int i = 0; i < joinedCourses.size(); i++){
            bld.append(joinedCourses.get(i).getFullName() + " (" + joinedCourses.get(i).getShortName() + ")\n");
            bld.append("CFU: " + joinedCourses.get(i).getCfu() + "\n");
            bld.append("YEAR: " + joinedCourses.get(i).getCourseYear() + "\n");
            bld.append("SESSION: " + joinedCourses.get(i).getSession() + "\n\n");
        }
        write.println(bld.toString());

    }
}
