package unispark.cli.clicontroller.student;



import unispark.engeneeringclasses.applicationcontroller.exams.ManageExams;
import unispark.engeneeringclasses.bean.exams.BeanExam;
import unispark.engeneeringclasses.bean.exams.BeanVerbalizeExam;
import unispark.engeneeringclasses.bean.student.BeanLoggedStudent;

import java.util.List;

public class FailedExams {

    public void failedExams(BeanLoggedStudent student){
        //Failed Exams List
        List<BeanExam> failedExams;

        //Application Controller
        ManageExams failedExamsAppController = new ManageExams();
        failedExams = failedExamsAppController.failedExams(student);

        //Show Failed Exams
        StringBuilder bld = new StringBuilder();
        bld.append("-------------------- Failed Exams --------------------\n\n");

        for(int i = 0; i < failedExams.size(); i++){
            BeanVerbalizeExam fExam = (BeanVerbalizeExam) failedExams.get(i);

            bld.append(fExam.getName() + "\n");
            bld.append(fExam.getDate() + "\n");
            bld.append("CFU: " + fExam.getCfu() + "\n");
            bld.append("Result: " + fExam.getResult() + "\n\n");
        }
        System.out.println(bld.toString());
    }
}
