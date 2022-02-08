package unispark.engeneeringclasses.exceptions;

public class CourseNeverJoined extends Exception{

    private static final long serialVersionUID = 1L;

    public CourseNeverJoined(String message) {
        super(message);
    }
}
