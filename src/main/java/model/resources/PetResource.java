package model.resources;


import model.resources.pojos.OfficialPojo;
import model.resources.pojos.PetPojo;
import model.services.OfficialService;
import model.services.OwnerService;
import model.services.PetService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PetResource {
    @POST
    @Path("/pets")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(PetPojo petpojo) {

        Optional<PetPojo> persistedPet = new PetService().savePet(petpojo.getPet_id(), petpojo.getMicrochip(), petpojo.getName(), petpojo.getSpecies(), petpojo.getRace(), petpojo.getSize(), petpojo.getSex());

        if (persistedPet .isPresent()) {
            return Response.status(Response.Status.CREATED)
                    .entity(persistedPet.get())
                    .build();
        } else {
            return Response.serverError()
                    .entity("pet could not be created")
                    .build();
        }

    }

    @GET
    @Path("/{pet_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@PathParam("pet_id") Integer pet_id) {

        List<PetPojo> pets = new ArrayList<PetPojo>();
        pets.add(new PetPojo("1", "Microchip1", "Max", "Especie1", "Raza1", "Pequeño", "M",  "Owner1"));
        pets.add(new PetPojo("2", "Microchip2", "Pepe", "Especie2", "Raza2", "Grande", "H",  "Owner2"));

        return Response.ok()
                .entity(pets)
                .build();
    }



    @PUT
    @Path("/microchip/{pet_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modify(@PathParam("pet_id") String pet_id, String microchip, PetPojo pet) {
        new PetService().updatePetMicrochi(pet_id,microchip);

        return Response.ok()
                .entity(pet)
                .build();
    }

    @PUT
    @Path("/{pet_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modify(@PathParam("pet_id") String pet_id, String name, String species, String race, String size, String sex, PetPojo pet) {
        new PetService().updatePet(pet_id, name, species, race, size, sex);

        return Response.ok()
                .entity(pet)
                .build();
    }
}