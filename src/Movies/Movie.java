package Movies;

import java.util.*;

public abstract class Movie {
    protected String Title;
    protected String Director;
    protected int ReleaseYear;
    protected List<Review> UserReviews;



    public String getTitle() {
        return Title;
    }



    public void setTitle(String title) {
        Title = title;
    }



    public String getDirector() {
        return Director;
    }



    public void setDirector(String director) {
        Director = director;
    }



    public int getReleaseYear() {
        return ReleaseYear;
    }



    public void setReleaseYear(int releaseYear) {
        ReleaseYear = releaseYear;
    }



    public List<Review> getUserReviews() {
        return UserReviews;
    }



    public void setUserReviews(List<Review> userReviews) {
        UserReviews = userReviews;
    }



    public class Review {
        private String Comment;
        private int NumberOfStars;

        public Review(int starInt, String comment) {
            this.Comment = comment;
            this.NumberOfStars = starInt;
        }

        public String getComment() {
            return this.Comment;
        }

        public int getStars() {
            return this.NumberOfStars;
        }

    }

}
