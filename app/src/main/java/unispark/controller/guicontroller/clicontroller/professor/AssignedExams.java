package unispark.controller.guicontroller.clicontroller.professor;


import unispark.controller.appcontroller.exams.ManageExams;
import unispark.engeneeringclasses.bean.exams.BeanBookExam;
import unispark.engeneeringclasses.bean.exams.BeanExam;
import unispark.engeneeringclasses.bean.professor.BeanLoggedProfessor;

import java.util.List;

public class AssignedExams {

    public void assignedExams(BeanLoggedProfessor professor){
        //List of Assigned Exams
        List<BeanExam> assignedExams;

        //App Controller
        ManageExams assignedExamsAppController = new ManageExams();
        assignedExams = assignedExamsAppController.assignedExams(professor);


        //Show assigned Exams
        StringBuilder bld = new StringBuilder();
        bld.append("-------------------- Assigned Exams --------------------\n\n");

        for(int i = 0; i < assignedExams.size(); i++){
            BeanBookExam aExam = (BeanBookExam) assignedExams.get(i);

            bld.append(aExam.getName() + "\n");
            bld.append(aExam.getDate() + "\n");
            bld.append(aExam.getYear() + "\n");
            bld.append("ID: " + aExam.getId() + "\n");
            bld.append("Classroom: " + aExam.getClassroom() + " Building: " + aExam.getBuilding() + "\n");
            bld.append("Position: " + Integer.toString(i) + "\n\n");
        }
        System.out.println(bld.toString());
    }
}
