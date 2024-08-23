package org.example.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.example.dto.BookDTO;

@RegisterRestClient(configKey="books-service")
public interface BookServiceClient {

    @GET
    @Path("/books/{id}")
    BookDTO getBookById(@PathParam("id") Long id);
}
