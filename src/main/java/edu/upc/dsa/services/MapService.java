package edu.upc.dsa.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.upc.dsa.cells.Map;
import edu.upc.dsa.cells.Views;
import io.swagger.annotations.Api;
import org.json.JSONArray;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

//este servicio de mapas está inacabado digamos que le falta integrarse con los DAOS y la base de datos
@Api(value="/maps", description = "Servicio de mapas")
@Path("/maps")
public class MapService {

    @GET
    @Path("/mapList")
    @Produces(MediaType.APPLICATION_JSON)
    public String getList(){
        List<Map> mapList = Map.loadMaps();
        JSONArray ja = new JSONArray();
        for(Map m : mapList){
            ja.put(m.getName());
        }
        return ja.toString();
    }

    @GET
    @Path("/getMapByName")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public String getMap(@QueryParam("mapName") String mapName){
        Map m = Map.loadMap(mapName);
        ObjectMapper om = new ObjectMapper();
        String result;
        try {
            result = om.writerWithView(Views.Normal.class).writeValueAsString(m);
        } catch (JsonProcessingException e) {
            result = "";
        }
        return result;
    }

}
