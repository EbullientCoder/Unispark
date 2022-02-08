package unispark.controller.guicontroller.clicontroller.student;



import unispark.controller.appcontroller.course.ManageCourses;
import unispark.engeneeringclasses.bean.courses.BeanCourse;
import unispark.engeneeringclasses.bean.student.BeanLoggedStudent;
import unispark.engeneeringclasses.exceptions.CourseAlreadyJoined;
import unispark.engeneeringclasses.exceptions.CourseDoesNotExist;
import unispark.engeneeringclasses.exceptions.GenericException;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class JoinCourse {

    public void joinCourse(BeanLoggedStudent student){
        Scanner sc = new Scanner(System.in);
        PrintStream write = System.out;

        //List of Available Courses
        List<BeanCourse> availableCourses;

        //Application Controller
        ManageCourses availableCourseAppController = new ManageCourses();
        availableCourses = availableCourseAppController.getAvaliableCourses(student);

        //Show Available Courses
        StringBuilder bld = new StringBuilder();
        bld.append("-------------------- Available Courses --------------------\n\n");

        for(int i = 0; i < availableCourses.size(); i++){
            bld.append(availableCourses.get(i).getFullName() + " (" + availableCourses.get(i).getShortName() + ")\n");
            bld.append("CFU: " + availableCourses.get(i).getCfu() + "\n");
            bld.append("YEAR: " + availableCourses.get(i).getCourseYear() + "  SESSION: " + availableCourses.get(i).getSession() + "\n");
            bld.append("POSITION: " + Integer.toString(i) + "\n\n");
        }
        write.println(bld.toString());

        //Choose the position of the course to join
        write.println("Position of the Course to JOIN: ");

        int position = 100;
        try {
            position = Integer.parseInt(sc.nextLine());

        } catch (NumberFormatException e) {
            write.println("\n\n\nERROR: not an integer. Redirecting to menu.\n\n\n");
        }

        //Check if position is in the range of available courses
        if (position >= availableCourses.size())
            write.println("\n\n\nERROR: Course not found. Redirecting to menu.\n\n\n");
        else {
            try {
                availableCourseAppController.joinCourse(student, availableCourses.get(position));
                availableCourses.remove(position);

                write.println("\n\n\nCOURSE JOINED\n\n\n");
            } catch (GenericException e) {
                e.printStackTrace();
                write.println(e.getMessage());
            } catch (CourseDoesNotExist courseDoesNotExist) {
                courseDoesNotExist.printStackTrace();
            } catch (CourseAlreadyJoined courseAlreadyJoined) {
                courseAlreadyJoined.printStackTrace();
            }
        }
    }
}
