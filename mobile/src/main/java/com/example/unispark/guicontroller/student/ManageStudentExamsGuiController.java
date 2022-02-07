package com.example.unispark.guicontroller.student;


import com.example.unispark.Session;
import com.example.common.bean.exams.BeanBookExam;
import com.example.common.bean.exams.BeanExam;
import com.example.common.bean.student.BeanLoggedStudent;
import com.example.common.applicationcontroller.exams.BookExam;
import com.example.common.applicationcontroller.exams.ManageExams;
import com.example.common.exceptions.ExamAlreadyVerbalized;
import com.example.common.exceptions.GenericException;
import com.example.unispark.view.student.StudentExamsView;

import java.util.List;

public class ManageStudentExamsGuiController extends StudentBaseGuiController {


    private StudentExamsView examsView;
    private List<BeanExam> beanExams;
    private int page;


    public ManageStudentExamsGuiController(Session session, StudentExamsView examsView) {
        super(session, examsView);
        this.examsView = examsView;
        this.page = 0;
    }

    public void showNextPageExams(){
        int newPage = page+1;
        this.page = getCorrectPage(newPage);
        showExams();


    }

    public void showPrevPageExams(){
        int newPage = page-1;
        this.page = getCorrectPage(newPage);
        showExams();
    }


    private static int getCorrectPage(int page){
        if(page > 3 || page < -3) page = 0;
        return page;
    }



    //Show Exams Page
    public void showExams(){
        BeanLoggedStudent student = (BeanLoggedStudent) session.getUser();
        

        //Select the Page
        if(page == 0){
            //Application Controller
            ManageExams manageExams = new ManageExams();
            //Set Title
            examsView.setExamsTitle("VERBALIZED EXAMS");

            this.beanExams = manageExams.verbalizedExams(student);
            examsView.setExamType(0);
        }
        if(page == 1 || page == -3) {
            //Application Controller
            ManageExams manageExams = new ManageExams();
            //Set Title
            examsView.setExamsTitle("FAILED EXAMS");

            this.beanExams = manageExams.failedExams(student);
            examsView.setExamType(0);
        }
        if(page == 2 || page == -2) {
            //Application Controller
            BookExam bookExamController = new BookExam();
            //Set Title
            examsView.setExamsTitle("BOOK UPCOMING EXAMS");

            this.beanExams = bookExamController.generateBookingExams(student);
            examsView.setExamType(1);


        }
        if(page == 3 || page == -1) {
            //Application Controller
            ManageExams manageExams = new ManageExams();
            //Set Title
            examsView.setExamsTitle("BOOKED EXAMS");

            this.beanExams = manageExams.getBookedExams(student);
            examsView.setExamType(2);
        }

        examsView.setExamAdapter(beanExams);

    }



    public void bookExam(int position){
        BeanLoggedStudent student = (BeanLoggedStudent) this.session.getUser();
        //Book Exam Controller
        BookExam bookExamController = new BookExam();

        try {
            bookExamController.bookExam(student, (BeanBookExam) beanExams.get(position));
            examsView.setMessage("Exam booked");

            //Removing the Booked Exam from the List
            beanExams.remove(position);
            examsView.notifyDataChanged(position);
        } catch (ExamAlreadyVerbalized | GenericException e) {
            e.printStackTrace();
            examsView.setMessage(e.getMessage());
        }
    }


    public void leaveExam(int position){
        BeanLoggedStudent student = (BeanLoggedStudent) this.session.getUser();

        try{

            ManageExams leaveExamAppController = new ManageExams();
            leaveExamAppController.removeExam(student, position);
            //Removing the Booked Exam from the List
            beanExams.remove(position);
            examsView.notifyDataChanged(position);

        } catch (GenericException e) {
            e.printStackTrace();
            examsView.setMessage(e.getMessage());
        }
    }
}
