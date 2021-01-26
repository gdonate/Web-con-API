package edu.upc.dsa.cells;

import com.fasterxml.jackson.annotation.JsonView;

public class Enemy extends Cell{

    private String letter = "E";

    @Override
    @JsonView(Views.NotNormal.class)
    public String getLetter() {
        return letter;
    }

    @Override
    @JsonView(Views.Normal.class)
    public String getName() {
        return "Enemy";
    }
}
