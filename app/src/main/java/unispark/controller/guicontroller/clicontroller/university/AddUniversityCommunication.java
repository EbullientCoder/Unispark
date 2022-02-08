package unispark.controller.guicontroller.clicontroller.university;


import unispark.controller.appcontroller.communications.AddCommunication;
import unispark.engeneeringclasses.bean.communications.BeanUniCommunication;
import unispark.engeneeringclasses.bean.university.BeanLoggedUniversity;
import unispark.engeneeringclasses.exceptions.GenericException;

import java.io.PrintStream;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Scanner;

public class AddUniversityCommunication {

    public void addUniversityCommunication(BeanLoggedUniversity university){
        Scanner sc = new Scanner(System.in);
        PrintStream write = System.out;

        //Faculties
        List<String> faculties = university.getFaculties();

        //Calendar
        OffsetDateTime offset = OffsetDateTime.now();
        String date = offset.getYear() + "-" + offset.getMonthValue() + "-" + offset.getDayOfMonth();

        //Communication's Title
        StringBuilder bld =  new StringBuilder();
        bld.append("-------------------- Add University Communication --------------------\n\n");


        //Show faculties
        int i;
        for(i = 0; i < faculties.size(); i++) bld.append(faculties.get(i) + "\nPosition: " + Integer.toString(i)  + "\n\n");
        bld.append("All" + "\nPosition: " + Integer.toString(i)  + "\n");
        write.println(bld.toString());


        //Choose the Faculty
        write.print("Faculty position: ");
        int position = 100;
        try {
            position = Integer.parseInt(sc.nextLine());

        } catch (NumberFormatException e) {
            write.println("\n\n\nERROR: not an integer. Redirecting to menu.\n\n\n");
        }


        //Check if position is in the range of available courses
        if (position > faculties.size()){
            write.println("\n\n\nERROR: Faculty not found. Redirecting to menu.\n\n\n");

        }
        else {
            //Communication's Type
            write.print("Title: ");
            String title = sc.nextLine();

            //Communication's Communication
            write.print("Communication: ");
            String text = sc.nextLine();

            //App Controller: Add Communication
            if (title.equals("") || text.equals("")) {
                write.println("\n\n\nERROR: empty items. Redirecting to the menu.\n\n\n");
            }
            else{

                //Communication Object
                BeanUniCommunication uniCommunication = new BeanUniCommunication();

                uniCommunication.setCommunication(text);
                uniCommunication.setDate(date);
                uniCommunication.setTitle(title);
                if(position != 2) uniCommunication.setFaculty(faculties.get(position));
                else uniCommunication.setFaculty("All");


                //Application Controller
                AddCommunication addCommunicationAppController = new AddCommunication();
                try {
                    addCommunicationAppController.addUniCommunication(uniCommunication);

                    write.println("\n\n\nCOMMUNICATION ADDED\n\n\n");
                } catch (GenericException e) {
                    e.printStackTrace();
                    write.println(e.getMessage());
                }
            }
        }
    }
}
