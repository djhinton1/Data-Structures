public class Driver {
	//                                  Name,                       Year, Watched, IMDB Rating
	static Movie moon       = new Movie("Moon",                     2009,       1, 4);
	static Movie resDogs    = new Movie("Reservoir Dogs",           1992,       3, 4);
	static Movie darkCity   = new Movie("Dark City",                1998,       2, 4);
	static Movie eveHorizon = new Movie("Event Horizon",            1997,       2, 3);
	static Movie gattaca    = new Movie("Gattaca",                  1997,       1, 4);
	static Movie jurWorld   = new Movie("Jurassic World",           2015,       1, 3);
	static Movie paulBlart  = new Movie("Paul Blart: Mall Cop",     2009,     338, 2);
	static Movie pineExpr   = new Movie("Pineapple Express",        2008,       4, 3);
	static Movie manos      = new Movie("Manos: The Hands of Fate", 1966,       1, 0);
	static Movie starWars45 = new Movie("Star Wars: Episode LXV",   2091,       0, 5);
	static Movie citKane    = new Movie("Citizen Kane",             1941,       0, 4);

	static Movie[] myMoviesList = new Movie[] {
		moon, resDogs, darkCity, eveHorizon, gattaca
	};

	static Movie[] friendsMoviesList = new Movie[] {
		jurWorld, paulBlart, pineExpr, manos
	};

	static Movie[] moviesToWatch = new Movie[] {
		gattaca, starWars45, citKane
	};

	static void printCollections(MovieShelfInterface myMovies, MovieShelfInterface friendsMovies) {
		System.out.println("My collection: ");
		myMovies.printAll();

		System.out.println("\nMy friend's collection: ");
		friendsMovies.printAll();
	}

	static void tryToWatchMovie(Movie m, MovieShelfInterface myMovies,
		MovieShelfInterface friendsMovies) {

		int watchCount = myMovies.watchMovie(m);

		if(watchCount < 0) {
			watchCount = friendsMovies.watchMovie(m);

			if(watchCount < 0) {
				System.out.println("\tWell, neither of us have " + m +
					", guess we'll just have to pira--I MEAN buy it");
				return;
			}

			System.out.printf("\tMy friend had %s, and we've watched it %d times now.\n",
				m, watchCount);
		} else {
			System.out.printf("\tI had %s, and we've watched it %d times now.\n",
				m, watchCount);
		}
	}

	public static void main(String[] args) {
		// --------------------------------------------------------------------------------
		// testing the array constructor
		MovieShelfInterface myMovies      = new MovieShelf(myMoviesList);
		MovieShelfInterface friendsMovies = new MovieShelf(friendsMoviesList);

		// --------------------------------------------------------------------------------
		// testing printAll
		printCollections(myMovies, friendsMovies);

		// --------------------------------------------------------------------------------
		// testing addItem
		System.out.println("\nI got a new movie! It's " + starWars45 + ".");

		if(myMovies.addItem(starWars45) == false) {
			System.out.println("I should have been able to add " + starWars45);
			System.exit(1);
		}

		// --------------------------------------------------------------------------------
		// testing borrowMovie (which should work)
		System.out.println("I'm going to let my friend borrow it.");

		if(friendsMovies.borrowMovie(myMovies, starWars45) == false) {
			System.out.println("They should have been able to borrow it.");
			System.exit(1);
		}

		// --------------------------------------------------------------------------------
		// testing removeItem
		System.out.println("My friend realized that " + paulBlart + " is not a good movie.");

		if(friendsMovies.removeItem(paulBlart) == false) {
			System.out.println("They should have been able to remove " + paulBlart);
			System.exit(1);
		}

		printCollections(myMovies, friendsMovies);

		// --------------------------------------------------------------------------------
		// testing borrowMovie (which should fail)
		System.out.println("\nNow I'm going to try to borrow " + citKane + " from them...");

		if(myMovies.borrowMovie(friendsMovies, citKane) == true) {
			System.out.println("I shouldn't have been able to borrow it.");
			System.exit(1);
		}

		System.out.println("Oh, they don't have it.");
		System.out.println("Our collections shouldn't have changed:");
		printCollections(myMovies, friendsMovies);

		// --------------------------------------------------------------------------------
		// testing watchMovie
		System.out.println("\nWe're having a movie night.");

		for(Movie m : moviesToWatch) {
			tryToWatchMovie(m, myMovies, friendsMovies);
		}
	}
}
