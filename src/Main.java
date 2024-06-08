import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\n-------------Welcome to Sundar's Quiz Generator--------------");
        System.out.println();
        while(true) {
            System.out.println("1. Create Quiz\n2. Take Predefined Quiz\n3. Take Quiz\n4. Modify Quiz\n5. List Created Quiz \n6. Exit");
            int option = sc.nextInt();
            //sc.nextLine();
            switch(option) {
                case 1:
                    QuizGenerator.createQuiz();
                    break;
                case 2:
                    QuizGenerator.takePredefinedQuiz();
                    break;
                case 3:
                    if(!QuizGenerator.topics.isEmpty())
                        QuizGenerator.takeQuiz();
                    else
                        System.out.println("Create your custom quiz before taking the test");
                    break;
                case 4:
                    if(!QuizGenerator.topics.isEmpty()){
                        QuizGenerator.modifyQuiz();
                    }
                    else
                        System.out.println("There is no quiz questions available to modify");

                    break;
                case 6:
                    return;
                case 5:
                    QuizGenerator.listQuiz();
                    break;
            }
            System.out.println("--------------------------------------------------------");
            System.out.println();
        }
    }
}