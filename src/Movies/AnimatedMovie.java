package Movies;
import java.util.List;
import MovieExceptions.*;

public class AnimatedMovie extends Movie {
    private List<String> AnimatorList;
    private int RecommendedAge;
    
    public void Rate(int stars) throws RatingIsOutOfBoundException{
        if (stars < 1 || stars > 10) throw new RatingIsOutOfBoundException("Stars out of bounds! (Allowed 1* - 10*)");
        UserReview.add(new Review(stars, ""));
    }

    public void Rate(int stars, String comment) throws RatingIsOutOfBoundException{
        if (stars < 1 || stars > 10) throw new RatingIsOutOfBoundException("Stars out of bounds! (Allowed 1* - 10*)");
        UserReview.add(new Review(stars, comment));
    }

}
