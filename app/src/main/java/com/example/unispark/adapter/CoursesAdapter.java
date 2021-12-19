package com.example.unispark.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.unispark.R;
import com.example.unispark.model.CourseModel;

import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //Attributes
    private List<CourseModel> items;
    private OnCourseClickListener onCourseClickListener;
    private String type;


    //Interface
    public interface OnCourseClickListener {
        void onCourseClick(int position);
    }


    //Methods
    public CoursesAdapter(List<CourseModel> items, OnCourseClickListener onCourseClickListener, String type){
        this.items = items;
        this.onCourseClickListener = onCourseClickListener;
        this.type = type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new CourseViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_courses,
                        parent,
                        false
                ), onCourseClickListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(type.equals("JOIN")){
            CourseModel courseModel = (CourseModel) items.get(position);
            ((CourseViewHolder) holder).setJoinCourseDate(courseModel);
        }
        else if(type.equals("LEAVE")){
            CourseModel courseModel = (CourseModel) items.get(position);
            ((CourseViewHolder) holder).setLeaveCourseDate(courseModel);
        }
        else if(type.equals("PROFESSOR")){
            CourseModel courseModel = (CourseModel) items.get(position);
            ((CourseViewHolder) holder).setProfessorCourseDate(courseModel);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /*@Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }*/


    //First Row
    static class CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Attributes
        private String shortName;
        private String id;
        private String session;
        private String link;
        private OnCourseClickListener onCourseClickListener;

        private TextView fullName;
        private TextView aa;
        private TextView cfu;
        private Button btnJoinLeave;
        private LinearLayout lyt_button;


        //Methods
        //Constructor
        CourseViewHolder(@NonNull View itemView, OnCourseClickListener onCourseClickListener) {
            super(itemView);
            fullName = itemView.findViewById(R.id.txt_course_subject_name);
            aa = itemView.findViewById(R.id.txt_course_aa_date);
            cfu = itemView.findViewById(R.id.txt_course_cfu);
            btnJoinLeave = itemView.findViewById(R.id.btn_join_leave_course);
            lyt_button = itemView.findViewById(R.id.lyt_contain_course_button);

            this.onCourseClickListener = onCourseClickListener;
            itemView.setOnClickListener(this);
        }

        void setJoinCourseDate(CourseModel courseModel) {
            fullName.setText(courseModel.getFullName());
            shortName = courseModel.getShortName();
            aa.setText(courseModel.getCourseYear());
            cfu.setText(courseModel.getCfu());
            id = courseModel.getId();
            session = courseModel.getSession();
            link = courseModel.getLink();
            btnJoinLeave.setText("JOIN");
        }

        void setLeaveCourseDate(CourseModel courseModel) {
            fullName.setText(courseModel.getFullName());
            shortName = courseModel.getShortName();
            aa.setText(courseModel.getCourseYear());
            cfu.setText(courseModel.getCfu());
            id = courseModel.getId();
            session = courseModel.getSession();
            link = courseModel.getLink();
            btnJoinLeave.setText("LEAVE");
        }

        void setProfessorCourseDate(CourseModel courseModel) {
            fullName.setText(courseModel.getFullName());
            shortName = courseModel.getShortName();
            aa.setText(courseModel.getCourseYear());
            cfu.setText(courseModel.getCfu());
            id = courseModel.getId();
            session = courseModel.getSession();
            link = courseModel.getLink();
            btnJoinLeave.setVisibility(View.INVISIBLE);
            lyt_button.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onClick(View view) {
            onCourseClickListener.onCourseClick(getAdapterPosition());
        }
    }
}

