package unispark.view.mobileview.professor.fragment;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.time.OffsetDateTime;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.example.unispark.R;
import unispark.engeneeringclasses.others.Session;
import unispark.controller.guicontroller.professor.AddHomeworkGuiController;
import unispark.view.mobileview.viewadapter.HomeworksAdapter;
import unispark.engeneeringclasses.bean.BeanHomework;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.List;

public class AddHomeworkView extends DialogFragment{

    //Title
    private TextInputLayout txtTitle;
    //Instructions
    private TextInputLayout txtInstructions;
    //Date Picker
    private TextView txtDisplayDate;
    private DatePickerDialog.OnDateSetListener dateListener;
    //Points
    private TextInputLayout txtPoints;
    //Course Selector
    private AutoCompleteTextView autoCompleteTxt;
    private ArrayAdapter adapterItems;


    //Homeworks
    private HomeworksAdapter homeworksAdapter;
    private AddHomeworkGuiController homeworkGuiController;

    String date = "";
    String courseSelection = "";


    //Constructor
    public AddHomeworkView(Session session, List<BeanHomework> bHomeworkList, HomeworksAdapter homeworksAdapter) {
        //Getting Professor Object
        this.homeworksAdapter = homeworksAdapter;
        this.homeworkGuiController = new AddHomeworkGuiController(session, this, bHomeworkList);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_homework, container, false);
        getDialog().setTitle("Simple Dialog");

        this.adapterItems = new ArrayAdapter(this.getContext(), R.layout.item_container_item);

        //Dismiss Button
        ImageButton btnDismiss;
        btnDismiss = rootView.findViewById(R.id.btn_goback);
        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });



        //DropDown Selector
        autoCompleteTxt = rootView.findViewById(R.id.select_course);
        //Gui controller
        homeworkGuiController.coursesNamesSelector();

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                courseSelection = (String)parent.getItemAtPosition(position);

                //Getting the selected Course position
                homeworkGuiController.selectPosition(position);
            }
        });

        //Date Picker
        Calendar calendar;
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        txtDisplayDate = rootView.findViewById(R.id.txt_selected_date);
        OffsetDateTime offset = OffsetDateTime.now();
        txtDisplayDate.setText(offset.getDayOfMonth() + " / " + offset.getMonthValue() + " / " + offset.getYear());


        ImageButton btnSelectDate;
        btnSelectDate = rootView.findViewById(R.id.btn_select_date);
        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeworkGuiController.showDateDialog(year, month, day);
            }
        });
        dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month++;
                date = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day);

                txtDisplayDate.setText(date);
            }
        };




        //Title
        txtTitle = rootView.findViewById(R.id.txt_add_homework_title);
        //Instructions
        txtInstructions = rootView.findViewById(R.id.txt_add_homework_instructions);
        //Points
        txtPoints = rootView.findViewById(R.id.txt_add_homework_points);

        //Add Homework
        Button btnAddHomework;
        btnAddHomework = rootView.findViewById(R.id.btn_add_homework_add);
        btnAddHomework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = txtTitle.getEditText().getText().toString();
                String instructions = txtInstructions.getEditText().getText().toString();
                String points = txtPoints.getEditText().getText().toString();

                homeworkGuiController.addHomework(courseSelection, title, instructions,
                        points, date);
            }
        });


        return rootView;
    }



    //Gui Controller Methods
    public void setAdapterItems(List<String> coursesNames) {
        adapterItems.addAll(coursesNames);
        autoCompleteTxt.setAdapter(adapterItems);
    }

    public void setMessage(String message){
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    //Homeworks Adapter from the HomeView
    public HomeworksAdapter getHomeworksAdapter() {
        return homeworksAdapter;
    }

    //Method to notify the adapter about the added bean
    public void notifyDataChanged(){
        homeworksAdapter.notifyDataSetChanged();
    }


    public DatePickerDialog.OnDateSetListener getDateListener() {
        return dateListener;
    }
}
