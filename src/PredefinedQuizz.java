import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class PredefinedQuizz {
    static Map<String, Quizz> quizes = new LinkedHashMap<>();

    static {
        Quizz q = new Quizz();
        quizes.put("Number of primitive data types in Java are?", new Quizz("8", new ArrayList<String>(Arrays.asList("6", "7", "8", "9")), 3));
        quizes.put("Automatic type conversion is possible in which of the possible cases?",
                new Quizz("Int to Long", new ArrayList<String>(Arrays.asList("Byte to Int", "Int to Long", "Long to Int", "Short to Int")), 2));
        quizes.put("When an array is passed to a method, what does the method receive?",
                new Quizz("The Reference of the Array",
                        new ArrayList<String>(Arrays.asList("The Reference of the Array", "The Copy of Array", "Length of Array", "First Element of Array")), 1));
        quizes.put("Arrays in java are-?", new Quizz("Objects",
                new ArrayList<String>(Arrays.asList("Object References", "Objects", "Primitive Data Type", "None")), 2));
        quizes.put("When is the object created with new keyword?", new Quizz("At Run Time",
                new ArrayList<String>(Arrays.asList("At Compile Time", "Depends on the Code", "At Run Time", "Depends on the Compiler")), 3));
    }
}
