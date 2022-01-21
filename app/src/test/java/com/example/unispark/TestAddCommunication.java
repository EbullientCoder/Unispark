package com.example.unispark;

import static org.junit.Assert.assertEquals;

import com.example.unispark.bean.BeanProfessorCommunication;
import com.example.unispark.bean.BeanUniCommunication;
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

        try {
            communicationController.addUniCommunication(new BeanUniCommunication(R.id.img_communication_background,
                    "Pubblicazione lezioni", "2022-01-20",
                    "Sono state aggiunte le lezioni per il secondo semestre", "Economia"));
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

        try {
            communicationController.addProfCommunication(new BeanProfessorCommunication(R.id.img_professor_profile_image,
                    "TES", "TEST COURSE",
                    "Francesco Manzini", "2022-01-20", "Lezioni", "Le lezioni sono rinviate alla prossima settimana"));
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

        try {
            communicationController.addProfCommunication(new BeanProfessorCommunication(R.id.img_professor_profile_image,
                    "MAT", "MATEMATICA GENERALE",
                    "Francesco Manzini", "2022-01-20", "Esami", "Gli esami verranno verbalizzati a breve"));
            code = 1;
        } catch (GenericException | CourseDoesNotExist genericException) {
            genericException.printStackTrace();
            code = 2;
        }

        assertEquals(code, 1);
    }










}
