package edu.upc.dsa.services;

import edu.upc.dsa.dao.DAOManager;
import edu.upc.dsa.dao.DAOManagerImpl;
import edu.upc.dsa.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

//en este fichero de la api se llevara a cabo todas las operaciones del usuario con la web
@Api(value="/auth", description = "Servicio del usuario")
@Path("/auth")
public class AuthenticationService {

    //creamos la variable para utilizar el log4j
    final static Logger logger = Logger.getLogger(AuthenticationService.class.getName());

    private DAOManager gservicedb;

    public AuthenticationService() throws Exception{
        this.gservicedb = DAOManagerImpl.getInstance();
        if (gservicedb.getUserDAO().findMax() == 0) {
            this.gservicedb.getUserDAO().addUser("tatiana1", "tatiana@hotmail.es","Tatiana", "Tkachuk", "Barcelona", "hola");
            this.gservicedb.getUserDAO().addUser("gabriel2", "gabriel@hotmail.es","Gabriel", "Donate", "Castelldefels", "buenas");
            this.gservicedb.getUserDAO().addUser("kevin3", "kevin@hotmail.es","Kevin", "Alcalde", "Barcelona", "bye");
            this.gservicedb.getUserDAO().addUser("oscar4", "oscar@hotmail.es","Oscar", "Vilamitjana", "Gavà", "hello");
            this.gservicedb.getUserDAO().addUser("miquel5", "miquel@hotmail.es","Miquel", "Arina", "Castelldefels", "adios");

        }
    }

    @POST
    @ApiOperation(value = "cargar el perfil", notes = "escribir el nombre de usuario y la contraseña para loggearse")
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "Succesful", response= User.class, responseContainer="User"),
            @ApiResponse(code= 404, message = "User not found"),
            @ApiResponse(code= 401, message = "Password not match"),
            @ApiResponse(code=403, message="You are connected in other device")
    })

    //parece que el login nos da buenos resultados
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logIn(User user) {
        try{
            user = this.gservicedb.getUserDAO().logIn(user.getUsername(), user.getPassword());
            System.out.println(user);
            return Response.status(201).entity(user).build();
        } catch (UserNotFoundException e){
            e.printStackTrace();
            return Response.status(404).build();
        } catch(PasswordNotMatchException e){
            e.printStackTrace();
            return Response.status(401).build();
        } catch(UserAlreadyConectedException e){
            e.printStackTrace();
            return Response.status(403).build();
        }
    }

    //desconectar un usuario del sistema
    @PUT
    @ApiOperation(value = "log out", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code= 201, message = "Succesful"),
            @ApiResponse(code= 404, message = "User not found")
    })

    @Path("/logout")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logOut(int idUser){
        try{
            this.gservicedb.getUserDAO().logOut(idUser);
            return Response.status(201).entity(idUser).build();
        } catch (UserNotFoundException e){
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

}
