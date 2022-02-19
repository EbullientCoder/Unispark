package unispark.controller.guicontroller.university;

import android.content.Intent;

import com.example.unispark.R;
import unispark.engeneeringclasses.others.Session;
import unispark.engeneeringclasses.bean.communications.BeanUniCommunication;
import unispark.engeneeringclasses.bean.university.BeanLoggedUniversity;
import unispark.controller.appcontroller.communications.AddCommunication;
import unispark.controller.guicontroller.UserBaseGuiController;
import unispark.engeneeringclasses.exceptions.GenericException;
import unispark.view.university.fragment.AddUniCommunicationView;

import java.util.List;

public class AddCommunicationGuiController extends UserBaseGuiController {

    private AddUniCommunicationView uniCommunicationView;
    private List<BeanUniCommunication> beanUniCommunications;



    public AddCommunicationGuiController(Session session, List<BeanUniCommunication> beanUniCommunications, AddUniCommunicationView uniCommunicationView) {
        super(session);
        this.uniCommunicationView = uniCommunicationView;
        this.beanUniCommunications = beanUniCommunications;
    }

    public void showAddMedia(){
        Intent i = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        i.setFlags(i.FLAG_ACTIVITY_NEW_TASK);
        final int ACTIVITY_SELECT_IMAGE = 1234;
        this.uniCommunicationView.startActivityForResult(i, ACTIVITY_SELECT_IMAGE);
    }




    public void showFaculties(){
        BeanLoggedUniversity university = (BeanLoggedUniversity) this.session.getUser();
        List<String> faculties = university.getFaculties();
        faculties.add("All");
        this.uniCommunicationView.setAdapterItems(faculties);
    }



    //Creating the Communication & Adding it into the Database
    public void addCommunication(String title, String text, String facultySelection, String date){

        if (title.equals("") || text.equals("") || facultySelection.equals("")){
            this.uniCommunicationView.setMessage("All fields required");
        }

        else{
            //Bean
            BeanUniCommunication beanUniCommunication;
            beanUniCommunication = new BeanUniCommunication();
            beanUniCommunication.setBackground(R.drawable.blank_img);
            beanUniCommunication.setTitle(title);
            beanUniCommunication.setDate(date);
            beanUniCommunication.setCommunication(text);
            beanUniCommunication.setFaculty(facultySelection);



            //Application Controller
            AddCommunication addCommunicationAppController = new AddCommunication();
            try {
                addCommunicationAppController.addUniCommunication(beanUniCommunication);
                this.uniCommunicationView.setMessage("Communication added");
                //Notify the Communications Adapter
                this.beanUniCommunications.add(0, beanUniCommunication);
                this.uniCommunicationView.notifyDataChanged();

                this.uniCommunicationView.dismiss();
            } catch (GenericException e) {
                e.printStackTrace();
                this.uniCommunicationView.setMessage(e.getMessage());
            }
        }
    }
}
