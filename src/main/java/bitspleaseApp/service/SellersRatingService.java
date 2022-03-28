package bitspleaseApp.service;

import bitspleaseApp.model.SellersRating;

public interface SellersRatingService {

    void save (SellersRating sellersRating);

    float getAverageRatingBySeller(long rated_user_id);

    Iterable<SellersRating> findAll();

    Iterable<SellersRating> findAllByRatedUserId(long rated_user_id);
}
