package com.example.unispark.adapter.exams;

public class ExamItem {

    //Attributes
    private int type;
    private Object object;

    //Methods
    //Constructor
    public ExamItem(int type, Object object) {
        this.type = type;
        this.object = object;
    }

    public int getType() {
        return type;
    }

    public Object getObject() {
        return object;
    }
}
