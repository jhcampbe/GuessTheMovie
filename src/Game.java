import java.io.*;
import java.util.*;

public class Game {
    private String[] movies;
    private Scanner scanner;
    private boolean hasWon;

    public Game(){
        movies = new String[500]; //Our game can take text files with up to 500 movies
        System.out.print("Please write the path to your movies file: ");
        scanner = new Scanner(System.in);
        File file = new File(scanner.nextLine());
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException exception){
            System.out.println("File not found");
        }
        hasWon = false;
    }

    public Game(String moviesFile) {
        movies = new String[500]; //Our game can take text files with up to 500 movies
        File file = new File(moviesFile);
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException exception){
            System.out.println("File not found");
        }
        hasWon = false;
    }

    public String pickMovie(){
        int index = 0;
        while (scanner.hasNextLine()) {
            movies[index] = scanner.nextLine();
            index++;
        }

        int randomIndex = (int) (Math.random()*(index));
        return movies[randomIndex];
    }

    public void guessMovie(String movie){
        Set<Character> alreadyGuessed = new HashSet<Character>();
        String randomMovie = movie.toLowerCase();
        int guesses = 0;
        char[] movieGuess = new char[randomMovie.length()];
        Scanner userGuess = new Scanner(System.in);
        boolean goodGuess = false;
        StringBuilder wrongLetters = new StringBuilder();

        for (int i = 0; i < randomMovie.length(); i++){
            if (randomMovie.charAt(i) == ' ' || randomMovie.charAt(i) == '-') {
                movieGuess[i] = randomMovie.charAt(i);
            } else {
                movieGuess[i] = '_';
            }
        }

        while((guesses < 10) && !hasWon) {
            System.out.println("You are guessing: " + String.valueOf(movieGuess));
            System.out.println("You have guessed (" + guesses + ") wrong letters: " + wrongLetters.toString());
            System.out.print("Guess a letter: ");
            String userInput = userGuess.nextLine();
            if (userInput.isEmpty()){
                System.out.println("Please enter a letter.");
                continue;
            }
            if (userInput.length() > 1){
                System.out.println("Please only guess a single letter at a time.");
                continue;
            }
            char letter = userInput.charAt(0);
            if (!Character.isLetter(letter)){
                System.out.println("The guess you entered is not a letter. Please only guess letters.");
                continue;
            }
            letter = Character.toLowerCase(letter);
            if (alreadyGuessed.contains(letter)){
                System.out.println("You already guessed this letter. Please guess a new letter.");
                continue;
            }

            for (int i = 0; i < randomMovie.length(); i++) {
                if (letter == randomMovie.charAt(i)) {
                    movieGuess[i] = movie.charAt(i);
                    goodGuess = true;
                }
            }

            if (movie.equals(String.valueOf(movieGuess))){
                hasWon = true;
                break;
            }
            if (!goodGuess){
                guesses++;
                wrongLetters.append(letter);
                wrongLetters.append(" ");
            }
            alreadyGuessed.add(letter);
            goodGuess = false;
        }

        if(hasWon){
            System.out.println("You win!");
            System.out.println("You have guessed '" + movie + "' correctly");
        } else {
            System.out.println("You have guessed 10 wrong letters");
            System.out.println("The movie was '" + movie + "'");
            System.out.println("GAME OVER");
        }

    }
}
