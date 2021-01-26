package edu.upc.dsa.cells;

import com.fasterxml.jackson.annotation.JsonView;

public class Wall extends Cell{

    private String letter = "W";


    //la manera de diferenciarlo se hace debido a un compromiso entre el objeto y el JSON
    //por una parte en el JSON se guardará correctamente
    //pero por otra nosotros cada entidad del mapa la trataremos como una letra

    @Override
    @JsonView(Views.NotNormal.class)
    public String getLetter() {
        return letter;
    }

    @Override
    @JsonView(Views.Normal.class)
    public String getName() {
        return "Wall";
    }

    public void setLetter(String letter){this.letter = letter;}

    public Wall(){}

}
