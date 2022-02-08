package unispark.engeneeringclasses.factory;

import unispark.engeneeringclasses.bean.exams.BeanBookExam;
import unispark.engeneeringclasses.bean.exams.BeanExam;
import unispark.engeneeringclasses.bean.exams.BeanVerbalizeExam;
import unispark.engeneeringclasses.model.exams.BookExamModel;
import unispark.engeneeringclasses.model.exams.ExamModel;
import unispark.engeneeringclasses.model.exams.VerbalizedExamModel;

import java.util.ArrayList;
import java.util.List;

public class FactoryExams {

    private static FactoryExams instance = null;

    private FactoryExams() {

    }

    public static FactoryExams getInstance() {
        if (instance == null) {
            instance = new FactoryExams();
        }
        return instance;
    }


    public List<BeanExam> createBeanVerbalizedExams(List<ExamModel> exams) {
        List<BeanExam> bExams = new ArrayList<>();

        //BeanVerbalizeExams
        for (int i = 0; i < exams.size(); i++) {
            VerbalizedExamModel vExam = (VerbalizedExamModel) exams.get(i);
            BeanVerbalizeExam beanVerbalizeExam;
            beanVerbalizeExam = new BeanVerbalizeExam();
            beanVerbalizeExam.setName(vExam.getName());
            beanVerbalizeExam.setResult(vExam.getResult());
            beanVerbalizeExam.setCfu(vExam.getCfu());
            beanVerbalizeExam.setDate(vExam.getDate());
            beanVerbalizeExam.setId(vExam.getId());
            beanVerbalizeExam.setYear(vExam.getYear());

            bExams.add(beanVerbalizeExam);
        }

        return bExams;

    }


    public List<BeanExam> createBeanBookExams(List<ExamModel> exams){
        List<BeanExam> bExams = new ArrayList<>();
        //BeanBookExams
        for (int i = 0; i < exams.size(); i++) {
            BookExamModel bExam = (BookExamModel) exams.get(i);
            BeanBookExam beanBookExam;
            beanBookExam = new BeanBookExam();
            beanBookExam.setDate(bExam.getDate());
            beanBookExam.setYear(bExam.getYear());
            beanBookExam.setName(bExam.getName());
            beanBookExam.setCfu(bExam.getCfu());
            beanBookExam.setId(bExam.getId());
            beanBookExam.setBuilding(bExam.getBuilding());
            beanBookExam.setClassroom(bExam.getClassroom());

            bExams.add(beanBookExam);
        }

        return bExams;
    }

}
