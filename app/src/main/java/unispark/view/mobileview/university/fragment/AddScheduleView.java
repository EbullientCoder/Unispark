package unispark.view.mobileview.university.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.unispark.R;
import unispark.engeneeringclasses.others.Session;
import unispark.engeneeringclasses.bean.BeanLesson;
import unispark.controller.guicontroller.university.AddScheduleGuiController;
import unispark.view.mobileview.viewadapter.LessonAdapter;

import java.util.List;


public class AddScheduleView extends DialogFragment {


    //Course Selector
    private AutoCompleteTextView autoCompleteTxtCourse;
    private ArrayAdapter adapterItemsCourse;

    //Day Selector
    private AutoCompleteTextView autoCompleteTxtDay;
    private ArrayAdapter adapterItemsDay;

    //Hour Selector
    private AutoCompleteTextView autoCompleteTxtHour;
    private ArrayAdapter adapterItemsHour;


    private LessonAdapter lessonAdapter;



    String courseSelection = "";
    String daySelection = "";
    String hourSelection = "";

    //Gui Controller
    private AddScheduleGuiController scheduleGuiController;



    //Constructor
    public AddScheduleView(Session session, LessonAdapter lessonAdapter, List<BeanLesson> schedulesItem, int indexDay) {
        this.lessonAdapter = lessonAdapter;
        this.scheduleGuiController = new AddScheduleGuiController(session, schedulesItem, indexDay, this);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_schedule, container, false);
        getDialog().setTitle("Simple Dialog");

        this.adapterItemsCourse = new ArrayAdapter(this.getContext(), R.layout.item_container_item);
        this.adapterItemsDay = new ArrayAdapter(this.getContext(), R.layout.item_container_item);
        this.adapterItemsHour = new ArrayAdapter(this.getContext(), R.layout.item_container_item);


        //Dismiss Button
        ImageButton btnDismiss;
        btnDismiss = rootView.findViewById(R.id.btn_schedule_goback);
        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });



        //Course selector
        this.autoCompleteTxtCourse = rootView.findViewById(R.id.add_schedule_course);
        //Gui Controller: Show Courses
        this.scheduleGuiController.showCoursesNames();
        this.autoCompleteTxtCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                courseSelection = (String)parent.getItemAtPosition(position);
            }
        });

        //Days
        this.autoCompleteTxtDay = rootView.findViewById(R.id.add_schedule_day);
        this.scheduleGuiController.showDays();
        this.autoCompleteTxtDay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                daySelection = (String)parent.getItemAtPosition(position);
            }
        });

        //Hours
        this.autoCompleteTxtHour = rootView.findViewById(R.id.add_schedule_hour);
        this.scheduleGuiController.showHours();
        this.autoCompleteTxtHour.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                hourSelection = (String)parent.getItemAtPosition(position);
            }
        });



        //Add StudentScheduleGUIController
        Button btnAddSchedule;
        btnAddSchedule = rootView.findViewById(R.id.btn_add_schedule_add);
        btnAddSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                scheduleGuiController.addSchedule(courseSelection, daySelection, hourSelection);
            }
        });

        return rootView;
    }





    public void setMessage(String message){
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }



    public void setAdapterItemsCourse(List<String> courses) {
        this.adapterItemsCourse.addAll(courses);
        this.autoCompleteTxtCourse.setAdapter(this.getAdapterItemsCourse());

    }

    public ArrayAdapter getAdapterItemsCourse() {
        return adapterItemsCourse;
    }

    public void setAdapterItemsDay(String[] days) {
        this.adapterItemsDay.addAll(days);
        this.autoCompleteTxtDay.setAdapter(this.getAdapterItemsDay());
    }

    public ArrayAdapter getAdapterItemsDay() {
        return adapterItemsDay;
    }

    public void setAdapterItemsHour(String[] hours) {
        this.adapterItemsHour.addAll(hours);
        this.autoCompleteTxtHour.setAdapter(this.getAdapterItemsHour());
    }

    public ArrayAdapter getAdapterItemsHour() {
        return adapterItemsHour;
    }


    public void notifyDataChanged(){
        this.lessonAdapter.notifyDataSetChanged();
    }
}