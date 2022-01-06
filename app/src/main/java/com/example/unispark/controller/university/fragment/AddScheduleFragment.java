package com.example.unispark.controller.university.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.unispark.R;
import com.example.unispark.adapter.LessonAdapter;
import com.example.unispark.database.dao.CommunicationsDAO;
import com.example.unispark.database.dao.CourseDAO;
import com.example.unispark.database.dao.LessonsDAO;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.LessonModel;
import com.example.unispark.model.UniversityModel;
import com.example.unispark.model.communications.UniversityCommunicationModel;
import com.google.android.material.textfield.TextInputLayout;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


public class AddScheduleFragment extends DialogFragment {
    //Attributes
    //Dismiss Button
    ImageButton btnDismiss;
    //Add Schedule Button
    Button btnAddSchedule;
    //Course Selector
    List<String> courses;
    AutoCompleteTextView autoCompleteTxtCourse;
    ArrayAdapter<String> adapterItemsCourse;
    String courseSelection;
    //Day Selector
    List<String> days;
    AutoCompleteTextView autoCompleteTxtDay;
    ArrayAdapter<String> adapterItemsDay;
    String daySelection;
    //Hour Selector
    List<String> hours;
    AutoCompleteTextView autoCompleteTxtHour;
    ArrayAdapter<String> adapterItemsHour;
    String hourSelection;
    //University Model
    UniversityModel university;
    List<LessonModel> schedulesItem;
    LessonAdapter lessonAdapter;

    int i;

    String DAYS[] = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
    String HOURS[] = {"08:30 - 09:15", "09:30 - 10:15", "10:30 - 11:15", "11:30 - 12:15", "12:30 - 13:15", "14:00 - 14:45", "15:00 - 15:45", "16:00 - 16:45", "17:00 - 17:45"};


    //Methods
    //Constructor
    public AddScheduleFragment(UniversityModel university, LessonAdapter lessonAdapter, List<LessonModel> schedulesItem) {
        //Getting Professor Object
        this.university = university;
        this.schedulesItem = schedulesItem;
        this.lessonAdapter = lessonAdapter;

        courses = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_schedule, container, false);
        getDialog().setTitle("Simple Dialog");

        //Dismiss Button
        btnDismiss = rootView.findViewById(R.id.btn_schedule_goback);
        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });




        //Courses
        List<CourseModel> coursesModel = CourseDAO.selectAvailableCourses("Ingegneria Informatica", 3, null);
        if(coursesModel != null) for(int j = 0; j < coursesModel.size(); j++) courses.add(coursesModel.get(j).getFullName());

        autoCompleteTxtCourse = rootView.findViewById(R.id.add_schedule_course);
        adapterItemsCourse = new ArrayAdapter<>(getContext(), R.layout.item_container_item, courses);
        autoCompleteTxtCourse.setAdapter(adapterItemsCourse);
        autoCompleteTxtCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                courseSelection = (String)parent.getItemAtPosition(position);
            }
        });

        //Days
        autoCompleteTxtDay = rootView.findViewById(R.id.add_schedule_day);
        adapterItemsDay = new ArrayAdapter<>(getContext(), R.layout.item_container_item, DAYS);
        autoCompleteTxtDay.setAdapter(adapterItemsDay);
        autoCompleteTxtDay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                daySelection = (String)parent.getItemAtPosition(position);
            }
        });

        //Hours
        autoCompleteTxtHour = rootView.findViewById(R.id.add_schedule_hour);
        adapterItemsHour = new ArrayAdapter<>(getContext(), R.layout.item_container_item, HOURS);
        autoCompleteTxtHour.setAdapter(adapterItemsHour);
        autoCompleteTxtHour.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                hourSelection = (String)parent.getItemAtPosition(position);
            }
        });



        //Add Schedule
        btnAddSchedule = rootView.findViewById(R.id.btn_add_schedule_add);
        btnAddSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creating new Lesson
                LessonModel lesson = new LessonModel(courseSelection, daySelection, hourSelection);

                //Adding Lesson to the DB
                LessonsDAO.addLesson(lesson);

                //Notifying the Lessons Adapter
                schedulesItem.add(0, lesson);
                lessonAdapter.notifyDataSetChanged();

                dismiss();
            }
        });

        return rootView;
    }
}