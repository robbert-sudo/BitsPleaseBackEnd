package com.rob.bitspleaseapp.controller;

import com.rob.bitspleaseapp.model.SellersRating;
import com.rob.bitspleaseapp.service.SellersRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "sellerratings")
public class SellersRatingController {

    private SellersRatingService sellersRatingService;

    @Autowired
    public SellersRatingController(SellersRatingService sellersRatingService) {
        this.sellersRatingService = sellersRatingService;
    }



    @PostMapping
    public ResponseEntity addSellerRating(@RequestBody SellersRating sellersRating) {
        sellersRatingService.save(sellersRating);
        return ResponseEntity.ok("bedankt voor uw stem.");
    }

    @GetMapping
    public ResponseEntity getAllRatings() {
        Iterable<SellersRating> sellersRatings = sellersRatingService.findAll();
        return ResponseEntity.ok(sellersRatings);
    }

    @GetMapping(value = "/{rated_user_id}")
    public ResponseEntity getAllRatingsFromUser(@PathVariable long rated_user_id) {
        Iterable<SellersRating> sellersRatings = sellersRatingService.findAllByRatedUserId(rated_user_id);
        return ResponseEntity.ok(sellersRatings);
    }

    @GetMapping(value = "/getaverage/{rated_user_id}")
    public ResponseEntity getAverageRatingBySeller(@PathVariable long rated_user_id) {
        float averageSellerRating = sellersRatingService.getAverageRatingBySeller(rated_user_id);
        return ResponseEntity.ok(averageSellerRating);
    }


}
