package unispark.controller.guicontroller.clicontroller.page;



import unispark.controller.guicontroller.clicontroller.CLI;
import unispark.controller.guicontroller.clicontroller.generic.Homeworks;
import unispark.controller.guicontroller.clicontroller.generic.UniversityCommunications;
import unispark.controller.guicontroller.clicontroller.professor.AddExam;
import unispark.controller.guicontroller.clicontroller.professor.AddExamGrade;
import unispark.controller.guicontroller.clicontroller.professor.AddHomework;
import unispark.controller.guicontroller.clicontroller.professor.AddProfessorCommunication;
import unispark.controller.guicontroller.clicontroller.professor.AssignedExams;
import unispark.engeneeringclasses.bean.professor.BeanLoggedProfessor;

import java.io.PrintStream;
import java.util.Scanner;

public class ProfessorPage {

    CLI console;
    BeanLoggedProfessor professor;

    PrintStream write = System.out;

    //Constructor
    public ProfessorPage(CLI console, BeanLoggedProfessor professor){
        this.console = console;
        this.professor = professor;
    }


    //Menu
    public void professorPage(){
        Scanner sc = new Scanner(System.in);
        String input = "";

        while(!input.equals("EXIT")){
            //Initialize Text
            presentUser();

            //Operations
            write.println("PROFESSOR OPERATIONS:\n");

            write.println("- University Communications");
            write.println("- Add Communication");
            write.println("- Homeworks");
            write.println("- Add Homework");
            write.println("- Assigned Exams");
            write.println("- Add Exam");
            write.println("- Verbalize Exam");

            //Select Operation
            input = sc.nextLine();
            professorMenu(input);

            //Continue
            write.print("\n\nContinue (Type anything): ");
            sc.nextLine();
        }
    }

    //Present the User
    private void presentUser(){
        write.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        write.println(professor.getFirstName() + " " + professor.getLastName());
        write.println(professor.getFaculty() + "\n\n");
    }



    //Professor Menu
    private void professorMenu(String input) {

        //Menu
        switch (input) {
            case "University Communications":
                UniversityCommunications universityCommunications = new UniversityCommunications();
                universityCommunications.universityCommunications(professor);
                break;

            case "Homeworks":
                Homeworks homeworks = new Homeworks();
                homeworks.homeworks(professor);
                break;

            case "Add Communication":
                AddProfessorCommunication addProfessorCommunication = new AddProfessorCommunication();
                addProfessorCommunication.addCommunication(professor);
                break;

            case "Add Homework":
                AddHomework addHomework = new AddHomework();
                addHomework.addHomework(professor);
                break;

            case "Assigned Exams":
                AssignedExams assignedExams = new AssignedExams();
                assignedExams.assignedExams(professor);
                break;

            case "Add Exam":
                AddExam addExam = new AddExam();
                addExam.addExam(professor);
                break;

            case "Verbalize Exam":
                AddExamGrade verbalizeExam = new AddExamGrade();
                verbalizeExam.verbalizeExam(professor);
                break;

            default:
                write.println("\n\n\nERROR: Operation not found. Redirecting to the menu.\n\n\n");

                break;
        }
    }
}
