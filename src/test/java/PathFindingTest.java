import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pathfindingapp.*;

public class PathFindingTest {
    /**
     * Test the algorithm using to find nodes to be colored.
     */
    @Test
    public void firstNode() {
        TestMain test = new TestMain();
        assertEquals(0, test.findNode(0, 100));
    }

    /**
     * Test the algorithm using to find nodes to be colored.
     */
    @Test
    public void secondNode() {
        TestMain test = new TestMain();
        assertEquals(1, test.findNode(15, 100));
    }

    @Test
    public void randomNode() {
        TestMain test = new TestMain();
        assertEquals(823, test.findNode(14 * 23, 100 + 14 * 16));
    }
}
