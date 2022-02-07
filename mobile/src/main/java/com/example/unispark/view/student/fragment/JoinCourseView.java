package com.example.unispark.view.student.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.R;
import com.example.unispark.Session;
import com.example.unispark.guicontroller.student.JoinCourseGuiController;
import com.example.unispark.viewadapter.CoursesAdapter;
import com.example.common.bean.courses.BeanCourse;



import java.util.List;

public class JoinCourseView extends DialogFragment
        implements CoursesAdapter.OnCourseClickListener,
        CoursesAdapter.OnCourseBtnClickListener {


    //Courses
    private RecyclerView rvCourses;
    private CoursesAdapter coursesAdapter;
    private CoursesAdapter joinedCoursesAdapter;


    //Gui controller
    private JoinCourseGuiController joinCourseGuiController;



    //Constructor
    public JoinCourseView(Session session, List<BeanCourse> joinedCourses, CoursesAdapter joinedCoursesAdapter){
        this.joinedCoursesAdapter = joinedCoursesAdapter;
        this.joinCourseGuiController = new JoinCourseGuiController(session, joinedCourses, this);
        this.coursesAdapter = new CoursesAdapter(this, this, "JOIN");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_course, container, false);
        getDialog().setTitle("Simple Dialog");



        //Dismiss Button
        ImageButton btnDismiss;
        btnDismiss = rootView.findViewById(R.id.btn_goback);
        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        //Courses
        this.rvCourses = (RecyclerView) rootView.findViewById(R.id.rv_choose_course);
        //Gui Controller
        this.joinCourseGuiController.showAvaliableCourses();

        return rootView;
    }



    //On Course Click
    @Override
    public void onCourseClick(int position) {
        this.joinCourseGuiController.showCourseDetails(position);
    }

    //On JoinCourse Click
    @Override
    public void onButtonClick(int position) {

        this.joinCourseGuiController.joinCourse(position);
    }


    public void setCoursesAdapter(List<BeanCourse> beanCourses) {
        this.coursesAdapter.setbCourses(beanCourses);
        this.rvCourses.setAdapter(this.coursesAdapter);
    }


    public void setJoinedCoursesAdapter(List<BeanCourse> beanCourses) {
        this.joinedCoursesAdapter.setbCourses(beanCourses);
    }

    public void notifyDataChanged(){
        this.joinedCoursesAdapter.notifyDataSetChanged();
    }



    public void setErrorMessage(String message){
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }


}
