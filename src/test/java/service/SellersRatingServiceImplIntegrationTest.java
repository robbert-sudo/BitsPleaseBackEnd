package service;

import com.rob.bitspleaseapp.BitspleaseApplication;
import com.rob.bitspleaseapp.model.SellersRating;
import com.rob.bitspleaseapp.repository.SellersRatingRepository;
import com.rob.bitspleaseapp.service.SellersRatingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ContextConfiguration(classes = {BitspleaseApplication.class})
public class SellersRatingServiceImplIntegrationTest {

    @Autowired
    private SellersRatingService sellersRatingService;

    @MockBean
    private SellersRatingRepository sellersRatingRepository;

    @Mock
    SellersRating sellersRating;
    SellersRating sellersRating2;



    @Test
    public void testFindAllRatingsByUserId() {

        ArrayList<SellersRating> expectedSellersRating = new ArrayList<>();

        sellersRating = new SellersRating(1, 8);
        sellersRating2= new SellersRating(1,7);
        expectedSellersRating.add(sellersRating);
        expectedSellersRating.add(sellersRating2);


        Mockito
                .when(sellersRatingRepository.findAllByRatedUserId(sellersRating.getRatedUserId()))
                .thenReturn(expectedSellersRating);

        Iterable<SellersRating> actualSellersRating = sellersRatingService.findAllByRatedUserId(1);

        assertEquals(expectedSellersRating, actualSellersRating);
    }


    @Test
    public void testGetAverageRatingBySeller() {

        ArrayList<SellersRating> expectedSellersRating = new ArrayList<>();

        sellersRating = new SellersRating(1, 8);
        sellersRating2= new SellersRating(1,7);
        expectedSellersRating.add(sellersRating);
        expectedSellersRating.add(sellersRating2);


        Mockito
                .when(sellersRatingRepository.findAllByRatedUserId(sellersRating.getRatedUserId()))
                .thenReturn(expectedSellersRating);

        float actualAverage = sellersRatingService.getAverageRatingBySeller(1);
        float expectedAverage = 7.5f;

        assertEquals(expectedAverage, actualAverage);
    }

}
