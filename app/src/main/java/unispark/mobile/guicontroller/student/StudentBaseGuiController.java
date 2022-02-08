package unispark.mobile.guicontroller.student;

import android.content.Context;
import android.content.Intent;

import com.example.unispark.R;
import unispark.mobile.Session;
import unispark.mobile.guicontroller.UserBaseGuiController;
import unispark.mobile.view.student.StudentExamsView;
import unispark.mobile.view.student.StudentHomeView;
import unispark.mobile.view.student.StudentLinksView;
import unispark.mobile.view.student.StudentProfileView;
import unispark.mobile.view.student.StudentScheduleView;


public class StudentBaseGuiController extends UserBaseGuiController {

    private Context view;

    protected StudentBaseGuiController(Session session, Context view) {
        super(session);
        this.view = view;
    }

    public void selectNextView(int selectedID){
        Intent intent = null;
        Context contextView = this.getView();
        switch (selectedID){
            //Student
            case R.id.home: intent = new Intent(contextView, StudentHomeView.class);
            break;
            case R.id.profile: intent = new Intent(contextView, StudentProfileView.class);
            break;
            case R.id.exams: intent = new Intent(contextView, StudentExamsView.class);
            break;
            case R.id.links: intent =  new Intent(contextView, StudentLinksView.class);
            break;
            case R.id.schedule: intent = new Intent(contextView, StudentScheduleView.class);
            break;
            default: break;
        }
        intent.putExtra("session", this.session);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        contextView.startActivity(intent);
    }



    public Context getView() {
        return view;
    }
}
