package com.example.unispark.controller.guicontroller.details;



import com.example.unispark.bean.courses.BeanCourse;
import com.example.unispark.view.details.DetailsCourseView;

public class DetailsCourseGuiController extends DetailsGuiController{


    private DetailsCourseView detailsView;
    private BeanCourse beanCourse;

    public DetailsCourseGuiController(DetailsCourseView detailsView, BeanCourse beanCourse) {
        super(detailsView, beanCourse.getLink());
        this.detailsView = detailsView;
        this.beanCourse = beanCourse;
    }

    public void showDetails(){
        this.detailsView.setTxtShortName(this.beanCourse.getShortName());
        this.detailsView.setTxtLongName(this.beanCourse.getFullName());
        this.detailsView.setTxtAA(this.beanCourse.getCourseYear());
        this.detailsView.setTxtCFU(this.beanCourse.getCfu());
        this.detailsView.setTxtFaculty(this.beanCourse.getFaculty());
        this.detailsView.setTxtID(this.beanCourse.getId());
        this.detailsView.setTxtLink(this.beanCourse.getLink());
        this.detailsView.setTxtSession(this.beanCourse.getSession());
    }
}
