import java.sql.SQLOutput;
import java.util.*;

public class QuizGenerator {


    static Map<String, Quizz> quizes;
    static Map<String, Map<String, Quizz>> quizBasedOnTopic = new HashMap<>();
    static Scanner sc = new Scanner(System.in);
    static ArrayList<String> topics = new ArrayList<>();
    static boolean check = false;

public static void createQuiz() {
    try {
        System.out.println("Enter the topic you want to create quiz:");
        String topic = sc.nextLine();
        topics.add(topic);

        System.out.println("Enter the number of questions:");
        int noOfQuestions = sc.nextInt();
        sc.nextLine(); // Consume newline
        Map<String, Quizz> quizes = new LinkedHashMap<>();
        Quizz q;
        for (int i = 0; i < noOfQuestions; i++) {
            q = new Quizz();
            System.out.println("Enter question " + (i + 1) + ": ");
            String ques = sc.nextLine();
            System.out.println("Enter the four options:");
            for (int j = 0; j < 4; j++) {
                String option = sc.nextLine();
                q.setOption(option);
            }

            int optionNo;
            while (true) {
                try {
                    System.out.println("Enter the correct option number (1-4):");
                    optionNo = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    if (optionNo < 1 || optionNo > 4) {
                        throw new IllegalArgumentException("Option number must be between 1 and 4.");
                    }
                    break; // Exit loop if input is valid
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                    sc.nextLine(); // Clear the invalid input
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            String answer = q.getOptions().get(optionNo - 1);
            q.setAnswer(answer);
            q.setAnswerId(optionNo);
            quizes.put(ques, q);
            System.out.println("Question added");
        }

        quizBasedOnTopic.put(topic, quizes);
        System.out.println("Quiz Created");
    } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a valid number.");
        sc.nextLine(); // Clear the invalid input
    } catch (Exception e) {
        System.out.println("An error occurred: " + e.getMessage());
    }
}

    public static void takeQuiz() {
        displayInstructions();
        System.out.println("On Which topic you want to take the quiz? ");
        for (String topic : topics)
            System.out.println(topic);
        String chosenTopic = sc.nextLine();
        Map<String, Quizz> quizesOfChosenTopic;

        if (quizBasedOnTopic.containsKey(chosenTopic))
            quizesOfChosenTopic = quizBasedOnTopic.get(chosenTopic);
        else {
            System.out.println("Enter a valid Topic!!");
            return;
        }
        takeCommonQuiz(quizesOfChosenTopic);
    }

    public static void takePredefinedQuiz() {
        displayInstructions();
        takeCommonQuiz(PredefinedQuizz.quizes);
    }

    //using this Common method we can avoid repeating of codes
    public static void takeCommonQuiz(Map<String, Quizz> quizes) {
        int index = 1;
        int score = 0;
        int correctCount = 0;

        for (Map.Entry<String, Quizz> quiz : quizes.entrySet()) {
            int length = 1;
            System.out.println("Question " + index++ + ": " + quiz.getKey());
            Quizz q = quiz.getValue();

            List<String> options = q.getOptions();
            for (String option : options) {
                System.out.println(length++ + ". " + option);
            }
            System.out.println();
            System.out.println("Please choose the options between 1 and 4");
            int optionNo = sc.nextInt();
            if (q.getAnswerId() == optionNo) {
                System.out.println("Correct Answer!!");
                System.out.println();
                score += 5;
                correctCount++;
            } else {
                System.out.println("Incorrect Answer. The correct answer is Option " + q.getAnswerId() + ". " + q.getAnswer());
                System.out.println();
                score -= 2;
            }
        }

        System.out.println();
        if (correctCount == quizes.size())
            System.out.println("Excellent Performance :)");
        else if (correctCount >= quizes.size() / 2)
            System.out.println("Average Performance :|");
        else
            System.out.println("Poor Performance :(");
        System.out.println("You have scored " + score + " out of " + quizes.size() * 5);
        sc.nextLine();
    }

    public static void modifyQuiz() {
        try {
            System.out.println("Modify Quiz");
            System.out.println("1. Add Question\n2. Remove Question\n3. Edit Question");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addQuestion();
                    break;
                case 2:
                    removeQuestion();
                    break;
                case 3:
                    editQuestion();
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            sc.nextLine(); // Clear the invalid input
        }
    }

    private static void addQuestion() {
        try {
            System.out.println("Select the topic to add a question:");
            for (String topic : topics) {
                System.out.println(topic);
            }
            String chosenTopic = sc.nextLine();
            if (quizBasedOnTopic.containsKey(chosenTopic)) {
                System.out.println("Enter the question you want to add:");
                String ques = sc.nextLine();
                Quizz q = new Quizz();
                System.out.println("Enter the four options:");
                for (int j = 0; j < 4; j++) {
                    String option = sc.nextLine();
                    q.setOption(option);
                }
                System.out.println("Enter the correct option number (1-4):");
                int optionNo = sc.nextInt();
                sc.nextLine(); // Consume newline
                if (optionNo < 1 || optionNo > 4) {
                    System.out.println("Invalid option number. It should be between 1 and 4.");
                    return;
                }
                String answer = q.getOptions().get(optionNo - 1);
                q.setAnswer(answer);
                q.setAnswerId(optionNo);
                quizBasedOnTopic.get(chosenTopic).put(ques, q);
                System.out.println("Question added to " + chosenTopic);
            } else {
                System.out.println("Invalid Topic!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            sc.nextLine(); // Clear the invalid input
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid option number. It should be between 1 and 4.");
            sc.nextLine(); // Clear the invalid input
        }
    }

    private static void removeQuestion() {
        try {
            System.out.println("Select the topic to remove a question:");
            for (String topic : topics) {
                System.out.println(topic);
            }
            String chosenTopic = sc.nextLine();
            if (quizBasedOnTopic.containsKey(chosenTopic)) {
                Map<String, Quizz> quizesOfChosenTopic = quizBasedOnTopic.get(chosenTopic);
                System.out.println("Select the question to remove:");
                List<String> questions = new ArrayList<>(quizesOfChosenTopic.keySet());
                for (int i = 0; i < questions.size(); i++) {
                    System.out.println((i + 1) + ". " + questions.get(i));
                }
                int quesNo = sc.nextInt();
                sc.nextLine(); // Consume newline
                if (quesNo > 0 && quesNo <= questions.size()) {
                    quizesOfChosenTopic.remove(questions.get(quesNo - 1));
                    System.out.println("Question removed from " + chosenTopic);
                } else {
                    System.out.println("Invalid question number!");
                }
            } else {
                System.out.println("Invalid Topic!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            sc.nextLine(); // Clear the invalid input
        }
    }

    private static void editQuestion() {
        try {
            System.out.println("Select the topic to edit a question:");
            for (String topic : topics) {
                System.out.println(topic);
            }
            String chosenTopic = sc.nextLine();
            if (quizBasedOnTopic.containsKey(chosenTopic)) {
                Map<String, Quizz> quizesOfChosenTopic = quizBasedOnTopic.get(chosenTopic);
                System.out.println("Select the question to edit:");
                List<String> questions = new ArrayList<>(quizesOfChosenTopic.keySet());
                for (int i = 0; i < questions.size(); i++) {
                    System.out.println((i + 1) + ". " + questions.get(i));
                }
                int quesNo = sc.nextInt();
                sc.nextLine(); // Consume newline
                if (quesNo > 0 && quesNo <= questions.size()) {
                    String oldQuestion = questions.get(quesNo - 1);
                    quizesOfChosenTopic.remove(oldQuestion);

                    System.out.println("Enter the new question:");
                    String newQuestion = sc.nextLine();
                    Quizz q = new Quizz();
                    System.out.println("Enter the four options:");
                    for (int j = 0; j < 4; j++) {
                        String option = sc.nextLine();
                        q.setOption(option);
                    }
                    System.out.println("Enter the correct option number (1-4):");
                    int optionNo = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    if (optionNo < 1 || optionNo > 4) {
                        System.out.println("Invalid option number. It should be between 1 and 4.");
                        return;
                    }
                    String answer = q.getOptions().get(optionNo - 1);
                    q.setAnswer(answer);
                    q.setAnswerId(optionNo);
                    quizesOfChosenTopic.put(newQuestion, q);

                    System.out.println("Question edited in " + chosenTopic);
                } else {
                    System.out.println("Invalid question number!");
                }
            } else {
                System.out.println("Invalid Topic!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            sc.nextLine(); // Clear the invalid input
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid option number. It should be between 1 and 4.");
            sc.nextLine(); // Clear the invalid input
        }
    }


    public static void listQuiz() {
        for(Map.Entry<String,Map<String,Quizz>> list : quizBasedOnTopic.entrySet()) {
            System.out.println(list.getKey());
            for (Map.Entry<String, Quizz> list1 : list.getValue().entrySet()) {
                System.out.println(list1.getKey());
            }
        }
    }
    public static void displayInstructions() {
        System.out.println("Instructions for the Quiz:");
        System.out.println("1. There will be quizzes on different topics, you can select one.");
        System.out.println("2. Each topic has a set of questions with 4 options, from which one is correct.");
        System.out.println("3. Each correct answer will reward you +5 marks.");
        System.out.println("4. Each incorrect answer will result in a negative score of -2 marks.");
        System.out.println("5. Negative marking is there for this quiz, please be cautious while picking the options.");
        System.out.println();
        System.out.println("--------------------All the best-----------------------");
        System.out.println();
    }
}