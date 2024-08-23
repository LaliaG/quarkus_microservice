package org.example.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.example.dto.AuthorDTO;

@Path("/authors")
@RegisterRestClient(configKey = "author-service")
public interface AuthorServiceClient {

    @GET
    @Path("/{id}")
    AuthorDTO getAuthorById(@PathParam("id") Long id);
}
