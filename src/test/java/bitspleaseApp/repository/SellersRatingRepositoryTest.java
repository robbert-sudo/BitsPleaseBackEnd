package bitspleaseApp.repository;

import bitspleaseApp.model.SellersRating;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SellersRatingRepositoryTest {

    @Autowired
    private SellersRatingRepository underTest;

    @BeforeEach
    void setUp() {
        SellersRating sellersRating1 = new SellersRating(1, 1, 8);
        underTest.save(sellersRating1);

        SellersRating sellersRating2 = new SellersRating(2, 1, 7);
        underTest.save(sellersRating2);

        SellersRating sellersRating3 = new SellersRating(3, 2, 9);
        underTest.save(sellersRating3);
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findAllByRatedUserId() {
        //arrange


        ArrayList<Long> expectedRatings = new ArrayList<>();
        Long rating1 = (long)8;
        expectedRatings.add(rating1);
        Long rating2 = (long)7;
        expectedRatings.add(rating2);

        //act
        Iterable<SellersRating> foundRatings = underTest.findAllByRatedUserId(1);

        ArrayList<Long> actualRatings = new ArrayList<>();
        for ( SellersRating sellersRating : foundRatings) {
            actualRatings.add(sellersRating.getRating());
        }

        //assert
        assertEquals( expectedRatings, actualRatings);
    }

    @Test
    void findAllByRatedUserIdWhenThereIsNoRating() {
        //arrange
        ArrayList<Long> expectedResults = new ArrayList<>();

        //act
        Iterable<SellersRating> foundRatings = underTest.findAllByRatedUserId(3);

        //assert
        assertEquals( expectedResults, foundRatings);
    }
}