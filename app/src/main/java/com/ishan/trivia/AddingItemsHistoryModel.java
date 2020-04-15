package com.ishan.trivia;

public class AddingItemsHistoryModel {

    private String index;
    private String date;
    private String name;
    private String ans1;
    private String ans2;

    public AddingItemsHistoryModel(String index, String date, String name, String ans1, String ans2) {
        this.index = index;
        this.date = date;
        this.name = name;

        this.ans1 = ans1;

        this.ans2 = ans2;
    }

    public String getIndex() {
        return index;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getAns1() {
        return ans1;
    }

    public String getAns2() {
        return ans2;
    }
}
