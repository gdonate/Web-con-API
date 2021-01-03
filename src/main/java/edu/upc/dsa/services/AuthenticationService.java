package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.models.PasswordNotMatchException;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.UserNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

//en este fichero de la api se llevara a cabo todas las operaciones del usuario con la web
@Api(value="/auth", description = "Servicio del usuario")
@Path("/auth")
public class AuthenticationService {

    //creamos la variable para utilizar el log4j
    final static Logger logger = Logger.getLogger(AuthenticationService.class.getName());

    //tenemos que crear variable para poder utilizar el GameManager
    private GameManager gservice;

    public AuthenticationService() throws Exception{
        //buscar esta instancia de autenticacion
        this.gservice = GameManagerImpl.getInstance();

        if (gservice.numUsers() == 0) {
            this.gservice.addUser("Tatiana", "tatiana@hotmail.es", "Tatiana", "Tkachuk", "Barcelona", "hola", false);
            this.gservice.addUser("Gabriel", "gabriel@hotmail.es", "Gabriel", "Donate", "Barcelona",  "buenas", false);
            this.gservice.addUser("Kevin", "kevin@hotmail.es", "Kevin", "Alcalde", "Barcelona", "bye", false);
            this.gservice.addUser("Oscar", "oscar@hotmail.es", "Oscar", "Vilamitjana", "Barcelona",  "hello", false);
            this.gservice.addUser("Miquel", "miquel@hotmail.es", "Miquel", "Arina", "Barcelona","adios", false);
        }
    }

    @POST
    @ApiOperation(value = "cargar el perfil", notes = "escribir el nombre de usuario y la contraseña para loggearse")
    @ApiResponses(value = {
            @ApiResponse(code= 201, message = "Succesful", response= User.class, responseContainer="User"),
            @ApiResponse(code= 404, message = "User not found"),
            @ApiResponse(code=500, message="Password not match")
    })

    //parece que el login nos da buenos resultados
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logIn(User user){
        try{
            User user2 = this.gservice.getUserLogin(user.getUsername(), user.getPassword());
            System.out.println(user2);
            return Response.status(201).entity(user2).build();
        } catch (UserNotFoundException e){
            e.printStackTrace();
            return Response.status(404).build();
        } catch(PasswordNotMatchException e){
            e.printStackTrace();
            return Response.status(500).build();
        }
    }

}
