package unispark.engeneeringclasses.exceptions;

import androidx.annotation.Nullable;


public class ExamException extends Exception{

    private final int code;
    private final String message;
    private static final long serialVersionUID = 1L;

    public ExamException(int code) {

        this.code = code;

        String mess = "";
        if (code == 0){
            mess = "Exam already exists";
        }
        else if (code == 1){
            mess = "Exam has not yet occured";
        }
        else if (code == 2){
            mess = "Exam verbalized, cannot book";
        }
        this.message = mess;
    }

    public int getCode() {
        return code;
    }

    @Nullable
    @Override
    public String getMessage() {
        return message;
    }
}

