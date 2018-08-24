
public class GuessTheMovie {

    public static void main(String[] args){
        Game game = new Game("movies.txt");
//        Game game = new Game();
        String randomMovie = game.pickMovie();
        game.guessMovie(randomMovie);
    }

}
