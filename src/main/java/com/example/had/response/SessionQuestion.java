package com.example.had.response;

import java.util.UUID;

public class SessionQuestion {
    private UUID id;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private float value1;
    private float value2;
    private float value3;
    private float value4;

    public SessionQuestion(UUID id,
                           String option1,
                           String option2,
                           String option3,
                           String option4,
                           float value1,
                           float value2,
                           float value3,
                           float value4) {
        this.id = id;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public void setValue1(float value1) {
        this.value1 = value1;
    }

    public void setValue2(float value2) {
        this.value2 = value2;
    }

    public void setValue3(float value3) {
        this.value3 = value3;
    }

    public void setValue4(float value4) {
        this.value4 = value4;
    }

    public UUID getId() {
        return id;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public float getValue1() {
        return value1;
    }

    public float getValue2() {
        return value2;
    }

    public float getValue3() {
        return value3;
    }

    public float getValue4() {
        return value4;
    }
}
