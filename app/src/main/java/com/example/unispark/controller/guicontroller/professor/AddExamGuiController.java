package com.example.unispark.controller.guicontroller.professor;

import com.example.unispark.Session;
import com.example.unispark.bean.courses.BeanCourse;
import com.example.unispark.bean.exams.BeanBookExam;
import com.example.unispark.bean.professor.BeanLoggedProfessor;
import com.example.unispark.controller.applicationcontroller.exams.ManageProfessorExams;
import com.example.unispark.exceptions.ExamAlreadyExists;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.view.professor.fragment.AddExamView;

public class AddExamGuiController extends AddItemGuiController{


    private AddExamView addExamView;


    public AddExamGuiController(Session session, AddExamView addExamView) {
        super(session, addExamView, null, null);
        this.addExamView = addExamView;
    }

    public void addExam(String date, String courseSelection, String hour, String building, String classroom){

        if (courseSelection.equals("") || hour.equals("") || building.equals("") || classroom.equals("")){
            this.addExamView.setMessage("All fields are required");
        }

        else{

            BeanLoggedProfessor professor = (BeanLoggedProfessor) this.session.getUser();
            BeanCourse beanCourse = this.getCourses(professor).get(this.getCoursePosition());

            //Bean Exam
            BeanBookExam bExam;
            bExam = new BeanBookExam();
            bExam.setId(1);
            bExam.setName(beanCourse.getFullName());
            bExam.setYear(beanCourse.getCourseYear());
            bExam.setDate(date + hour);
            bExam.setCfu(beanCourse.getCfu());
            bExam.setClassroom(classroom);
            bExam.setBuilding(building);

            //Application Controller
            ManageProfessorExams addExamAppController = new ManageProfessorExams();
            try {

                addExamAppController.addExam(bExam, professor);
                this.addExamView.setMessage("Exam added");
                this.addExamView.dismiss();

            } catch (ExamAlreadyExists | GenericException e) {
                e.printStackTrace();
                this.addExamView.setMessage(e.getMessage());
            }
        }
    }


}
