package com.example.unispark.controller.guicontroller.student;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unispark.bean.exams.BeanBookExam;
import com.example.unispark.bean.exams.BeanExamType;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.controller.applicationcontroller.exams.BookExam;
import com.example.unispark.controller.applicationcontroller.exams.LeaveExam;
import com.example.unispark.controller.applicationcontroller.exams.ShowExams;
import com.example.unispark.exceptions.ExamAlreadyVerbalized;
import com.example.unispark.controller.guicontroller.BottomNavigationMenuGuiController;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.viewadapter.exams.ExamAdapter;

import java.util.List;

public class MenageStudentExamsGuiController extends BottomNavigationMenuGuiController {



    public int getNextPageExams(int page){

        int newPage = page + 1;
        newPage = getCorrectPage(newPage);

        return newPage;
    }

    public int getPrevPageExams(int page){

        int newPage = page - 1;
        newPage = getCorrectPage(newPage);

        return newPage;
    }


    private int getCorrectPage(int page){
        if(page > 3 || page < -3) page = 0;
        return page;
    }



    //Page Menu
    public List<BeanExamType> showExams(int page, TextView examsTitle, BeanLoggedStudent student){

        List<BeanExamType> exams = null;

        //Application Controller
        ShowExams studentExamsAppController = new ShowExams();

        //Select the Page
        if(page == 0){
            //Set Title
            examsTitle.setText("VERBALIZED EXAMS");
            //Exams Item
            exams = studentExamsAppController.verbalizedExams(student);
        }
        if(page == 1 || page == -3) {
            //Set Title
            examsTitle.setText("FAILED EXAMS");
            //Exams Item
            exams = studentExamsAppController.failedExams(student);
        }
        if(page == 2 || page == -2) {
            //Set Title
            examsTitle.setText("BOOK UPCOMING EXAMS");
            //Exams Item
            exams = studentExamsAppController.bookExams(student);


        }
        if(page == 3 || page == -1) {
            //Set Title
            examsTitle.setText("BOOKED EXAMS");
            //Exams Item
            exams = studentExamsAppController.bookedExams(student);
        }

        return exams;
    }


    public void bookExam(Context context, BeanLoggedStudent student, List<BeanExamType> exams, int position, ExamAdapter examAdapter){

        //Application Controller
        BookExam bookExamAppController = new BookExam();

        try {
            bookExamAppController.bookExam(student, (BeanBookExam) exams.get(position).getBeanExamType());
            getBookedExamMessage(context);

            //Removing the Booked Exam from the List
            exams.remove(position);
            examAdapter.notifyItemRemoved(position);
        } catch (ExamAlreadyVerbalized | GenericException e) {
            e.printStackTrace();
            getErrorMessage(context, e.getMessage());
        }
    }


    public void leaveExam(Context context, BeanLoggedStudent student, List<BeanExamType> exams, int position, ExamAdapter examAdapter){

        //Application Controller
        try{

            LeaveExam leaveExamAppController = new LeaveExam();
            leaveExamAppController.removeExam(student, position);
            //Removing the Booked Exam from the List
            exams.remove(position);
            examAdapter.notifyItemRemoved(position);

        } catch (GenericException e) {
            e.printStackTrace();
            getErrorMessage(context, e.getMessage());
        }


    }


    private void getErrorMessage(Context context, String message){

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private void getBookedExamMessage(Context context){

        Toast.makeText(context, "Exam booked", Toast.LENGTH_SHORT).show();
    }


}
