import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    private SILab2 lab2 = new SILab2();

    private List<String> createList(String... elements) {
        return Arrays.asList(elements);
    }

    @Test
    public void everyPath() {
        RuntimeException ex;
        // start, 1, 2, end // exception
        ex = assertThrows(RuntimeException.class, () -> lab2.function(createList()));
        assertTrue(ex.getMessage().contains("List length should be greater than 0"));

        // start, 1, 3, 4, 5, 6, 7, 8, 11, 14, 16, 5, 17, end // 0
        assertEquals(createList("0"), lab2.function(createList("0")));

        // start, 1, 3, 4, 5, 6, 7, 8, 11, 12, 14, 16, 5, 6, 7, 8, 9, 11, 14, 16, 5, 17, end // 00
        assertEquals(createList("0", "0"), lab2.function(createList("0", "0")));

        // start, 1, 3, 4, 5, 6, 7, 8, 11, 12, 13, 14, 16, 5, 6, 15, 16, 5, 17, end // 0#
        assertEquals(createList("1", "#"), lab2.function(createList("0", "#")));

        // start, 1, 3, 4, 5, 6, 7, 8, 11, 12, 14, 16, 5, 6, 7, 8, 9, 11, 12, 14, 16, 5, 6, 7, 8, 9, 11, 14, 16, 5, 17, end // 000
        assertEquals(createList("0", "0", "0"), lab2.function(createList("0", "0", "0")));

        // start, 1, 3, 4, 5, 6, 15, 16, 5, 17, end // #
        assertEquals(createList("#"), lab2.function(createList("#")));

        // start, 1, 3, 4, 5, 6, 15, 16, 5, 6, 7, 8, 9, 10, 11, 14, 16, 5, 17, end // #0
        assertEquals(createList("#", "1"), lab2.function(createList("#", "0")));

        // start, 1, 3, 4, 5, 6, 7, 8, 11, 12, 14, 16, 5, 6, 7, 8, 9, 11, 12, 13, 14, 16, 5, 17, end // 00#
        assertEquals(createList("0", "1", "#"), lab2.function(createList("0", "0", "#")));

        // start, 1, 3, 4, 5, 6, 7, 8, 11, 12, 13, 14, 16, 5, 6, 15, 16, 5, 17, end // 0##
        assertEquals(createList("1", "#", "#"), lab2.function(createList("0", "#", "#")));

        // start, 1, 3, 4, 5, 6, 7, 8, 11, 12, 13, 14, 16, 5, 6, 15, 16, 5, 6, 7, 8, 9, 10, 11, 14, 16, 5, 17, end // 0#0
        assertEquals(createList("1", "#", "1"), lab2.function(createList("0", "#", "0")));

        // start, 1, 3, 4, 5, 6, 15, 16, 5, 6, 15, 16, 5, 6, 7, 8, 9, 10, 11, 14, 16, 5, 17, end // ##0
        assertEquals(createList("#", "#", "1"), lab2.function(createList("#", "#", "0")));

        // start, 1, 3, 4, 5, 6, 15, 16, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 5, 6, 15, 16, 5, 17, end // #0#
        assertEquals(createList("#", "2", "#"), lab2.function(createList("#", "0", "#")));

        // start, 1, 3, 4, 5, 6, 15, 16, 5, 6, 7, 8, 9, 10, 11, 14, 16, 5, 6, 7, 8, 9, 11, 14, 16, 5, 17, end // #00
        assertEquals(createList("#", "1", "0"), lab2.function(createList("#", "0", "0")));

        // mixed -> 0#00##0#0##00#0#0
        assertEquals(createList("1", "#", "1", "1", "#", "#", "2", "#", "2", "#", "#", "1", "1", "#", "2",
                "#", "1"), lab2.function(createList("0", "#", "0", "0", "#", "#", "0", "#", "0", "#", "#",
                "0", "0", "#", "0", "#", "0")));
    }

    @Test
    public void multipleConditions() {
        // if (i-1>=0 && list.get(i-1).equals("#"))
        // T && T
        // T && F
        // F && X

        // the T && T case will invoke when the main element is the zero in the following list -> #0
        assertEquals(createList("#", "1"), lab2.function(createList("#", "0")));

        // the T && F case will invoke when the main element is the second zero in the following list -> 00
        assertEquals(createList("0", "0"), lab2.function(createList("0", "0")));

        // the F && X case will invoke only when the main element is the first element of the given list -> 0
        assertEquals(createList("0"), lab2.function(createList("0")));


        // if (i+1<list.size() && list.get(i+1).equals("#"))
        // T && T
        // T && F
        // F && X

        // the T && T case will invoke when the main element is the zero in the following list -> 0#
        assertEquals(createList("1", "#"), lab2.function(createList("0", "#")));

        // the T && F case will invoke when the main element is the first zero in the following list -> 00
        assertEquals(createList("0", "0"), lab2.function(createList("0", "0")));

        // the F && X case will invoke only when the main element is the last element of the given list -> 0
        assertEquals(createList("0"), lab2.function(createList("0")));
    }


}
