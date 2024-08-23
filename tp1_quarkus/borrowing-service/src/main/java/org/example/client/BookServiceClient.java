package org.example.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.example.dto.BookDTO;


@Path("/books")
@RegisterRestClient(configKey = "books-service")
public interface BookServiceClient {

    @GET
    @Path("/{id}")
    BookDTO getBookById(@PathParam("id") Long id);
}
