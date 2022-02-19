package unispark.controller.guicontroller.professor;


import android.content.Context;
import android.content.Intent;




import com.example.unispark.R;
import unispark.engeneeringclasses.others.Session;

import unispark.controller.guicontroller.UserBaseGuiController;
import unispark.view.professor.ProfessorExamsView;
import unispark.view.professor.ProfessorHomeView;
import unispark.view.professor.ProfessorProfileView;


public class ProfBaseGuiController extends UserBaseGuiController {

    //Attributes
    private Context context;
    private boolean isOpen;

    //Constructor
    protected ProfBaseGuiController(Session session, Context view) {
        super(session);
        this.context = view;
    }

    //Select the next Activity through BottomNavigationMenu
    public void selectNextView(int selectedID){
        Intent intent = null;

        //Use the ID to select the activity
        switch (selectedID){
            //Professor
            case R.id.professor_home: intent = new Intent(context, ProfessorHomeView.class);
                break;
            case R.id.professor_profile: intent = new Intent(context, ProfessorProfileView.class);
                break;
            case R.id.professor_exams: intent = new Intent(context, ProfessorExamsView.class);
                break;
            default: break;
        }
        //Pass the Session to the new Activity
        intent.putExtra("session", session);

        //If the Activity already exist open it again, don't create a new instance of the same.
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //Start the Activity
        context.startActivity(intent);
    }


    public void setOpen(boolean open) {
        isOpen = open;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean isOpen() {
        return isOpen;
    }

}
