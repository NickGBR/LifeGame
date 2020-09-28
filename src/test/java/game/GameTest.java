package game;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game;

    @Test
    void play() throws InterruptedException {

        Field field = Mockito.mock(Field.class);

        game = new Game(field, 1);
        Field result = game.play(1);

        assertTrue(result.isAlive(1, 2));
        assertTrue(result.isAlive(2, 2));
        assertTrue(result.isAlive(3, 2));
    }
}