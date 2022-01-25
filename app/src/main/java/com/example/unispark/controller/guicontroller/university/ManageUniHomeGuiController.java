package com.example.unispark.controller.guicontroller.university;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.bean.courses.BeanCourse;
import com.example.unispark.bean.BeanLesson;
import com.example.unispark.bean.communications.BeanUniCommunication;
import com.example.unispark.bean.university.BeanLoggedUniversity;
import com.example.unispark.controller.applicationcontroller.communications.ShowCommunications;
import com.example.unispark.controller.applicationcontroller.course.ManageCourses;
import com.example.unispark.controller.applicationcontroller.schedule.DeleteLesson;
import com.example.unispark.controller.applicationcontroller.schedule.GetScheduleUniversity;
import com.example.unispark.view.details.DetailsUniCommunicationView;
import com.example.unispark.view.university.fragment.AddScheduleView;
import com.example.unispark.view.university.fragment.AddUniCommunicationView;
import com.example.unispark.viewadapter.LessonAdapter;
import com.example.unispark.viewadapter.communications.UniCommunicationsAdapter;

import java.util.List;

public class ManageUniHomeGuiController extends UniBaseGuiController {



    public void showAddCommunication(FragmentManager fragmentManager, BeanLoggedUniversity university,
                                     UniCommunicationsAdapter uniCommunicationsAdapter, List<BeanUniCommunication> communications){

        AddUniCommunicationView fragment = new AddUniCommunicationView(university, communications, uniCommunicationsAdapter);
        fragment.show(fragmentManager, "AddUniCommunication");
    }




    public List<BeanUniCommunication> showCommunications(){
        List<BeanUniCommunication> communications = null;

        ShowCommunications uniCommunicationsAppController = new ShowCommunications();
        communications = uniCommunicationsAppController.showUniversityCommunications();


        return communications;
    }






    public void showDetailsCommunication(Context context, BeanUniCommunication communication){
        Intent intent = new Intent(context, DetailsUniCommunicationView.class);
        //Pass Items to the new Activity
        intent.putExtra("Communication", communication);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }




    private List<BeanCourse> getCourses(List<String> faculties){
        List<BeanCourse> courses;

        ManageCourses getCoursesAppController = new ManageCourses();
        courses = getCoursesAppController.getFacultyCourses(faculties);


        return courses;
    }





    private List<BeanLesson> getLessons(String day, List<BeanCourse> courses){
        List<BeanLesson> lessons;

        GetScheduleUniversity getScheduleUniversityAppController = new GetScheduleUniversity();
        lessons = getScheduleUniversityAppController.getLessons(day, courses);

        return lessons;
    }





    public void deleteLesson(Context context, List<BeanLesson> lessons, int position, LessonAdapter lessonAdapter, RecyclerView rvSchedules){

        //Application Controller
        DeleteLesson deleteLessonAppController = new DeleteLesson();
        try {
            deleteLessonAppController.deleteLesson(lessons.get(position));
            //Show Removed StudentSchedule
            lessons.remove(position);
            lessonAdapter.notifyItemRemoved(position);
            rvSchedules.setAdapter(lessonAdapter);
        } catch (Exception e) {
            e.printStackTrace();
            getErrorMessage(context, e.getMessage());
        }
    }




    //Show StudentScheduleGUIController
    public List<BeanLesson> showSchedule(int index, TextView txtScheduleTitle, List<String> faculties, TextView txtSchedule, RecyclerView rvSchedules){

        String day = null;

        switch (index){
            case 1: day = "TUESDAY";
                break;
            case 2: day = "WEDNESDAY";
                break;
            case 3: day = "THURSDAY";
                break;
            case 4: day = "FRIDAY";
                break;
            default: day = "MONDAY";
        }

        txtScheduleTitle.setText("SCHEDULE: " + day);

        //Lessons
        //Get Courses
        List<BeanCourse> courses;
        courses = getCourses(faculties);
        //Get Lessons
        List<BeanLesson> lessons;
        lessons = getLessons(day, courses);

        return lessons;
    }



    public int getCorrectIndex(int index){

        if(index == 4) index = 0;
        else index++;

        return index;
    }



    public void showAddSchedule(FragmentManager fragmentManager, BeanLoggedUniversity university,
                                LessonAdapter lessonAdapter, List<BeanLesson> lessons, int indexDay){
        AddScheduleView fragment = new AddScheduleView(university, lessonAdapter, lessons, indexDay);
        fragment.show(fragmentManager, "AddSchedule");


    }

}
