package com.example.unispark.adapter.communications;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.R;
import com.example.unispark.model.communications.UniversityCommunicationModel;

import java.util.List;

public class UniCommunicationsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //Attributes
    private List<UniversityCommunicationModel> items;
    private OnUniComClickListener onUniComClickListener;

    //Click ExamItem Interface
    public interface OnUniComClickListener {
        void onUniClick(int comBackground, String title, String date, String communication);
    }


    //Methods
    public UniCommunicationsAdapter(List<UniversityCommunicationModel> items, OnUniComClickListener onUniComClickListener){
        this.items = items;
        this.onUniComClickListener = onUniComClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UniCommunicationViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_uni_communication,
                        parent,
                        false
                ), onUniComClickListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UniversityCommunicationModel universityCommunicationModel = (UniversityCommunicationModel) items.get(position);
        ((UniCommunicationViewHolder) holder).setCommunicationDate(universityCommunicationModel);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /*@Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }*/


    static class UniCommunicationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Attributes
        private ImageView imgCommunication;
        private int imageID;
        private TextView txtTitle;
        private String date;
        private String communication;
        private OnUniComClickListener onUniComClickListener;

        //Methods
        //Constructor
        public UniCommunicationViewHolder(@NonNull View itemView, OnUniComClickListener onUniComClickListener) {
            super(itemView);
            imgCommunication = itemView.findViewById(R.id.img_communication_background);
            txtTitle = itemView.findViewById(R.id.txt_communication_title);

            this.onUniComClickListener = onUniComClickListener;
            itemView.setOnClickListener(this);
        }

        void setCommunicationDate(UniversityCommunicationModel universityCommunicationModel){
            imgCommunication.setImageResource(universityCommunicationModel.getBackground());
            imageID = universityCommunicationModel.getBackground();
            txtTitle.setText(universityCommunicationModel.getTitle());
            date = universityCommunicationModel.getDate();
            communication = universityCommunicationModel.getCommunication();
        }

        @Override
        public void onClick(View view) {
            onUniComClickListener.onUniClick(imageID, txtTitle.getText().toString(), date, communication);
        }
    }
}

