package andypizza.pizza.exeption;

public class UserPasswordException  extends RuntimeException {
    public UserPasswordException(String message) {
        super(message);
    }
}