package com.example.cli.clicontroller.professor;



import com.example.common.applicationcontroller.course.ManageCourses;
import com.example.common.applicationcontroller.exams.AddExamAppController;
import com.example.common.bean.courses.BeanCourse;
import com.example.common.bean.exams.BeanBookExam;
import com.example.common.bean.professor.BeanLoggedProfessor;
import com.example.common.exceptions.ExamAlreadyExists;
import com.example.common.exceptions.GenericException;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class AddExam {

    public void addExam(BeanLoggedProfessor professor){
        Scanner sc = new Scanner(System.in);
        PrintStream write = System.out;

        //Professor Courses
        List<BeanCourse> professorCourses;

        //Getting Professor Courses
        //Application Controller
        ManageCourses professorCoursesController = new ManageCourses();
        professorCourses = professorCoursesController.getCourses(professor);

        //Show Professor Courses

        write.println("-------------------- Add Exam --------------------\n\n");

        for(int i = 0; i < professorCourses.size(); i++){
            write.println(professorCourses.get(i).getFullName() + "\n");
            write.println("Position: " + Integer.toString(i) + "\n\n");
        }

        //Communication's Course
        write.print("Course's Position: ");
        int position = 100;
        try {
            position = Integer.parseInt(sc.nextLine());

        } catch (NumberFormatException e) {
            write.println("\n\n\nERROR: not an integer. Redirecting to menu.\n\n\n");
        }


        //Check if position is in the range of available courses
        if (position >= professorCourses.size()){
            write.println("\n\n\nERROR: Course not found. Redirecting to menu.\n\n\n");

        }
        else{
            //Exam's Date
            write.print("Day: ");
            String day = sc.nextLine();
            write.print("Month: ");
            String month = sc.nextLine();
            write.print("Year: ");
            String year = sc.nextLine();

            String date = year + "-" + month + "-" + day;

            //Exam's Building
            write.print("Building: ");
            String building = sc.nextLine();

            //Exam's Classroom
            write.print("Classroom: ");
            String classroom = sc.nextLine();


            if (building.equals("") || classroom.equals("")) {
                write.println("\n\n\nERROR: empty items\n\n\n");
            }
            else{
                //Exam Object
                BeanBookExam exam = new BeanBookExam();

                exam.setName(professor.getCourses().get(position).getFullName());
                exam.setYear(professor.getCourses().get(position).getCourseYear());
                exam.setDate(date);
                exam.setCfu(professor.getCourses().get(position).getCfu());
                exam.setClassroom(classroom);
                exam.setBuilding(building);
                exam.setId(10);


                //Application Controller
                AddExamAppController addExamAppController = new AddExamAppController();
                try {
                    addExamAppController.addExam(exam, professor);

                    write.println("\n\n\nEXAM ADDED\n\n\n");
                } catch (ExamAlreadyExists | GenericException e) {
                    e.printStackTrace();
                    write.println(e.getMessage());
                }
            }
        }
    }
}
