import java.util.*;

public class Quizz {
    String answer;
    int answerId;
    List<String> options = new ArrayList<>();

    public Quizz(){};

    public String getAnswer() {

        return answer;
    }

    public void setAnswer(String answer) {

        this.answer = answer;
    }

    public void setAnswerId(int answerId) {

        this.answerId = answerId;
    }

    public int getAnswerId() {

        return answerId;
    }

    public List<String> getOptions() {

        return options;
    }

    public void setOptions(List<String> options) {

        this.options = options;
    }

    //custom setter method
    public void setOption(String option) {

        this.options.add(option);
    }

    public Quizz(String answer, List<String> options, int answerid) {
        this.answer = answer;
        this.options = options;
        this.answerId = answerid;
    }

}
