package com.example.unispark.adapter.exams;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.R;
import com.example.unispark.model.exams.FailedExamModel;
import com.example.unispark.model.exams.ReserveExamModel;
import com.example.unispark.model.exams.VerbalizedExamModel;

import java.util.List;

public class ExamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    //Attributes
    private List<ExamItem> examItems;

    //Methods
    //Constructor
    public ExamAdapter(List<ExamItem> examItems) {
        this.examItems = examItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Verbalized Exams
        if(viewType == 0){
            return new VerbalizedExamViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_container_verbalized_exams,
                            parent,
                            false
                    )
            );
        }
        //Failed Exams
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
        else{
            return new ReserveExamViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_container_reserve_exams,
                            parent,
                            false
                    )
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == 0){
            VerbalizedExamModel vExam = (VerbalizedExamModel) examItems.get(position).getObject();
            ((VerbalizedExamViewHolder) holder).setVerbalizedExamDate(vExam);
        }
        //Failed Exams
        else if(getItemViewType(position) == 1){
            FailedExamModel fExam = (FailedExamModel) examItems.get(position).getObject();
            ((FailedExamViewHolder) holder).setFailedExamDate(fExam);
        }
        //Reserve Exams
        else{
            ReserveExamModel rExam = (ReserveExamModel) examItems.get(position).getObject();
            ((ReserveExamViewHolder) holder).setReserveExamDate(rExam);
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


    //Exams ViewHolder
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
            vExamName.setText(exam.getExamName());
            vExamYear.setText(exam.getExamYear());
            vExamDate.setText(exam.getExamDate());
            vExamCFU.setText(exam.getExamCFU());
            vExamResult.setText(exam.getExamResult());
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

        void setFailedExamDate(FailedExamModel exam){
            fExamName.setText(exam.getExamName());
            fExamYear.setText(exam.getExamYear());
            fExamDate.setText(exam.getExamDate());
            fExamCFU.setText(exam.getExamCFU());
            fExamResult.setText(exam.getExamResult());
        }
    }

    static class ReserveExamViewHolder extends RecyclerView.ViewHolder{
        //Attributes
        private TextView rExamName;
        private TextView rExamYear;
        private TextView rExamDate;
        private TextView rExamCFU;

        public ReserveExamViewHolder(@NonNull View itemView) {
            super(itemView);
            rExamName = itemView.findViewById(R.id.txt_reserve_exam_subject_name);
            rExamYear = itemView.findViewById(R.id.txt_reserve_exam_aa);
            rExamDate = itemView.findViewById(R.id.txt_reservve_exam_date);
            rExamCFU = itemView.findViewById(R.id.txt_reserve_exam_cfu);
        }

        void setReserveExamDate(ReserveExamModel exam){
            rExamName.setText(exam.getExamName());
            rExamYear.setText(exam.getExamYear());
            rExamDate.setText(exam.getExamDate());
            rExamCFU.setText(exam.getExamCFU());
        }
    }
}
