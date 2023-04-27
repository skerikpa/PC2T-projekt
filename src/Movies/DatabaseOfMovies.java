package Movies;

import java.util.List;
import java.util.LinkedList;
import java.util.Formatter;

public class DatabaseOfMovies {
    List<Movie> ListOfMovies = new LinkedList<Movie>();
    public static List<String> querries = new LinkedList<String>();

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
        for (Movie movie : ListOfMovies) {
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

    public void DeleteMovieFromDB(String title) {
        // ShowMovieDescription(title);
        if (ListOfMovies.remove(FindMovie(title))) {
            System.out.println("Deleted!");
            querries.add("DELETE FROM movies WHERE name ='" + title + "'");
            querries.add("DELETE FROM actors WHERE title ='" + title + "'");
            querries.add("DELETE FROM animators WHERE title ='" + title + "'");
            querries.add("DELETE FROM anim_reviews WHERE title ='" + title + "'");
            querries.add("DELETE FROM live_reviews WHERE title ='" + title + "'");
        } else
            System.out.println("Movie was not found in database");

    }

    public void RateMovie(String title) {
        System.out.println("Searching \"" + title + "\"");
        var movie = FindMovie(title);
        if (movie != null) {

        } else
            System.out.println("No such movie was found in database!");
    }

    public void EditMovie(String mov, int op, String value) {
        var movie = FindMovie(mov);
        if (movie == null)
            return;

        switch (op) {
            case 1:
                movie.setTitle(value);
                querries.add("UPDATE movies SET name = '" + value + "' WHERE name = '" + mov + "'");
                querries.add("UPDATE actors SET title = '" + value + "' WHERE title = '" + mov + "'");
                querries.add("UPDATE animators SET title = '" + value + "' WHERE title = '" + mov + "'");
                querries.add("UPDATE anim_reviews SET title = '" + value + "' WHERE title = '" + mov + "'");
                querries.add("UPDATE live_reviews SET title = '" + value + "' WHERE title = '" + mov + "'");

                break;
            case 2:
                movie.setDirector(value);
                querries.add("UPDATE movies SET director = '" + value + "' WHERE name = '" + mov + "'");
                break;
            case 3:
                movie.setReleaseYear(Integer.parseInt(value));
                querries.add("UPDATE movies SET release_year = " + value + " WHERE name = '" + mov + "'");
                break;
            case 5:
                if (movie instanceof AnimatedMovie)
                    ((AnimatedMovie) movie).setRecommendedAge(Integer.parseInt(value));
                querries.add("UPDATE movies SET age_rating = " + value + " WHERE name = '" + mov + "'");
                break;
            default:
                break;
        }
    }

    public void EditMovieWorkers(String mov, List<String> workers) {
        var movie = FindMovie(mov);
        if (movie == null)
            return;
        if (movie instanceof ActionMovie)
            ((ActionMovie) movie).setActorList(workers);
        if (movie instanceof AnimatedMovie)
            ((AnimatedMovie) movie).setAnimatorList(workers);
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

    public void ListWorkerHistory(String name) {
        var history = GetWorkerPortfolio(name);
        if (history != null) {
            System.out.println(name + " participated ");
            for (Movie movie : history) {
                System.out.println((movie instanceof ActionMovie ? "as actor in \"" : "as animator in \"")
                        + movie.getTitle() + "\";");
            }
        } else {
            System.out.println("No such person was found in Database!");
        }
    }

    public void BruteForceTheProcessor() {
        List<String> uniqueWorkers = new LinkedList<String>();
        for (Movie movie : ListOfMovies) {
            if (movie instanceof ActionMovie) {
                for (String worker : ((ActionMovie) movie).getActorList()) {
                    if (!(uniqueWorkers.contains(worker)))
                        uniqueWorkers.add(worker);
                }
            } else {
                for (String worker : ((AnimatedMovie) movie).getAnimatorList()) {
                    if (!(uniqueWorkers.contains(worker)))
                        uniqueWorkers.add(worker);
                }
            }
        }
        for (String worker : uniqueWorkers) {

            var anothergoddamnlist = GetWorkerPortfolio(worker);
            if (anothergoddamnlist.size() > 1) {
                ListWorkerHistory(worker);
                System.out.println("_____________________");
            }
        }
    }

    public String getMovieDescription(String title) {
        StringBuilder sb = new StringBuilder();
        System.out.println("Searching \"" + title + "\"");
        var movie = FindMovie(title);

        if (movie != null) {
            sb.append("Title: " + movie.getTitle() +
                    "\nType: " + (movie instanceof ActionMovie ? "Action Movie" : "Animated Movie") +
                    "\nDirector: " + movie.getDirector() +
                    "\nYear of release: " + movie.getReleaseYear() +
                    (movie instanceof AnimatedMovie ? "\nRating(Age): +" + ((AnimatedMovie) movie).getRecommendedAge()
                            : ""));
            sb.append("\n");
            if (movie instanceof ActionMovie) {
                sb.append("List of actors:\n");
                for (String person : ((ActionMovie) movie).getActorList()) {
                    sb.append(person + "\n");
                }
            } else {
                sb.append("List of animators:\n");
                for (String person : ((AnimatedMovie) movie).getAnimatorList()) {
                    sb.append(person + "\n");
                }
            }
            return sb.toString();
        } else
            return null;

    }

}
