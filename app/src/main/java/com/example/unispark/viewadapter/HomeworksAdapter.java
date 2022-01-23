package com.example.unispark.viewadapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.R;
import com.example.unispark.bean.BeanHomework;

import java.util.List;

public class HomeworksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //Attributes
    private List<BeanHomework> beanHomeworkList;
    private OnHomeworkBtnClickListener onHomeworkBtnClickListener;
    private String type;

    //Click HomeworkItem Interface
    public interface OnHomeworkBtnClickListener {
        void onBtnClick(int position);
    }


    //Methods
    public HomeworksAdapter(List<BeanHomework> beanHomeworkList, OnHomeworkBtnClickListener onHomeworkBtnClickListener, String type){
        this.beanHomeworkList = beanHomeworkList;
        this.onHomeworkBtnClickListener = onHomeworkBtnClickListener;
        this.type = type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeworkViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_homework,
                        parent,
                        false
                ), onHomeworkBtnClickListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //Student Homework
        if(type.equals("STUDENT")){
            BeanHomework homework = (BeanHomework) beanHomeworkList.get(position);
            ((HomeworkViewHolder) holder).setHomeworkDate(homework);
        }
        //Professor Homework
        else if(type.equals("PROFESSOR")){
            BeanHomework homework = (BeanHomework) beanHomeworkList.get(position);
            ((HomeworkViewHolder) holder).setProfessorHomeworkDate(homework);
        }
    }

    @Override
    public int getItemCount() {
        return beanHomeworkList.size();
    }



    //Homeworks ViewHolder
    static class HomeworkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Attributes
        String shortName;
        String title;
        String instructions;
        String points;
        int id;

        TextView txtCourse;
        TextView txtExpiration;
        Button btnView;
        OnHomeworkBtnClickListener onHomeworkBtnClickListener;

        //Methods
        //Constructor
        HomeworkViewHolder(@NonNull View itemView, OnHomeworkBtnClickListener onHomeworkBtnClickListener) {
            super(itemView);
            txtCourse = itemView.findViewById(R.id.txt_homework_subject_name);
            txtExpiration = itemView.findViewById(R.id.txt_homework_submit_date);
            btnView = itemView.findViewById(R.id.btn_homework_view);

            this.onHomeworkBtnClickListener = onHomeworkBtnClickListener;
            btnView.setOnClickListener(this);
        }

        //Student
        void setHomeworkDate(BeanHomework homework){
            txtCourse.setText(homework.getFullName());
            txtExpiration.setText(homework.getExpiration());

            shortName = homework.getShortName();
            title = homework.getTitle();
            instructions = homework.getInstructions();
            points = homework.getPoints();
        }

        //Professor
        void setProfessorHomeworkDate(BeanHomework homework){
            txtCourse.setText(homework.getTitle().toUpperCase());
            txtExpiration.setText(homework.getExpiration());

            shortName = homework.getShortName();
            title = homework.getTitle();
            instructions = homework.getInstructions();
            points = homework.getPoints();
            id = 1;
        }

        @Override
        public void onClick(View view) {
            onHomeworkBtnClickListener.onBtnClick(getAdapterPosition());
        }
    }
}


