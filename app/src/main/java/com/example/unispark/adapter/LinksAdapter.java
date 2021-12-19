package com.example.unispark.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.R;
import com.example.unispark.adapter.exams.ExamItem;
import com.example.unispark.model.LinkModel;

import java.util.List;

public class LinksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //Attributes
    private List<ExamItem> examItems;
    private OnLinkClickListener onLinkClickListener;
    private OnDelBtnClickListener onDelBtnClickListener;

    //Click ExamItem Interface
    public interface OnLinkClickListener{
        void onLinkClick(String url);
    }
    //Click Delete Link Interface
    public interface OnDelBtnClickListener{
        void onDelBtnClick(int position);
    }


    //Methods
    public LinksAdapter(List<ExamItem> examItems, OnLinkClickListener onLinkClickListener, OnDelBtnClickListener onDelBtnClickListener){
        this.examItems = examItems;
        this.onLinkClickListener = onLinkClickListener;
        this.onDelBtnClickListener = onDelBtnClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new LinkViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_link,
                        parent,
                        false
                ), onLinkClickListener, onDelBtnClickListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LinkModel link = (LinkModel) examItems.get(position).getObject();
        ((LinkViewHolder) holder).setLinkDate(link);
    }

    @Override
    public int getItemCount() {
        return examItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return examItems.get(position).getType();
    }



    static class LinkViewHolder extends RecyclerView.ViewHolder{
        //Attributes
        private TextView linkName;
        private TextView linkAddress;
        private ImageButton btnDelete;

        //Methods
        //Constructor
        LinkViewHolder(@NonNull View itemView, OnLinkClickListener onLinkClickListener, OnDelBtnClickListener onDelBtnClickListener) {
            super(itemView);
            linkName = itemView.findViewById(R.id.txt_link_name);
            linkAddress = itemView.findViewById(R.id.txt_link_address);
            btnDelete = itemView.findViewById(R.id.btn_delete_link);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onLinkClickListener.onLinkClick(linkAddress.getText().toString());
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onDelBtnClickListener.onDelBtnClick(getAdapterPosition());
                }
            });
        }

        void setLinkDate(LinkModel link){
            linkName.setText(link.getLinkName());
            linkAddress.setText(link.getLinkAddress());
        }
    }

}

