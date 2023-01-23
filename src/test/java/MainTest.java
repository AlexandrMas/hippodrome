import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class MainTest {
    @Test
    @Disabled("If necessary, run manually")
    @Timeout(value = 22)
    void mainRunsForNoMoreThan22Seconds() throws Exception {
        Main.main(new String[0]);
    }
}