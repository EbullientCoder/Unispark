package unispark.controller.guicontroller.clicontroller.student;


import unispark.controller.appcontroller.communications.ManageCommunications;
import unispark.engeneeringclasses.bean.communications.BeanProfessorCommunication;
import unispark.engeneeringclasses.bean.student.BeanLoggedStudent;

import java.util.List;

public class ProfessorCommunications {

    public void professorCommunications(BeanLoggedStudent student){
        //List of Professor Communications
        List<BeanProfessorCommunication> profCommunicationsList;

        //Application Controller
        ManageCommunications profCommunicationsAppController = new ManageCommunications();
        profCommunicationsList = profCommunicationsAppController.showProfessorCommunications(student);

        //Show Professor Communications
        StringBuilder bld = new StringBuilder();
        bld.append("-------------------- Professor Communications --------------------\n\n");

        for(int i = 0; i < profCommunicationsList.size(); i++){
            bld.append(profCommunicationsList.get(i).getProfessorName() + "\n");
            bld.append(profCommunicationsList.get(i).getFullName() + " (" + profCommunicationsList.get(i).getShortCourseName() + ")\n");
            bld.append(profCommunicationsList.get(i).getDate() + "\n");
            bld.append(profCommunicationsList.get(i).getType() + "\n");
            bld.append(profCommunicationsList.get(i).getCommunication() + "\n\n");
        }
        System.out.println(bld.toString());
    }
}
