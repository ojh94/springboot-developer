import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JUnitTest {
    @DisplayName("1+2는 3이다.")
    @Test
    public void junitTest() {
        int a = 1;
        int b = 2;
        int sum = 3;

        // Assertions : 예상 결과를 검증하는 assert- 메서드 제공
        //Assertions.assertEquals(sum, a + b);
        assertThat(a + b).isEqualTo(sum);
    }

    @DisplayName("1+3는 3이다.")
    @Test
    public void junitFailedTest() {
        int a = 1;
        int b = 3;
        int sum = 3;

        // Assertions : 예상 결과를 검증하는 assert- 메서드 제공
        //Assertions.assertEquals(sum, a + b);
        assertThat(a + b).isEqualTo(sum);
    }
}
