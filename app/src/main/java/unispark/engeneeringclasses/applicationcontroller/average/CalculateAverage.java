package unispark.engeneeringclasses.applicationcontroller.average;

import unispark.engeneeringclasses.bean.student.BeanLoggedStudent;
import unispark.engeneeringclasses.dao.ExamsDAO;
import unispark.engeneeringclasses.model.exams.ExamModel;
import unispark.engeneeringclasses.model.exams.VerbalizedExamModel;

import java.sql.SQLException;
import java.util.List;

public class CalculateAverage {

    //Arithmetic Average
    public float arithmeticAverage(BeanLoggedStudent student){
        float average = 0;
        try {
            student.setVerbalizedExams(ExamsDAO.getVerbalizedExams(student.getId()));

            //Calculating the Average if the Student has Verbalized StudentExamsGUIController
            if(student.getVerbalizedExams() != null){
                for(int i = 0; i < student.getVerbalizedExams().size(); i++) {
                    VerbalizedExamModel vExam = (VerbalizedExamModel) student.getVerbalizedExams().get(i);
                    average += Double.parseDouble(vExam.getResult());
                }

                average = average / student.getVerbalizedExams().size();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return average;
    }

    //Circular Arithmetic Average
    public int graphicArithmeticAverage(float average){

        float circularAverage = 0;

        //Calculating the Average if the Student has Verbalized StudentExamsGUIController
        if(average != 0) circularAverage = (average * 100 / 35);

        return (int) circularAverage;
    }



    //Weighted Average
    public float weightedAverage(BeanLoggedStudent student){
        float average = 0;
        float cfu = 0;

        try {
            student.setVerbalizedExams(ExamsDAO.getVerbalizedExams(student.getId()));
            List<ExamModel> exams =  student.getVerbalizedExams();
            //Calculating the Weighted Average if the Student has Verbalized StudentExamsGUIController
            if(exams != null){
                for(int i = 0; i < exams.size(); i++){
                    VerbalizedExamModel vExam = (VerbalizedExamModel) student.getVerbalizedExams().get(i);
                    average += (Double.parseDouble(vExam.getResult()) * Double.parseDouble(exams.get(i).getCfu()));
                    cfu += Double.parseDouble(exams.get(i).getCfu());
                }

                average = average / cfu;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return average;
    }

    //Circular Weighted Average
    public int graphicWeightedAverage(float average){
        float circularAverage = 0;

        //Calculating the Average if the Student has Verbalized StudentExamsGUIController
        if(average != 0) circularAverage = (average * 100 / 36);

        return (int) circularAverage;
    }
}
