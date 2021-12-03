package model.resources;


import model.resources.pojos.VisitPojo;
import model.services.PetService;
import model.services.VisitService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/visits")
public class VisitResource {

    @GET
    @Path("/{username}/pet/{pet_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@PathParam("username") String username, @PathParam("pet_id") Integer pet_id) {

        List<VisitPojo> visit = new ArrayList<VisitPojo>();
        visit.add(new VisitPojo(1, "Fecha1", "Vacunacion", "Vacunacion descripcion", username, pet_id));
        visit.add(new VisitPojo(2, "Fecha2", "Esterilizacion", "Esterilizacion descripcion", username, pet_id));

        return Response.ok()
                .entity(visit)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pets/{pet_id}/vets/{vet_id}")
    public Response create(@PathParam("vet_id") String vet_id, @PathParam("pet_id") Integer pet_id, VisitPojo visit, String microchip) {

        new VisitService().saveVisit(visit.getCreated_at(), visit.getType(), visit.getDescription(), pet_id);


        if (visit.getType().equals("Esterilizacion") || visit.getType().equals("Implantacion de microchip") || visit.getType().equals("Vacunacion") || visit.getType().equals("Desparasitacion") || visit.getType().equals("Urgencia") || visit.getType().equals("Control")) {

            new PetService().updatePetMicrochi(pet_id, microchip);
            if (visit != null) {
                return Response.status(Response.Status.CREATED)
                        .entity(visit)
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