package unispark.controller.guicontroller.clicontroller.university;



import unispark.controller.appcontroller.course.ManageCourses;
import unispark.controller.appcontroller.schedule.AddLesson;
import unispark.engeneeringclasses.bean.BeanLesson;
import unispark.engeneeringclasses.bean.courses.BeanCoursesNames;
import unispark.engeneeringclasses.bean.university.BeanLoggedUniversity;
import unispark.engeneeringclasses.exceptions.GenericException;
import unispark.engeneeringclasses.exceptions.LessonAlreadyExists;

import java.io.PrintStream;
import java.util.Scanner;

public class AddSchedule {

    public void addSchedule(BeanLoggedUniversity university){
        Scanner sc = new Scanner(System.in);
        PrintStream write = System.out;

        //Get Courses
        //Application Controller: Get Courses
        ManageCourses getCoursesAppController = new ManageCourses();
        BeanCoursesNames coursesNames = getCoursesAppController.getCoursesNamesByFaculty(university.getFaculties());

        //Show Lessons
        StringBuilder bld = new StringBuilder();
        bld.append("-------------------- Add Lesson --------------------\n\n");

        for(int i = 0; i < coursesNames.getCourses().size(); i++){
            bld.append(coursesNames.getCourses().get(i) + "\n");
            bld.append("Position: " + Integer.toString(i) + "\n\n");
        }
        write.append(bld.toString());

        //Choose the course
        write.append("Course's position: ");
        int position = 100;
        try {
            position = Integer.parseInt(sc.nextLine());

        } catch (NumberFormatException e) {
            write.append("\n\n\nERROR: not an integer. Redirecting to menu.\n\n\n");
        }

        //Check if position is in the range of available courses
        if (position > coursesNames.getCourses().size()){
            write.append( "\n\n\nERROR: Faculty not found. Redirecting to menu.\n\n\n");

        }
        else{
            //Lesson's day
            write.append( "Day: ");
            String day = sc.nextLine();

            //Lesson's hour
            write.append("Hour: ");
            String hour = sc.nextLine();

            //Check the Field
            if(!day.equals("") && !hour.equals("")){
                //Creating new Lesson
                BeanLesson bLesson = new BeanLesson();

                bLesson.setLessonName(coursesNames.getCourses().get(position));
                bLesson.setDay(day);
                bLesson.setHour(hour);

                //Application Controller: Add Lesson
                AddLesson addLessonAppController = new AddLesson();
                try {
                    addLessonAppController.addLesson(bLesson);

                    write.append( "\n\n\nLESSON ADDED\n\n\n");
                } catch (GenericException | LessonAlreadyExists e) {
                    e.printStackTrace();
                    write.append( e.getMessage());
                }

            }
            else{
                write.append( "\n\n\nERROR: empty fields. Going back to menu.\n\n\n");
            }
        }
    }
}
