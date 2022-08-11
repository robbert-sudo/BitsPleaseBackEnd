package repository;

import bitspleaseApp.BitspleaseApplication;
import bitspleaseApp.model.SellersRating;
import bitspleaseApp.repository.SellersRatingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ContextConfiguration(classes = {BitspleaseApplication.class})
public class SellerRatingRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SellersRatingRepository sellersRatingRepository;


    @Test
    void testFindAllByRatedUserId() {

        //Arrange
        SellersRating sellersRating1 = new SellersRating(1, 7);
        SellersRating sellersRating2 = new SellersRating(1, 8);
        SellersRating sellersRating3 = new SellersRating(1, 5);
        entityManager.persist(sellersRating1);
        entityManager.persist(sellersRating2);
        entityManager.persist(sellersRating3);
        entityManager.flush();

        //Act
        Iterable<SellersRating> actualRatings = sellersRatingRepository.findAllByRatedUserId(1);

        ArrayList<Long> expectedRatings = new ArrayList<>();
        long expectedRating1 = 7;
        long expectedRating2 = 8;
        long expectedRating3 = 5;
        Long expectedLong1 = expectedRating1;
        Long expectedLong2 = expectedRating2;
        Long expectedLong3 = expectedRating3;
        expectedRatings.add(expectedLong1);
        expectedRatings.add(expectedLong2);
        expectedRatings.add(expectedLong3);

        ArrayList<Long> actualRatingList = new ArrayList<>();

        for (SellersRating sellersRating : actualRatings) {
            actualRatingList.add(sellersRating.getRating());
        }

        //Assert
        assertEquals(expectedRatings, actualRatingList);
    }

    @Test
    void testFindAllByRatedUserIdWithNoResults() {
        //Act
        Iterable<SellersRating> actualSellerRating = sellersRatingRepository.findAllByRatedUserId(4);

        ArrayList<Object> expectedResult = new ArrayList();

        //Assert

        assertEquals(expectedResult, actualSellerRating);


    }


}
