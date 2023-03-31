package Movies;

import java.util.LinkedList;
import java.util.List;
import MovieExceptions.*;

public class AnimatedMovie extends Movie {
    private List<String> AnimatorList;
    private int RecommendedAge;

    public AnimatedMovie(String title, String director, int yearofrelease, int agerecomended,
            List<String> listOfAnimators) {
        Title = title;
        Director = director;
        ReleaseYear = yearofrelease;
        RecommendedAge = agerecomended;
        AnimatorList = listOfAnimators;
        UserReviews = new LinkedList<Review>();
    }

    public void Rate(int stars) throws RatingIsOutOfBoundException {
        if (stars < 1 || stars > 10)
            throw new RatingIsOutOfBoundException("Stars out of bounds! (Allowed 1* - 10*)");
        UserReviews.add(new Review(stars, ""));
    }

    public void Rate(int stars, String comment) throws RatingIsOutOfBoundException {
        if (stars < 1 || stars > 10)
            throw new RatingIsOutOfBoundException("Stars out of bounds! (Allowed 1* - 10*)");
        UserReviews.add(new Review(stars, comment));
    }

}
