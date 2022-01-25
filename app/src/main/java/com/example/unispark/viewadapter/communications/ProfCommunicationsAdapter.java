package com.example.unispark.viewadapter.communications;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.R;
import com.example.unispark.bean.communications.BeanProfessorCommunication;

import java.util.List;

public class ProfCommunicationsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //Attributes
    private List<BeanProfessorCommunication> beanProfCommunicationList;
    private OnProfComClickListener onProfComClickListener;

    //Click ExamItem Interface
    public interface OnProfComClickListener {
        void onProfClick(int position);
    }


    //Methods
    public ProfCommunicationsAdapter(OnProfComClickListener onProfComClickListener){

        this.beanProfCommunicationList = beanProfCommunicationList;
        this.onProfComClickListener = onProfComClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProfCommunicationViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_prof_communication,
                        parent,
                        false
                ), onProfComClickListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BeanProfessorCommunication professorCommunication = (BeanProfessorCommunication) beanProfCommunicationList.get(position);
        ((ProfCommunicationViewHolder) holder).setCommunicationDate(professorCommunication);
    }

    @Override
    public int getItemCount() {
        return beanProfCommunicationList.size();
    }



    static class ProfCommunicationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Attributes
        private ImageView imageProfessor;
        private TextView shortName;
        private TextView type;
        private OnProfComClickListener onProfComClickListener;

        //Methods
        //Constructor
        public ProfCommunicationViewHolder(@NonNull View itemView, OnProfComClickListener onProfComClickListener) {
            super(itemView);
            imageProfessor = itemView.findViewById(R.id.img_course_professor);
            shortName = itemView.findViewById(R.id.txt_prof_communication_course);
            type = itemView.findViewById(R.id.txt_prof_communication_context);

            this.onProfComClickListener = onProfComClickListener;
            itemView.setOnClickListener(this);
        }

        void setCommunicationDate(BeanProfessorCommunication professorCommunicationModel){
            imageProfessor.setImageResource(professorCommunicationModel.getProfilePhoto());
            shortName.setText(professorCommunicationModel.getShortCourseName());
            type.setText(professorCommunicationModel.getType());
        }

        @Override
        public void onClick(View view) {

            onProfComClickListener.onProfClick(getAdapterPosition());
        }
    }


    public void setBeanProfCommunicationList(List<BeanProfessorCommunication> beanProfCommunicationList) {
        this.beanProfCommunicationList = beanProfCommunicationList;
    }
}

