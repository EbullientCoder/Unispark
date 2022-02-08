package unispark.cli.clicontroller.student;


import unispark.engeneeringclasses.applicationcontroller.course.ManageCourses;
import unispark.engeneeringclasses.bean.courses.BeanCourse;
import unispark.engeneeringclasses.bean.student.BeanLoggedStudent;

import java.util.List;

public class AvailableCourses {

    public void availableCourses(BeanLoggedStudent student){
        //Available Courses
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
            bld.append("YEAR: " + availableCourses.get(i).getCourseYear() + "\n");
            bld.append("SESSION: " + availableCourses.get(i).getSession() + "\n\n");
        }
        System.out.println(bld.toString());
    }
}
