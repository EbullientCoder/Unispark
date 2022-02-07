package com.example.cli.clicontroller.generic;


import com.example.common.applicationcontroller.communications.ShowCommunications;
import com.example.common.bean.communications.BeanUniCommunication;
import com.example.common.bean.professor.BeanLoggedProfessor;
import com.example.common.bean.student.BeanLoggedStudent;

import java.util.List;

public class UniversityCommunications {

    //Student
    public void universityCommunications(BeanLoggedStudent student){
        //List of University Communications
        List<BeanUniCommunication> uniCommunicationsList;

        //Application Controller
        ShowCommunications uniCommunicationsAppController = new ShowCommunications();
        uniCommunicationsList = uniCommunicationsAppController.showUniversityCommunications(student);

        showCommunications(uniCommunicationsList);
    }

    //Professor
    public void universityCommunications(BeanLoggedProfessor professor){
        //List of University Communications
        List<BeanUniCommunication> uniCommunicationsList;

        //Application Controller
        ShowCommunications uniCommunicationsAppController = new ShowCommunications();
        uniCommunicationsList = uniCommunicationsAppController.showUniversityCommunications(professor);

        showCommunications(uniCommunicationsList);
    }

    //University
    public void universityCommunications(){
        //List of University Communications
        List<BeanUniCommunication> uniCommunicationsList;

        //Application Controller
        ShowCommunications uniCommunicationsAppController = new ShowCommunications();
        uniCommunicationsList = uniCommunicationsAppController.showUniversityCommunications();

        showCommunications(uniCommunicationsList);
    }


    //Show Communications
    private void showCommunications(List<BeanUniCommunication> uniCommunicationsList){

        //Show Uni Communications
        StringBuilder bld = new StringBuilder();
        bld.append("-------------------- University Communications --------------------\n\n");

        for(int i = 0; i < uniCommunicationsList.size(); i++){
            bld.append(uniCommunicationsList.get(i).getTitle() + "\n");
            bld.append(uniCommunicationsList.get(i).getDate() + "\n");
            bld.append(uniCommunicationsList.get(i).getCommunication() + "\n\n");
        }
        System.out.println(bld.toString());
    }
}
