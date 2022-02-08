package unispark.engeneeringclasses.exceptions;

public class CourseAlreadyJoined extends Exception{
    private static final long serialVersionUID = 1L;

    public CourseAlreadyJoined(String message) {
        super(message);
    }
}
