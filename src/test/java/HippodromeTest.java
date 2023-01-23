import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {
    @Mock
    Horse horse;

    @Test
    void HippodromeConstructorParameterIsNullShouldTrowIllegalArgumentExceptionWithMessageHorsesCannotBeNull() {
        String message = "Horses cannot be null.";
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );
        String expected = message;
        String actual = exception.getMessage();
        assertEquals(expected, actual);

    }

    @Test
    void HippodromeConstructorParameterIsEmptyShouldTrowIllegalArgumentExceptionWithMessageHorsesCannotBeEmpty() {
        String message = "Horses cannot be empty.";
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>())
        );
        String expected = message;
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void getHorsesShouldReturnListHorsesParameterOfConstructor() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse(Horse.class.getName(), Math.random(), Math.random()));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        boolean expected = horses.equals(hippodrome.getHorses());
        assertTrue(expected);
    }

    @Test
    void moveShouldToBeCalledFromAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        verify(horse, times(50)).move();
    }

    @Test
    void getWinnerShouldReturnHorseWithGreatestDistance() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse(Horse.class.getName(), Math.random(), Math.random()));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        double expected = horses.stream().max(Comparator.comparingDouble(Horse::getDistance)).get().getDistance();
        double actual = hippodrome.getWinner().getDistance();
        assertEquals(expected, actual);
    }

}