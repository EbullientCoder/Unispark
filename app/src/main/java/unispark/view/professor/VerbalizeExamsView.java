package unispark.view.professor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unispark.R;
import unispark.controller.guicontroller.professor.VerbalizeExamGuiController;
import unispark.view.viewadapter.SignedStudentsAdapter;
import unispark.engeneeringclasses.bean.exams.BeanBookExam;
import unispark.engeneeringclasses.bean.BeanStudentSignedToExam;

import java.util.List;

public class VerbalizeExamsView extends AppCompatActivity
        implements SignedStudentsAdapter.OnAddBtnClickListener {


    //Exam Data
    private TextView txtCourseName;
    private TextView txtCourseDate;
    private RecyclerView rvStudents;
    private SignedStudentsAdapter studentsAdapter;



    //Gui Controller
    private VerbalizeExamGuiController verbalizeExamGuiController;


    //Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_verbalize_exams);


        this.verbalizeExamGuiController = new VerbalizeExamGuiController((BeanBookExam) getIntent().getExtras().getSerializable("Exam"), this);
        this.studentsAdapter = new SignedStudentsAdapter(this);

        //GoBack Button
        ImageView btnGoBack;
        btnGoBack = findViewById(R.id.btn_verbalize_exam_goback);
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //Exam Name - Date
        this.txtCourseName = findViewById(R.id.txt_verbalize_exam_shortname);
        this.txtCourseDate = findViewById(R.id.txt_verbalize_exam_date);
        //Students Recycler View
        this.rvStudents = findViewById(R.id.rv_signedStudents);
        //Gui Controller
        this.verbalizeExamGuiController.showStudents();
    }

    @Override
    public void onAddBtnClick(int position, String result) {
        this.verbalizeExamGuiController.verbalizeExam(result, position);

    }




    public void setStudentsAdapter(List<BeanStudentSignedToExam> beanStudentSignedToExams) {
        this.studentsAdapter.setItems(beanStudentSignedToExams);
        this.rvStudents.setAdapter(this.getStudentsAdapter());
    }

    public SignedStudentsAdapter getStudentsAdapter() {
        return studentsAdapter;
    }


    public void setMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    public void notifyDataChanged(int position){
        this.studentsAdapter.notifyItemRemoved(position);
    }

    public void setTxtCourseName(String content) {
        this.txtCourseName.setText(content);
    }

    public void setTxtCourseDate(String content) {
        this.txtCourseDate.setText(content);
    }
}