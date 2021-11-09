package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/Game", description = "Endpoint to Text Service")
@Path("/users")
public class GameService {
    private GameManager manager;

    public GameService(){}

    @GET
    @ApiOperation(value = "get all Users", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/listaUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {

        List<User> users = this.manager.findAll();
        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build();

    }

    @POST
    @ApiOperation(value = "create a new User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=User.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/addUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(User user) {

        if (user.getNombre()==null || user.getApellido() == null || user.getId() == null)
            return Response.status(500).entity(user).build();
        this.manager.addUser(user.getNombre(),user.getApellido(),user.getId());
        return Response.status(201).entity(user).build();
    }

    @PUT
    @ApiOperation(value = "update a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/updateUser")
    public Response updateUser(User user) {

        User t = this.manager.modificarUser(user.getNombre(),user.getApellido(),user.getId());
        if (t == null) return Response.status(404).build();
        return Response.status(201).build();
    }

    @GET
    @ApiOperation(value = "get a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/getUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) {
        User t = this.manager.getUserById(id);
        if (t == null) return Response.status(404).build();
        else  return Response.status(201).entity(t).build();
    }

    @GET
    @ApiOperation(value = "get a Objetos de Usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Objeto.class),
            @ApiResponse(code = 404, message = "Lista Vacia")
    })
    @Path("/getObjetosUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObjetoYUser(@PathParam("id") String id) {
        User t = this.manager.getUserById(id);
        List<Objeto> objetoList = t.getListaobjetos();
        if (objetoList.size() == 0) return Response.status(404).build();
        else {
            GenericEntity<List<Objeto>> entity = new GenericEntity<List<Objeto>>(objetoList) {};
            return Response.status(201).entity(entity).build();
        }
    }

    @PUT
    @ApiOperation(value = "Añadir objeto a Usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/AddObjeto/{idUser}{idObjeto}")
    public Response addObjeto(@PathParam("idUser") String idUser, @PathParam("idObjeto") String idObjeto) {
        User user = manager.getUserById(idUser);
        Objeto objeto = manager.getObjetoByName(idObjeto);
        manager.addObjetoToUser(idUser,idObjeto);
        if (user == null || objeto == null) return Response.status(404).build();
        return Response.status(201).build();
    }



}
