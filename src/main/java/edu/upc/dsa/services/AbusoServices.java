package edu.upc.dsa.services;

import edu.upc.dsa.AbusoManager;
import edu.upc.dsa.AbusoManagerImpl;
import edu.upc.dsa.models.Abuso;
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
@Api(value = "/abuso", description = "Endpoint to User Service")
@Path("/abuso")
public class AbusoServices {

    private AbusoManager manager;

    public AbusoServices() {
        this.manager = AbusoManagerImpl.getInstance();
        if (manager.abusoListSize() == 0) {
            Abuso u1 = this.manager.addAbuso(new Abuso("03/06/2022", "Esther","ME HAN BORRADO LA PARTIDA"));

        }
    }

    //Examen minimo2
    //Abuso
    @POST
    @ApiOperation(value = "abuso", notes = "date,informer,message")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/abuso")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAbuso(Abuso a) {
        Abuso abuso = new Abuso(a.getDate(), a.getInformer(), a.getMessage());
        if (abuso.getDate().equals("") || abuso.getInformer().equals("") || abuso.getMessage().equals("")) {
            return Response.status(500).build();
        }
        for (Abuso qs : this.manager.getAllAbusos()) {
            if (qs.getDate().equals(abuso.getDate())) {
                return Response.status(500).build();
            }
        }
        abuso.setInformer(a.getInformer());
        abuso.setMessage(a.getMessage());
        this.manager.addAbuso(abuso);
        return Response.status(200).entity(abuso).build();
    }
    //Get de la lista de abusos
    @GET
    @ApiOperation(value = "Get de todos los abusos", notes = "Get todos los abusos")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer = "Lista"),
        })
        @Path("/")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getAllQuestions() {
            List<Abuso> questions = this.manager.getAllAbusos();
            GenericEntity<List<Abuso>> entity = new GenericEntity<List<Abuso>>(questions){};
            return Response.status(201).entity(entity).build();
        }


 }

