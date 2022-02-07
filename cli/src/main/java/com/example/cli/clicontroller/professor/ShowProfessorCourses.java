package com.example.cli.clicontroller.professor;


import com.example.common.bean.courses.BeanCourse;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class ShowProfessorCourses {

    private ShowProfessorCourses(){}

    public static int showProfessorCourses(List<BeanCourse> professorCourses){
        Scanner sc = new Scanner(System.in);
        PrintStream write = System.out;

        //Show Professor Courses
        StringBuilder bld = new StringBuilder();

        bld.append("-------------------- Add Professor Communication --------------------\n\n");

        for(int i = 0; i < professorCourses.size(); i++){
            bld.append(professorCourses.get(i).getFullName() + "\n");
            bld.append("Position: " + Integer.toString(i) + "\n\n");
        }
        write.println(bld.toString());

        //Communication's Course
        write.print("Course's Position: ");
        int position = 100;
        try {
            position = Integer.parseInt(sc.nextLine());

        } catch (NumberFormatException e) {
            write.println("\n\n\nERROR: not an integer. Redirecting to menu.\n\n\n");
        }

        return position;
    }
}
