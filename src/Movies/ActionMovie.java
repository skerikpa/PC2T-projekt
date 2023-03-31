package Movies;

import MovieExceptions.*;

import java.util.LinkedList;
import java.util.List;

public class ActionMovie extends Movie {
    private List<String> ActorList;

    public ActionMovie(String title, String director, int yearofrelease) {
        Title = title;
        Director = director;
        ReleaseYear = yearofrelease;
        UserReviews = new LinkedList<Review>();
    }

    public void Rate(String stars) throws RatingIsOutOfBoundException, RatingBadFormatException {
        var cnt = 0;
        for (char i : stars.toCharArray()) {
            if (i != '*')
                throw new RatingBadFormatException("Wrong Symbol in a String! (Only '*' allowed!)");
            if (cnt > 5)
                throw new RatingIsOutOfBoundException("Stars out of bounds! (Allowed 1* - 5*)");
            cnt++;
        }
        UserReviews.add(new Review(cnt, ""));
    }

    public void Rate(String stars, String comment) throws RatingIsOutOfBoundException, RatingBadFormatException {
        var cnt = 0;
        for (char i : stars.toCharArray()) {
            if (i != '*')
                throw new RatingBadFormatException("Wrong Symbol in a String! (Only '*' allowed!)");
            if (cnt > 5)
                throw new RatingIsOutOfBoundException("Stars out of bounds! (Allowed 1* - 5*)");
            cnt++;
        }
        UserReviews.add(new Review(cnt, comment));
    }
}
