package Glava11;

import org.example.Glava11.B2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
public class B2Test {
    @Test
    public void testRearrangeList() {
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 8, 3, 10, 7, 2));
        int X = 6;
        B2.rearrangeList(list, X);
        assertEquals(Arrays.asList(5, 3, 2, 10, 7, 8), list);
    }

    @Test
    public void testRearrangeListWithEmptyList() {
        List<Integer> list = new ArrayList<>();
        int X = 6;
        B2.rearrangeList(list, X);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testRearrangeListWithAllGreaterThanX() {
        List<Integer> list = new ArrayList<>(Arrays.asList(8, 10, 7));
        int X = 6;
        B2.rearrangeList(list, X);
        assertEquals(Arrays.asList(8, 10, 7), list);
    }

    @Test
    public void testRearrangeListWithAllLessThanOrEqualToX() {
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 3, 2));
        int X = 6;
        B2.rearrangeList(list, X);
        assertEquals(Arrays.asList(5, 3, 2), list);
    }
}
