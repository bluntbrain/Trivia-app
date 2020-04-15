package com.ishan.trivia;

public class TriviaModel {

    private Integer question;
    private Integer option1;
    private Integer option2;
    private Integer option3;
    private Integer option4;

    public TriviaModel(Integer question, Integer option1, Integer option2, Integer option3, Integer option4) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }

    public Integer getQuestion() {
        return question;
    }

    public Integer getOption1() {
        return option1;
    }

    public Integer getOption2() {
        return option2;
    }

    public Integer getOption3() {
        return option3;
    }

    public Integer getOption4() {
        return option4;
    }
}
