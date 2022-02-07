package com.example.unispark.viewadapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.R;
import com.example.common.bean.BeanLink;

import java.util.List;

public class LinksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //Attributes
    private List<BeanLink> beanLinkList;
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
    public LinksAdapter(OnLinkClickListener onLinkClickListener, OnDelBtnClickListener onDelBtnClickListener){
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
        BeanLink beanLink = (BeanLink) beanLinkList.get(position);
        ((LinkViewHolder) holder).setLinkDate(beanLink);
    }

    @Override
    public int getItemCount() {
        return beanLinkList.size();
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

        void setLinkDate(BeanLink link){
            linkName.setText(link.getLinkName());
            linkAddress.setText(link.getLinkAddress());
        }
    }

    public void setBeanLinkList(List<BeanLink> beanLinkList) {
        this.beanLinkList = beanLinkList;
    }
}

