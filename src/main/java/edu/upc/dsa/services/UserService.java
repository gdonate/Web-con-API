package edu.upc.dsa.services;

import edu.upc.dsa.dao.DAOManager;
import edu.upc.dsa.dao.DAOManagerImpl;
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
import java.util.List;
import java.util.logging.Logger;

@Api(value = "/users", description = "Endpoint to Text Service")
@Path("users")
public class UserService {

    //creamos variables logger para ir comentando el service del user
    final static Logger logger = Logger.getLogger(UserService.class.getName());

    //creamos instancia privada de nuestro contrato debido en parte al singleton
    private DAOManager gservicedb;

    //procedemos a cambiar el GameManager por el DataBase que es quien gestiona todos los servicios en realidad
    //llamaremos a todos los daos y gestionaremos a partir de ahí

    public UserService() throws Exception {
        this.gservicedb = DAOManagerImpl.getInstance();
        if (gservicedb.getUserDAO().findMax() == 0) {
            this.gservicedb.getUserDAO().addUser("tatiana1", "tatiana@hotmail.es","Tatiana", "Tkachuk", "Barcelona", "hola");
            this.gservicedb.getUserDAO().addUser("gabriel2", "gabriel@hotmail.es","Gabriel", "Donate", "Castelldefels", "buenas");
            this.gservicedb.getUserDAO().addUser("kevin3", "kevin@hotmail.es","Kevin", "Alcalde", "Barcelona", "bye");
            this.gservicedb.getUserDAO().addUser("oscar4", "oscar@hotmail.es","Oscar", "Vilamitjana", "Gavà", "hello");
            this.gservicedb.getUserDAO().addUser("miquel5", "miquel@hotmail.es","Miquel", "Arina", "Castelldefels", "adios");

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
        //HashMap<String, User> users = this.gservicedb.getUserDAO().getUsers();
        List<User> users = this.gservicedb.getUserDAO().getUsers();
        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users){};
        return Response.status(201).entity(entity).build();
    }

    //register User = Sign Up
    @POST
    @ApiOperation(value = "registrar un usuario", notes = "Create a new User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=void.class, responseContainer = "Void class"),
            @ApiResponse(code = 409, message="Existant user")
    })
    @Path("/registerUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        try {
            this.gservicedb.getUserDAO().addUser(user.getUsername(), user.getPassword(), user.getMail(), user.getName(), user.getLastname(), user.getCity());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(409).entity(user).build();
        }
        return Response.status(201).entity(user).build();
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
        User user=this.gservicedb.getUserDAO().getUser(username);
        if(user==null) return Response.status(404).build();
        else return Response.status(201).entity(user).build();
    }

    //añadir un eliminar usuario
    @DELETE
    @ApiOperation(value = "eliminar un usuario", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User Not Found Exception")
    })

    //cambiar pasaremos username y password
    @Path("/deleteUser/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("username") String username) throws UserNotFoundException {
        User user = this.gservicedb.getUserDAO().getUser(username);
        if(user == null) return Response.status(404).build();
        else this.gservicedb.getUserDAO().deleteUser(user.getId());
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
        //revisar servicio update de los diferentes campos
        //User user = this.gservicedb.getUserDAO().updateUser(id, username, mail, name, lastname, city, password);
        //if(user == null) return Response.status(404).build();
        return Response.status(201).build();
    }
}
