package com.example.unispark.controller.guicontroller.details;



import com.example.unispark.bean.communications.BeanProfessorCommunication;
import com.example.unispark.view.details.DetailsProfCommunicationView;



public class DetailsProfCommunicationGuiController{

    private DetailsProfCommunicationView communicationView;
    private BeanProfessorCommunication beanCommunication;

    public DetailsProfCommunicationGuiController(DetailsProfCommunicationView communicationView, BeanProfessorCommunication beanCommunication) {
        this.communicationView = communicationView;
        this.beanCommunication = beanCommunication;
    }


    public void showDetails(){
        this.communicationView.setTxtCommunication(this.beanCommunication.getCommunication());
        this.communicationView.setImgProfProfile(this.beanCommunication.getProfilePhoto());
        this.communicationView.setTxtProfName(this.beanCommunication.getProfessorName());
        this.communicationView.setTxtType(this.beanCommunication.getType());
        this.communicationView.setTxtFullName(this.beanCommunication.getFullName());
        this.communicationView.setTxtShortName(this.beanCommunication.getShortCourseName());
        this.communicationView.setTxtDate(this.beanCommunication.getDate());
    }
}
