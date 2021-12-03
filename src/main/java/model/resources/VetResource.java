package model.resources;


import model.resources.filters.Logged;
import model.resources.pojos.VetPojo;
import model.services.VetService;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("/vets")
public class VetResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {

        List<VetPojo> authors = new ArrayList<VetPojo>();
        // authors.add(new VetPojo("Veterinaria1", "Nombre veterinaria 1", "Direccion1", "Vecindario1"));
        //authors.add(new VetPojo("Veterinaria2", "Nombre veterinaria 2", "Direccion2", "Vecindario2"));

        return Response.ok()
                .entity(authors)
                .build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(VetPojo vet) {

        Optional<VetPojo> persistedVet = new VetService().saveVet(vet);
        if (persistedVet.isPresent()) {
            return Response.status(Response.Status.CREATED)
                    .entity(persistedVet.get())
                    .build();
        } else {
            return Response.serverError()
                    .entity("Owner user could not be created")

                    .build();
        }
    }

    @Logged
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello(@HeaderParam("role") String role) {

        // If role doesn't match
        if (!"vet".equals(role))
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("Role " + role + " cannot access to this method")
                    .build();

        return Response.ok()
                .entity("Hello, World, " + role + "!")
                .build();

    }


}