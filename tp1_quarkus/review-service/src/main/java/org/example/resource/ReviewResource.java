package org.example.resource;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.dto.ReviewDTO;
import org.example.entity.Review;
import org.example.service.ReviewService;

import java.util.List;

@Path("/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReviewResource  {

    @Inject
    ReviewService reviewService;

    @GET
    public List<ReviewDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GET
    @Path("/user/{userName}")
    public List<ReviewDTO> getReviewsByUserName(@PathParam("userName") String userName) {
        return reviewService.getReviewsByUserName(userName);
    }

    @GET
    @Path("/book/{isbn}")
    public List<ReviewDTO> getReviewsByBookIsbn(@PathParam("isbn") String isbn) {
        return reviewService.getReviewsByBookIsbn(isbn);
    }

    @GET
    @Path("/rating/{rating}")
    public List<ReviewDTO> getReviewsByRating(@PathParam("rating") int rating) {
        return reviewService.getReviewsByRating(rating);
    }
}
