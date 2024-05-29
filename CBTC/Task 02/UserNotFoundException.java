public class UserNotFoundException extends Exception{
    UserNotFoundException(){
        super("The User was not found");
    }
}
