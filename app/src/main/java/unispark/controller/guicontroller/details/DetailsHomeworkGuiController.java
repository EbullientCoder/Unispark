package unispark.controller.guicontroller.details;



import unispark.engeneeringclasses.bean.BeanHomework;
import unispark.view.mobileview.details.DetailsHomeworkView;

public class DetailsHomeworkGuiController{

    private DetailsHomeworkView detailsView;
    private BeanHomework beanHomework;



    public DetailsHomeworkGuiController(DetailsHomeworkView detailsView, BeanHomework beanHomework) {
        this.beanHomework = beanHomework;
        this.detailsView = detailsView;
    }

    public void showDetails(String homeViewName){

        this.detailsView.setTxtExpiration(beanHomework.getExpiration());
        this.detailsView.setTxtInstructions(beanHomework.getInstructions());
        this.detailsView.setTxtPoints(beanHomework.getPoints());
        this.detailsView.setTxtShortName(beanHomework.getShortName());
        this.detailsView.setTxtTitle(beanHomework.getTitle());

        if(homeViewName.equals("ProfessorHome")){
            this.detailsView.setSubmitLayout();
        }
    }
}
