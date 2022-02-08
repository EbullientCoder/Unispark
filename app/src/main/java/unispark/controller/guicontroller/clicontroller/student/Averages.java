package unispark.controller.guicontroller.clicontroller.student;


import unispark.controller.appcontroller.average.CalculateAverage;
import unispark.engeneeringclasses.bean.student.BeanLoggedStudent;

public class Averages {

    public void averages(BeanLoggedStudent student){
        float aAverage;
        float wAverage;
        //Application Controller
        CalculateAverage averageAppController = new CalculateAverage();
        //Arithmetic Average
        aAverage = averageAppController.arithmeticAverage(student);
        //Weighted Averages
        wAverage = averageAppController.weightedAverage(student);

        //Show Averages
        String output = "-------------------- Averages --------------------\n\n";

        output = output + "Arithmetic Average: " + String.format("%.02f", aAverage) + "\n";
        output = output + "Weighted Average: " + String.format("%.02f", wAverage) + "\n";

        System.out.println(output);
    }
}
