package andypizza.pizza.exeption;

public class NotFoundIdException  extends RuntimeException {
    public NotFoundIdException(String message) {
        super(message);
    }
}