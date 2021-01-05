package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.models.ExistantUserException;
import edu.upc.dsa.models.PasswordNotMatchException;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.UserNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;

@Api(value = "/users", description = "Endpoint to Text Service")
@Path("users")
public class UserService {

    //creamos variables logger para ir comentando el service del user
    final static Logger logger = Logger.getLogger(UserService.class.getName());

    //instancia privada para hacer el gservice
    private GameManager gservice;

    //procedemos a cambiar el GameManager por el DataBase que es quien gestiona todos los servicios en realidad
    //llamaremos a todos los daos y gestionaremos a partir de ahí

    public UserService() throws Exception {
        this.gservice = GameManagerImpl.getInstance();
        if (gservice.numUsers() == 0) {
            this.gservice.addUser("Tatiana", "tatiana@hotmail.es", "Tatiana", "Tkachuk", "Barcelona", "hola", false);
            this.gservice.addUser("Gabriel", "gabriel@hotmail.es", "Gabriel", "Donate", "Barcelona",  "buenas", false);
            this.gservice.addUser("Kevin", "kevin@hotmail.es", "Kevin", "Alcalde", "Barcelona", "bye", false);
            this.gservice.addUser("Oscar", "oscar@hotmail.es", "Oscar", "Vilamitjana", "Barcelona",  "hello", false);
            this.gservice.addUser("Miquel", "miquel@hotmail.es", "Miquel", "Arina", "Barcelona","adios", false);

            //añadir algunas imagenes a los usuarios
            this.gservice.addImage("Tatiana", "hola", "/resources/users/tatiana.jpg");
            this.gservice.addImage("Gabriel", "buenas", "/resources/users/gabriel.png");
            this.gservice.addImage("Kevin", "bye", "/resources/users/kevin.png");
            this.gservice.addImage("Oscar", "hello", "/resources/users/oscar.jpg");
            this.gservice.addImage("Miquel", "adios", "/resources/users/mikel.png");

        }
    }

    //obtener todos los usuarios
    @GET
    @ApiOperation(value = "obtener lista de usuarios", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer = "HashMap"),
    })
    @Path("/getUsers")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        HashMap<String, User> users = this.gservice.findAll();
        GenericEntity<Collection<User>> entity = new GenericEntity<Collection<User>>(users.values()){};
        return Response.status(201).entity(entity).build();
    }

    //hacemos el post de un user /añadimos un usuario al servicio
    //y le damos una respuesta correcta al haberlo añadido
    @POST
    @ApiOperation(value = "registrar un usuario", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=void.class, responseContainer = "Void class"),
            @ApiResponse(code = 500, message="Existant user")
    })

    @Path("/registerUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        try {
            this.gservice.addUser(user.getUsername(), user.getPassword(), user.getMail(), user.getName(), user.getLastname(), user.getCity(), user.isConnected());
            return Response.status(201).entity(user).build();
        }
        catch (ExistantUserException e){
            e.printStackTrace();
            return Response.status(500).build();
        }
    }

    //añadir imagen al usuario
    @POST
    @ApiOperation(value = "añadir imagen usuario", notes = "escribir el nombre de usuario y la contraseña para poner imagen")
    @ApiResponses(value = {
            @ApiResponse(code= 201, message = "Succesful", response= User.class, responseContainer="List"),
            @ApiResponse(code= 404, message = "User not found", responseContainer="List"),
            @ApiResponse(code=500, message="Password not match", responseContainer = "List")
    })

    //parece que el login nos da buenos resultados
    @Path("/addImage")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addImage(User user){
        try{
            this.gservice.addImage(user.getUsername(), user.getPassword(), user.getImage());
            return Response.status(201).build();
        } catch (UserNotFoundException e1){
            e1.printStackTrace();
            return Response.status(404).build();
        } catch(PasswordNotMatchException e2){
            e2.printStackTrace();
            return Response.status(500).build();
        }
    }

    //este servicio lo utilizaremos para encontar un usuario según su id
    @GET
    @ApiOperation(value = "obtener usuario según su username", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "UserNotFoundException")
    })
    @Path("/getUser/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("username") String username) throws UserNotFoundException {
        //versión alternativa que de momento funciona
        User user=this.gservice.getUser(username);
        if(user==null) return Response.status(404).build();
        else return Response.status(201).entity(user).build();
    }

    //añadir un eliminar usuario
    @DELETE
    @ApiOperation(value = "eliminar un usuario", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "UserNotFoundException")
    })

    @Path("/deleteUser/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("username") String username) throws UserNotFoundException {
        User user = this.gservice.getUser(username);
        if(user == null) return Response.status(404).build();
        else this.gservice.deleteUserAdmin(username);
        return Response.status(201).build();
    }

    //por último hacemos un put de usuario
    @PUT
    @ApiOperation(value = "actualizar un usuario", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "UserNotFoundException")
    })

    @Path("/updateUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(User u) throws UserNotFoundException {
        User user = this.gservice.updateUser(u);
        if(user == null) return Response.status(404).build();
        return Response.status(201).build();
    }
}
