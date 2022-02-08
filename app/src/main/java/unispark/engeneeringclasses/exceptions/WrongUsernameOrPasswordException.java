package unispark.engeneeringclasses.exceptions;

public class WrongUsernameOrPasswordException extends Exception{
    private static final long serialVersionUID = 1L;

    public WrongUsernameOrPasswordException(String message)
    {
        super(message);
    }
}
