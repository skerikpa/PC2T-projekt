package Movies;
import MovieExceptions.*;

import java.util.List;

public class ActionMovie extends Movie {
    private List<String> ActorList;

    public void Rate(int stars) throws RatingIsOutOfBoundException{
        if (stars < 1 || stars > 5) throw new RatingIsOutOfBoundException("Stars out of bounds! (Allowed 1* - 5*)");
        UserReview.add(new Review(stars, ""));
    }

    public void Rate(int stars, String comment) throws RatingIsOutOfBoundException{
        if (stars < 1 || stars > 5) throw new RatingIsOutOfBoundException("Stars out of bounds! (Allowed 1* - 5*)");
        UserReview.add(new Review(stars, comment));
    }
}
