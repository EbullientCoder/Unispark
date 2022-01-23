package com.example.unispark.viewadapter.exams;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.R;
import com.example.unispark.bean.exams.BeanBookExam;
import com.example.unispark.bean.exams.BeanExamType;
import com.example.unispark.bean.exams.BeanVerbalizeExam;


import java.util.List;

public class ExamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    //Attributes
    private List<BeanExamType> bExams;
    private OnViewExamClickListener onViewExamClickListener;
    private OnBookExamClickListener onBookExamClickListener;
    private OnLeaveExamClickListener onLeaveExamClickListener;


    //Student: Click on "Book Exam" Button
    public interface OnBookExamClickListener{
        void onBookBtnClick(int position);
    }

    //Student: Click on "Leave Exam" Button
    public interface OnLeaveExamClickListener{
        void onLeaveBtnClick(int position);
    }

    //Professor: Click on "View Assigned Exam" Button
    public interface OnViewExamClickListener {
        void onViewBtnClick(int position);
    }



    //Methods
    //Constructor
    public ExamAdapter(List<BeanExamType> bExams,
                       OnBookExamClickListener onBookExamClickListener,
                       OnLeaveExamClickListener onLeaveExamClickListener) {
        this.bExams = bExams;
        this.onBookExamClickListener = onBookExamClickListener;
        this.onLeaveExamClickListener = onLeaveExamClickListener;
    }

    public ExamAdapter(List<BeanExamType> bExams,
                       OnViewExamClickListener onViewExamClickListener) {
        this.bExams = bExams;
        this.onViewExamClickListener = onViewExamClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Verbalized - Failed ExamModel
        if(viewType == 0){
            return new VerbalizedExamViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_container_verbalized_exams,
                            parent,
                            false
                    )
            );
        }
        //Professor: View Assigned StudentExamsGUIController
        else if(viewType == 1){
            return new UpcomingExamViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_container_professor_assigned_exams,
                            parent,
                            false
                    ), onViewExamClickListener
            );
        }
        //Student: Book StudentExamsGUIController
        else if(viewType == 2){
            return new BookExamViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_container_book_exams,
                            parent,
                            false
                    ), onBookExamClickListener
            );
        }
        //Student: Booked StudentExamsGUIController
        else{
            return new BookedExamViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_container_book_exams,
                            parent,
                            false
                    ), onLeaveExamClickListener
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //Verbalized - Failed StudentExamsGUIController
        if(getItemViewType(position) == 0){
            BeanVerbalizeExam vExam = (BeanVerbalizeExam) bExams.get(position).getExamType();
            ((VerbalizedExamViewHolder) holder).setVerbalizedExamDate(vExam);
        }
        //Professor: Assigned StudentExamsGUIController
        else if(getItemViewType(position) == 1){
            BeanBookExam assignedExam = (BeanBookExam) bExams.get(position).getExamType();
            ((UpcomingExamViewHolder) holder).setUpcomingExamDate(assignedExam);
        }
        //Student: Book StudentExamsGUIController
        else if(getItemViewType(position) == 2){
            BeanBookExam bookExam = (BeanBookExam) bExams.get(position).getExamType();
            ((BookExamViewHolder) holder).setBookExamDate(bookExam);
        }
        //Student: Booked StudentExamsGUIController
        else {
            BeanBookExam bookExam = (BeanBookExam) bExams.get(position).getExamType();
            ((BookedExamViewHolder) holder).setBookedExamDate(bookExam);
        }
    }

    @Override
    public int getItemCount() {
        return bExams.size();
    }

    @Override
    public int getItemViewType(int position){
        return bExams.get(position).getType();
    }



    //0: Verbalized - Failed StudentExamsGUIController
    static class VerbalizedExamViewHolder extends RecyclerView.ViewHolder{
        //Attributes
        TextView txtExamName;
        TextView txtExamYear;
        TextView txtExamDate;
        TextView txtExamCFU;
        TextView txtExamResult;

        //Methods
        //Constructor
        public VerbalizedExamViewHolder(@NonNull View itemView) {
            super(itemView);
            txtExamName = itemView.findViewById(R.id.txt_verbalized_exam_subject_name);
            txtExamYear = itemView.findViewById(R.id.txt_verbalized_exam_aa);
            txtExamDate = itemView.findViewById(R.id.txt_verbalized_exam_date);
            txtExamCFU = itemView.findViewById(R.id.txt_verbalized_exam_cfu);
            txtExamResult = itemView.findViewById(R.id.txt_verbalized_exam_result);
        }

        void setVerbalizedExamDate(BeanVerbalizeExam exam){
            txtExamName.setText(exam.getName());
            txtExamYear.setText(exam.getYear());
            txtExamDate.setText(exam.getDate());
            txtExamCFU.setText(exam.getCfu());
            txtExamResult.setText(exam.getResult());
        }
    }

    //1: Professor Assigned StudentExamsGUIController
    static class UpcomingExamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Attributes
        TextView txtExamName;
        TextView txtExamYear;
        TextView txtExamDate;
        TextView txtExamCFU;
        TextView txtExamBuilding;
        TextView txtExamClassroom;
        Button btnView;

        OnViewExamClickListener onViewExamClickListener;


        public UpcomingExamViewHolder(@NonNull View itemView, OnViewExamClickListener onViewExamClickListener) {
            super(itemView);
            txtExamName = itemView.findViewById(R.id.txt_upcoming_exam_subject_name);
            txtExamYear = itemView.findViewById(R.id.txt_upcoming_exam_aa);
            txtExamDate = itemView.findViewById(R.id.txt_upcoming_exam_date);
            txtExamCFU = itemView.findViewById(R.id.txt_upcoming_exam_cfu);
            txtExamClassroom = itemView.findViewById(R.id.txt_upcoming_exam_classroom);
            txtExamBuilding = itemView.findViewById(R.id.txt_upcoming_exam_building);
            btnView = itemView.findViewById(R.id.btn_upcoming_exam_view);

            this.onViewExamClickListener = onViewExamClickListener;
            btnView.setOnClickListener(this);
        }

        void setUpcomingExamDate(BeanBookExam exam){
            txtExamName.setText(exam.getName());
            txtExamYear.setText(exam.getYear());
            txtExamDate.setText(exam.getDate());
            txtExamCFU.setText(exam.getCfu());
            txtExamClassroom.setText(exam.getClassroom());
            txtExamBuilding.setText(exam.getBuilding());
        }

        @Override
        public void onClick(View view) {
            onViewExamClickListener.onViewBtnClick(getAdapterPosition());
        }
    }

    //2: Student Book Exam
    static class BookExamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Attributes
        TextView txtExamName;
        TextView txtExamYear;
        TextView txtExamDate;
        TextView txtExamCFU;
        TextView txtExamClassroom;
        TextView txtExamBuilding;
        Button btnBook;

        OnBookExamClickListener onBookExamClickListener;


        public BookExamViewHolder(@NonNull View itemView, OnBookExamClickListener onBookExamClickListener) {
            super(itemView);
            txtExamName = itemView.findViewById(R.id.txt_book_exam_subject_name);
            txtExamYear = itemView.findViewById(R.id.txt_book_exam_aa);
            txtExamDate = itemView.findViewById(R.id.txt_book_exam_date);
            txtExamCFU = itemView.findViewById(R.id.txt_book_exam_cfu);
            txtExamClassroom = itemView.findViewById(R.id.txt_book_exam_classroom);
            txtExamBuilding = itemView.findViewById(R.id.txt_book_exam_building);
            btnBook = itemView.findViewById(R.id.btn_book_exam_book);

            this.onBookExamClickListener = onBookExamClickListener;
            btnBook.setOnClickListener(this);
        }

        void setBookExamDate(BeanBookExam exam){
            txtExamName.setText(exam.getName());
            txtExamYear.setText(exam.getYear());
            txtExamDate.setText(exam.getDate());
            txtExamCFU.setText(exam.getCfu());
            txtExamClassroom.setText(exam.getClassroom());
            txtExamBuilding.setText(exam.getBuilding());
        }

        @Override
        public void onClick(View view) {
            onBookExamClickListener.onBookBtnClick(getAdapterPosition());
        }
    }

    //3: Student Booked Exam
    static class BookedExamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //Attributes
        TextView txtExamName;
        TextView txtExamYear;
        TextView txtExamDate;
        TextView txtExamCFU;
        TextView txtExamClassroom;
        TextView txtExamBuilding;
        Button btnLeave;

        OnLeaveExamClickListener onLeaveExamClickListener;

        public BookedExamViewHolder(@NonNull View itemView, OnLeaveExamClickListener onLeaveExamClickListener) {
            super(itemView);
            txtExamName = itemView.findViewById(R.id.txt_book_exam_subject_name);
            txtExamYear = itemView.findViewById(R.id.txt_book_exam_aa);
            txtExamDate = itemView.findViewById(R.id.txt_book_exam_date);
            txtExamCFU = itemView.findViewById(R.id.txt_book_exam_cfu);
            txtExamClassroom = itemView.findViewById(R.id.txt_book_exam_classroom);
            txtExamBuilding = itemView.findViewById(R.id.txt_book_exam_building);
            btnLeave = itemView.findViewById(R.id.btn_book_exam_book);

            this.onLeaveExamClickListener = onLeaveExamClickListener;
            btnLeave.setOnClickListener(this);
        }

        void setBookedExamDate(BeanBookExam exam){
            txtExamName.setText(exam.getName());
            txtExamYear.setText(exam.getYear());
            txtExamDate.setText(exam.getDate());
            txtExamCFU.setText(exam.getCfu());
            txtExamClassroom.setText(exam.getClassroom());
            txtExamBuilding.setText(exam.getBuilding());
            btnLeave.setText("LEAVE");
        }

        @Override
        public void onClick(View view) {
            onLeaveExamClickListener.onLeaveBtnClick(getAdapterPosition());
        }
    }
}
