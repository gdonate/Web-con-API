package edu.upc.dsa.cells;

import com.fasterxml.jackson.annotation.JsonView;

public class Money extends Cell{

    private String letter = "M";

    @Override
    @JsonView(Views.NotNormal.class)
    public String getLetter() {
        return letter;
    }
    @Override
    @JsonView(Views.Normal.class)
    public String getName() {
        return "Money";
    }
}
