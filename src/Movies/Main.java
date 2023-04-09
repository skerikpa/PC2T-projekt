package Movies;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		DatabaseOfMovies dbm = new DatabaseOfMovies();
		Scanner MenuInput = new Scanner(System.in);
		Scanner LocalInput;

		loop: while (true) {
			System.out.println("--Movies--");
			System.out.println("1. Add new movie;");
			System.out.println("2. Edit Movie;");
			System.out.println("3. Delete Movie;");
			System.out.println("4. Add Rating;");
			System.out.println("5. List all movies;");
			System.out.println("6. Find movie;");
			System.out.println("7. List people involved in multiple projects;");
			System.out.println("8. List movies involving particular person;");
			System.out.println("9. Save a movie to .txt;");
			System.out.println("10. Load a movie from .txt;");
			System.out.println("11. Save and Quit;");

			int userSelection = MenuInput.nextInt(); // Integer.parseInt(input.nextLine());

			switch (userSelection) {
				case 1:
					LocalInput = new Scanner(System.in);
					System.out.println("--Add new movie--");
					System.out.println("Select Option: 1. Action Movie; | 2. Animated Movie;");
					var typeOption = LocalInput.nextInt();

					LocalInput = new Scanner(System.in);

					System.out.println("Input info in this format: <title> <director> <year of release>:");
					var _title = LocalInput.next();
					var _director = LocalInput.next();
					var _releaseYear = LocalInput.nextInt();

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
							System.out.println("Input recommended age: ");
							LocalInput = new Scanner(System.in);
							var _age = LocalInput.nextInt();

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

					break;

				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					dbm.ListAllMovies();
					System.in.read();
					break;
				case 11:
					break loop;
				default:
					break;
			}
		}
	}
}
