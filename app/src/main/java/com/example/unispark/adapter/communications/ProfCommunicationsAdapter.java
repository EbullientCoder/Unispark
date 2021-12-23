package com.example.unispark.adapter.communications;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.R;
import com.example.unispark.model.communications.ProfessorCommunicationModel;

import java.util.List;

public class ProfCommunicationsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //Attributes
    private List<ProfessorCommunicationModel> items;
    private OnProfComClickListener onProfComClickListener;

    //Click ExamItem Interface
    public interface OnProfComClickListener {
        void onProfClick(int position);
    }


    //Methods
    public ProfCommunicationsAdapter(List<ProfessorCommunicationModel> items, OnProfComClickListener onProfComClickListener){
        this.items = items;
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
        ProfessorCommunicationModel professorCommunicationModel = (ProfessorCommunicationModel) items.get(position);
        ((ProfCommunicationViewHolder) holder).setCommunicationDate(professorCommunicationModel);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /*@Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }*/


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

        void setCommunicationDate(ProfessorCommunicationModel professorCommunicationModel){
            imageProfessor.setImageResource(professorCommunicationModel.getProfilePhoto());
            shortName.setText(professorCommunicationModel.getShortCourseName());
            type.setText(professorCommunicationModel.getType());
        }

        @Override
        public void onClick(View view) {

            onProfComClickListener.onProfClick(getAdapterPosition());
        }
    }
}

