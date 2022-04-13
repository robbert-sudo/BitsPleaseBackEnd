package bitspleaseApp.service;

import bitspleaseApp.model.SellersRating;
import bitspleaseApp.repository.SellersRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellersRatingServiceImpl implements SellersRatingService {

    private SellersRatingRepository sellersRatingRepository;

    @Autowired
    SellersRatingServiceImpl(SellersRatingRepository sellersRatingRepository) {
        this.sellersRatingRepository = sellersRatingRepository;
    }


    @Override
    public void save(SellersRating sellersRating) {
        sellersRatingRepository.save(sellersRating);
    }

    @Override
    public Iterable<SellersRating> findAll() {
        Iterable<SellersRating> sellersRatings = sellersRatingRepository.findAll();
        return sellersRatings;
    }


    @Override
    public Iterable<SellersRating> findAllByRatedUserId(long rated_user_id) {
        Iterable<SellersRating> sellersRatings = sellersRatingRepository.findAllByRatedUserId(rated_user_id);
        return sellersRatings;

    }

    @Override
    public float getAverageRatingBySeller(long rated_user_id) {

        float total = 0;
        float size = 0;

        Iterable<SellersRating> sellersRatings = sellersRatingRepository.findAllByRatedUserId(rated_user_id);
        for (SellersRating sellersRating : sellersRatings) {
            total += sellersRating.getRating();
            size += 1;
        }
        float averageRating;
        if (size == 0) {
            averageRating = 0;
        } else {
            averageRating = total / size;

        }
        return averageRating;
    }
}
