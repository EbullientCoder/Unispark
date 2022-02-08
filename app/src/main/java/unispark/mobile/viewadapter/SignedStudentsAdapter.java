package unispark.mobile.viewadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.R;
import unispark.engeneeringclasses.bean.BeanStudentSignedToExam;

import java.util.List;

public class SignedStudentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    //Attributes
    List<BeanStudentSignedToExam> items;
    OnAddBtnClickListener onAddBtnClickListener;


    //Click SignedStudent Add Button Interface
    public interface OnAddBtnClickListener {
        void onAddBtnClick(int position, String grade);
    }


    //Methods
    public SignedStudentsAdapter(OnAddBtnClickListener onAddBtnClickListener){
        this.onAddBtnClickListener = onAddBtnClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_signed_student,
                        parent,
                        false
                ), onAddBtnClickListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BeanStudentSignedToExam student = (BeanStudentSignedToExam) items.get(position);
        ((StudentViewHolder) holder).setStudentDate(student);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }



    static class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Attributes
        TextView fullname;
        TextView id;
        EditText txtGrade;
        Button btnAddGrade;

        String result;
        OnAddBtnClickListener onAddBtnClickListener;

        //Methods
        //Constructor
        StudentViewHolder(@NonNull View itemView, OnAddBtnClickListener onAddBtnClickListener){
            super(itemView);

            fullname = itemView.findViewById(R.id.txt_signed_student_fullname);
            id = itemView.findViewById(R.id.txt_signed_student_id);
            txtGrade = itemView.findViewById(R.id.txt_verbalize_exam_grade);
            btnAddGrade = itemView.findViewById(R.id.btn_signed_student_add);

            this.onAddBtnClickListener = onAddBtnClickListener;
            btnAddGrade.setOnClickListener(this);
        }

        void setStudentDate(BeanStudentSignedToExam student){
            fullname.setText(student.getFullName());
            id.setText(student.getId() + ": ");
        }

        @Override
        public void onClick(View view) {
            result = String.valueOf(txtGrade.getText());
            onAddBtnClickListener.onAddBtnClick(getAdapterPosition(), result);
        }
    }


    public void setItems(List<BeanStudentSignedToExam> items) {
        this.items = items;
    }
}
