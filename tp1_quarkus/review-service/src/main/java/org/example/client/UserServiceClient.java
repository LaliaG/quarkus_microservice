package org.example.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.example.dto.UserDTO;

@RegisterRestClient(configKey="users-service")
public interface UserServiceClient {

    @GET
    @Path("/users/{id}")
    UserDTO getUserById(@PathParam("id") Long id);
}
