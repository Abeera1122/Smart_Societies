package com.HomeManaging.homemanaging.Model;

import java.util.ArrayList;
import java.util.List;

public class Pool {
    private String question, option1, option2;

    private List<PollResult> results;

    private String id;

    public Pool(String question, String option1, String option2) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
    }

    public Pool() {
    }


    public void addResult(PollResult pollResult) {
        if (results == null)
            results = new ArrayList<>();
        results.add(pollResult);
    }

    public void setResults(List<PollResult> results) {
        this.results = results;
    }

    public List<PollResult> getResults() {
        return results;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
