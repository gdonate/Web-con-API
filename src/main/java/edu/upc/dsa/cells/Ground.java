package edu.upc.dsa.cells;

import com.fasterxml.jackson.annotation.JsonView;

public class Ground extends Cell{

    private String letter = "G";

    @Override
    @JsonView(Views.NotNormal.class)
    public String getLetter() {
        return letter;
    }

    @Override
    @JsonView(Views.Normal.class)
    public String getName() {
        return "Ground";
    }
}
