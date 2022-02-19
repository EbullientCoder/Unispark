package unispark.controller.guicontroller.details;


import unispark.engeneeringclasses.bean.courses.BeanCourse;
import unispark.engeneeringclasses.bean.professor.BeanProfessorDetails;
import unispark.view.details.DetailsProfessorView;

import java.util.List;

public class DetailsProfessorGuiController extends DetailsGuiController{

    private DetailsProfessorView detailsView;
    private BeanProfessorDetails beanProfessorDetails;


    public DetailsProfessorGuiController(DetailsProfessorView detailsView, BeanProfessorDetails beanProfessorDetails) {
        super(detailsView, beanProfessorDetails.getWebsite());
        this.detailsView  = detailsView;
        this.beanProfessorDetails = beanProfessorDetails;
    }


    public void showDetails(){
        int imageID = this.beanProfessorDetails.getProfilePicture();
        String firstname = this.beanProfessorDetails.getFirstName();
        String lastname = this.beanProfessorDetails.getLastName();
        String website = this.beanProfessorDetails.getWebsite();

        this.detailsView.setImgProfImage(imageID);
        this.detailsView.setTxtProfName(firstname + ' ' + lastname);
        this.detailsView.setTxtWebsite(website);
    }

    public void showCourseFirstLink(){
        List<BeanCourse> courses = this.beanProfessorDetails.getCourses();
        this.detailsView.setTxtCourse1(courses.get(0).getFullName());
        this.detailsView.setTxtLink1(courses.get(0).getLink());
    }

    public boolean showCourseSecondLink(){
        List<BeanCourse> courses = this.beanProfessorDetails.getCourses();
        if(courses.size() >= 2){
            this.detailsView.setTxtCourse2(courses.get(1).getFullName());
            this.detailsView.setTxtLink2(courses.get(1).getLink());
            return true;
        }
        else{
            this.detailsView.setTxtCourse2("//");
            this.detailsView.setTxtLink2("//");
            return false;
        }
    }



    public boolean showCourseThirdLink(){
        List<BeanCourse> courses = this.beanProfessorDetails.getCourses();
        if(courses.size() >= 3){
            this.detailsView.setTxtCourse3(courses.get(2).getFullName());
            this.detailsView.setTxtLink3(courses.get(2).getLink());
            return true;
        }
        else{
            this.detailsView.setTxtCourse3("//");
            this.detailsView.setTxtLink3("//");
            return false;
        }
    }


    public void mainLink(){
        this.setLink(this.beanProfessorDetails.getWebsite());
    }

    public void selectFirstLink(){
        List<BeanCourse> courses = this.beanProfessorDetails.getCourses();
        this.setLink(courses.get(0).getLink());
        this.goToLink();
        this.mainLink();
    }


    public void selectSecondLink(){
        List<BeanCourse> courses = this.beanProfessorDetails.getCourses();
        this.setLink(courses.get(1).getLink());
        this.goToLink();
        this.mainLink();
    }


    public void selectThirdLink(){
        List<BeanCourse> courses = this.beanProfessorDetails.getCourses();
        this.setLink(courses.get(2).getLink());
        this.goToLink();
        this.mainLink();
    }





}
