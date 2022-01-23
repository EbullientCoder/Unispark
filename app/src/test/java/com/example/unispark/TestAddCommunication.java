package com.example.unispark;

import static org.junit.Assert.assertEquals;

import com.example.unispark.bean.communications.BeanProfessorCommunication;
import com.example.unispark.bean.communications.BeanUniCommunication;
import com.example.unispark.controller.applicationcontroller.communications.AddCommunication;
import com.example.unispark.exceptions.CourseDoesNotExist;
import com.example.unispark.exceptions.GenericException;

import org.junit.Test;

/**
 *
 *
 * @author Andrea Lapiana
 *
 *
 */

public class TestAddCommunication {

    String data = "2022-01-20";


    @Test
    public void testAddUniCommunication(){
        int code = 0;

        //Communication
        AddCommunication communicationController;
        communicationController = new AddCommunication();
        BeanUniCommunication communication;
        communication = new BeanUniCommunication();
        communication.setTitle("Pubblicazione lezioni");
        communication.setCommunication("Sono state aggiunte le lezioni per il secondo semestre");
        communication.setFaculty("Economia");
        communication.setDate(data);
        communication.setBackground(R.id.img_uni_com_background);

        try {
            communicationController.addUniCommunication(communication);
            code = 1;
        } catch (GenericException genericException) {
            genericException.printStackTrace();
            code = 2;
        }

        assertEquals(code, 1);
    }




    @Test
    public void testCannotAddProfCommunication(){
        int code = 1;

        //Communication
        AddCommunication communicationController;
        communicationController = new AddCommunication();
        BeanProfessorCommunication communication = createBean(
                "Lezioni",
                "Le lezioni sono rinviate alla prossima settimana",
                "Francesco Manzini",
                data,
                "TEST COURSE",
                "TES");

        try {
            communicationController.addProfCommunication(communication);
            code = 2;
        } catch (GenericException genericException) {
            genericException.printStackTrace();
            code = 3;
        } catch (CourseDoesNotExist courseDoesNotExist) {
            courseDoesNotExist.printStackTrace();
            code = 0;
        }

        assertEquals(code, 0);
    }





    @Test
    public void testAddProfCommunication(){
        int code = 0;

        //Communication
        AddCommunication communicationController;
        communicationController = new AddCommunication();
        BeanProfessorCommunication communication = createBean(
                "Esami",
                "Gli esami verranno verbalizzati a breve",
                "Francesco Manzini",
                data,
                "MATEMATICA GENERALE",
                "MAT");

        try {
            communicationController.addProfCommunication(communication);
            code = 1;
        } catch (GenericException | CourseDoesNotExist genericException) {
            genericException.printStackTrace();
            code = 2;
        }

        assertEquals(code, 1);
    }



    //Create the Communication Bean
    private static BeanProfessorCommunication createBean(String type, String text, String profName, String data, String fullName, String shortName){
        BeanProfessorCommunication communication;
        communication= new BeanProfessorCommunication();
        communication.setType(type);
        communication.setCommunication(text);
        communication.setProfessorName(profName);
        communication.setDate(data);
        communication.setProfilePhoto(R.id.img_professor_profile_image);
        communication.setFullName(fullName);
        communication.setShortCourseName(shortName);

        return communication;
    }
}
