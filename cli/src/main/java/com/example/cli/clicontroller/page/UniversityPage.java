package com.example.cli.clicontroller.page;


import com.example.cli.clicontroller.CLI;
import com.example.cli.clicontroller.generic.Schedule;
import com.example.cli.clicontroller.generic.UniversityCommunications;
import com.example.cli.clicontroller.university.AddSchedule;
import com.example.cli.clicontroller.university.AddUniversityCommunication;
import com.example.cli.clicontroller.university.DeleteSchedule;
import com.example.common.bean.university.BeanLoggedUniversity;

import java.io.PrintStream;
import java.util.Scanner;

public class UniversityPage {

    CLI console;
    BeanLoggedUniversity university;

    PrintStream write = System.out;

    //Constructor
    public UniversityPage(CLI console, BeanLoggedUniversity university){
        this.console = console;
        this.university = university;
    }


    //Menu
    public void universityPage(){
        Scanner sc = new Scanner(System.in);
        String input = "";

        while(!input.equals("EXIT")){
            //Initialize Text
            presentUser();

            //Operations
            write.println("UNIVERSITY OPERATIONS:\n");

            write.println("- University Communications");
            write.println("- Add Communication");
            write.println("- Schedule");
            write.println("- Add Lesson");
            write.println("- Delete Lesson");

            //Select Operation
            input = sc.nextLine();
            universityMenu(input);

            //Continue
            write.print("\n\nContinue (Type anything): ");
            sc.nextLine();
        }
    }

    //Present the User
    private void presentUser(){
        write.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        write.println(university.getName());
        write.println(university.getStreetAddress() + "\n\n");
    }



    //University Menu
    private void universityMenu(String input) {

        //Menu
        switch (input){
            case "University Communications":
                UniversityCommunications universityCommunications = new UniversityCommunications();
                universityCommunications.universityCommunications();
                break;

            case "Add Communication":
                AddUniversityCommunication addUniversityCommunication = new AddUniversityCommunication();
                addUniversityCommunication.addUniversityCommunication(university);
                break;

            case "Schedule":
                Schedule schedule = new Schedule();
                schedule.schedule(university);
                break;

            case "Add Lesson":
                AddSchedule addSchedule = new AddSchedule();
                addSchedule.addSchedule(university);
                break;

            case "Delete Lesson":
                DeleteSchedule deleteSchedule = new DeleteSchedule();
                deleteSchedule.deleteSchedule(university);
                break;

            default:
                write.println("\n\n\nERROR: Operation not found. Redirecting to the menu.\n\n\n");

                break;
        }
    }
}
