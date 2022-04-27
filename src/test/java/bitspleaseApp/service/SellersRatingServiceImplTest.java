package bitspleaseApp.service;

import bitspleaseApp.model.SellersRating;
import bitspleaseApp.repository.SellersRatingRepository;
import org.hamcrest.collection.IsEmptyIterable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SellersRatingServiceImplTest {


    @Test
    void getAverageRatingBySeller() {
        //arrange

        SellersRatingRepository sellersRatingRepository = mock(SellersRatingRepository.class);

        ArrayList<SellersRating> fakeresult = new ArrayList<SellersRating>();
        SellersRating fakesellerRating = new SellersRating(1, 1, 7);
        SellersRating fakesellerRating2 = new SellersRating(2, 1, 8);
        SellersRating fakesellerRating3 = new SellersRating(3, 1, 8);
        SellersRating fakesellerRating4 = new SellersRating(4, 1, 6);
        fakeresult.add(fakesellerRating2);
        fakeresult.add(fakesellerRating);
        fakeresult.add(fakesellerRating3);
        fakeresult.add(fakesellerRating4);
        when(sellersRatingRepository.findAllByRatedUserId(1))
                .thenReturn(fakeresult);

        SellersRatingServiceImpl sellersRatingServiceImpl = new SellersRatingServiceImpl(sellersRatingRepository);

        //act
        float result = sellersRatingServiceImpl.getAverageRatingBySeller(1);


        //assert
        assertEquals(7.25, result);

    }

    @Test
    void getAverageRatingBySellerWithNoRatings() {
        //arrange

        SellersRatingRepository sellersRatingRepository = mock(SellersRatingRepository.class);

        ArrayList<SellersRating> fakeresult = new ArrayList<SellersRating>();

        when(sellersRatingRepository.findAllByRatedUserId(1))
                .thenReturn(fakeresult);

        SellersRatingServiceImpl sellersRatingServiceImpl = new SellersRatingServiceImpl(sellersRatingRepository);

        //act
        float result = sellersRatingServiceImpl.getAverageRatingBySeller(1);


        //assert
        assertEquals(0.0, result);

    }

    @Test
    void findAllWithZeroRatings() {
        //arrange
        SellersRatingRepository sellersRatingRepository = mock(SellersRatingRepository.class);

        ArrayList<SellersRating> fakeresult = new ArrayList<SellersRating>();

        when(sellersRatingRepository.findAll())
                .thenReturn(fakeresult);

        SellersRatingServiceImpl sellersRatingServiceImpl = new SellersRatingServiceImpl(sellersRatingRepository);

        //act
        Iterable<SellersRating> result = sellersRatingServiceImpl.findAll();


        //assert
        assertThat(result, IsEmptyIterable.emptyIterable());
    }

}