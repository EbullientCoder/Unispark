package com.example.unispark.controller.guicontroller.student;


import com.example.unispark.Session;
import com.example.unispark.bean.exams.BeanBookExam;
import com.example.unispark.bean.exams.BeanExamType;
import com.example.unispark.bean.student.BeanLoggedStudent;
import com.example.unispark.controller.applicationcontroller.exams.BookExam;
import com.example.unispark.controller.applicationcontroller.exams.LeaveExam;
import com.example.unispark.controller.applicationcontroller.exams.ShowExams;
import com.example.unispark.exceptions.ExamAlreadyVerbalized;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.view.student.StudentExamsView;

import java.util.List;

public class ManageStudentExamsGuiController extends StudentBaseGuiController {


    private StudentExamsView examsView;
    private List<BeanExamType> beanExams;
    private int page;


    public ManageStudentExamsGuiController(Session session, StudentExamsView examsView) {
        super(session, examsView);
        this.examsView = examsView;
        this.page = 0;
    }

    public void showNextPageExams(){
        int newPage = this.page+1;
        this.page = this.getCorrectPage(newPage);
        this.showExams();


    }

    public void showPrevPageExams(){
        int newPage = this.page-1;
        this.page = this.getCorrectPage(newPage);
        this.showExams();
    }


    private int getCorrectPage(int page){
        if(page > 3 || page < -3) page = 0;
        return page;
    }



    //Page Menu
    public void showExams(){
        BeanLoggedStudent student = (BeanLoggedStudent) this.session.getUser();
        List<BeanExamType> exams = null;

        //Application Controller
        ShowExams studentExamsAppController = new ShowExams();

        //Select the Page
        if(this.page == 0){
            //Set Title
            this.examsView.setExamsTitle("VERBALIZED EXAMS");
            //Exams Item
            this.beanExams = studentExamsAppController.verbalizedExams(student);
        }
        if(this.page == 1 || this.page == -3) {
            //Set Title
            this.examsView.setExamsTitle("FAILED EXAMS");
            //Exams Item
            this.beanExams = studentExamsAppController.failedExams(student);
        }
        if(page == 2 || page == -2) {
            //Set Title
            this.examsView.setExamsTitle("BOOK UPCOMING EXAMS");
            //Exams Item
            this.beanExams = studentExamsAppController.bookExams(student);


        }
        if(page == 3 || page == -1) {
            //Set Title
            this.examsView.setExamsTitle("BOOKED EXAMS");
            //Exams Item
            this.beanExams = studentExamsAppController.bookedExams(student);
        }

        this.examsView.setExamAdapter(this.getBeanExams());

    }



    public void bookExam(int position){
        BeanLoggedStudent student = (BeanLoggedStudent) this.session.getUser();
        //Application Controller
        BookExam bookExamAppController = new BookExam();

        try {
            bookExamAppController.bookExam(student, (BeanBookExam) this.beanExams.get(position).getExamType());
            this.examsView.setMessage("Exam booked");

            //Removing the Booked Exam from the List
            this.beanExams.remove(position);
            this.examsView.notifyDataChanged(position);
        } catch (ExamAlreadyVerbalized | GenericException e) {
            e.printStackTrace();
            this.examsView.setMessage(e.getMessage());
        }
    }


    public void leaveExam(int position){
        BeanLoggedStudent student = (BeanLoggedStudent) this.session.getUser();
        //Application Controller
        try{

            LeaveExam leaveExamAppController = new LeaveExam();
            leaveExamAppController.removeExam(student, position);
            //Removing the Booked Exam from the List
            this.beanExams.remove(position);
            this.examsView.notifyDataChanged(position);

        } catch (GenericException e) {
            e.printStackTrace();
            this.examsView.setMessage(e.getMessage());
        }
    }

    public StudentExamsView getExamsView() {
        return examsView;
    }

    public List<BeanExamType> getBeanExams() {
        return beanExams;
    }
}
