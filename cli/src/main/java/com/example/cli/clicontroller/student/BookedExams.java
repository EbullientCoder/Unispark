package com.example.cli.clicontroller.student;


import com.example.common.applicationcontroller.exams.ManageExams;
import com.example.common.bean.exams.BeanBookExam;
import com.example.common.bean.exams.BeanExam;
import com.example.common.bean.student.BeanLoggedStudent;

import java.util.List;

public class BookedExams {

    public void bookedExams(BeanLoggedStudent student){
        //Booked Exams
        List<BeanExam> bookedExams;

        //Application Controller
        ManageExams showBookedExamsAppController = new ManageExams();
        bookedExams = showBookedExamsAppController.getBookedExams(student);

        //Show Booked Exams
        StringBuilder bld = new StringBuilder();
        bld.append("-------------------- Booked Exams --------------------\n\n");

        for(int i = 0; i < bookedExams.size(); i++){
            BeanBookExam bookExam = (BeanBookExam) bookedExams.get(i);

            bld.append(bookExam.getName() + "\n");
            bld.append(bookExam.getDate() + "\n");
            bld.append("CFU: " + bookExam.getCfu() + "\n");
            bld.append("Classroom: " + bookExam.getClassroom() + " Building: " + bookExam.getBuilding() + "\n");
            bld.append("Position: " + Integer.toString(i) + "\n\n");
        }
        System.out.println(bld.toString());
    }
}
