package com.example.unispark.viewadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.R;
import com.example.common.bean.BeanLesson;

import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    //Attributes
    private List<BeanLesson> lessonItem;
    private OnDelBtnClickListener onDelBtnClickListener;
    private String type;


    //Click Delete Link Interface
    public interface OnDelBtnClickListener{
        void onDelBtnClick(int position);
    }

    //Methods
    //Constructor
    public LessonAdapter(String type){
        this.type = type;
    }

    public LessonAdapter(OnDelBtnClickListener onDelBtnClickListener, String type) {
        this.onDelBtnClickListener = onDelBtnClickListener;
        this.type = type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(type.equals("STUDENT")){
            return new LessonViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_container_lesson,
                            parent,
                            false
                    )
            );
        }
        else{
            return new LessonUniViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_container_lesson,
                            parent,
                            false
                    ), onDelBtnClickListener
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(type.equals("STUDENT")){
            BeanLesson lesson = (BeanLesson) lessonItem.get(position);
            ((LessonViewHolder) holder).setLessonDate(lesson);
        }
        else if(type.equals("UNIVERSITY")){
            BeanLesson lesson = (BeanLesson) lessonItem.get(position);
            ((LessonUniViewHolder) holder).setLessonDate(lesson);
        }
    }

    @Override
    public int getItemCount() {
        return lessonItem.size();
    }

    //ExamModel ViewHolder
    static class LessonViewHolder extends RecyclerView.ViewHolder{
        //Attributes
        private TextView lessonName;
        private TextView lessonTime;
        private ImageButton btnDelete;

        //Methods
        //Constructor
        public LessonViewHolder(@NonNull View itemView) {
            super(itemView);
            lessonName = itemView.findViewById(R.id.txt_lesson_name);
            lessonTime = itemView.findViewById(R.id.txt_lesson_time);
            btnDelete = itemView.findViewById(R.id.btn_delete_lesson);
            btnDelete.setVisibility(View.GONE);
        }

        void setLessonDate(BeanLesson lesson){
            lessonName.setText(lesson.getLessonName());
            lessonTime.setText(lesson.getHour());
        }
    }

    //ExamModel ViewHolder
    static class LessonUniViewHolder extends RecyclerView.ViewHolder{
        //Attributes
        private TextView lessonName;
        private TextView lessonTime;
        private ImageButton btnDelete;

        //Methods
        //Constructor
        public LessonUniViewHolder(@NonNull View itemView, OnDelBtnClickListener onDelBtnClickListener) {
            super(itemView);
            lessonName = itemView.findViewById(R.id.txt_lesson_name);
            lessonTime = itemView.findViewById(R.id.txt_lesson_time);
            btnDelete = itemView.findViewById(R.id.btn_delete_lesson);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onDelBtnClickListener.onDelBtnClick(getAdapterPosition());
                }
            });
        }

        void setLessonDate(BeanLesson lesson){
            lessonName.setText(lesson.getLessonName());
            lessonTime.setText(lesson.getHour());
        }
    }

    public void setLessonItem(List<BeanLesson> lessonItem) {
        this.lessonItem = lessonItem;
    }
}
