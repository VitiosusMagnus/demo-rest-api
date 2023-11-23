package com.vitiosusmagnus.demorestapi.webapi.controllers;

import com.vitiosusmagnus.demorestapi.business.concretes.ReviewManager;
import com.vitiosusmagnus.demorestapi.business.request.ReviewRequest;
import com.vitiosusmagnus.demorestapi.entities.concretes.Review;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    private ReviewManager manager;

    public ReviewController(ReviewManager manager){
        this.manager = manager;
    }

    @GetMapping("{id}")
    public Review getReview(@PathVariable Long id){
        return manager.getById(id);
    }

    @GetMapping("/{filmId}")
    public List<Review> getReviewByFilmId(@PathVariable long filmId){
        return manager.findReviewByFilmId(filmId);
    }

    @PostMapping()
    public Review create(@RequestBody ReviewRequest reviewRequest){
        Review review = new Review();
        review.setName(reviewRequest.getName());
        review.setFilm(reviewRequest.getFilm());
        review.setRating(reviewRequest.getRating());
        review.setComment(reviewRequest.getComment());
        return manager.create(review);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id){
        manager.deleteById(id);
    }

}
