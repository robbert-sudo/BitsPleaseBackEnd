import com.rob.bitspleaseapp.BitspleaseApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest()
@ContextConfiguration(classes = {BitspleaseApplication.class})
class SpringBootStarterTestApplicationTests {

    @Test
    @DisplayName("Testing if context is correctly set")
    void ContextLoadsTest() {
        assertNotEquals(1, 2);
    }



}
