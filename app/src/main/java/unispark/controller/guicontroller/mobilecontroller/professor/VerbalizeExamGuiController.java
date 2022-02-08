package unispark.controller.guicontroller.mobilecontroller.professor;


import unispark.engeneeringclasses.bean.exams.BeanBookExam;
import unispark.engeneeringclasses.bean.BeanStudentSignedToExam;
import unispark.controller.appcontroller.exams.VerbalizeExam;
import unispark.engeneeringclasses.exceptions.ExamNotYetOccured;
import unispark.engeneeringclasses.exceptions.GenericException;
import unispark.view.mobileview.professor.VerbalizeExamsView;


import java.util.List;

public class VerbalizeExamGuiController{

    private VerbalizeExamsView verbalizeExamsView;
    private BeanBookExam beanBookExam;
    private List<BeanStudentSignedToExam> beanStudentSignedToExams;


    public VerbalizeExamGuiController(BeanBookExam beanBookExam, VerbalizeExamsView verbalizeExamsView) {
        this.verbalizeExamsView = verbalizeExamsView;
        this.beanBookExam = beanBookExam;
    }

    public void showStudents(){

        this.verbalizeExamsView.setTxtCourseName(this.beanBookExam.getName());
        this.verbalizeExamsView.setTxtCourseDate(this.beanBookExam.getDate());

        //Application Controller
        VerbalizeExam studentsSignedToExam = new VerbalizeExam();
        this.beanStudentSignedToExams = studentsSignedToExam.getStudentsVerbalizeExam(this.getBeanBookExam());

        if(this.getBeanStudentSignedToExams().isEmpty()) this.verbalizeExamsView.setMessage("No students signed");
        else this.verbalizeExamsView.setStudentsAdapter(this.getBeanStudentSignedToExams());

    }





    public void verbalizeExam(String result, int position){

        //Application Controller
        VerbalizeExam verbalizeExamAppController = new VerbalizeExam();
        try {
            double doubleResult = Double.parseDouble(result);
            if (doubleResult < 0 || doubleResult > 30){
                this.verbalizeExamsView.setMessage("Grade must be a number between 0 and 30");
            }
            else{
                verbalizeExamAppController.verbalizeExam(this.getBeanBookExam(), this.getBeanStudentSignedToExams().get(position), result);
                //Remove Verbalized Exam
                this.beanStudentSignedToExams.remove(position);
                this.verbalizeExamsView.notifyDataChanged(position);
            }




        } catch (ExamNotYetOccured | GenericException e) {
            e.printStackTrace();
            this.verbalizeExamsView.setMessage(e.getMessage());
        } catch (NumberFormatException numberFormatException){
            this.verbalizeExamsView.setMessage(numberFormatException.getMessage());
        }
    }





    public BeanBookExam getBeanBookExam() {
        return beanBookExam;
    }

    public List<BeanStudentSignedToExam> getBeanStudentSignedToExams() {
        return beanStudentSignedToExams;
    }
}
