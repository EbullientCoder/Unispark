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


    @Test
    public void testAddUniCommunication(){
        int code = 0;

        AddCommunication communicationController;
        communicationController = new AddCommunication();
        BeanUniCommunication communication;
        communication = new BeanUniCommunication();
        communication.setTitle("Pubblicazione lezioni");
        communication.setCommunication("Sono state aggiunte le lezioni per il secondo semestre");
        communication.setFaculty("Economia");
        communication.setDate("2022-01-20");
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

        AddCommunication communicationController;
        communicationController = new AddCommunication();
        BeanProfessorCommunication communication;
        communication= new BeanProfessorCommunication();
        communication.setType("Lezioni");
        communication.setCommunication("Le lezioni sono rinviate alla prossima settimana");
        communication.setProfessorName("Francesco Manzini");
        communication.setDate("2022-01-20");
        communication.setProfilePhoto(R.id.img_professor_profile_image);
        communication.setFullName("TEST COURSE");
        communication.setShortCourseName("TES");

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

        AddCommunication communicationController;
        communicationController = new AddCommunication();
        BeanProfessorCommunication communication;
        communication= new BeanProfessorCommunication();
        communication.setType("Esami");
        communication.setCommunication("Gli esami verranno verbalizzati a breve");
        communication.setProfessorName("Francesco Manzini");
        communication.setDate("2022-01-20");
        communication.setProfilePhoto(R.id.img_professor_profile_image);
        communication.setFullName("MATEMATICA GENERALE");
        communication.setShortCourseName("MAT");

        try {
            communicationController.addProfCommunication(communication);
            code = 1;
        } catch (GenericException | CourseDoesNotExist genericException) {
            genericException.printStackTrace();
            code = 2;
        }

        assertEquals(code, 1);
    }

}
