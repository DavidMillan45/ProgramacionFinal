package model.resources;


import model.jpa.entities.PetCase;
import model.resources.pojos.PetCasePojo;
import model.resources.pojos.PetPojo;
import model.services.PetCaseService;
import model.services.PetService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/petscase")
public class PetCaseResource {
    @GET
    @Path("/{pet_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@PathParam("pet_id") String pet_id) {


        List<PetCasePojo> petscase = new PetCaseService().listPetcase(pet_id);


        return Response.ok()
                .entity(petscase)
                .build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public Response create(PetCasePojo petcase) {


        new PetCaseService().savePetCase(petcase.getCase_id(),petcase.getCreated_at(), petcase.getType(), petcase.getDescription(), petcase.getPet_id());


        if (petcase.getType().equals("Perdida") || petcase.getType().equals("Robo") || petcase.getType().equals("Fallecimiento")) {

            if (petcase != null) {
                return Response.status(Response.Status.CREATED)
                        .entity(petcase)
                        .build();
            } else {
                return Response.serverError()
                        .entity("Owner user could not be created")
                        .build();
            }
        } else {
            return Response.serverError()
                    .entity("Owner user could not be created")
                    .build();

        }


    }
}