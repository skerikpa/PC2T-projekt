package Movies;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

	static DatabaseOfMovies dbm = new DatabaseOfMovies();

	public static void main(String[] args) throws IOException {

		Scanner MenuInput = new Scanner(System.in);
		// Scanner LocalInput;

		loop: while (true) {
			System.out.println("--Movies--");
			System.out.println("1. Add new movie;");	//Done
			System.out.println("2. Edit Movie;");		//Works, but one at a time
			System.out.println("3. Delete Movie;");		//Done
			System.out.println("4. Add Rating;");		//Done
			System.out.println("5. List all movies;");	//Done
			System.out.println("6. Find movie;");		// Description works, but RATING'N'SHITT is still WIP
			System.out.println("7. List people involved in multiple projects;");	//wip
			System.out.println("8. List movies involving particular person;");		//Done
			System.out.println("9. Save a movie to .txt;");		//wip
			System.out.println("10. Load a movie from .txt;");	//wip
			System.out.println("11. Save and Quit;");

			int userSelection = MenuInput.nextInt(); // Integer.parseInt(input.nextLine());

			switch (userSelection) {
				case 1:
					AddNewMovie();
					System.in.read();
					break;

				case 2:
					EditMovie();
					System.in.read();
					break;
				case 3:
					DeleteMovie();
					System.in.read();
					break;
				case 4:
					RateMovie();
					System.in.read();
					break;
				case 5:
					dbm.ListAllMovies();
					System.in.read();
					break;
				case 6:
					FindMovie();
					System.in.read();
					break;
				case 8:
					FindPerson();
					System.in.read();
					break;
				case 11:
					MenuInput.close();
					break loop;
				default:
					break;
			}
		}
	}

	private static void AddNewMovie() {
		Scanner LocalInput = new Scanner(System.in);
		System.out.println("--Add new movie--");

		while (true) {
			System.out.println("Select Option: 1. Action Movie; | 2. Animated Movie;");
			if (LocalInput.hasNextInt())
				break;
			LocalInput.next();
		}
		int typeOption = LocalInput.nextInt();

		System.out.println("Input info in this format: <title> <director>:");
		var _title = LocalInput.next();
		var _director = LocalInput.next();

		while (true) {
			System.out.println("Input year of release:");
			if (LocalInput.hasNextInt())
				break;
			LocalInput.next();
		}
		int _releaseYear = LocalInput.nextInt();

		switch (typeOption) {
			case 1:
				System.out.println("Add actors (leave empty to finish)");
				List<String> _actorList = new LinkedList<String>();
				String actor;
				LocalInput = new Scanner(System.in);
				while (!(actor = LocalInput.nextLine()).isEmpty())
					_actorList.add(actor);

				dbm.AddActionMovie(_title, _director, _releaseYear, _actorList);
				break;
			case 2:
				while (true) {
					System.out.println("Input recommended age: ");
					if (LocalInput.hasNextInt())
						break;
					LocalInput.next();
				}
				int _age = LocalInput.nextInt();

				List<String> _AnimatorList = new LinkedList<String>();
				String Animator;
				System.out.println("Add animators (leave empty to finish)");
				LocalInput = new Scanner(System.in);
				while (!(Animator = LocalInput.nextLine()).isEmpty())
					_AnimatorList.add(Animator);

				dbm.AddAnimatedMovie(_title, _director, _releaseYear, _age, _AnimatorList);

				break;

			default:
				break;
		}
		System.out.println("Added \"" + _title + "\"");
	}

	private static void EditMovie() { // need to finish this :/
		Scanner LocalInput = new Scanner(System.in);
		System.out.println("--Edit movie (currently movie finder)--");
		System.out.println("Write movie to find: ");
		var key = LocalInput.nextLine();
		var mov = dbm.FindMovie(key);
		if (mov != null) {
			System.out.println("Edit: 1. Title; 2. Director; 3. Year Of Release; " + (mov instanceof ActionMovie ? " 4. Actors List" : "4. Animators List; 5. Age Rating"));
			var option = Integer.parseInt(LocalInput.nextLine());
			if (option != 4){
			System.out.println("Insert new value: ");
			var newvalue = LocalInput.nextLine();
			dbm.EditMovie(key, option, newvalue);
			}
			else{
				System.out.println("Add actors (leave empty to finish)");
				List<String> _actorList = new LinkedList<String>();
				String actor;
				LocalInput = new Scanner(System.in);
				while (!(actor = LocalInput.nextLine()).isEmpty())
					_actorList.add(actor);
				dbm.EditMovieWorkers(key, _actorList);
			}
		}
		else System.out.println("No such movie was found in database!");
		
	}

	private static void DeleteMovie() {
		Scanner LocalInput = new Scanner(System.in);
		System.out.println("--Delete movie--");
		System.out.println("Write movie to find and delete: ");
		var key = LocalInput.nextLine();
		dbm.DeleteMovieFromDB(key);
	}

	private static void RateMovie(){
		Scanner LocalInput = new Scanner(System.in);
		System.out.println("--Delete movie--");
		System.out.println("Write movie to find and rate: ");
		var key = LocalInput.nextLine();
		var mov = dbm.FindMovie(key);
		if (mov != null) {
			if (mov instanceof ActionMovie) {
				System.out.println("Rate with '*': ");
				String score = LocalInput.nextLine();

				System.out.println("Add comment: ");
				var comment = LocalInput.nextLine();
				try {
					mov.Rate(score, comment);
				} catch (RatingIsOutOfBoundException | RatingBadFormatException e) {
					e.printStackTrace();
				} 
			}									
				else {
				System.out.println("Rate with number from 1 to 10: ");
				int score = Integer.parseInt(LocalInput.nextLine());

				System.out.println("Add comment: ");
				var comment = LocalInput.nextLine();
				try {
					mov.Rate(score, comment);
				} catch (RatingIsOutOfBoundException | RatingBadFormatException e) {
					e.printStackTrace();
				}				
			}
			System.out.println("Rating Submited!");
		} else
			System.out.println("No such movie was found in database!");
	}

	private static void FindMovie() { // need to finish this :/
		Scanner LocalInput = new Scanner(System.in);
		System.out.println("--Find Movie--");
		System.out.println("Write movie to find: ");
		var key = LocalInput.nextLine();
		dbm.ShowMovieDescription(key);
	}

	private static void FindPerson(){
		Scanner LocalInput = new Scanner(System.in);
		System.out.println("--List movies involving particular person--");
		System.out.println("Write name of a person: ");
		var key = LocalInput.nextLine();
		dbm.ListWorkerHistory(key);
	}
}
