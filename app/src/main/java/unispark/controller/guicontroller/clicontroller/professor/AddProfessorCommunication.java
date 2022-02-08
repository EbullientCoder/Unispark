package unispark.controller.guicontroller.clicontroller.professor;


import unispark.controller.appcontroller.communications.AddCommunication;
import unispark.controller.appcontroller.course.ManageCourses;
import unispark.engeneeringclasses.bean.communications.BeanProfessorCommunication;
import unispark.engeneeringclasses.bean.courses.BeanCourse;
import unispark.engeneeringclasses.bean.professor.BeanLoggedProfessor;
import unispark.engeneeringclasses.exceptions.CourseDoesNotExist;
import unispark.engeneeringclasses.exceptions.GenericException;

import java.io.PrintStream;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Scanner;

public class AddProfessorCommunication {

    public void addCommunication(BeanLoggedProfessor professor){
        Scanner sc = new Scanner(System.in);
        PrintStream write = System.out;

        //Professor Courses
        List<BeanCourse> professorCourses;

        //Calendar
        OffsetDateTime offset = OffsetDateTime.now();
        String date = offset.getYear() + "-" + offset.getMonthValue() + "-" + offset.getDayOfMonth();

        //Getting Professor Courses
        //Application Controller
        ManageCourses professorCoursesController = new ManageCourses();
        professorCourses = professorCoursesController.getCourses(professor);

        //Show Professor Courses
        int position = ShowProfessorCourses.showProfessorCourses(professorCourses);

        //Check if position is in the range of available courses
        if (position >= professorCourses.size()){
            write.println("\n\n\nERROR: Course not found. Redirecting to menu.\n\n\n");

        }
        else{
            //Communication's Type
            write.print("Type: ");
            String type = sc.nextLine();

            //Communication's Communication
            write.print("Communication: ");
            String text = sc.nextLine();


            //App Controller: Add Communication
            if (type.equals("") || text.equals("")) {
                write.println("\n\n\nERROR: empty items. Redirecting to the menu.\n\n\n");
            } else {

                //Communication Object
                BeanProfessorCommunication profCommunication = new BeanProfessorCommunication();

                profCommunication.setProfilePhoto(professor.getProfilePicture());
                profCommunication.setShortCourseName(professor.getCourses().get(position).getShortName());
                profCommunication.setFullName(professor.getCourses().get(position).getFullName());
                profCommunication.setProfessorName(professor.getFirstName() + " " + professor.getLastName());
                profCommunication.setDate(date);
                profCommunication.setType(type);
                profCommunication.setCommunication(text);


                //Application Controller
                AddCommunication addCommunicationAppController = new AddCommunication();
                try {
                    addCommunicationAppController.addProfCommunication(profCommunication);

                    write.println("\n\n\nCOMMUNICATION ADDED\n\n\n");

                } catch (GenericException | CourseDoesNotExist e) {
                    e.printStackTrace();

                    write.println(e.getMessage());
                }
            }
        }

    }
}
