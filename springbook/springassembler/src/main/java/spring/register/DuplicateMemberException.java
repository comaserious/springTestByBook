package spring.register;

public class DuplicateMemberException extends RuntimeException{

    public DuplicateMemberException(String message){
        super(message);
    }
}
