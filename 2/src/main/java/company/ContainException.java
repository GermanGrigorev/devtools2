package company;

public class ContainException extends RuntimeException {
    public ContainException(String message){
        super(message);
    }

    public ContainException(String message, Throwable cause){
        super(message, cause);
    }
}