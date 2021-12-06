package model.resources;




import model.resources.pojos.PetCasePojo;
import model.services.PetCaseService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("petscase/{case_id}")
public class PetCaseResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("case_id") Integer case_id) {

        List<PetCasePojo> petcase = new ArrayList<PetCasePojo>();
        petcase.add(new PetCasePojo("1", null, "Perdida", "Perdida descripcion", "1"));
        petcase.add(new PetCasePojo("2", null, "Fallecimiento", "Fallecimiento descripcion", "2"));

        return Response.ok()
                .entity(petcase)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public Response create(PetCasePojo petcase) {


        new PetCaseService().savePetCase(petcase.getCase_id(),petcase.getCreated_at(),petcase.getType(), petcase.getDescription(), petcase.getPet_id());


        if (petcase.getType().equals("Perdida")||petcase.getType().equals("Robo")||petcase.getType().equals("Fallecimiento")) {

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