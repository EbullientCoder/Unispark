package com.example.unispark.bean.exams;

public class BeanExamType {

    //Attributes
    private int type;
    private BeanExam beanExamType;

    //Methods
    //Constructor
    public BeanExamType(int type, BeanExam beanExamType) {
        this.type = type;
        this.beanExamType = beanExamType;
    }

    public int getType() {
        return type;
    }

    public BeanExam getBeanExamType() {
        return beanExamType;
    }
}
