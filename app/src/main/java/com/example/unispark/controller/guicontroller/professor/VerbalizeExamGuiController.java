package com.example.unispark.controller.guicontroller.professor;


import com.example.unispark.bean.exams.BeanBookExam;
import com.example.unispark.bean.BeanStudentSignedToExam;
import com.example.unispark.controller.applicationcontroller.exams.AddExamGrade;
import com.example.unispark.exceptions.ExamNotYetOccured;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.view.professor.VerbalizeExamsView;


import java.util.List;

public class VerbalizeExamGuiController{

    private VerbalizeExamsView verbalizeExamsView;
    private BeanBookExam beanBookExam;
    private List<BeanStudentSignedToExam> beanStudentSignedToExams;


    public VerbalizeExamGuiController(BeanBookExam beanBookExam, VerbalizeExamsView verbalizeExamsView) {
        this.verbalizeExamsView = verbalizeExamsView;
        this.beanBookExam = beanBookExam;
    }

    public void showStudents(){

        this.verbalizeExamsView.setTxtCourseName(this.beanBookExam.getName());
        this.verbalizeExamsView.setTxtCourseDate(this.beanBookExam.getDate());

        //Application Controller
        AddExamGrade studentsSignedToExam = new AddExamGrade();
        this.beanStudentSignedToExams = studentsSignedToExam.getStudentsVerbalizeExam(this.getBeanBookExam());

        if(this.getBeanStudentSignedToExams().isEmpty()) this.verbalizeExamsView.setMessage("No students signed");
        else this.verbalizeExamsView.setStudentsAdapter(this.getBeanStudentSignedToExams());

    }





    public void verbalizeExam(String result, int position){

        //Application Controller
        AddExamGrade verbalizeExamAppController = new AddExamGrade();
        try {
            double doubleResult = Double.parseDouble(result);
            if (doubleResult < 0 || doubleResult > 30){
                this.verbalizeExamsView.setMessage("Grade must be a number between 0 and 30");
            }
            else{
                verbalizeExamAppController.verbalizeExam(this.getBeanBookExam(), this.getBeanStudentSignedToExams().get(position), result);
                //Remove Verbalized Exam
                this.beanStudentSignedToExams.remove(position);
                this.verbalizeExamsView.notifyDataChanged(position);
            }




        } catch (ExamNotYetOccured | GenericException e) {
            e.printStackTrace();
            this.verbalizeExamsView.setMessage(e.getMessage());
        } catch (NumberFormatException numberFormatException){
            this.verbalizeExamsView.setMessage(numberFormatException.getMessage());
        }
    }





    public BeanBookExam getBeanBookExam() {
        return beanBookExam;
    }

    public List<BeanStudentSignedToExam> getBeanStudentSignedToExams() {
        return beanStudentSignedToExams;
    }
}
