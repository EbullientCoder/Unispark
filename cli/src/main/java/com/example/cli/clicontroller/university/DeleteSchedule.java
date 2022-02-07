package com.example.cli.clicontroller.university;



import com.example.cli.clicontroller.generic.Schedule;
import com.example.common.applicationcontroller.course.ManageCourses;
import com.example.common.applicationcontroller.schedule.DeleteLesson;
import com.example.common.applicationcontroller.schedule.GetScheduleUniversity;
import com.example.common.bean.BeanLesson;
import com.example.common.bean.courses.BeanCourse;
import com.example.common.bean.university.BeanLoggedUniversity;
import com.example.common.exceptions.GenericException;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class DeleteSchedule {

    public void deleteSchedule(BeanLoggedUniversity university){
        Scanner sc = new Scanner(System.in);
        PrintStream write = System.out;

        //Get University Lessons
        List<BeanLesson> lessons;

        //Courses
        ManageCourses manageCourses = new ManageCourses();
        List<BeanCourse> courses = manageCourses.getFacultyCourses(university.getFaculties());

        //Get Day
        Schedule schedule = new Schedule();
        String day = schedule.getDay();

        //Application Controller
        GetScheduleUniversity getScheduleUniversity = new GetScheduleUniversity();
        lessons = getScheduleUniversity.getLessons(day, courses);
        //Show schedule
        schedule.showSchedule(lessons);


        //Choose the lesson's position
        write.println("Position of the Lesson you want to DELETE: ");

        int position = 100;
        try {
            position = Integer.parseInt(sc.nextLine());

        } catch (NumberFormatException e) {
            write.println("\n\n\nERROR: not an integer. Redirecting to menu.\n\n\n");
        }

        //Check if position is in the range of available courses
        if (position >= lessons.size()){
            write.println("\n\n\nERROR: Lesson not found. Redirecting to menu.\n\n\n");

        }
        else{
            try {
                //App Controller: Delete Lesson
                DeleteLesson deleteLesson = new DeleteLesson();
                deleteLesson.deleteLesson(lessons.get(position));

                write.println("\n\n\nLESSON DELETED\n\n\n");
            }catch (GenericException e) {
                e.printStackTrace();
                write.println(e.getMessage());
            }
        }
    }
}
