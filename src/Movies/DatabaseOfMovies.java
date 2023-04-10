package Movies;

import java.util.List;
import java.util.LinkedList;

public class DatabaseOfMovies {
    List<Movie> ListOfMovies = new LinkedList<Movie>();

    public void AddAnimatedMovie(String title, String director, int yearofrelease, int agerecomended, List<String> listOfAnimators){
        ListOfMovies.add(new AnimatedMovie(title, director, yearofrelease, agerecomended, listOfAnimators));
    }

    public void AddActionMovie(String title, String director, int yearofrelease, List<String> listofactors){
        ListOfMovies.add(new ActionMovie(title, director, yearofrelease, listofactors));
    }

    public void ListAllMovies(){
        System.out.println("Title | Type | Director | Year Of Release | Rating(Age)");
        for (Movie movie : ListOfMovies) {
            System.out.println(
                movie.getTitle() + " | " +
                (movie instanceof ActionMovie ? "Action Movie" : "Animated Movie") + " | " +
                movie.getDirector() + " | " +
                movie.getReleaseYear() + " | " +
                (movie instanceof AnimatedMovie ? ((AnimatedMovie)movie).getRecommendedAge() : "N/A")
            );
        }
    }

    public Movie FindMovie(String title){
        for (Movie movie : ListOfMovies) {
            if (movie.getTitle() == title) {
                return movie;
            }
        }
        return null;
    }


}
