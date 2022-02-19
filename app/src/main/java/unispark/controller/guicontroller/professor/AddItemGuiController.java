package unispark.controller.guicontroller.professor;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import unispark.engeneeringclasses.others.Session;
import unispark.engeneeringclasses.bean.courses.BeanCourse;
import unispark.engeneeringclasses.bean.professor.BeanLoggedProfessor;
import unispark.controller.appcontroller.course.ManageCourses;
import unispark.view.mobileview.professor.fragment.AddExamView;
import unispark.view.mobileview.professor.fragment.AddHomeworkView;
import unispark.view.mobileview.professor.fragment.AddProfCommunicationView;

import java.util.List;

public class AddItemGuiController extends ProfBaseGuiController{

    private AddExamView addExamView;
    private AddHomeworkView addHomeworkView;
    private AddProfCommunicationView addProfCommunicationView;

    //Selector
    private int coursePosition;

    public AddItemGuiController(Session session, AddExamView addExamView, AddHomeworkView addHomeworkView, AddProfCommunicationView addProfCommunicationView) {
        super(session, null);
        if (addExamView != null){
            setContext(addExamView.getContext());
            this.addExamView = addExamView;
        }
        if (addHomeworkView != null){
            setContext(addHomeworkView.getContext());
            this.addHomeworkView = addHomeworkView;
        }
        if (addProfCommunicationView != null){
            setContext(addProfCommunicationView.getContext());
            this.addProfCommunicationView = addProfCommunicationView;
        }
    }


    public List<BeanCourse> getCourses(BeanLoggedProfessor professor){
        ManageCourses getCoursesController = new ManageCourses();

        return getCoursesController.getCourses(professor);
    }

    public void coursesNamesSelector(){
        BeanLoggedProfessor professor = (BeanLoggedProfessor) session.getUser();
        List<String> coursesNames;
        ManageCourses coursesGuiController = new ManageCourses();
        coursesNames = coursesGuiController.getCoursesNames(professor);
        if (addExamView != null){
            addExamView.setAdapterItems(coursesNames);
        }
        if (addHomeworkView != null){
            addHomeworkView.setAdapterItems(coursesNames);
        }
        if (addProfCommunicationView != null){
            addProfCommunicationView.setAdapterItems(coursesNames);
        }

    }

    public void showDateDialog(int year, int month, int day){

        DatePickerDialog datePickerDialog = null;
        if (addExamView != null){
            datePickerDialog = new DatePickerDialog(
                    addExamView.getContext(),
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    addExamView.getDateListener(),
                    year,
                    month,
                    day);
        }
        if (addHomeworkView != null){
            datePickerDialog = new DatePickerDialog(
                    addHomeworkView.getContext(),
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    addHomeworkView.getDateListener(),
                    year,
                    month,
                    day);
        }


        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }

    public void selectPosition(int position){
        this.coursePosition = position;
    }

    public int getCoursePosition() {
        return coursePosition;
    }
}
