import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    private static final int MAX_ATTEMPTS = 10;
    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain;
        
        do {
            int randomNumber = random.nextInt(UPPER_BOUND - LOWER_BOUND + 1) + LOWER_BOUND;
            int attempts = 0;
            boolean hasWon = false;

            System.out.println("Welcome to the Number Game!");
            System.out.println("I have generated a number between " + LOWER_BOUND + " and " + UPPER_BOUND + ".");
            System.out.println("Can you guess it within " + MAX_ATTEMPTS + " attempts?");

            while (attempts < MAX_ATTEMPTS) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess < randomNumber) {
                    System.out.println("Too low!");
                } else if (userGuess > randomNumber) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                    hasWon = true;
                    break;
                }
            }

            if (!hasWon) {
                System.out.println("Sorry, you've used all " + MAX_ATTEMPTS + " attempts. The correct number was " + randomNumber + ".");
            }

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println("Thank you for playing! Goodbye!");
        scanner.close();
    }
}
