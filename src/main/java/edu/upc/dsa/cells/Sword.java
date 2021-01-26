package edu.upc.dsa.cells;

import com.fasterxml.jackson.annotation.JsonView;

//todas las entidades del mapa se extienden de la entidad celda
public class Sword extends Cell{

    private String letter = "S";

    //implementamos las funciones principales de la clase abstracta (Superclase)
    @Override
    @JsonView(Views.NotNormal.class)
    public String getLetter() {
        return letter;
    }
    @Override
    @JsonView(Views.Normal.class)
    public String getName() {
        return "Sword";
    }
}
