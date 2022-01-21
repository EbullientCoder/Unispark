package com.example.unispark.controller.guicontroller.professor;

import android.content.Context;
import android.content.Intent;

import com.example.unispark.bean.homework.BeanHomework;
import com.example.unispark.bean.communication.BeanUniCommunication;
import com.example.unispark.bean.login.BeanLoggedProfessor;
import com.example.unispark.controller.applicationcontroller.communications.ShowCommunications;
import com.example.unispark.controller.applicationcontroller.homeworks.ShowHomeworks;
import com.example.unispark.view.details.DetailsHomeworkView;
import com.example.unispark.view.details.DetailsUniCommunicationView;

import java.util.List;

public class ManageProfessorHomeGuiController extends BaseProfessorGuiController {




    public List<BeanUniCommunication> showUniCommunications(BeanLoggedProfessor professor){
        List<BeanUniCommunication> uniCommunications;

        //Application Controller
        ShowCommunications uniCommunicationsAppController = new ShowCommunications();
        uniCommunications = uniCommunicationsAppController.showUniversityCommunications(professor);


        return uniCommunications;
    }

    public List<BeanHomework> showHomeworks(BeanLoggedProfessor professor){
        List<BeanHomework> homeworks;

        ShowHomeworks homeworksAppController = new ShowHomeworks();
        homeworks = homeworksAppController.getHomework(professor);

        return homeworks;
    }

    public void showDetailsCommunication(Context context, BeanUniCommunication communication){
        Intent intent = new Intent(context, DetailsUniCommunicationView.class);
        //Pass Items to the new Activity
        intent.putExtra("Communication", communication);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    public void showHomeworkDetails(Context context, BeanHomework homework){

        Intent intent = new Intent(context, DetailsHomeworkView.class);
        //Pass Items to the new Activity
        intent.putExtra("Homework", homework);
        intent.putExtra("StudentHomeView", "ProfessorHome");
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
