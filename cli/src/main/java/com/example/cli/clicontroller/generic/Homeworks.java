package com.example.cli.clicontroller.generic;



import com.example.common.applicationcontroller.homeworks.GetHomeworks;
import com.example.common.bean.BeanHomework;
import com.example.common.bean.professor.BeanLoggedProfessor;
import com.example.common.bean.student.BeanLoggedStudent;

import java.io.PrintStream;
import java.util.List;

public class Homeworks {

    //Student
    public void homeworks(BeanLoggedStudent student){
        //List of Homeworks
        List<BeanHomework> homeworksList;

        //Application Controller
        GetHomeworks homeworksAppController = new GetHomeworks();
        homeworksList = homeworksAppController.getHomework(student);

        showHomeworks(homeworksList);
    }

    //Professor
    public void homeworks(BeanLoggedProfessor professor){
        //List of Homeworks
        List<BeanHomework> homeworksList;

        //Application Controller
        GetHomeworks homeworksAppController = new GetHomeworks();
        homeworksList = homeworksAppController.getHomework(professor);

        showHomeworks(homeworksList);
    }


    //Show Homeworks
    private void showHomeworks(List<BeanHomework> homeworksList){
        PrintStream write = System.out;

        //Show Homeworks
        StringBuilder bld = new StringBuilder();
        bld.append("-------------------- Homeworks --------------------\n\n\n");

        for(int i = 0; i < homeworksList.size(); i++){
            bld.append(homeworksList.get(i).getFullName() + " (" + homeworksList.get(i).getShortName() + ")\n");
            bld.append(homeworksList.get(i).getTitle() + "\n");
            bld.append(homeworksList.get(i).getInstructions() + "\n");
            bld.append("Points: " + homeworksList.get(i).getPoints() + "\n");
            bld.append("Due To: " + homeworksList.get(i).getExpiration() + "\n\n\n");
        }
        write.println(bld.toString());
    }
}
