package com.example.unispark.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.R;
import com.example.unispark.adapter.exams.ExamItem;
import com.example.unispark.model.LessonModel;

import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    //Attributes
    private List<LessonModel> examItems;

    //Methods
    //Constructor
    public LessonAdapter(List<LessonModel> examItems) {
        this.examItems = examItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LessonViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_lesson,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == 0){
            LessonModel lesson = (LessonModel) examItems.get(position);
            ((LessonViewHolder) holder).setLessonDate(lesson);
        }
    }

    @Override
    public int getItemCount() {
        return examItems.size();
    }

    //ExamModel ViewHolder
    static class LessonViewHolder extends RecyclerView.ViewHolder{
        //Attributes
        private TextView lessonName;
        private TextView lessonTime;

        //Methods
        //Constructor
        public LessonViewHolder(@NonNull View itemView) {
            super(itemView);
            lessonName = itemView.findViewById(R.id.txt_lesson_name);
            lessonTime = itemView.findViewById(R.id.txt_lesson_time);
        }

        void setLessonDate(LessonModel lesson){
            lessonName.setText(lesson.getLessonName());
            lessonTime.setText(lesson.getHour());
        }
    }
}
