package unispark.cli.clicontroller.generic;


import unispark.engeneeringclasses.applicationcontroller.course.ManageCourses;
import unispark.engeneeringclasses.applicationcontroller.schedule.GetScheduleStudent;
import unispark.engeneeringclasses.applicationcontroller.schedule.GetScheduleUniversity;
import unispark.engeneeringclasses.bean.BeanLesson;
import unispark.engeneeringclasses.bean.courses.BeanCourse;
import unispark.engeneeringclasses.bean.student.BeanLoggedStudent;
import unispark.engeneeringclasses.bean.university.BeanLoggedUniversity;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class Schedule {

    Scanner sc = new Scanner(System.in);
    PrintStream write = System.out;

    //Student
    public void schedule(BeanLoggedStudent student){
        //Lessons
        List<BeanLesson> lessons;

        //Day
        String day = getDay();

        //Application Controller
        GetScheduleStudent studentScheduleAppController = new GetScheduleStudent();
        lessons = studentScheduleAppController.getLessons(student, day);

        //Show
        showSchedule(lessons);
    }


    //University
    public void schedule(BeanLoggedUniversity university){
        //Lessons
        List<BeanLesson> lessons;
        //Courses
        ManageCourses manageCourses = new ManageCourses();
        List<BeanCourse> courses = manageCourses.getFacultyCourses(university.getFaculties());

        //Day
        String day = getDay();

        //Application Controller
        GetScheduleUniversity getScheduleUniversity = new GetScheduleUniversity();
        lessons = getScheduleUniversity.getLessons(day, courses);

        //Show
        showSchedule(lessons);
    }



    //Get Day
    public String getDay(){
        //Day
        write.print("\n\n\nDay: ");
        String day = sc.nextLine();
        while(!day.equals("monday") && !day.equals("tuesday") && !day.equals("wednesday") && !day.equals("thursday") && !day.equals("friday") && !day.equals("saturday") && !day.equals("sunday")){
            write.println("\n\n\nERROR: day not found.");

            write.print("Day: ");
            day = sc.nextLine();
        }

        return day;
    }


    //Show Schedule
    public void showSchedule(List<BeanLesson> lessons){
        //App Controller: GetScheduleUniversity
        //Sorting the Lessons
        GetScheduleUniversity getScheduleUniversity = new GetScheduleUniversity();
        getScheduleUniversity.lessonsSort(lessons);

        //Show Schedule
        StringBuilder bld = new StringBuilder();
        bld.append("\n\n-------------------- Schedule --------------------\n\n");

        for(int i = 0; i < lessons.size(); i++){
            bld.append(lessons.get(i).getHour() + "   " + lessons.get(i).getLessonName() + "\n");
            bld.append("Position: " + Integer.toString(i) + "\n\n");
        }

        write.println(bld.toString());
    }
}
