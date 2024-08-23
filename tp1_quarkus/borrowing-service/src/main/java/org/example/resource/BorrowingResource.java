package org.example.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.dto.BorrowingDTO;
import org.example.entity.Borrowing;
import org.example.service.BorrowingService;

import java.time.LocalDateTime;
import java.util.List;

@Path("/borrowings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BorrowingResource {

    @Inject
    BorrowingService borrowingService;

    @POST
    public Borrowing createBorrowing(@QueryParam("bookId") Long bookId,
                                     @QueryParam("userId") Long userId) {
        return borrowingService.createBorrowing(bookId, userId);
    }

    @DELETE
    @Path("/{id}")
    public void deleteBorrowing(@PathParam("id") Long id) {
        borrowingService.deleteBorrowing(id);
    }

    @GET
    public List<BorrowingDTO> getCurrentBorrowings() {
        return borrowingService.getCurrentBorrowings();
    }

    @GET
    @Path("/by-period")
    public List<BorrowingDTO> getBorrowingsByPeriod(@QueryParam("startDate") String startDate,
                                                    @QueryParam("endDate") String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        return borrowingService.getBorrowingsByPeriod(start, end);
    }

    @GET
    @Path("/by-user/{userId}")
    public List<BorrowingDTO> getBorrowingsByUser(@PathParam("userId") Long userId) {
        return borrowingService.getBorrowingsByUser(userId);
    }

    @GET
    @Path("/is-borrowed/{bookId}")
    public boolean isBookBorrowed(@PathParam("bookId") Long bookId) {
        return borrowingService.isBookBorrowed(bookId);
    }
}
