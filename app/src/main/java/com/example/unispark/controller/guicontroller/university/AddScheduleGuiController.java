package com.example.unispark.controller.guicontroller.university;

import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import com.example.unispark.bean.BeanCoursesNames;
import com.example.unispark.bean.BeanLesson;
import com.example.unispark.controller.applicationcontroller.course.MenageCourses;
import com.example.unispark.controller.applicationcontroller.schedule.AddLesson;
import com.example.unispark.controller.applicationcontroller.schedule.GetScheduleUniversity;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.exceptions.LessonAlreadyExists;
import com.example.unispark.viewadapter.LessonAdapter;

import java.util.List;

public class AddScheduleGuiController extends BaseUniGuiController{



    public BeanCoursesNames showCoursesNames(List<String> faculties){

        BeanCoursesNames bCoursesNames;
        MenageCourses getCoursesAppController = new MenageCourses();
        bCoursesNames = getCoursesAppController.getCoursesNamesByFaculty(faculties);

        return bCoursesNames;

    }



    public void addSchedule(Context context, Dialog dialog, String onDay, String courseSelection, String daySelection, String hourSelection, List<BeanLesson> lessons, LessonAdapter lessonAdapter){

        if (courseSelection.equals("") || daySelection.equals("") || hourSelection.equals("")){
            getInvalidMessagge(context);
        }

        else{
            //Creating new Lesson
            BeanLesson bLesson;
            bLesson = new BeanLesson(courseSelection, daySelection, hourSelection);

            //Application Controller: Add Lesson
            AddLesson addLessonAppController = new AddLesson();

            try {
                addLessonAppController.addLesson(bLesson);
                ScheduleUpdatedMessage(context);

                if (onDay.equals(daySelection)){
                    lessons.add(0, bLesson);
                }

                //Notifying the Lessons Adapter
                lessonAdapter.notifyDataSetChanged();

                //App Controller: GetScheduleUniversity
                //Sorting the Lessons
                GetScheduleUniversity getScheduleUniversity =  new GetScheduleUniversity();
                getScheduleUniversity.LessonsSort(lessons);

                dialog.dismiss();
            } catch (GenericException | LessonAlreadyExists e) {
                e.printStackTrace();
                getErrorMessage(context, e.getMessage());
            }
        }
    }




    private void ScheduleUpdatedMessage(Context context){
        Toast.makeText(context, "Schedule updated", Toast.LENGTH_SHORT).show();
    }


}
