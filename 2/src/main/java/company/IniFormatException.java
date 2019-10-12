package company;

public class IniFormatException extends RuntimeException {
    public IniFormatException(String message) {
        super(message);
    }

    public IniFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
