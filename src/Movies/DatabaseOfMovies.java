package Movies;

import java.util.List;
import java.util.LinkedList;
import java.util.Formatter;

public class DatabaseOfMovies {
    List<Movie> ListOfMovies = new LinkedList<Movie>();

    public void AddAnimatedMovie(String title, String director, int yearofrelease, int agerecomended,
            List<String> listOfAnimators) {
        ListOfMovies.add(new AnimatedMovie(title, director, yearofrelease, agerecomended, listOfAnimators));
    }

    public void AddActionMovie(String title, String director, int yearofrelease, List<String> listofactors) {
        ListOfMovies.add(new ActionMovie(title, director, yearofrelease, listofactors));
    }

    public void ListAllMovies() {
        Formatter fmt = new Formatter();
        fmt.format("%-20s %-20s %-20s %-20s %-20s\n", "Title", "Type", "Director", "Year Of Release", "Rating");
        // System.out.println("Title | Type | Director | Year Of Release |
        // Rating(Age)");
        for (Movie movie : ListOfMovies) {
            /*
             * System.out.println(
             * movie.getTitle() + " | " +
             * (movie instanceof ActionMovie ? "Action Movie" : "Animated Movie") + " | " +
             * movie.getDirector() + " | " +
             * movie.getReleaseYear() + " | " +
             * (movie instanceof AnimatedMovie ? ((AnimatedMovie) movie).getRecommendedAge()
             * : "N/A"));
             */

            fmt.format("%-20s %-20s %-20s %-20s %-20s\n",
                    movie.getTitle(),
                    (movie instanceof ActionMovie ? "Action Movie" : "Animated Movie"),
                    movie.getDirector(),
                    movie.getReleaseYear(),
                    (movie instanceof AnimatedMovie ? ((AnimatedMovie) movie).getRecommendedAge() : "N/A"));
        }
        System.out.println(fmt);
    }

    public Movie FindMovie(String title) {
        for (Movie movie : ListOfMovies) {
            if (movie.getTitle().equals(title))
                return movie;
        }
        return null;
    }

    public void ShowMovieDescription(String title) {
        System.out.println("Searching \"" + title + "\"");
        var movie = FindMovie(title);
        if (movie != null) {
            System.out.println("Title: " + movie.getTitle() +
                    "\nType: " + (movie instanceof ActionMovie ? "Action Movie" : "Animated Movie") +
                    "\nDirector: " + movie.getDirector() +
                    "\nYear of release: " + movie.getReleaseYear() +
                    (movie instanceof AnimatedMovie ? "\nRating(Age): +" + ((AnimatedMovie) movie).getRecommendedAge()
                            : ""));
            if (movie instanceof ActionMovie) {
                System.out.println("List of actors:");
                for (String person : ((ActionMovie) movie).getActorList()) {
                    System.out.println(person);
                }
            } else {
                System.out.println("List of animators:");
                for (String person : ((AnimatedMovie) movie).getAnimatorList()) {
                    System.out.println(person);
                }
            }

        } else
            System.out.println("No such movie was found in database!");
    }

    public void DeleteMovieFromDB(String title){
        //ShowMovieDescription(title);
        System.out.println(ListOfMovies.remove(FindMovie(title)) ? "Deleted!" : "No such movie was found in database!");
    }

    public void RateMovie(String title){
        System.out.println("Searching \"" + title + "\"");
        var movie = FindMovie(title);
        if (movie != null) {
            


        } 
        else System.out.println("No such movie was found in database!");
    }

    public void EditMovie(String mov, int op, String value){
        var movie = FindMovie(mov);
        if (movie == null)
            return;

        switch (op) {
            case 1:
                movie.setTitle(value);
                break;
            case 2:
                movie.setDirector(value);
                break;
            case 3:
                movie.setReleaseYear(Integer.parseInt(value));
                break;
            case 5:
                if (movie instanceof AnimatedMovie) ((AnimatedMovie)movie).setRecommendedAge(Integer.parseInt(value));
            break;
            default:
                break;
        }
    }

    public void EditMovieWorkers(String mov, List<String> workers){
        var movie = FindMovie(mov);
        if (movie == null)
            return;
        if (movie instanceof ActionMovie) ((ActionMovie)movie).setActorList(workers);
        if (movie instanceof AnimatedMovie) ((AnimatedMovie)movie).setAnimatorList(workers);
    }

    public List<Movie> GetWorkerPortfolio(String name) {
        List<Movie> portfolio = new LinkedList<Movie>();
        for (Movie movie : ListOfMovies) {
            if (movie instanceof ActionMovie && ((ActionMovie) movie).getActorList().contains(name)
                    || movie instanceof AnimatedMovie && ((AnimatedMovie) movie).getAnimatorList().contains(name))
                portfolio.add(movie);
        }
        return (portfolio.isEmpty() ? null : portfolio);
    }

    public void ListWorkerHistory(String name){
        var history = GetWorkerPortfolio(name);
        if(history != null){
            System.out.println(name + " participated ");
            for (Movie movie : history) {
                System.out.println((movie instanceof ActionMovie ? "as actor in \"" : "as animator in \"") + movie.getTitle() + "\";");
            }
        }
        else{
            System.out.println("No such person was found in Database!");
        }
    }

}
