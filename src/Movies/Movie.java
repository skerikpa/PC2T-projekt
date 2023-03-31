package Movies;

import java.util.*;

public abstract class Movie {
    protected String Title;
    protected String Director;
    protected int ReleaseYear;
    protected List<Review> UserReviews;

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
