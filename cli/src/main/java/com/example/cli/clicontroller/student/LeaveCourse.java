package com.example.cli.clicontroller.student;


import com.example.common.applicationcontroller.course.ManageCourses;
import com.example.common.bean.courses.BeanCourse;
import com.example.common.bean.student.BeanLoggedStudent;
import com.example.common.exceptions.CourseNeverJoined;
import com.example.common.exceptions.ExamBookedException;
import com.example.common.exceptions.GenericException;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class LeaveCourse {

    public void leaveCourse(BeanLoggedStudent student){
        Scanner sc = new Scanner(System.in);
        PrintStream write = System.out;

        //Joined Courses List
        //Application Controller
        ManageCourses joinedCoursesAppController = new ManageCourses();
        List<BeanCourse> joinedCourses = joinedCoursesAppController.getCourses(student);


        //Show Joined Courses
        write.println("-------------------- Joined Courses --------------------\n\n");

        for(int i = 0; i < joinedCourses.size(); i++){
            write.println(joinedCourses.get(i).getFullName() + " (" + joinedCourses.get(i).getShortName() + ")\n");
            write.println("CFU: " + joinedCourses.get(i).getCfu() + "\n");
            write.println("YEAR: " + joinedCourses.get(i).getCourseYear() + "\n");
            write.println("SESSION: " + joinedCourses.get(i).getSession() + "\n");
            write.println(" Position: " + Integer.toString(i) + "\n\n");
        }

        //Choose the position of the course to leave
        write.println("Position of the Course to LEAVE: ");

        //Check if the position is numeric
        int position = 100;
        try {
            position = Integer.parseInt(sc.nextLine());

        } catch (NumberFormatException e) {
            write.println("\n\n\nERROR: not an integer. Redirecting to menu.\n\n\n");
        }

        //Check if position is in the range of joined courses
        if (position >= joinedCourses.size())
            write.println("\n\n\nERROR: Course not found. Redirecting to menu.\n\n\n");
        else {

            try {
                joinedCoursesAppController.leaveCourse(student, joinedCourses.get(position), position);
                joinedCourses.remove(position);

                write.println("\n\n\nCOURSE LEFT\n\n\n");
            } catch (GenericException | ExamBookedException | CourseNeverJoined e) {
                e.printStackTrace();
            }
        }
    }
}
