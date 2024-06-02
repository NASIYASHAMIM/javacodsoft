import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApplication {
    private static final int QUESTION_TIME_LIMIT = 10; // time limit per question in seconds
    private static final Scanner scanner = new Scanner(System.in);
    private static int score = 0;
    private static boolean answered;

    private static class Question {
        String question;
        String[] options;
        int correctAnswer;

        Question(String question, String[] options, int correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }

    private static void startQuiz(Question[] questions) {
        for (Question question : questions) {
            askQuestion(question);
        }
        showResults(questions.length);
    }

    private static void askQuestion(Question question) {
        answered = false;
        System.out.println(question.question);
        for (int i = 0; i < question.options.length; i++) {
            System.out.println((i + 1) + ". " + question.options[i]);
        }

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!answered) {
                    System.out.println("\nTime's up!");
                    checkAnswer(question, -1);
                }
            }
        }, QUESTION_TIME_LIMIT * 1000);

        int userAnswer = -1;
        if (scanner.hasNextInt()) {
            userAnswer = scanner.nextInt();
            answered = true;
            timer.cancel();
        }
        checkAnswer(question, userAnswer - 1);
    }

    private static void checkAnswer(Question question, int userAnswer) {
        if (userAnswer == question.correctAnswer) {
            System.out.println("Correct!\n");
            score++;
        } else {
            System.out.println("Incorrect. The correct answer is " + (question.correctAnswer + 1) + ". " + question.options[question.correctAnswer] + "\n");
        }
    }

    private static void showResults(int totalQuestions) {
        System.out.println("Quiz Over!");
        System.out.println("Your final score is: " + score + " out of " + totalQuestions);
    }

    public static void main(String[] args) {
        Question[] questions = {
            new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 2),
            
            new Question("Which among the following was the first Indian woman who went into space?", new String[]{"Sunita Williams","Kalpana Chawla","Koneru Humpy","None of the above"}, 1),
            
            new Question("Who was the first Indian woman to win the Miss World Title?", new String[]{"Aishwarya Rai","Sushmita Sen","Reita Faria","Diya Mirza"}, 2),
            
            new Question("What is the largest ocean on Earth?", new String[]{"Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"}, 3),
            
             new Question("Which planet is known as the Red Planet?", new String[]{"Earth", "Mars", "Jupiter", "Venus"}, 1)
        };

        startQuiz(questions);
    }
}
