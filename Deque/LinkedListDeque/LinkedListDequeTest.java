import com.google.common.truth.Truth;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    @DisplayName("LinkedListDeque has no fields besides nodes and primitives")
    void noNonTrivialFields() {
        Class<?> nodeClass = NodeChecker.getNodeClass(LinkedListDeque.class, true);
        List<Field> badFields = Reflection.getFields(LinkedListDeque.class)
                .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(nodeClass) || f.isSynthetic()))
                .toList();

        assertWithMessage("Found fields that are not nodes or primitives").that(badFields).isEmpty();
    }

    @Test
    /** In this test, we have three different assert statements that verify that addFirst works correctly. */
    public void addFirstTestBasic() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<>();

        lld1.addFirst("back"); // after this call we expect: ["back"]
        assertThat(lld1.toList()).containsExactly("back").inOrder();

        lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
        assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

        lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
    }

    @Test
    /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
     *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
    public void addLastTestBasic() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void addFirstAndAddLastTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
    }
    @Test
    public void testIsEmptyonEmpty() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();

        assertThat(deque.isEmpty()).isTrue();
    }
    @Test
    public void testIsNotEmpty() {
        LinkedListDeque<String> deque = new LinkedListDeque<>();
        deque.addFirst("Hello");

        assertThat(deque.isEmpty()).isFalse();
    }

    @Test
    public void testSizeOnEmpty() {
        LinkedListDeque<Character> deque = new LinkedListDeque<>();

        assertThat(deque.size()).isEqualTo(0);
    }
    @Test
    public void testSizeOnNonEmptyDeque() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addFirst(10);
        deque.addLast(20);

        assertThat(deque.size()).isEqualTo(2);
    }


    // Below, you'll write your own tests for LinkedListDeque.

    @Test
    public void getEmptyDequeTest() {
        LinkedListDeque<String> lld2;}

    @Test
    public void getTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();

        lld1.addLast(10);
        lld1.addLast(20);
        lld1.addLast(30);

        assertThat(lld1.get(0)).isEqualTo(10);
        assertThat(lld1.get(1)).isEqualTo(20);
        assertThat(lld1.get(2)).isEqualTo(30);
        assertThat(lld1.get(3)).isNull(); // out of range index
        assertThat(lld1.get(-1)).isNull(); // out of range index

    }
    @Test
    public void getRecursiveTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();

        lld1.addLast(10);
        lld1.addLast(20);
        lld1.addLast(30);

        assertThat(lld1.getRecursive(0)).isEqualTo(10);
        assertThat(lld1.getRecursive(1)).isEqualTo(20);
        assertThat(lld1.getRecursive(2)).isEqualTo(30);
        assertThat(lld1.getRecursive(3)).isNull(); // out of range
        assertThat(lld1.getRecursive(-1)).isNull(); // out of range
    }

    @Test
    @DisplayName("Test removeFirst() on a non-empty deque")
    public void testRemoveFirstOnNonEmptyDeque() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addFirst(10);
        deque.addLast(20);

        Integer removed = deque.removeFirst();
        assertThat(removed).isEqualTo(10);
        assertThat(deque.toList()).containsExactly(20).inOrder();
    }

    @Test
    @DisplayName("Test removeFirst() on an empty deque")
    public void testRemoveFirstOnEmptyDeque() {
        LinkedListDeque<String> deque = new LinkedListDeque<>();

        String removed = deque.removeFirst();
        assertThat(removed).isNull();
        assertThat(deque.toList()).isEmpty(); }
        @Test
        @DisplayName("Test removeLast() on a non-empty deque")
        public void testRemoveLastOnNonEmptyDeque() {
            LinkedListDeque<Character> deque = new LinkedListDeque<>();
            deque.addFirst('A');
            deque.addLast('B');
            deque.addLast('C');

            Character removed = deque.removeLast();
            assertThat(removed).isEqualTo('C');
            assertThat(deque.toList()).containsExactly('A', 'B').inOrder();
        }

        @Test
        @DisplayName("Test removeLast() on an empty deque")
        public void testRemoveLastOnEmptyDeque() {
            LinkedListDeque<Double> deque = new LinkedListDeque<>();

            Double removed = deque.removeLast();
            assertThat(removed).isNull();
            assertThat(deque.toList()).isEmpty();
        }


    @Test
    public void add_last_after_remove_to_empty() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.removeFirst();
        deque.addLast(10);
        assertEquals(10, (int) deque.get(0));
        assertEquals(1, deque.size());
    }

    @Test
    public void remove_first() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addFirst(5);
        deque.addFirst(10);
        assertEquals(10, (int) deque.removeFirst());
        assertEquals(1, deque.size());
    }

    @Test
    public void remove_first_to_empty() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addFirst(5);
        assertEquals(5, (int) deque.removeFirst());
        assertEquals(0, deque.size());
        assertNull(deque.removeFirst());
    }

    @Test
    public void remove_first_to_one() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addFirst(5);
        assertEquals(5, (int) deque.removeFirst());
        assertEquals(0, deque.size());
        assertNull(deque.get(0));
    }

    @Test
    public void remove_last() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addFirst(5);
        deque.addLast(10);
        assertEquals(10, (int) deque.removeLast());
        assertEquals(1, deque.size());
    }

    @Test
    public void remove_last_to_empty() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addFirst(5);
        assertEquals(5, (int) deque.removeLast());
        assertEquals(0, deque.size());
        assertNull(deque.removeLast());
    }

    @Test
    public void remove_last_to_one() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addFirst(5);
        assertEquals(5, (int) deque.removeLast());
        assertEquals(0, deque.size());
        assertNull(deque.get(0));
    }
    @Test
    public void testRemoveFirst() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);

        int removed = deque.removeFirst();

        assertEquals(3, removed);
        assertEquals(2, deque.size());
        assertEquals(2, deque.get(0).intValue());
        assertEquals(1, deque.get(1).intValue());
    }

    @Test
    public void testRemoveLast() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        int removed = deque.removeLast();

        assertEquals(3, removed);
        assertEquals(2, deque.size());
        assertEquals(1, deque.get(0).intValue());
        assertEquals(2, deque.get(1).intValue());
    }

    @Test
    public void testRemoveFirstEmptyDeque() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();

        Integer removed = deque.removeFirst();

        assertNull(removed);
        assertEquals(0, deque.size());
    }

    @Test
    public void testRemoveLastEmptyDeque() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();

        Integer removed = deque.removeLast();

        assertNull(removed);
        assertEquals(0, deque.size());
    }
    @Test
    public void testAddFirstAfterRemoveToEmpty() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();

        deque.addLast(1);
        deque.removeFirst();
        deque.addFirst(2);

        Truth.assertThat(deque.size()).isEqualTo(1);
        Truth.assertThat(deque.toList()).isEqualTo(List.of(2));
    }

    @Test
    public void testAddLastAfterRemoveToEmpty() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();

        deque.addLast(1);
        deque.removeLast();
        deque.addLast(2);

        Truth.assertThat(deque.size()).isEqualTo(1);
        Truth.assertThat(deque.toList()).isEqualTo(List.of(2));
    }
    }

// @Test
//    public void getEmptyDequeTest() {
//        LinkedListDeque<String> lld2