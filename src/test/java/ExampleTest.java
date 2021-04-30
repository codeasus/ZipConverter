import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ExampleTest {
    @Test
    void shouldShowSimpleAssertion() {
        Assertions.assertEquals(1,1);
    }

    @Test
    @Disabled("Has not been implemented yet")
    void checkFileStructure() {
    }
}
