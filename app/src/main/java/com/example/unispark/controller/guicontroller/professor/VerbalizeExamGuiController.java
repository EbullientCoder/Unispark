package com.example.unispark.controller.guicontroller.professor;

import android.content.Context;
import android.widget.Toast;

import com.example.unispark.bean.exam.BeanBookExam;
import com.example.unispark.bean.BeanStudentSignedToExam;
import com.example.unispark.controller.applicationcontroller.exams.ShowSignedToExamStudents;
import com.example.unispark.controller.applicationcontroller.exams.VerbalizeExam;
import com.example.unispark.exceptions.ExamNotYetOccured;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.viewadapter.SignedStudentsAdapter;

import java.util.List;

public class VerbalizeExamGuiController {


    public List<BeanStudentSignedToExam> showStudents(Context context, BeanBookExam bookExam){
        List<BeanStudentSignedToExam> studentSignedToExams;

        //Application Controller
        ShowSignedToExamStudents bookedStudentAppController = new ShowSignedToExamStudents();
        studentSignedToExams = bookedStudentAppController.showBookedStudents(bookExam);

        if(studentSignedToExams.isEmpty()) getMessage(context);

        return studentSignedToExams;
    }


    private void getMessage(Context context){

        Toast.makeText(context, "NO STUDENTS SIGNED", Toast.LENGTH_SHORT).show();
    }




    public void verbalizeExam(Context context, String result, BeanBookExam bookExam,
                              List<BeanStudentSignedToExam> studentSignedToExams, int position,
                              SignedStudentsAdapter studentsAdapter){

        //Application Controller
        VerbalizeExam verbalizeExamAppController = new VerbalizeExam();
        try {
            double doubleResult = Double.parseDouble(result);
            if (doubleResult < 0 || doubleResult > 30){
                getResultInvalidMessage(context);
            }
            else{
                verbalizeExamAppController.verbalizeExam(bookExam, studentSignedToExams.get(position), result);
                //Remove Verbalized Exam
                studentSignedToExams.remove(position);
                studentsAdapter.notifyItemRemoved(position);
            }




        } catch (ExamNotYetOccured | GenericException e) {
            e.printStackTrace();
            getErrorMessage(context, e.getMessage());
        } catch (NumberFormatException numberFormatException){
            getResultInvalidMessage(context);
        }
    }




    private void getResultInvalidMessage(Context context){

        Toast.makeText(context, "Result format not valid, insert a number between 0 and 30", Toast.LENGTH_SHORT).show();
    }



    private void getErrorMessage(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
