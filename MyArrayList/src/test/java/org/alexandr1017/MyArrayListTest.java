package org.alexandr1017;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    @Test
    @DisplayName("Add Integers")
    void testAdd() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals("[1,2,3]", list.toString());
    }

    @Test
    @DisplayName("Add Strings")
    void testAddString() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("лошадь");
        list.add("бык");
        list.add("коза");
        assertEquals("[лошадь,бык,коза]", list.toString());


    }

    @Test
    @DisplayName("Add null")
    public void testAddNull() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(null);
        assertEquals("[null]", list.toString());
    }

    @Test
    @DisplayName("Add Integer at Index")
    public void testAddAtIndex() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(1, 2);
        assertEquals("[1,2,3,4]", list.toString());
    }

    @Test
    @DisplayName("Add String at Index")
    public void testAddStringAtIndex() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Всем");
        list.add("пожаловать");
        list.add(1, "добро");
        assertEquals("[Всем,добро,пожаловать]", list.toString());
    }

    @Test
    @DisplayName("Capacity increase")
    public void testIncreaseCapacityAndCopyArray() {
        MyArrayList<String> list = new MyArrayList<>();
        for (int i = 0; i <= 3; i++) {
            list.add("Всем");
            list.add("добро");
            list.add("пожаловать");
        }
        assertEquals(list.getCapacity(), 16);
    }


    @Test
    void testGet() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Лошадь");
        assertEquals("Лошадь", list.get(0));
    }

    @Test
    void testRemove() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Лошадь");
        list.add("Бык");
        list.remove(0);
        assertEquals("Бык", list.get(0));
    }

    @Test
    void testClear() {
        MyArrayList<String> list = new MyArrayList<>();
        for (int i = 0; i <= 3; i++) {
            list.add("Всем");
            list.add("добро");
            list.add("пожаловать");
        }
        list.clear();
        assertEquals("[]", list.toString());
    }

    @Test
    void testSet() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Лошадь");
        list.set(0, "Бык");
        assertEquals("Бык", list.get(0));
    }

    @Test
    void testGetSize() {
        MyArrayList<String> list = new MyArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add("Всем");
            list.add("добро");
            list.add("пожаловать");
        }
        assertEquals(9, list.getSize());
    }


    @Test
    void testTrimToSize() {
        MyArrayList<String> list = new MyArrayList<>();
        for (int i = 0; i <= 3; i++) {
            list.add("Всем");
            list.add("добро");
            list.add("пожаловать");
        }
        list.trimToSize();
        assertEquals(12, list.getSize());
    }

    @Test
    void testSort() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(8);
        list.add(9);
        list.add(3);
        list.add(7);
        list.add(6);
        list.add(7);
        list.add(1);
        list.sort(Integer::compare);
        assertEquals("[1,3,6,7,7,8,9]", list.toString());
    }


    @Test
    public void testIterator() {
        ArrayList<String> list = new ArrayList<>();
        list.add("всем");
        list.add("добро");
        list.add("пожаловать");
        Iterator<String> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("всем", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("добро", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("пожаловать", iterator.next());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testValidIndexInRange() throws NoSuchMethodException {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        Method method = MyArrayList.class.getDeclaredMethod("validIndex", int.class);
        method.setAccessible(true);
        Assertions.assertDoesNotThrow(() -> method.invoke(list, 0));
    }

    @Test
    public void testBinarySearch() {
        MyArrayList<BigInteger> list = new MyArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            list.add(BigInteger.valueOf(i).multiply(BigInteger.TEN));
        }
        assertEquals(list.search(BigInteger.valueOf(500_000L), BigInteger::compareTo), 50000);

    }

}