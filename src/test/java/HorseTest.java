import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.only;

class HorseTest {
    @Test
    void horseConstructorNameIsNullShouldTrowIllegalArgumentException() {
        Class<IllegalArgumentException> expected = IllegalArgumentException.class;
        assertThrows(expected, () -> new Horse(null, 0, 0));
    }

    @Test
    void horseConstructorNameIsNullShouldSendMessageNameCannotBeNull() {
        String expected = "Name cannot be null.";
        try {
            new Horse(null, 0, 0);
        } catch (IllegalArgumentException exception) {
            String actual = exception.getMessage();
            assertEquals(expected, actual);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\n", "\t"})
    void horseConstructorNameIsBlankShouldTrowIllegalArgumentException(String name) {
        Class<IllegalArgumentException> expected = IllegalArgumentException.class;
        assertThrows(expected, () -> new Horse(name, 0, 0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\n", "\t"})
    void horseConstructorNameIsBlankShouldSendMessageNameCannotBeBlank(String name) {
        String expected = "Name cannot be blank.";
        try {
            new Horse(name, 0, 0);
        } catch (IllegalArgumentException exception) {
            String actual = exception.getMessage();
            assertEquals(expected, actual);
        }
    }

    @Test()
    void horseConstructorSpeedIsNegativeShouldTrowIllegalArgumentException() {
        Class<IllegalArgumentException> expected = IllegalArgumentException.class;
        assertThrows(expected, () -> new Horse("name", -1, 0));
    }

    @Test
    void horseConstructorSpeedIsNegativeShouldSendMessageSpeedCannotBeNegative() {
        String expected = "Speed cannot be negative.";
        try {
            new Horse("name", -1, 0);
        } catch (IllegalArgumentException exception) {
            String actual = exception.getMessage();
            assertEquals(expected, actual);
        }
    }

    @Test
    void horseConstructorDistanceIsNegativeShouldTrowIllegalArgumentException() {
        Class<IllegalArgumentException> expected = IllegalArgumentException.class;
        assertThrows(expected, () -> new Horse("name", 0, -1));
    }

    @Test
    void horseConstructorDistanceIsNegativeShouldSendMessageDistanceCannotBeNegative() {
        String expected = "Distance cannot be negative.";
        try {
            new Horse("name", 0, -1);
        } catch (IllegalArgumentException exception) {
            String actual = exception.getMessage();
            assertEquals(expected, actual);
        }
    }

    @Test
    void getNameShouldReturnName() {
        String name = "name";
        Horse horse = new Horse(name, 0, 0);
        String expected = name;
        String actual = horse.getName();
        assertEquals(expected, actual);
    }

    @Test
    void getSpeedShouldReturnSpeed() {
        double speed = 0;
        Horse horse = new Horse("name", speed, 0);
        double expected = speed;
        double actual = horse.getSpeed();
        assertEquals(expected, actual);
    }

    @Test
    void getDistanceShouldReturnDistance() {
        double distance = 0;
        Horse horse = new Horse("name", 0, distance);
        double expected = distance;
        double actual = horse.getDistance();
        assertEquals(expected, actual);
    }

    @Test
    void getDistanceShouldReturnZero() {
        Horse horse = new Horse("name", 0);
        double expected = 0;
        double actual = horse.getDistance();
        assertEquals(expected, actual);
    }

    @Test
    void moveShouldToCallGetRandomDouble() {
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            new Horse("name", 0, 0).move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9), only());
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.0, 2.0, 3.0})
    void moveShouldAssignValueToDistanceField(double value) {
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(value);
            Horse horse = new Horse("name", 1, 0);
            horse.move();
            double expected = horse.getDistance();
            assertEquals(expected, Horse.getRandomDouble(0.2, 0.9));
        }
    }
}