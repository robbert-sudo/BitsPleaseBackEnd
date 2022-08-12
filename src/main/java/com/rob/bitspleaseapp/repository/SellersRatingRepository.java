package com.rob.bitspleaseapp.repository;

import com.rob.bitspleaseapp.model.SellersRating;
import org.springframework.data.repository.CrudRepository;

public interface SellersRatingRepository extends CrudRepository <SellersRating, Long> {

    Iterable<SellersRating> findAllByRatedUserId(long rated_user_id);

}
