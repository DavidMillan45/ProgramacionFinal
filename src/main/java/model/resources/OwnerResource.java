package model.resources;


import model.resources.filters.Logged;
import model.resources.pojos.OfficialPojo;
import model.resources.pojos.OwnerPojo;
import model.resources.pojos.PetPojo;
import model.services.OfficialService;
import model.services.OwnerService;
import model.services.PetService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/owners")
public class OwnerResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(OwnerPojo owner) {

        Optional<OwnerPojo> persistedOwner = new OwnerService().saveOwner(owner);

        if (persistedOwner.isPresent()) {
            return Response.status(Response.Status.CREATED)
                    .entity(persistedOwner.get())
                    .build();
        } else {
            return Response.serverError()
                    .entity("Owner user could not be created")
                    .build();
        }

    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {


        List<OwnerPojo> owners = new OwnerService().listOwners();


        return Response.ok()
                .entity(owners)
                .build();
    }


    @Logged
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello(@HeaderParam("role") String role) {

        // If role doesn't match
        if (!"owner".equals(role))
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("Role " + role + " cannot access to this method")
                    .build();

        return Response.ok()
                .entity("Hello, World, " + role + "!")
                .build();

    }

    @PUT
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modify(@PathParam("username") String username, OwnerPojo ownerPojo) {
        new OwnerService().updateOwner2(ownerPojo);
        return Response.ok()
                .entity(ownerPojo)
                .build();

    }

}