package Movies;

public class RatingIsOutOfBoundException extends Exception {
    public RatingIsOutOfBoundException(String Msg){
        super(Msg);
    }  
}
