package com.example.unispark.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.R;
import com.example.unispark.bean.StudentBean;
import com.example.unispark.model.ProfessorModel;
import com.example.unispark.model.StudentModel;

import java.util.List;

public class SignedStudentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    //Attributes
    List<StudentBean> items;


    //Methods
    public SignedStudentsAdapter(List<StudentBean> items){
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_signed_student,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        StudentBean student = (StudentBean) items.get(position);
        ((SignedStudentsAdapter.StudentViewHolder) holder).setStudentDate(student);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }



    static class StudentViewHolder extends RecyclerView.ViewHolder{
        //Attributes
        private TextView fullname;
        private TextView id;

        //Methods
        //Constructor
        StudentViewHolder(@NonNull View itemView){
            super(itemView);

            fullname = itemView.findViewById(R.id.txt_signed_student_fullname);
            id = itemView.findViewById(R.id.txt_signed_student_id);
        }

        void setStudentDate(StudentBean student){
            fullname.setText(student.getFullName());
            id.setText(student.getId());
        }
    }
}
