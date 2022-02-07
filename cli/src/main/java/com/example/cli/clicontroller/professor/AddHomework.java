package com.example.cli.clicontroller.professor;



import com.example.common.applicationcontroller.course.ManageCourses;
import com.example.common.applicationcontroller.homeworks.AddHomeworkAppController;
import com.example.common.bean.BeanHomework;
import com.example.common.bean.courses.BeanCourse;
import com.example.common.bean.professor.BeanLoggedProfessor;
import com.example.common.exceptions.GenericException;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class AddHomework {

    public void addHomework(BeanLoggedProfessor professor){
        Scanner sc = new Scanner(System.in);
        PrintStream write = System.out;

        //Professor Courses
        List<BeanCourse> professorCourses;

        //Getting Professor Courses
        //Application Controller
        ManageCourses professorCoursesController = new ManageCourses();
        professorCourses = professorCoursesController.getCourses(professor);

        //Show Professor Courses
        //Show Professor Courses
        int position = ShowProfessorCourses.showProfessorCourses(professorCourses);


        //Check if position is in the range of available courses
        if (position >= professorCourses.size()){
            write.println("\n\n\nERROR: Course not found. Redirecting to menu.\n\n\n");

        }
        else{
            //Homework's Title
            write.print("Title: ");
            String title = sc.nextLine();

            //Homework's Expiration
            write.print("Expiration Date: ");
            String date = sc.nextLine();

            //Homework's Instructions
            write.print("Instructions: ");
            String instructions = sc.nextLine();

            //Homework's Points
            write.print("Points: ");
            String points = sc.nextLine();


            //App Controller: Add Homework
            if (title.equals("") || instructions.equals("") || points.equals("") || date.equals("")) {
                write.println("\n\n\nERROR: empty items.\n\n\n");
            }
            else{
                //Homework Object
                BeanHomework homework =  new BeanHomework();

                homework.setShortName(professor.getCourses().get(position).getShortName());
                homework.setFullName(professor.getCourses().get(position).getFullName());
                homework.setTitle(title);
                homework.setExpiration(date);
                homework.setInstructions(instructions);
                homework.setPoints(points);
                //homework.setTrackProfessor(professor.getId());


                //Application Controller
                AddHomeworkAppController addHomeworkAppController = new AddHomeworkAppController();
                try {
                    addHomeworkAppController.addHomework(homework, professor);

                    write.println("\n\n\nHOMEWORK ADDED\n\n\n");
                } catch (GenericException e) {
                    e.printStackTrace();
                    write.println(e.getMessage());
                }
            }
        }
    }
}
