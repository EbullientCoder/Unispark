package com.example.unispark.adapter.exams;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.R;
import com.example.unispark.model.exams.BookExamModel;
import com.example.unispark.model.exams.VerbalizedExamModel;


import java.util.List;

public class ExamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    //Attributes
    private List<ExamItem> examItems;
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
    public ExamAdapter(List<ExamItem> examItems,
                       OnBookExamClickListener onBookExamClickListener,
                       OnLeaveExamClickListener onLeaveExamClickListener) {
        this.examItems = examItems;
        this.onBookExamClickListener = onBookExamClickListener;
        this.onLeaveExamClickListener = onLeaveExamClickListener;
    }

    public ExamAdapter(List<ExamItem> examItems,
                       OnViewExamClickListener onViewExamClickListener) {
        this.examItems = examItems;
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
            VerbalizedExamModel vExam = (VerbalizedExamModel) examItems.get(position).getObject();
            ((VerbalizedExamViewHolder) holder).setVerbalizedExamDate(vExam);
        }
        //Professor: Assigned StudentExamsGUIController
        else if(getItemViewType(position) == 1){
            BookExamModel assignedExam = (BookExamModel) examItems.get(position).getObject();
            ((UpcomingExamViewHolder) holder).setUpcomingExamDate(assignedExam);
        }
        //Student: Book StudentExamsGUIController
        else if(getItemViewType(position) == 2){
            BookExamModel bookExam = (BookExamModel) examItems.get(position).getObject();
            ((BookExamViewHolder) holder).setBookExamDate(bookExam);
        }
        //Student: Booked StudentExamsGUIController
        else {
            BookExamModel bookExam = (BookExamModel) examItems.get(position).getObject();
            ((BookedExamViewHolder) holder).setBookedExamDate(bookExam);
        }
    }

    @Override
    public int getItemCount() {
        return examItems.size();
    }

    @Override
    public int getItemViewType(int position){
        return examItems.get(position).getType();
    }



    //0: Verbalized - Failed StudentExamsGUIController
    static class VerbalizedExamViewHolder extends RecyclerView.ViewHolder{
        //Attributes
        private TextView ExamName;
        private TextView ExamYear;
        private TextView ExamDate;
        private TextView ExamCFU;
        private TextView ExamResult;

        //Methods
        //Constructor
        public VerbalizedExamViewHolder(@NonNull View itemView) {
            super(itemView);
            ExamName = itemView.findViewById(R.id.txt_verbalized_exam_subject_name);
            ExamYear = itemView.findViewById(R.id.txt_verbalized_exam_aa);
            ExamDate = itemView.findViewById(R.id.txt_verbalized_exam_date);
            ExamCFU = itemView.findViewById(R.id.txt_verbalized_exam_cfu);
            ExamResult = itemView.findViewById(R.id.txt_verbalized_exam_result);
        }

        void setVerbalizedExamDate(VerbalizedExamModel exam){
            ExamName.setText(exam.getName());
            ExamYear.setText(exam.getYear());
            ExamDate.setText(exam.getDate());
            ExamCFU.setText(exam.getCFU());
            ExamResult.setText(exam.getResult());
        }
    }

    //1: Professor Assigned StudentExamsGUIController
    static class UpcomingExamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Attributes
        private TextView ExamName;
        private TextView ExamYear;
        private TextView ExamDate;
        private TextView ExamCFU;
        private TextView ExamBuilding;
        private TextView ExamClassroom;
        private Button btnView;

        private OnViewExamClickListener onViewExamClickListener;


        public UpcomingExamViewHolder(@NonNull View itemView, OnViewExamClickListener onViewExamClickListener) {
            super(itemView);
            ExamName = itemView.findViewById(R.id.txt_upcoming_exam_subject_name);
            ExamYear = itemView.findViewById(R.id.txt_upcoming_exam_aa);
            ExamDate = itemView.findViewById(R.id.txt_upcoming_exam_date);
            ExamCFU = itemView.findViewById(R.id.txt_upcoming_exam_cfu);
            ExamClassroom = itemView.findViewById(R.id.txt_upcoming_exam_classroom);
            ExamBuilding = itemView.findViewById(R.id.txt_upcoming_exam_building);
            btnView = itemView.findViewById(R.id.btn_upcoming_exam_view);

            this.onViewExamClickListener = onViewExamClickListener;
            btnView.setOnClickListener(this);
        }

        void setUpcomingExamDate(BookExamModel exam){
            ExamName.setText(exam.getName());
            ExamYear.setText(exam.getYear());
            ExamDate.setText(exam.getDate());
            ExamCFU.setText(exam.getCFU());
            ExamClassroom.setText(exam.getClassroom());
            ExamBuilding.setText(exam.getBuilding());
        }

        @Override
        public void onClick(View view) {
            onViewExamClickListener.onViewBtnClick(getAdapterPosition());
        }
    }

    //2: Student Book Exam
    static class BookExamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Attributes
        private TextView ExamName;
        private TextView ExamYear;
        private TextView ExamDate;
        private TextView ExamCFU;
        private TextView ExamClassroom;
        private TextView ExamBuilding;
        private Button btnBook;

        private OnBookExamClickListener onBookExamClickListener;


        public BookExamViewHolder(@NonNull View itemView, OnBookExamClickListener onBookExamClickListener) {
            super(itemView);
            ExamName = itemView.findViewById(R.id.txt_book_exam_subject_name);
            ExamYear = itemView.findViewById(R.id.txt_book_exam_aa);
            ExamDate = itemView.findViewById(R.id.txt_book_exam_date);
            ExamCFU = itemView.findViewById(R.id.txt_book_exam_cfu);
            ExamClassroom = itemView.findViewById(R.id.txt_book_exam_classroom);
            ExamBuilding = itemView.findViewById(R.id.txt_book_exam_building);
            btnBook = itemView.findViewById(R.id.btn_book_exam_book);

            this.onBookExamClickListener = onBookExamClickListener;
            btnBook.setOnClickListener(this);
        }

        void setBookExamDate(BookExamModel exam){
            ExamName.setText(exam.getName());
            ExamYear.setText(exam.getYear());
            ExamDate.setText(exam.getDate());
            ExamCFU.setText(exam.getCFU());
            ExamClassroom.setText(exam.getClassroom());
            ExamBuilding.setText(exam.getBuilding());
        }

        @Override
        public void onClick(View view) {
            onBookExamClickListener.onBookBtnClick(getAdapterPosition());
        }
    }

    //3: Student Booked Exam
    static class BookedExamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //Attributes
        private TextView ExamName;
        private TextView ExamYear;
        private TextView ExamDate;
        private TextView ExamCFU;
        private TextView ExamClassroom;
        private TextView ExamBuilding;
        private Button btnLeave;

        private OnLeaveExamClickListener onLeaveExamClickListener;

        public BookedExamViewHolder(@NonNull View itemView, OnLeaveExamClickListener onLeaveExamClickListener) {
            super(itemView);
            ExamName = itemView.findViewById(R.id.txt_book_exam_subject_name);
            ExamYear = itemView.findViewById(R.id.txt_book_exam_aa);
            ExamDate = itemView.findViewById(R.id.txt_book_exam_date);
            ExamCFU = itemView.findViewById(R.id.txt_book_exam_cfu);
            ExamClassroom = itemView.findViewById(R.id.txt_book_exam_classroom);
            ExamBuilding = itemView.findViewById(R.id.txt_book_exam_building);
            btnLeave = itemView.findViewById(R.id.btn_book_exam_book);

            this.onLeaveExamClickListener = onLeaveExamClickListener;
            btnLeave.setOnClickListener(this);
        }

        void setBookedExamDate(BookExamModel exam){
            ExamName.setText(exam.getName());
            ExamYear.setText(exam.getYear());
            ExamDate.setText(exam.getDate());
            ExamCFU.setText(exam.getCFU());
            ExamClassroom.setText(exam.getClassroom());
            ExamBuilding.setText(exam.getBuilding());
            btnLeave.setText("LEAVE");
        }

        @Override
        public void onClick(View view) {
            onLeaveExamClickListener.onLeaveBtnClick(getAdapterPosition());
        }
    }
}
