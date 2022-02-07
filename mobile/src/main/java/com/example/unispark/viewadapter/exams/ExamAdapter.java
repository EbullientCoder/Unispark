package com.example.unispark.viewadapter.exams;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.R;
import com.example.common.bean.exams.BeanBookExam;
import com.example.common.bean.exams.BeanExam;
import com.example.common.bean.exams.BeanVerbalizeExam;


import java.util.List;

public class ExamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    //Attributes
    private List<BeanExam> bExams;
    private int examType;
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
    public ExamAdapter(OnBookExamClickListener onBookExamClickListener,
                       OnLeaveExamClickListener onLeaveExamClickListener) {
        this.onBookExamClickListener = onBookExamClickListener;
        this.onLeaveExamClickListener = onLeaveExamClickListener;
    }

    public ExamAdapter(OnViewExamClickListener onViewExamClickListener) {
        this.onViewExamClickListener = onViewExamClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Verbalized - Failed Exams
        if(viewType == 0){
            return new VerbalizedExamViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_container_verbalized_exams,
                            parent,
                            false
                    )
            );
        }

        else if(viewType == 1){
            //Book Exams
            return new BookExamViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_container_book_exams,
                            parent,
                            false
                    ), onBookExamClickListener
            );
        }
        //Student: Booked Exams
        else if(viewType == 2){
            return new BookedExamViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_container_book_exams,
                            parent,
                            false
                    ), onLeaveExamClickListener
            );
        }
        //Professor: View Assigned Exams
        else{
            return new UpcomingExamViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_container_professor_assigned_exams,
                            parent,
                            false
                    ), onViewExamClickListener
            );
        }
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //Verbalized - Failed
        if(examType == 0){
            BeanVerbalizeExam vExam = (BeanVerbalizeExam) bExams.get(position);
            ((VerbalizedExamViewHolder) holder).setVerbalizedExamDate(vExam);
        }

        //Student: Book
        else if(examType == 1){
            BeanBookExam bookExam = (BeanBookExam) bExams.get(position);
            ((BookExamViewHolder) holder).setBookExamDate(bookExam);
        }
        //Student: Booked
        else if(examType == 2){
            BeanBookExam bookExam = (BeanBookExam) bExams.get(position);
            ((BookedExamViewHolder) holder).setBookedExamDate(bookExam);
        }
        //Professor: Assigned
        else {
            BeanBookExam assignedExam = (BeanBookExam) bExams.get(position);
            ((UpcomingExamViewHolder) holder).setUpcomingExamDate(assignedExam);
        }
    }

    @Override
    public int getItemCount() {
        return bExams.size();
    }

    @Override
    public int getItemViewType(int position){
        return examType;
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

    //3: Professor Assigned StudentExamsGUIController
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

    //1: Student Book Exam
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


    public void setbExams(List<BeanExam> bExams) {
        this.bExams = bExams;
    }

    public void setExamType(int examType) {
        this.examType = examType;
    }
}
