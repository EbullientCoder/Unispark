package com.example.unispark.guicontroller.professor;

import com.example.unispark.Session;
import com.example.common.bean.BeanHomework;
import com.example.common.bean.courses.BeanCourse;
import com.example.common.bean.professor.BeanLoggedProfessor;
import com.example.common.applicationcontroller.homeworks.AddHomeworkAppController;
import com.example.common.exceptions.GenericException;
import com.example.unispark.view.professor.fragment.AddHomeworkView;

import java.util.List;

public class AddHomeworkGuiController extends AddItemGuiController{

    private List<BeanHomework> beanHomeworks;
    private AddHomeworkView addHomeworkView;

    public AddHomeworkGuiController(Session session, AddHomeworkView addHomeworkView, List<BeanHomework> beanHomeworks) {
        super(session, null, addHomeworkView, null);
        this.addHomeworkView = addHomeworkView;
        this.beanHomeworks = beanHomeworks;
    }

    public void addHomework(String courseSelection, String title,
                            String instructions, String points, String date){

        //Get the LoggedProfessor from the Session
        BeanLoggedProfessor professor = (BeanLoggedProfessor) session.getUser();
        BeanCourse beanCourse = getCourses(professor).get(getCoursePosition());

        //Homework Object
        BeanHomework bHomework;
        bHomework = new BeanHomework();
        bHomework.setFullName(beanCourse.getFullName());
        bHomework.setShortName(beanCourse.getShortName());

        //Syntactic Error check through the BeanHomework private methods
        if (courseSelection.equals("") ||
                !bHomework.setPoints(points) ||
                !bHomework.setInstructions(instructions) ||
                !bHomework.setExpiration(date) ||
                !bHomework.setTitle(title)){

            addHomeworkView.setMessage("All fields are required");
        }
        else{
            //Application Controller
            AddHomeworkAppController addHomeworkAppController = new AddHomeworkAppController();
            try {
                addHomeworkAppController.addHomework(bHomework, professor);
                addHomeworkView.setMessage("Homework added");

                //Notify the Homework Adapter
                if(beanHomeworks != null && addHomeworkView.getHomeworksAdapter() != null){
                    beanHomeworks.add(0, bHomework);
                    addHomeworkView.notifyDataChanged();
                }
                addHomeworkView.dismiss();
            } catch (GenericException e) {
                e.printStackTrace();
                addHomeworkView.setMessage(e.getMessage());
            }
        }
    }




}
