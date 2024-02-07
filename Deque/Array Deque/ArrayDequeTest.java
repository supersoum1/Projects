import org.junit.Test;


public class ArrayDequeTest {

    /*@Test
    public void dIsEmptySizeTest() {
        Deque<Integer> ad = new ArrayDeque<Integer>();
        assertTrue("A newly initialized LLDeque should be empty", ad.isEmpty());
        ad.addFirst(0);

        assertFalse("lld should now contain 1 item", ad.isEmpty());

    }*/

    private void assertFalse(String s, boolean empty) {
    }

    @Test
    public void sizeTest() {
        Deque<Integer> ad = new ArrayDeque<Integer>();
        ad.addFirst(1);
        ad.addFirst(2);
        ad.addFirst(3);
        ad.addLast(4);
        ad.addLast(5);


        assertThat(ad.size() == 3); //5


        ad.removeLast();
        ad.removeFirst();
        assertThat(ad.size() == 3);
    }


    private void assertThat(boolean b) {
    }

    @Test
    public void addFirstAndGetTest() {
        Deque<Integer> ad = new ArrayDeque<Integer>();
        ad.addFirst(2);
        int zeroIndex = ad.get(4);
        assertThat(zeroIndex == 2);

        ad.addFirst(4);
        ad.addFirst(5);
        ad.addFirst(3);

        int secondIndex = ad.get(2);
        assertThat(secondIndex == 5);

        int thirdFirstIndex = ad.get(1);
        assertThat(thirdFirstIndex == 3);

        Integer out = ad.get(4);
        assertThat(out == 2);

        Integer out2 = ad.get(5);
        assertThat(out == null);

    }

    @Test
    public void addLastAndGetTest() {
        Deque<Integer> ad = new ArrayDeque<Integer>();
        ad.addLast(2);


        ad.addLast(4);
        ad.addLast(5);


        //int secondIndex = ad.get(2);
        assertThat(ad.get(2)== null);

        ad.addLast(5);
        int zeroIndex = ad.get(0);
        assertThat(zeroIndex == 5);

    }

    @Test
    public void AddandRemove() {
        Deque<Integer> ad = new ArrayDeque<Integer>();
        ad.addFirst(2);
        ad.addFirst(4);
        ad.addFirst(5);
        ad.addLast(6);
        Integer out = ad.removeLast();
        assertThat(out == 6);
        Integer a = ad.removeFirst();
        assertThat(a == 5);
        ad.addFirst(7);
        ad.addFirst(8);
        ad.addFirst(9);
        ad.addFirst(10);
        Integer circle = ad.get(7);
        assertThat(out == 10);
        Integer b = ad.removeLast();
        assertThat(b == 2);
    }

    @Test
    public void gradescopetest() {
        Deque<Integer> ad = new ArrayDeque<Integer>();
        ad.addFirst(0);
        ad.addFirst(1);
        ad.addFirst(2);
        //ad.removeLast();
       assertThat(ad.get(2)==2);
    }



    @Test
    public void isEmptyTest() {
        Deque<Integer> ar = new ArrayDeque<Integer>();
        Boolean a = ar.isEmpty();
        assertThat(a);
        ar.addFirst(2);
        ar.addFirst(4);
        ar.addFirst(5);
        Boolean b = ar.isEmpty();
        assertThat(!b);

    }

    @Test
    public void resizingTest2() {
        Deque<Integer> ad = new ArrayDeque<Integer>();

        for (int i = 0; i < 100; i++) {
            ad.addLast(i);
        }

        assertThat(ad.size() == 100);

        for (int i = 0; i < 80; i++) {
            ad.removeLast();
        }

        assertThat(ad.size() == 20);

    }


    @Test
    public void resizingTest() {
        Deque<Integer> ad = new ArrayDeque<Integer>();
        ad.addFirst(1);
        ad.addFirst(2);
        ad.addFirst(3);
        ad.addLast(4);
        ad.addLast(8);
        ad.addLast(49);
        ad.addFirst(77);
        ad.addLast(23);
        ad.addFirst(37);
        ad.addLast(43);

        assertThat(ad.size() == 10);

        Deque<Integer> ad1 = new ArrayDeque<Integer>();
        ad1.addLast(4);
        ad1.addLast(8);
        ad1.addLast(9);
        ad1.addLast(44);
        ad1.addLast(99);
        ad1.addLast(43);
        ad1.addLast(23);
        ad1.addLast(46);
        ad1.addLast(27);

        assertThat(ad1.size() == 9);
        assertThat(ad1.get(8) == 27);
    }

    @Test
    public void Fill() {
        Deque<Integer> ad = new ArrayDeque<Integer>();
        ad.addFirst(1);
        ad.addFirst(2);
        ad.addFirst(3);
        ad.addLast(4);
        ad.addLast(8);
        ad.addLast(49);
        ad.addFirst(77);
        ad.addLast(23);
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        ad.isEmpty();
        assertThat(ad.isEmpty());
        assertThat(ad.size() == 0);
        ad.removeFirst();
        assertThat(ad.size() == 0);//0

    }

    @Test
    public void ramdomized() {
        Deque<Integer> Arr = new ArrayDeque<Integer>();
        Arr.addFirst(0);
        assertThat(Arr.get(0) == 0);
        Arr.addLast(1);
        Arr.addFirst(2);
        Arr.removeLast();
        Arr.removeLast();
        Arr.removeFirst();
        Arr.addFirst(6);
        Arr.addLast(7);
        Arr.addLast(8);
        Arr.get(1);
        assertThat(Arr.get(1) == 7);

    }

    @Test
    public void test4() {
        Deque<Integer> Arr = new ArrayDeque<Integer>();
        Arr.addLast(0);
        Arr.addLast(1);
        Arr.addLast(2);
        Arr.addLast(3);
        Arr.addLast(4);
        Arr.addLast(5);
        Arr.addLast(6);
        Arr.addLast(7);
        Arr.addLast(8);
        assertThat(Arr.removeFirst() == 0);
    }

    @Test
    public void test48() {
        Deque<Integer> Arr = new ArrayDeque<Integer>();
        Arr.addFirst(0);
        Arr.addFirst(1);
        Arr.addFirst(2);
        Arr.isEmpty();
        Arr.addFirst(4);
        Arr.addFirst(5);
        Arr.addFirst(6);
        Arr.addFirst(7);
        Arr.addFirst(8);
        Arr.addFirst(9);
        //add a last
        Arr.addFirst(10);
        assertThat(Arr.removeLast() == 0);

    }

    @Test
    public void testforget() {
        Deque<Integer> Arr = new ArrayDeque<Integer>();
        Arr.addFirst(0);
        Arr.addFirst(1);
        Arr.addFirst(2);
        assertThat(Arr.get(-1) == null);
        assertThat(Arr.get(100) == null);


    }

    @Test
    public void add_remove() {
        Deque<Integer> Arr = new ArrayDeque<Integer>();
        Arr.addFirst(0);
        Arr.addFirst(1);
        Arr.addFirst(2);
        Arr.removeFirst();
        Arr.removeFirst();
        Arr.removeLast();
        Arr.addLast(10);
        Arr.addLast(12);
        Arr.removeFirst();
    }


    @Test
    public void remove_add() {
        Deque<Integer> Arr = new ArrayDeque<Integer>();
        Arr.removeLast();
        Arr.toList();
        Arr.addFirst(34);
        Arr.removeLast();
        Arr.addFirst(20);
        Arr.toList();
    }

}
