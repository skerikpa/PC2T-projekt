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

    private Movie FindMovie(String title){
        for (Movie movie : ListOfMovies) {
            if (movie.getTitle().equals(title)) return movie;
        }
        return null;
    }

    public void ShowMovieDescription(String title){
        System.out.println("Searching \"" + title + "\"");
        var movie = FindMovie(title);
        if (movie != null) {
            System.out.println("Title: " + movie.getTitle() + 
            "\nType: " + (movie instanceof ActionMovie ? "Action Movie" : "Animated Movie") +
            "\nDirector: " + movie.getDirector() +
            "\nYear of release: " + movie.getReleaseYear() +
            (movie instanceof AnimatedMovie ? "\nRating(Age): +" + ((AnimatedMovie)movie).getRecommendedAge() : ""));
            if (movie instanceof ActionMovie) {
                System.out.println("List of actors:");
                for (String person : ((ActionMovie)movie).getActorList()) {
                    System.out.println(person);
                }
            }
            else{
                System.out.println("List of animators:");
                for (String person : ((AnimatedMovie)movie).getAnimatorList()) {
                    System.out.println(person);
                }
            }

        }
        else System.out.println("No such movie was found in database!");
    }

}
