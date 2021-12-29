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
    private OnExamBtnClickListener onExamBtnClickListener;




    //Click ExamItem Interface
    public interface OnExamBtnClickListener {
        void onBtnClick(int position);
    }


    //Methods
    //Constructor
    public ExamAdapter(List<ExamItem> examItems) {
        this.examItems = examItems;
    }

    public ExamAdapter(List<ExamItem> examItems, OnExamBtnClickListener onExamBtnClickListener) {
        this.examItems = examItems;
        this.onExamBtnClickListener = onExamBtnClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Verbalized ExamModel
        if(viewType == 0){
            return new VerbalizedExamViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_container_verbalized_exams,
                            parent,
                            false
                    )
            );
        }
        //Failed ExamModel
        else if(viewType == 1){
            return new FailedExamViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_container_failed_exams,
                            parent,
                            false
                    )
            );
        }
        //Reserve Exam
        else if(viewType == 2){
            return new BookExamViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_container_book_exams,
                            parent,
                            false
                    )
            );
        }
        //Professor Upcoming Exam
        else{
            return new UpcomingExamViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_container_upcoming_exams,
                            parent,
                            false
                    ), onExamBtnClickListener
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //verbalized Exams
        if(getItemViewType(position) == 0){
            VerbalizedExamModel vExam = (VerbalizedExamModel) examItems.get(position).getObject();
            ((VerbalizedExamViewHolder) holder).setVerbalizedExamDate(vExam);
        }
        //Failed Exams
        else if(getItemViewType(position) == 1){
            VerbalizedExamModel fExam = (VerbalizedExamModel) examItems.get(position).getObject();
            ((FailedExamViewHolder) holder).setFailedExamDate(fExam);
        }
        //Reserve Exams
        else if(getItemViewType(position) == 2){
            BookExamModel rExam = (BookExamModel) examItems.get(position).getObject();
            ((BookExamViewHolder) holder).setBookExamDate(rExam);
        }
        //Professor Upcoming Exams
        else {
            BookExamModel uExam = (BookExamModel) examItems.get(position).getObject();
            ((UpcomingExamViewHolder) holder).setUpcomingExamDate(uExam);
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


    //ExamModel ViewHolder
    static class VerbalizedExamViewHolder extends RecyclerView.ViewHolder{
        //Attributes
        private TextView vExamName;
        private TextView vExamYear;
        private TextView vExamDate;
        private TextView vExamCFU;
        private TextView vExamResult;

        //Methods
        //Constructor
        public VerbalizedExamViewHolder(@NonNull View itemView) {
            super(itemView);
            vExamName = itemView.findViewById(R.id.txt_verbalized_exam_subject_name);
            vExamYear = itemView.findViewById(R.id.txt_verbalized_exam_aa);
            vExamDate = itemView.findViewById(R.id.txt_verbalized_exam_date);
            vExamCFU = itemView.findViewById(R.id.txt_verbalized_exam_cfu);
            vExamResult = itemView.findViewById(R.id.txt_verbalized_exam_result);
        }

        void setVerbalizedExamDate(VerbalizedExamModel exam){
            vExamName.setText(exam.getName());
            vExamYear.setText(exam.getYear());
            vExamDate.setText(exam.getDate());
            vExamCFU.setText(exam.getCFU());
            vExamResult.setText(exam.getResult());
        }
    }

    static class FailedExamViewHolder extends RecyclerView.ViewHolder{
        //Attributes
        private TextView fExamName;
        private TextView fExamYear;
        private TextView fExamDate;
        private TextView fExamCFU;
        private TextView fExamResult;

        public FailedExamViewHolder(@NonNull View itemView) {
            super(itemView);
            fExamName = itemView.findViewById(R.id.txt_failed_exam_subject_name);
            fExamYear = itemView.findViewById(R.id.txt_failed_exam_aa);
            fExamDate = itemView.findViewById(R.id.txt_failed_exam_date);
            fExamCFU = itemView.findViewById(R.id.txt_failed_exam_cfu);
            fExamResult = itemView.findViewById(R.id.txt_failed_exam_result);
        }

        void setFailedExamDate(VerbalizedExamModel exam){
            fExamName.setText(exam.getName());
            fExamYear.setText(exam.getYear());
            fExamDate.setText(exam.getDate());
            fExamCFU.setText(exam.getCFU());
            fExamResult.setText(exam.getResult());
        }
    }

    static class BookExamViewHolder extends RecyclerView.ViewHolder{
        //Attributes
        private TextView rExamName;
        private TextView rExamYear;
        private TextView rExamDate;
        private TextView rExamCFU;
        private TextView rExamClassroom;
        private TextView rExamBuilding;

        public BookExamViewHolder(@NonNull View itemView) {
            super(itemView);
            rExamName = itemView.findViewById(R.id.txt_book_exam_subject_name);
            rExamYear = itemView.findViewById(R.id.txt_book_exam_aa);
            rExamDate = itemView.findViewById(R.id.txt_book_exam_date);
            rExamCFU = itemView.findViewById(R.id.txt_book_exam_cfu);
            rExamClassroom = itemView.findViewById(R.id.txt_book_exam_classroom);
            rExamBuilding = itemView.findViewById(R.id.txt_book_exam_building);
        }

        void setBookExamDate(BookExamModel exam){
            rExamName.setText(exam.getName());
            rExamYear.setText(exam.getYear());
            rExamDate.setText(exam.getDate());
            rExamCFU.setText(exam.getCFU());
            rExamClassroom.setText(exam.getClassroom());
            rExamBuilding.setText(exam.getBuilding());
        }
    }

    static class UpcomingExamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Attributes
        private TextView uExamName;
        private TextView uExamYear;
        private TextView uExamDate;
        private TextView uExamCFU;
        private TextView uExamBuilding;
        private TextView uExamClassroom;
        private Button btnView;

        private OnExamBtnClickListener onExamBtnClickListener;


        public UpcomingExamViewHolder(@NonNull View itemView, OnExamBtnClickListener onExamBtnClickListener) {
            super(itemView);
            uExamName = itemView.findViewById(R.id.txt_upcoming_exam_subject_name);
            uExamYear = itemView.findViewById(R.id.txt_upcoming_exam_aa);
            uExamDate = itemView.findViewById(R.id.txt_upcoming_exam_date);
            uExamCFU = itemView.findViewById(R.id.txt_upcoming_exam_cfu);
            uExamClassroom = itemView.findViewById(R.id.txt_upcoming_exam_classroom);
            uExamBuilding = itemView.findViewById(R.id.txt_upcoming_exam_building);
            btnView = itemView.findViewById(R.id.btn_upcoming_exam_view);

            this.onExamBtnClickListener = onExamBtnClickListener;
            btnView.setOnClickListener(this);
        }

        void setUpcomingExamDate(BookExamModel exam){
            uExamName.setText(exam.getName());
            uExamYear.setText(exam.getYear());
            uExamDate.setText(exam.getDate());
            uExamCFU.setText(exam.getCFU());
            uExamClassroom.setText(exam.getClassroom());
            uExamBuilding.setText(exam.getBuilding());
        }

        @Override
        public void onClick(View view) {
            onExamBtnClickListener.onBtnClick(getAdapterPosition());
        }
    }
}
