package Movies;

import java.util.LinkedList;
import java.util.List;

public class AnimatedMovie extends Movie {
    private List<String> AnimatorList;
    private int RecommendedAge;

    public List<String> getAnimatorList() {
        return AnimatorList;
    }

    public void setAnimatorList(List<String> animatorList) {
        AnimatorList = animatorList;
    }

    public int getRecommendedAge() {
        return RecommendedAge;
    }

    public void setRecommendedAge(int recommendedAge) {
        RecommendedAge = recommendedAge;
    }

    public AnimatedMovie(String title, String director, int yearofrelease, int agerecomended,
            List<String> listOfAnimators) {
        Title = title;
        Director = director;
        ReleaseYear = yearofrelease;
        RecommendedAge = agerecomended;
        AnimatorList = listOfAnimators;
        UserReviews = new LinkedList<Review>();
    }

    @Override
    public void Rate(String stars, String comment) throws RatingBadFormatException {
        throw new RatingBadFormatException("Wrong Symbol in a String! (Only number allowed!)");
    }

    @Override
    public void Rate(int stars, String comment) throws RatingIsOutOfBoundException, RatingBadFormatException {
        if (stars < 1 || stars > 10)
            throw new RatingIsOutOfBoundException("Stars out of bounds! (Allowed 1 - 10)");
        UserReviews.add(new Review(stars, comment));
    }

}
